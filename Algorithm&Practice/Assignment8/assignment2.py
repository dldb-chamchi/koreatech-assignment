# 실습과제 08 [문제 2]
import time

def time_it(func, *args):
    start = time.perf_counter()
    res = func(*args)
    elapsed = time.perf_counter() - start
    return res, elapsed

# 1) 분할정복(재귀) - Divide & Conquer
def bino_coef_dc(n, k):
    if k == 0 or k == n:
        return 1
    return bino_coef_dc(n - 1, k - 1) + bino_coef_dc(n - 1, k)

# 2) DP (Tabulation)
def bino_coef_dp(n, k):
    c = [[0] * (k + 1) for _ in range(n + 1)]
    for i in range(n + 1):
        for j in range(min(i, k) + 1):
            if j == 0 or j == i:
                c[i][j] = 1
            else:
                c[i][j] = c[i - 1][j - 1] + c[i - 1][j]
    return c[n][k]

# 3) Memoization (Top-down DP)
def bino_memo(n, k, memo=None):
    if memo is None:
        memo = [[None] * (k + 1) for _ in range(n + 1)]

    if memo[n][k] is not None:
        return memo[n][k]

    if k == 0 or k == n:
        memo[n][k] = 1
    else:
        memo[n][k] = bino_memo(n - 1, k - 1, memo) + bino_memo(n - 1, k, memo)

    return memo[n][k]


n, k = 15, 5
methods = [
    ("Recursive", bino_coef_dc),
    ("Tabulation", bino_coef_dp),
    ("Memoization", bino_memo)
]

for name, func in methods:
    result, elapsed = time_it(func, n, k)
    print(f"{name} 방식 - 결과 : {result}, 시간 : {elapsed:.6f}초")