# 실습과제 07 [문제 1]

def bruteforce(A):  # 완전탐색
    count = 0
    n = len(A)
    for i in range(n - 1):
        for j in range(i + 1, n):
            if A[i] > A[j]:
                count += 1
    return count


def merge_count(left, right):
    merged = []
    i = j = 0
    inv = 0

    while i < len(left) and j < len(right):
        if left[i] <= right[j]:
            merged.append(left[i])
            i += 1
        else:
            merged.append(right[j])
            inv += len(left) - i  # left에 남아있는 개수만큼 역전 증가
            j += 1

    merged.extend(left[i:])
    merged.extend(right[j:])

    return merged, inv


def sort_count(arr):
    n = len(arr)
    if n <= 1:
        return arr, 0

    mid = n // 2
    left, inv_left = sort_count(arr[:mid])
    right, inv_right = sort_count(arr[mid:])
    merged, inv_split = merge_count(left, right)

    return merged, inv_left + inv_right + inv_split


def count_inversions(A):
    _, total_inv = sort_count(A)   # ✅ 문법 오류 수정
    return total_inv


A = [1, 1, 1, 1, 1, 1, 1]
print("완전탐색 역전 수:", bruteforce(A))
print("개선 알고리즘 역전 수:", count_inversions(A))