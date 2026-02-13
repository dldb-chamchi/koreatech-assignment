//https://judge.koreatech.ac.kr/problem.php?id=1226

#include <iostream>
#include <vector>
 
int solve(const std::vector<int>& nums) 
{
	int cnt = 0;
	for(int i{0}; i<size(nums); ++i){
		if(nums[i] == 0) return 0;
		else if(nums[i] < 0) ++cnt;
	}
	if(cnt % 2 == 0) return 1;
	else return -1;
}
 
int main()
{
	std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);
    int T;
    std::cin >> T;
    for(int t{0}; t < T; ++t)
	{
        int N;
        std::cin >> N;
        std::vector<int> nums(N, 0);
        for(auto& n: nums) std::cin >> n;
        std::cout << solve(nums) << '\n';
    }
    return 0;
}