#실습과제 10

import random

class DisjointSets:
    def __init__(self, n):
        # parent[x] < 0 이면 x가 루트, -parent[x]는 집합 크기
        self.parent = [-1] * n
        self.set_count = n

    def find(self, x):
        # 경로 압축
        if self.parent[x] < 0:
            return x
        self.parent[x] = self.find(self.parent[x])
        return self.parent[x]

    def union(self, a, b):
        # union by size
        a = self.find(a)
        b = self.find(b)
        if a == b:
            return False

        # a가 더 큰 집합(더 음수) 되게 유지
        if self.parent[a] > self.parent[b]:
            a, b = b, a

        self.parent[a] += self.parent[b]
        self.parent[b] = a
        self.set_count -= 1
        return True


def MSTKruskal(V, adj, maze, nx):
    n = len(V)
    dsets = DisjointSets(n)

    # 간선 수집
    E = []
    for i in range(n):
        for j in range(i + 1, n):
            if adj[i][j] is not None:
                E.append((i, j, adj[i][j]))

    # 가중치 오름차순 정렬
    E.sort(key=lambda e: e[2])

    # MST 간선 선택하며 벽 제거
    ecount = 0
    for u, v, w in E:
        if dsets.union(u, v):
            y1, x1 = divmod(u, nx)
            y2, x2 = divmod(v, nx)

            # 두 방(셀) 사이 벽의 위치 (maze 좌표계)
            wr = y1 + y2 + 1
            wc = x1 + x2 + 1
            maze[wr][wc] = 0

            ecount += 1
            if ecount == n - 1:
                break


def print_maze(maze):
    for row in maze:
        print(''.join('█' if cell else ' ' for cell in row))


def randommaze(nx, ny):
    N = nx * ny

    # 인접 행렬 초기화
    adj = [[None] * N for _ in range(N)]

    # 격자 그래프의 인접 간선(오른쪽/아래쪽)만 만들고 무방향으로 저장
    for y in range(ny):
        for x in range(nx):
            u = y * nx + x
            if x + 1 < nx:
                v = u + 1
                w = random.random()
                adj[u][v] = adj[v][u] = w
            if y + 1 < ny:
                v = u + nx
                w = random.random()
                adj[u][v] = adj[v][u] = w

    # 미로 배열 초기화 (벽=1, 길=0)
    H, W = 2 * ny + 1, 2 * nx + 1
    maze = [[1] * W for _ in range(H)]

    # 각 셀(방) 위치를 길로 뚫기
    for y in range(ny):
        for x in range(nx):
            maze[2 * y + 1][2 * x + 1] = 0

    # 크루스칼로 MST 만들며 벽 제거
    V = list(range(N))
    MSTKruskal(V, adj, maze, nx)

    # 입구/출구 뚫기
    maze[1][0] = 0
    maze[H - 2][W - 1] = 0

    print_maze(maze)


# 실행 예시
randommaze(11, 10)
print()
randommaze(11, 10)
print()
randommaze(9, 7)