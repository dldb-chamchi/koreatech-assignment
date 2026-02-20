#실습과제 11 [문제 3]

def bound(obj, W, level, weight, profit):
    if weight > W:
        return 0
    pBound = profit
    for j in range(level+1, len(obj)):
        pBound += obj[j][1]
    return pBound

def bound2(arr, W, level, weight, profit):
    if weight > W:
        return 0
    pBound = profit
    tWeight = weight
    j = level+1
    n = len(arr)
    while j < n and (tWeight + arr[j][0] <= W):
        tWeight += arr[j][0]
        pBound += arr[j][1]
        j += 1
    if j < n:
        pBound += (W - tWeight) * (arr[j][1] / arr[j][0])
    return pBound

#노드 카운트
node_count_naive = 0
node_count_fractional = 0

#bound (naive) 기반 분기한정
def knapSack_bnb_naive(obj, W, level, weight, profit, maxProfit):
    global node_count_naive
    node_count_naive += 1 # 노드 방문 카운트

    if level == len(obj):
        return maxProfit

    #아이템 넣는 분기
    if weight + obj[level][0] <= W:
        newWeight = weight + obj[level][0]
        newProfit = profit + obj[level][1]
        if newProfit > maxProfit:
            maxProfit = newProfit
        newBound = bound(obj, W, level, newWeight, newProfit)
        if newBound >= maxProfit:
            maxProfit = knapSack_bnb_naive(obj, W, level+1, newWeight, newProfit, maxProfit)

    #아이템 안 넣는 분기
    newWeight = weight
    newProfit = profit
    newBound = bound(obj, W, level, newWeight, newProfit)
    if newBound >= maxProfit:
        maxProfit = knapSack_bnb_naive(obj, W, level+1, newWeight, newProfit, maxProfit)

    return maxProfit
#bound2 (fractional) 기반 분기한정
def knapSack_bnb_fractional(obj, W, level, weight, profit, maxProfit):
    global node_count_fractional
    node_count_fractional += 1 # 노드 방문 카운트

    if level == len(obj):
        return maxProfit

    #아이템 넣는 분기
    if weight + obj[level][0] <= W:
        newWeight = weight + obj[level][0]
        newProfit = profit + obj[level][1]
        if newProfit > maxProfit:
            maxProfit = newProfit
        newBound = bound2(obj, W, level, newWeight, newProfit)
        if newBound >= maxProfit:
            maxProfit = knapSack_bnb_fractional(obj, W, level+1, newWeight, newProfit, maxProfit)

    #아이템 안 넣는 분기
    newWeight = weight
    newProfit = profit
    newBound = bound2(obj, W, level, newWeight, newProfit)
    if newBound >= maxProfit:
        maxProfit = knapSack_bnb_fractional(obj, W, level+1, newWeight, newProfit, maxProfit)

    return maxProfit

W = 15
obj = [
    (2,  40, "A"),
    (3,  50, "B"),
    (5,  80, "C"),
    (10, 120, "F"),
    (3,  30, "E"),
    (5,  45, "D")
]

maxProfit1 = knapSack_bnb_naive(obj, W, 0, 0, 0, 0)
print("Naive bound 최대 이익:", maxProfit1)
print("Naive bound 탐색 노드 수:", node_count_naive)

obj.sort(key=lambda e: e[1]/e[0], reverse=True)
maxProfit2 = knapSack_bnb_fractional(obj, W, 0, 0, 0, 0)
print("Fractional bound 최대 이익:", maxProfit2)
print("Fractional bound 탐색 노드 수:", node_count_fractional)