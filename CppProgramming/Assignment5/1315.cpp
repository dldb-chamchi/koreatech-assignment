//https://judge.koreatech.ac.kr/problem.php?id=1315

#include <iostream>
#include <vector>
#include <climits>
using namespace std;

int findMinSumOfThreePairs(vector<int>& nums) {
    int n = nums.size();
    int minSum = INT_MAX;

    for (int j = 1; j < n - 1; ++j) {
        int sum = -1;
        for (int i = j - 1; i >= 0; --i) {
            for (int k = j + 1; k < n; ++k) {
                if (nums[i] < nums[j] && nums[j] > nums[k]) {
                    sum = nums[i] + nums[j] + nums[k];
                    minSum = min(minSum, sum);
                }
            }
        }
    }

    return (minSum == INT_MAX) ? -1 : minSum;
}

int main() {
    int T;
    cin >> T;

    for (int t = 0; t < T; ++t) {
        int N;
        cin >> N;
        vector<int> nums(N);
        for (int i = 0; i < N; ++i) {
            cin >> nums[i];
        }
        int result = findMinSumOfThreePairs(nums);
        cout << result << endl;
    }

    return 0;
}