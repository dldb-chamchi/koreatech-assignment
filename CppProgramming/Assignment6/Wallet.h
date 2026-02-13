#ifndef WALLET_H
#define WALLET_H

#include <iostream>
#include <map>


class Wallet {
private:
    int M[6]{};
    
    int value[6]{100, 500, 1000, 5000, 10000, 50000};
    std::map<int, int> index = {{100, 0}, {500, 1}, {1000, 2}, {5000, 3}, {10000, 4}, {50000, 5}};
    
    void makeAmount(int amount);
    bool canMakeAmount(int amount) const;
    
public:
    Wallet() = default;
    virtual ~Wallet() = default;
    void add(int denomination, int amount);
    void clear();
    int getCurrentAmount() const;
    void spend(int amount);
    
};

#endif // WALLET_H
