#include "Wallet.h"
#include <algorithm>
#include <map>

void Wallet::add(int denomination, int amount){ //지갑 돈 추가 
    
    if(amount <= 0){ 
        std::cout << "추가할 갯수는 양수이어야 합니다." << '\n';
        return;
    }
    
    auto tmp = index.find(denomination);
    
    if(tmp == index.end()){
        std::cout << "추가할 수 없는 금액을 추가했습니다." << '\n';
        return;
    }
    
    M[index[denomination]] += amount;
}

void Wallet::clear(){
    for(int i{0}; i < 6; ++i) M[i] = 0;
}

int Wallet::getCurrentAmount() const{
    
    int total{0};
    
    for(int i{0}; i < 6; ++i){
        int denomination = value[i];
        total += M[i] * denomination;
    }
    
    return total;
}

void Wallet::spend(int amount){
    
    int remain{0};
    int tmp{0};
    remain = amount; 
    
    if(amount <= 0){ 
        std::cout << "사용할 금액은 0보다 커야 합니다." << '\n';
        return;
    }    

    if(getCurrentAmount() < amount){
        std::cout << "지갑에 충분한 잔액이 없습니다." << '\n';
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

bool Wallet::canMakeAmount(int amount) const{
    
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

void Wallet::makeAmount(int amount){
    
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
