#실습과제 07 [문제 2]

def string_mathcing(T, P): #완전탐색
    cnt = 0
    n = len(T)
    m = len(P)
    for i in range(n-m+1):
        j = 0
        while(j<m and P[j] == T[i+j]):
            cnt+=1 #비교횟수 계산 코드
            j += 1
        
        cnt += 1
        
        if j == m:
            print(f"패턴위치:{i}, 완전탐색 비교횟수:{cnt-1}")
            return
    print(f"패턴위치:-1, 완전탐색 비교횟수:{cnt-1}")
    return

def search_horspol(T, P): #호스풀
    m = len(P)
    n = len(T)
    t = shift_table(P)
    i = m-1
    cnt = 0
    while(i<= n-1):
        k = 0
        while(k<=m-1 and P[m-1-k]==T[i-k]):
            k += 1
            cnt += 1 #비교횟수
        cnt += 1 #비교횟수

        if k == m:
            print(f"패턴위치:{i-m+1}, 호스풀 비교횟수:{cnt-1}")
            return
        else:
            tc = t[ord(T[i-k])]
            i += max(1, (tc-k))
    
    print(f"패턴위치:-1, 호스풀 비교횟수:{cnt-1}")
    return
    
NO_OF_CHARS = 128

def shift_table(pat):
    m = len(pat)
    tbl = [m]*NO_OF_CHARS

    for i in range(m-1):
        tbl[ord(pat[i])] = m-1-i
    
    return tbl

t = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
p = "BAAAAA"

search_horspol(t, p), string_mathcing(t, p)