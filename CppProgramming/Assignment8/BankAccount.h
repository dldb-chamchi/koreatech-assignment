#ifndef BANKACCOUNT_H
#define BANKACCOUNT_H

#include <stdexcept>

//BankAccount
class BankAccount {
protected:
    int balance{0};
    int number{assignID()};
    static inline int accountCounter{0};
    
    static int assignID();
        
public:
    BankAccount() = default;
    virtual ~BankAccount() = default;

    int getBalance() const noexcept;
    int getNumber() const noexcept;
    
    virtual void deposit(int amount);
    virtual void withdraw(int amount);
    virtual void addInterest() noexcept = 0;
};

//상속 SavingsAccount
class SavingsAccount : public BankAccount {
private:
    static double interestRate;

public:
    SavingsAccount() = default;
    
    static void setInterest(double rate) noexcept;
    void addInterest() noexcept override;
};

//상속 MinusAccount
class MinusAccount : public BankAccount {
private:
    static double interestRate;
    int loanLimit;
    double loanInterestRate;

public:
    MinusAccount(int limit, double loanRate);

    static void setInterest(double rate) noexcept;
    void withdraw(int amount) override;
    void addInterest() noexcept override;
    void deposit(int amount) override;
};

#endif // BANKACCOUNT_H
