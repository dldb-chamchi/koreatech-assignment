#실습과제 01

#1-1
def gcd1(a, b):
    list1 = []
    list2 = []
    
    for i in range(1, a+1):
        if a%i == 0:
            list1.append(i)
    
    for i in range(1, b+1):
        if b%i == 0:
            list2.append(i)
    
    inter = [x for x in list1 if x in list2]
    return (inter[len(inter)-1])

#1-2
def gcd2(a, b):
    list1 = []

    for i in range(1, a+1):
        if a%i == 0:
            list1.append(i)

    for i in range(len(list1)-1, -1, -1):
        if b%list1[i] == 0:
            return list1[i]

#1-3
def gcd3(a, b):
    while(b != 0):
        r = a % b
        a = b
        b = r
    return a

#2
def lcm(a, b):
    gcd = gcd3(a, b)
    return (a*b)//gcd

#인풋
a, b = map(int, input().split())

if a < b:
    a, b = b, a

#출력
print("1-1 최대 공약수 :", gcd1(a, b))
print("1-2 최대 공약수 :", gcd2(a, b))
print("1-3 최대 공약수 :", gcd3(a, b))
print("2 최소 공배수 :", lcm(a, b))