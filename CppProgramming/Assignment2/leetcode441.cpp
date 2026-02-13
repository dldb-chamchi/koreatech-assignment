//Leetcode 441  
class Solution {
public:
    int arrangeCoins(int n){
        int i = 1; int num = 0;
        while(n-i>=0){
            n -= i;
            i += 1;
            num+=1;
        }
        return num; 
    }
};
//Runtime : 7m/s, Beats : 10.89%