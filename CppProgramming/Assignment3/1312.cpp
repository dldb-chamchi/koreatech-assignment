#include <iostream>
#include <string>
 
bool solve(std::string_view A, std::string_view B) 
{
	int freq1[26]{};
	int freq2[26]{};
	
	for(auto i:A) ++freq1[i-'a'];
	for(auto i2:B) ++freq2[i2 - 'a'];
	
	for(int j{0}; j<26; ++j)
		if(freq1[j] != freq2[j]) return 0;
	
    return 1;
}
 
int main()
{
	std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);
    int T;
    std::cin >> T;
    for(int t{0}; t < T; ++t) {
      std::string A, B;
		  std::cin >> A >> B;
      std::cout << std::boolalpha << solve(A, B) << '\n';
    }
    return 0;
}