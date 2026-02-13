//https://judge.koreatech.ac.kr/problem.php?id=1314

#include <iostream>
#include <string>
bool overlap(std::string_view S){
    std::string S1, S2, S3, S4;
    S1.push_back(S[0]); S1.push_back(S[1]); S1.push_back(S[3]); S1.push_back(S[4]);
    S2.push_back(S[6]); S2.push_back(S[7]); S2.push_back(S[9]); S2.push_back(S[10]); 
    S3.push_back(S[12]); S3.push_back(S[13]); S3.push_back(S[15]); S3.push_back(S[16]);
    S4.push_back(S[18]); S4.push_back(S[19]); S4.push_back(S[21]); S4.push_back(S[22]);
    return !(S2 < S3 || S1 > S4);
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
        std::cout << std::boolalpha << overlap(s) << '\n';
    }
    return 0;
}