#실습과제 08 [문제 1]

def build_good_suffix_table(pat):
    m = len(pat)
    suff = [0] * m
    suff[m-1] = m
    g = m - 1
    f = 0
    for i in range(m-2, -1, -1):
        if i > g and suff[i + m - 1 - f] < i - g:
            suff[i] = suff[i + m - 1 - f]
        else:
            if i < g:
                g = i
            f = i
            while g >= 0 and pat[g] == pat[g + m - 1 - f]:
                g -= 1
            suff[i] = f - g
    good_suffix_shift = [m] * m
    j = 0
    for i in range(m-1, -1, -1):
        if suff[i] == i + 1:
            while j < m-1-i:
                good_suffix_shift[j] = m-1-i
                j += 1
    for i in range(m-1):
        good_suffix_shift[m-1-suff[i]] = m-1-i
    return good_suffix_shift

def boyer_moore_count(text, pat):
    n, m = len(text), len(pat)
    # Bad-character 테이블
    bad_char = {c: -1 for c in set(text + pat)}
    for idx, c in enumerate(pat):
        bad_char[c] = idx
    # Good-suffix 테이블
    good_suffix = build_good_suffix_table(pat)

    i = 0
    comps = 0
    while i <= n - m:
        j = m - 1
        # 뒤에서부터 비교
        while j >= 0:
            comps += 1
            if pat[j] != text[i + j]:
                break
            j -= 1
        if j < 0:
            # 첫 번째 매칭 지점에서 즉시 출력하고 종료
            print(f"패턴위치:{i}, 보이어무어 비교횟수:{comps}")
            return
        # 이동량 계산
        bc_shift = j - bad_char.get(text[i + j], -1)
        gs_shift = good_suffix[j]
        i += max(bc_shift, gs_shift)

    # 매칭 실패 시
    print(f"패턴위치:-1, 보이어무어 비교횟수:{comps}")
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

T = "ABCABCDABABCDABCDABDE"
P = "ABCDABD"
search_horspol(T, P), string_mathcing(T, P), boyer_moore_count(T, P)