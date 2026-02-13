//Leetcode 389

class Solution {
public:
    char findTheDifference(string s, string t) {
        std::vector<int> freq(26, 0);
        for(auto &ch1 : s) ++freq[ch1 - 'a'];
        for(auto &ch2 : t) --freq[ch2 - 'a'];
        for(int i{0}; i<26; ++i){
            if(freq[i] < 0) return i+'a';
        }
        return 'a';
    }
};