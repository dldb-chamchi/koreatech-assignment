#실습과제 05

def partition(A, low, high):
    pivotIdx = low
    pivotValue = A[pivotIdx]
    A[pivotIdx], A[high] = A[high], A[pivotIdx]
    storeIdx = low
    for i in range(low, high):
        if(A[i] < pivotValue):
            A[i], A[storeIdx] = A[storeIdx], A[i]
            storeIdx += 1
 
    A[storeIdx], A[high] = A[high], A[storeIdx]

    return storeIdx

def quick_select(A, k):
    left = 0
    right = len(A)-1
    while(left <= right):
        pos = partition(A, left, right)
        if(pos == k-1): return A[pos]
        elif pos > k-1: right = pos-1
        else: left = pos + 1

A = [10, 8, 6, 4, 2]
print(f"{4}번째 작은 수 : {quick_select(A, 4)}")
