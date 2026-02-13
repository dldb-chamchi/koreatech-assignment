#include "BankAccount.h"
#include <iostream>
#include <cassert>

void testSavingsAccount() {
    SavingsAccount::setInterest(3.0);
    SavingsAccount sa;
    
    sa.deposit(10000);
    sa.addInterest();
    assert(sa.getBalance() == 10300); // 10000 + 3% 
    sa.withdraw(300);
    assert(sa.getBalance() == 10000); // 10300 - 300

    try {
        sa.withdraw(10000);
    } 
    catch (const std::runtime_error &e) {
        assert(std::string(e.what()) == "Insufficient balance.");
        throw;
    }
    
    std::cout << "SavingsAccount passed.\n";
    
}

void testMinusAccount() {
    MinusAccount::setInterest(2.0); //이자
    MinusAccount ma(50000000, 5.0); //대출 이자
    
    ma.deposit(50000); //50000
    ma.withdraw(100000); //-50000
    std::cout << ma.getBalance() << ' ';
    ma.addInterest(); //이자 지급 안됨 
    
    ma.deposit(60000); //7000
    std::cout << ma.getBalance() << ' ';
    ma.withdraw(20000); //-13000
    std::cout << ma.getBalance() << ' ';
    ma.deposit(10000); //-3500
    std::cout << ma.getBalance() << ' ';
    std::cout << '\n';
    
    /*
    try {
        ma.withdraw(110000);
    } 
    catch (const std::runtime_error e) {
        assert(std::string(e.what()) == "Loan limit exceeded.");
        throw;
    }
    */
    std::cout << "MinusAccount passed.\n";

}
