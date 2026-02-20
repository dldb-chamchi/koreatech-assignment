#실습과제 03

import random

def generateRandomInput(): #리스트 랜덤 크기
    return random.randint(5, 20)

#완전 랜덤
def generateRandomZeroOne(n):
    return [random.randint(0, 1) for _ in range(n)]

#0개수 설정 가능
def generateRandomZeroOne2(n, num_zeros):
    zeros = [0] * num_zeros
    ones = [1] * (n - num_zeros)
    combined = zeros + ones
    
    random.shuffle(combined)
    
    return combined

def solve(li):
    for i in range(len(li)):
        if li[i] == 0 and i != 0:
            curr = i
            prev = i - 1
            while prev >= 0 and li[prev] == 1:
                li[curr], li[prev] = li[prev], li[curr]  # Swap 1과 0
                curr = prev
                prev -= 1
                print(li)

def solve2(li):

    left = 0
    right = len(li)-1

    while(left <= right):
        if(li[left] == 1 and li[right] == 0):
            li[left], li[right] = li[right], li[left]
            left += 1
            right -= 1
        elif(li[left] == 0):
            left +=1
        else:
            right -= 1
        print(li)

# 테스트
random_list = generateRandomZeroOne(15)
random_list2 = generateRandomZeroOne2(10, 5)
print("원본 리스트")
print(random_list)
print("solve")
solve(random_list.copy())
print("solve2")
solve2(random_list.copy())
print("원본 리스트")
print(random_list2)
print("solve")
solve(random_list2.copy())
print("solve2")
solve2(random_list2.copy())

def generateRandomList(n):
    li = []
    string = 'AB'
    for _ in range(n):
        idx = random.randint(0, 1)
        li.append(string[idx])
    return li

def solve3(li):
    cnt = 0
    for i in range(len(li)):
        if(li[i] == 'A'):
            for j in range(i+1, len(li)):
                if(li[j] == 'B'):
                    cnt+=1
    return cnt
def solve4(li):
    a = 0; cnt = 0
    for i in range(len(li)):
        if(li[i] == 'A'):
            a += 1
        elif(li[i] == 'B'):
            cnt += a
    return cnt

li = generateRandomList(20)
#li = ['A', 'F', 'F', 'C', 'M', 'A', 'A', 'B', 'A', 'C', 'G', 'B', 'B', 'A', 'H']
print("문자열")
print(li)
print("solve3")
print(solve3(li))
print("solve4")
print(solve4(li))