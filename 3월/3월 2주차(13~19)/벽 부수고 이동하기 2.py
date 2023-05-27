from sys import stdin
from collections import deque

input = stdin.readline
# https://www.acmicpc.net/problem/14442

# 3차원 방문배열 입력 : n번 부쉈을 때의 2차원 방문배열
R,C,K = map(int, input().split())
visit = [[[False for _ in range(C)] for _ in range(R)] for _ in range(K+1)]
mapp = []
for _ in range(R):
    mapp.append(list(map(int, input().strip())))

dr = [0,0,1,-1]
dc = [1,-1,0,0]
answer = -1

q = deque()
# 행, 열, 이동거리, 부순 벽의 수
q.append((0,0,1,0))
visit[0][0][0] = True


while q:
    r, c, cnt, broken = q.popleft()
    if r == R-1 and c == C-1:
        answer = cnt
        break

    for i in range(4):
        nr = r + dr[i]
        nc = c + dc[i]
        if 0 <= nr < R and 0 <= nc < C:
            # 벽을 부수지 않고 가는경우
            if not visit[broken][nr][nc] and mapp[nr][nc] == 0:
                q.append((nr,nc,cnt+1,broken))
                visit[broken][nr][nc] = True
            # 벽을 부수고 가는경우
            if mapp[nr][nc] == 1 and broken < K and not visit[broken+1][nr][nc]:
                q.append((nr,nc,cnt+1,broken+1))
                visit[broken+1][nr][nc] = True

print(answer)