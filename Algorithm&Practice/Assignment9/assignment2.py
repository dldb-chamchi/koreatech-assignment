#실습과제 09 [문제 2]

def edit_distance_withops(S, T):
    m, n = len(S), len(T)
    D = [[0]*(n+1) for _ in range(m+1)]
    P = [[None]*(n+1) for _ in range(m+1)]

    for i in range(1, m+1):
        D[i][0] = i
        P[i][0] = 'delete'
    for j in range(1, n+1):
        D[0][j] = j
        P[0][j] = 'insert'

    for i in range(1, m+1):
        for j in range(1, n+1):
            if S[i-1] == T[j-1]:
                D[i][j] = D[i-1][j-1]
                P[i][j] = 'match'
            else:
                #삭제, 삽입, 교체 비용 계산
                delete_cost  = D[i-1][j]   + 1
                insert_cost  = D[i][j-1]   + 1
                replace_cost = D[i-1][j-1] + 1
                #최소 비용 연산 선택
                D[i][j], P[i][j] = min(
                    (delete_cost,  'delete'),
                    (insert_cost,  'insert'),
                    (replace_cost, 'replace'),
                    key=lambda x: x[0]
                )

    #최단 거리
    dist = D[m][n]

    ops = []
    i, j = m, n
    while i > 0 or j > 0:
        op = P[i][j]
        if op == 'match':
            i -= 1
            j -= 1
        elif op == 'delete':
            ops.append(f"삭제 '{S[i-1]}' 위치 {i-1}")
            i -= 1
        elif op == 'insert':
            ops.append(f"삽입 '{T[j-1]}' 위치 {i}")
            j -= 1
        elif op == 'replace':
            ops.append(f"대체 '{S[i-1]}' 위치 {i-1} 대체값 '{T[j-1]}'")
            i -= 1
            j -= 1

    ops.reverse()  #처음부터 순서대로

    return dist, ops

S = "tuesday"
T = "thursday"
distance, operations = edit_distance_withops(S, T)

print(f"문자열: {S} → {T}")
print(f"편집 거리 = {distance}")
print("수행 연산 순서:")
for step, op in enumerate(operations, 1):
    print(f"{step}. {op}")