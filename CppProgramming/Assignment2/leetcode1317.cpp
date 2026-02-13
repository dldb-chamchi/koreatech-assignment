//Leetcode 1317

class Solution {
public:
    bool zero_t_or_f(int num){
        while(num>0){
            if(num % 10 == 0){
                return false;
            }
            num /= 10;
        }
        return true;
    }
    
    vector<int> getNoZeroIntegers(int n) {
    	
        int a, b = 0;
        
        if(n % 2 == 0){
            a = n/2; 
            b = n/2;
        }
        else{
            a = (n-1)/2;
            b = (n-1)/2 + 1;
        }

        while(b>0){
            if(zero_t_or_f(a) && zero_t_or_f(b)){
                return std::vector<int>{a, b};
            }
            a+=1;
            b-=1;
        }
        return std::vector<int>{};    
    }
};
//Runtime : 0m/s, Beats : 67.38% 