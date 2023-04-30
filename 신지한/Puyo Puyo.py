from sys import stdin
from collections import deque

input = stdin.readline

# https://www.acmicpc.net/problem/11559

mapp = [list(input().strip()) for _ in range(12)]
dr = [0,0,1,-1]
dc = [1,-1,0,0]
R,C = 12,6
visited = [[False for _ in range(6)] for _ in range(12)]

# r,c부터 탐색하면서 연쇄가 가능하면 터트린다
def popping(r,c):
    q = deque()
    # 제거목록
    erase = [(r,c)]
    q.append((r,c,mapp[r][c]))
    visited[r][c] = True
    
    while q:
        cr, cc, color = q.popleft()
        for i in range(4):
            nr = cr + dr[i]
            nc = cc + dc[i]
            if 0 <= nr < R and 0 <= nc < C and not visited[nr][nc] and mapp[nr][nc] == color:
                q.append((nr,nc,color))
                visited[nr][nc] = True
                erase.append((nr,nc))
    # 연쇄가 일어나면 True 반환
    if len(erase) >= 4:
        for er,ec in erase:
            mapp[er][ec] = '.'
        return True
    else:
        return False

# 터트리고 난 후 뿌요들을 아래로 내린다
def gravity():
    for c in range(C):
        bottom = R-1
        r = R-2
        while r >= 0:
            # 바닥이 빈칸이 아니라면 다음으로
            if mapp[bottom][c] != '.':
                bottom -= 1
                r = bottom - 1
                continue
            # 위로 올라가면서 뿌요찾기
            if mapp[r][c] != '.':
                mapp[bottom][c] = mapp[r][c]
                mapp[r][c] = '.'
                bottom -= 1
            r -= 1
answer = 0
while True:
    # 방문배열 초기화
    visited = [[False for _ in range(6)] for _ in range(12)]
    is_poped = False

    for r in range(R):
        for c in range(C):
            if mapp[r][c] != '.' and popping(r,c):
                is_poped = True
    # 한번도 연쇄가 없으면 탈출
    if not is_poped:
        break

    answer += 1
    gravity()
    
print(answer)