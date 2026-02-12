# 3*3 행렬의 원소를 입력받고, 행렬 곱을 출력하시오. (단, 행렬곱 라이브러리 함수를 활용하지 말 것) 

print("Please input 2 3x3 matrices : ")
m1, m2, m3, m4, m5, m6, m7, m8, m9 = map(int, input("- 1st matrix : ").split())
n1, n2, n3, n4, n5, n6, n7, n8, n9 = map(int, input("- 2nd matrix : ").split())

r1 = m1*n1 + m2*n4 + m3*n7
r2 = m1*n2 + m2*n5 + m3*n8
r3 = m1*n3 + m2*n6 + m3*n9
r4 = m4*n1 + m5*n4 + m6*n7 
r5 = m4*n2 + m5*n5 + m6*n8
r6 = m4*n3 + m5*n6 + m6*n9
r7 = m7*n1 + m8*n4 + m9*n7
r8 = m7*n2 + m8*n5 + m9*n8
r9 = m7*n3 + m8*n6 + m9*n9
print("Result Matrix : ")
print("{0} {1} {2}".format(r1, r2, r3))
print("{0} {1} {2}".format(r4, r5, r6))
print("{0} {1} {2}".format(r7, r8, r9))