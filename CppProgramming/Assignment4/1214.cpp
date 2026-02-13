//https://judge.koreatech.ac.kr/problem.php?id=1214

#include <iostream>
#include <string>

int segment(std::string_view S) 
{
    int cnt = 0;
    bool space = true; 
    
    for(char c : S){
        if(space && c != ' '){ 
            ++cnt;
            space = false;
        }
        else if(c == ' ') space = true; 
    }
    
    return cnt;
}
 
int main()
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);
    
    int T;
    std::string s;
    
    std::cin >> T;
    std::cin.ignore(); 
    
    for(int t{0}; t < T; ++t)
    {
        std::getline(std::cin, s); 
        std::cout << segment(s) << '\n';
    }
    return 0;
}