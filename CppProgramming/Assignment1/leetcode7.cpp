//LeetCode 7

class Solution {
public:
    int reverse(int x) {
        
        long re = 0; //int보다 더 큰 자료형
        
        while(x!=0){
            re *= 10; //값 구하고 자리수 높이기 
            re += x % 10; //다음 번째 자릿 값 받아오기 
            x /= 10; //다음 번째 자리 값 구하기 위한 설정 
        }
        if(re>INT_MAX || re<INT_MIN){ //오버플로우 확인
            return 0;
        }
        else{
            return re;
        
        }
    }    
};

//leetcode version
//Runtime: 5ms, Beats : 20.5%