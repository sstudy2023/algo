from collections import deque

dr = [0, 1, 0, -1]
dc = [1, 0, -1, 0]

WIDTH = 6
HEIGHT = 12

# 뿌요 터뜨리는 함수
def explode(sr, sc, kind):
    global board, v
    q = deque()
    q.append((sr, sc))
    v[sr][sc] = 1
    puyo = [(sr, sc)]
    rlt = 0

    # 붙어있는 뿌요 검사
    while q:
        cr, cc = q.popleft()

        for d in range(4):
            nr, nc = cr + dr[d], cc + dc[d]
            if 0 <= nr < HEIGHT and 0 <= nc < WIDTH and not v[nr][nc] and board[nr][nc] == kind:
                q.append((nr, nc))
                v[nr][nc] = 1
                puyo.append((nr, nc))
    
    # 4개 이상 같은 뿌요가 붙어있으면 처리
    if len(puyo) >= 4:
        rlt = 1

        # 뿌요 터뜨리기
        for i in range(len(puyo)):
            cr, cc = puyo[i]
            board[cr][cc] = '.'
    
    return rlt

board = [list(input()) for _ in range(HEIGHT)]

can_explode = 1
total_explode = 0

while can_explode:
    can_explode = 0
    v = [[0] * WIDTH for _ in range(HEIGHT)]

    # 터뜨릴 수 있는 뿌요 터뜨리기
    for r in range(HEIGHT - 1, -1, -1):
        for c in range(WIDTH - 1, -1, -1):
            if board[r][c] != '.' and not v[r][c]:
                can_explode += explode(r, c, board[r][c])
    
    # 터뜨린 후 위에 있던 뿌요 내리기
    for c in range(WIDTH):
        for r in range(HEIGHT - 1, -1, -1):
            if board[r][c] == '.':
                nr = r - 1
                while nr > -1 and board[nr][c] == '.':
                    nr -= 1
                if nr > -1:
                    board[r][c], board[nr][c] = board[nr][c], board[r][c]

    if can_explode:
        total_explode += 1

print(total_explode)