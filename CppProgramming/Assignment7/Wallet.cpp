#include "Wallet.h"
#include <algorithm>
#include <map>
#include <climits>

void Wallet::add(int denomination, int amount){
    
    if(amount <= 0){ 
        throw std::invalid_argument("add: amount <= 0"); // 종류 1
        return;
    }
    if(denomination <= 0){
        throw std::invalid_argument("add: denomination <= 0"); // 종류 1
        return;
    }
    
    auto tmp = index.find(denomination);
    
    if(tmp == index.end()){ //index 내에 없으면 index.end를 반환
        throw std::invalid_argument("add: denomination not in index"); // 종류 2
        return;
    }
    
    if(denomination > INT_MAX/amount){
        throw std::overflow_error("add: amount&denomination overflow_error"); //종류2
        return;
    }

    if(getCurrentAmount() > INT_MAX - denomination*amount){
        throw std::overflow_error("add: current_amount overflow_error"); //종류2
        return;
    }
    
    M[index[denomination]] += amount;
}

void Wallet::clear() noexcept{
    for(int i{0}; i < 6; ++i) M[i] = 0;
}

int Wallet::getCurrentAmount() const{
    
    int total{0};
    
    for(int i{0}; i < 6; ++i){
        int denomination = value[i];
        total += M[i] * denomination;
        
        if(M[i] > (INT_MAX - total)/ denomination) throw std::overflow_error("getCurrentAmount: current_amount overflow_error"); //종류2
    }
    
    return total;
}

void Wallet::spend(int amount){
    
    int remain{0};
    int tmp{0};
    remain = amount; 
    
    if(amount <= 0){ 
        throw std::invalid_argument("spend: amount <= 0"); //종류 1
        return;
    }    

    if(getCurrentAmount() < amount){
        throw std::invalid_argument("spend: spend_amount > current_amount"); //종류 2
        return;
    }

    if(canMakeAmount(amount)) makeAmount(amount);
    
    else{
        for(int i{0}; i<6; ++i){
            if(value[i]*M[i] > amount){
                tmp = i;
                break;
            }
        }

        int r = (amount / value[tmp]) + 1;
        M[tmp] -= r;
        int change = r*value[tmp] - amount;
        
        int i{5};
        while(change != 0){
            int nam = change / value[i];
            M[i] += nam;
            change -= nam*value[i];
            i -= 1;
        }
    }
}

bool Wallet::canMakeAmount(int amount) const noexcept{
    
    int remain{0};
    int tmp{0};
    remain = amount; 
    
    if(amount <= 0) return false;
    
    for(int i{0}; i<6; ++i){
        if(value[i]*M[i] > amount){
            tmp = i;
            break;
        }
    }
    
    for(int i{tmp}; i>=0; --i){
        int denomination = value[i];
        int num = std::min(remain / denomination, M[i]); // 사용할 수 있는 돈의 최대 개수 계산
        if(M[i] > 0){
            remain -= num * denomination;
        }
    }
    return(remain == 0);
}

void Wallet::makeAmount(int amount) noexcept{
    
    int remain{0};
    int tmp{0};

    remain = amount; 
    
    for(int i{0}; i<6; ++i){
        if(value[i]*M[i] > amount){
            tmp = i;
            break;
        }
    }
    
    for(int i{tmp}; i>=0; --i){
        int denomination = value[i];
        int num = std::min(remain / denomination, M[i]); // 사용할 수 있는 돈의 최대 개수 계산
        if(M[i] > 0){
            remain -= num * denomination;
            M[i] -= num;
        }
    }
}
