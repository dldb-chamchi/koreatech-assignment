//https://judge.koreatech.ac.kr/problem.php?id=1278

#include <iostream>
#include <string>
#include <string_view>

int solve(std::string_view A, int N){
    int cnt{0}, max_cnt{0};
    
    for(int i{0}; i < N; ++i){
        if (A[i] == 'a' || A[i] == 'e' || A[i] == 'i' || A[i] == 'o' || A[i] == 'u')
            ++cnt;
    }
    
    max_cnt = cnt; 
    
    for(int i{N}; i < A.size(); ++i){
        if(A[i - N] == 'a' || A[i - N] == 'e' || A[i - N] == 'i' || A[i - N] == 'o' || A[i - N] == 'u')
            --cnt;
        
        if(A[i] == 'a' || A[i] == 'e' || A[i] == 'i' || A[i] == 'o' || A[i] == 'u')
            ++cnt;
        
        if(cnt > max_cnt)
            max_cnt = cnt;
    }
    
    return max_cnt;
}

int main(){
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);
    
    int T, N;
    std::cin >> T;
    
    for(int t{0}; t < T; ++t){
        std::string A;
        std::cin >> A >> N;
        std::cout << solve(A, N) << '\n';
    }
    
    return 0;
}