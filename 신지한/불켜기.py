import sys
from collections import deque

input = sys.stdin.readline
# https://www.acmicpc.net/problem/11967

N,M = map(int, input().split())
# 방문배열
visit = [[False for _ in range(N+1)] for _ in range(N+1)]

# 불이 켜져있으면 True 아니면 False
mapp = [[False for _ in range(N+1)] for _ in range(N+1)]

# 스위치 정보가 있는 배열
switch = [[[] for _ in range(N+1)] for _ in range(N+1)]

for _ in range(M):
    x,y,a,b = map(int, input().split())
    switch[x][y].append((a,b))

answer = 1
q = deque()
q.append((1,1))
visit[1][1] = True
mapp[1][1] = True
if switch[1][1]:
    for r,c in switch[1][1]:
        if not mapp[r][c]:
            mapp[r][c] = True
            answer += 1

# 방문한 방에 스위치가 있다면 불을 모두 켜기
def check_room(r,c):
    global answer
    visit[r][c] = True
    if switch[r][c]:
        for a,b in switch[r][c]:
            if not mapp[a][b]:
                mapp[a][b] = True
                answer += 1
                # 불을 켠 방 근처에 간적이 있으면 이 방은 갈수 있는 방
                for i in range(4):
                    nr = a + dr[i]
                    nc = b + dc[i]
                    if 1 <= nr <= N and 1 <= nc <= N and visit[nr][nc]:
                        visit[nr][nc] = True
                        q.append((nr,nc))

dr = [1,-1,0,0]
dc = [0,0,1,-1]
while q:
    cr, cc = q.popleft()
    for i in range(4):
        nr = cr + dr[i]
        nc = cc + dc[i]
        # 불이 켜져있고 방문하지 않았다면 queue에 추가
        if 1 <= nr <= N and 1 <= nc <= N and not visit[nr][nc] and mapp[nr][nc]:
            visit[nr][nc] = True
            check_room(nr,nc)
            q.append((nr,nc))

print(answer)