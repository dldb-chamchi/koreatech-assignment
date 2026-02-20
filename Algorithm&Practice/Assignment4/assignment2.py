#실습과제 04 [문제 2]
def printStep(A, i):
    print(f"step {i}: {A}")

def insertion_sort(A):
    n = len(A)
    for i in range(1, n):
        key = A[i]
        j = i - 1
        while j >=0 and A[j] > key:
            A[j+1] = A[j]
            j -= 1
        A[j+1] = key
        printStep(A, i)

data = [5, 3, 2, 6, 9, 8, 7, 10, 15]
print("Original :", data)
insertion_sort(data)
print("Insertion :", data)

def insertion_sort_recursive(A, n):
    if n <= 1:
        return

    insertion_sort_recursive(A, n - 1)

    last = A[n - 1]
    j = n - 2

    while j >= 0 and A[j] > last:
        A[j + 1] = A[j]
        j -= 1
    A[j + 1] = last

    printStep(A, n - 1)

data = [5, 3, 2, 6, 9, 8, 7, 10, 15]
print("Original :", data)
insertion_sort_recursive(data, len(data))
print("InsertionR :", data)