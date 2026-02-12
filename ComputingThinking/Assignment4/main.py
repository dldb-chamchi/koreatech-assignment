#

print("Welcome to My Calculator")

t= True

while t == True:
    a, b, c, d = input("Type the equation : ").split()
    a = int(a)
    c = int(c)
    if b == '+':
        print(a+c)
        e = input("Next ? (y/n) : ")
        if e == 'y':
            t = True
        elif e == 'n':
            t = False
            print("Bye !")
        else:
            print("Error")
            t = False
    if b == '-':
        print(a-c)
        e = input("Next ? (y/n) : ")
        if e == 'y':
            t = True
        elif e == 'n':
            t = False
            print("Bye !")
        else:
            print("Error")
            t = False        
    if b == '*':
        print(a*c)
        e = input("Next ? (y/n) : ")
        if e == 'y':
            t = True
        elif e == 'n':
            t = False
            print("Bye !")
        else:
            print("Error")
            t = False            
    if b == '/':
        print(a//c)
        e = input("Next ? (y/n) : ")
        if e == 'y':
            t = True
        elif e == 'n':
            t = False
            print("Bye !")
        else:
            print("Error")
            t = False
