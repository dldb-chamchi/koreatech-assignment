#include "BankAccount.h"

// BankAccount 메소드 
int BankAccount::getBalance() const noexcept {
    return balance;
}

int BankAccount::getNumber() const noexcept {
    return number;
}

int BankAccount::assignID() {
        return ++accountCounter;
    }

void BankAccount::deposit(int amount) {
    if (amount < 0) {
        throw std::invalid_argument("BankAccount : Amount deposit is negative.");
    }
    balance += amount;
}

void BankAccount::withdraw(int amount) {
    if (amount < 0) {
        throw std::invalid_argument("BankAccount : Amount withdraw is negative.");
    }
    if (balance < amount) {
        throw std::runtime_error("Insufficient balance.");
    }
    balance -= amount;
}

// SavingsAccount 메소드 
double SavingsAccount::interestRate = 0.0;

void SavingsAccount::setInterest(double rate) noexcept {
    interestRate = rate;
}

void SavingsAccount::addInterest() noexcept {
    balance += static_cast<int>(balance * (interestRate / 100));
}

// MinusAccount 메소드 
double MinusAccount::interestRate = 0.0;

MinusAccount::MinusAccount(int limit, double loanRate)
    : loanLimit(limit), loanInterestRate(loanRate) {
        if(limit > 50000000) throw std::runtime_error("MinusAccount : Loan limit exceeded.");
    }

void MinusAccount::setInterest(double rate) noexcept {
    interestRate = rate;
}

void MinusAccount::withdraw(int amount) {
    if (amount < 0) {
        throw std::invalid_argument("MinusAccount : Amount withdraw is negative.");
    }
    if (balance - amount < -loanLimit) {
        throw std::runtime_error("Loan limit exceeded.");
    }
    balance -= amount;
}

void MinusAccount::addInterest() noexcept {
    if (balance > 0) {
        balance += static_cast<int>(balance * (interestRate / 100));
    }
}

void MinusAccount::deposit(int amount) {
    if (amount < 0) {
        throw std::invalid_argument("MinusAccount : Amount deposit is negative.");
    }
    if (balance < 0) {
        int loanRepayment = amount;
        balance += loanRepayment;
        amount -= loanRepayment;
        balance -= static_cast<int>(loanRepayment * (loanInterestRate / 100));
    }
    balance += amount;
}
