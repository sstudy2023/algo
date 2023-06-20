from collections import deque

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

# K개 이상 이어진 블록을 찾고 없애는 함수
def removeCells(sr, sc, kind):
    global v, board

    q = deque()
    q.append((sr, sc))
    v[sr][sc] = 1
    cells = [(sr, sc)]

    while q:
        cr, cc = q.popleft()

        for d in range(len(dr)):
            nr, nc = cr + dr[d], cc + dc[d]
            if 0 <= nr < N and 0 <= nc < WIDTH and not v[nr][nc] and board[nr][nc] == kind:
                q.append((nr, nc))
                v[nr][nc] = 1
                cells.append((nr, nc))
    
    if len(cells) >= K:
        for i in range(len(cells)):
            r, c = cells[i]
            board[r][c] = 0
        
        return 1
    
    return 0

# 블록을 아래로 떨어뜨리는 함수
def fallDown():
    global board

    for c in range(WIDTH):
        for r in range(N - 1, 0, -1):
            if not board[r][c]:
                nr = r - 1
                while nr >= 0 and not board[nr][c]:
                    nr -= 1
                
                if nr >= 0:
                    board[r][c] = board[nr][c]
                    board[nr][c] = 0


N, K = map(int, input().split())

board = [list(map(int, input())) for _ in range(N)]
can_disappear = 1
WIDTH = len(board[0])

while can_disappear:
    v = [[0] * WIDTH for _ in range(N)]
    can_disappear = 0

    # K개 이상 이어져있는 것을 검사하고 지우기
    for c in range(WIDTH):
        for r in range(N - 1, -1, -1):
            if board[r][c] and not v[r][c]:
                can_disappear += removeCells(r, c, board[r][c])

    # 중력 적용
    fallDown()

for i in range(N):
    print(''.join(list(map(str, board[i]))))