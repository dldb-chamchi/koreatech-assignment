#실습과제 02

import random
import time

def A(li):
    n = len(li)
    for i in range(n-1):
        for j in range(i+1, n):
            if li[i] == li[j]:
                return False
    return True

def B(li):
    sorted_li = sorted(li)  # 원본 데이터 변경 방지
    for i in range(len(sorted_li)-1):
        if sorted_li[i] == sorted_li[i+1]:
            return False
    return True

def measure_time(func, data, iterations=10000):
    starttime = time.time()
    for i in range(iterations):
        func(data.copy())  # 원본 데이터 변경 방지
    return time.time() - starttime

# 데이터 크기에 따른 A, B 알고리즘 실행 시간 비교
sizes = [10, 100, 1000, 10000]  # 데이터 크기

for size in sizes:
    data = [random.randint(1, 10000000) for _ in range(size)]
    
    time_A = measure_time(A, data)
    time_B = measure_time(B, data)

    print(f"A : {A(data)}, B : {B(data)}")
    print(f"데이터 크기: {size}")
    print(f"  알고리즘 A 실행 시간: {time_A:.6f}초")
    print(f"  알고리즘 B 실행 시간: {time_B:.6f}초")
    print("-----------------------------------")