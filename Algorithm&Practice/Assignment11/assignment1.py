#실습과제 11 [문제 1]

def isSafeBoard(board, x, y):
    N = len(board)
    for i in range(y):
        if board[i][x] == 1: return False
    for i, j in zip(range(y-1,-1, -1), range(x-1, -1, -1)):
        if board[i][j] == 1: return False
    for i, j in zip(range(y-1, -1, -1), range(x+1, N)):
        if board[i][j] == 1: return False
    return True

def solve_N_Queen(board, y):
    N = len(board)
    if y == N:
        printBoard(board)
        return
    
    for x in range(N):
        if isSafeBoard(board, x, y):
            board[y][x] = 1
            solve_N_Queen(board, y+1)
            board[y][x] = 0

def printBoard(board):
    for i in range(len(board)):
        for j in range(len(board)):
            if board[i][j] == 1:
                print("Q", end="")
            else:
                print(".", end="")
        print()
    print()

#N = 8
#board = [[0 for  in range(N)] for _ in range(N)]
#solve_N_Queen(board, 0)

cnt = 0

def solve_N_Queen_count(board, y):
    global cnt
    N = len(board)
    if y == N:
        cnt += 1
        return
    
    for x in range(N):
        if isSafeBoard(board, x, y):
            board[y][x] = 1
            solve_N_Queen_count(board, y+1)
            board[y][x] = 0

#4~9-Queen 해의 개수 계산 및 출력
for N in range(4, 10):
    board = [[0 for _ in range(N)] for _ in range(N)]
    cnt = 0
    solve_N_Queen_count(board, 0)
    print(f"{N}-Queen 해의 개수: {cnt}")