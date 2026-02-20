#실습과제 04 [문제 3]
def binary_search(A):
    st = 0
    end = len(A) - 1
    
    while st <= end:
        mid = (st + end) // 2
        if A[mid] == mid + 1:
            st = mid + 1
        else:
            end = mid - 1
    return st + 1

a = [1, 2, 3, 5, 6, 7, 8, 9]
print("list : ", a)
print(binary_search(a))