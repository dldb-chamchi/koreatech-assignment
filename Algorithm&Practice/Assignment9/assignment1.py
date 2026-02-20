#실습과제 09 [문제 1]

def lcs_dpmatrix(X, Y):
    m, n = len(X), len(Y)
    L = [[0] * (n + 1) for _ in range(m + 1)]
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if X[i - 1] == Y[j - 1]:
                L[i][j] = L[i - 1][j - 1] + 1
            else:
                L[i][j] = max(L[i - 1][j], L[i][j - 1])
    return L

def lcs_dp_length(X, Y):
    L = lcs_dpmatrix(X, Y)
    return L[len(X)][len(Y)]

def lcs_memo(X, Y):
    memo = {}
    def recur(i, j):
        if i == 0 or j == 0:
            return 0
        if (i, j) in memo:
            return memo[(i, j)]
        if X[i - 1] == Y[j - 1]:
            memo[(i, j)] = recur(i - 1, j - 1) + 1
        else:
            memo[(i, j)] = max(recur(i - 1, j), recur(i, j - 1))
        return memo[(i, j)]
    return recur(len(X), len(Y))

def lcs_dp_traceback(X, Y, L):
    i, j = len(X), len(Y)
    lcs = ''
    while i > 0 and j > 0:
        if X[i - 1] == Y[j - 1]:
            lcs = X[i - 1] + lcs
            i -= 1
            j -= 1
        elif L[i - 1][j] >= L[i][j - 1]:
            i -= 1
        else:
            j -= 1
    return lcs

X = "HELLO WORLD"
Y = "GAME OVER"

L = lcs_dpmatrix(X, Y)
print(lcs_dp_length(X, Y))          #DP로 계산한 LCS 길이
print(lcs_memo(X, Y))               #메모이제이션으로 계산한 LCS 길이
print(lcs_dp_traceback(X, Y, L))    #DP 행렬로부터 구한 LCS 문자열