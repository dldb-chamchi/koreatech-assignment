//Leetcode 1456

class Solution {
public:
    bool check(char c){
        return c == 'a' || c == 'e' || c == 'i' || c == 'o'|| c == 'u';
    }

    int maxVowels(string_view s, int k) {
        int num{0}, Max{0};
        for(auto i{0}; i<k; ++i){
            if(check(s[i])) ++num;
        }
        
        Max = num;

        for(auto i{k}; i<s.length(); ++i){
            if(check(s[i-k])) --num;
            if(check(s[i])) ++num;
            Max = max(num, Max);
        }
        return Max;
    }
    
};