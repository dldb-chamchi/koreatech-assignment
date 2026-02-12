#a, b를 입력받고 a+b를 출력하시오.

a, b = map(float, input("please type two number : ").split())
print("Addition of {0:g} and {1:g} is {2:g}".format(a, b, a+b))