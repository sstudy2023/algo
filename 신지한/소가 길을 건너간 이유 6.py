from sys import stdin
from collections import deque

input = stdin.readline

# https://www.acmicpc.net/problem/14466

# 각 소에서 다리를 이용하지 않고 bfs로 탐색 -> 방문하지 않은 소를 전부 세주기
N, K, R = map(int, input().split())
def minus_one(n):
    return int(n)-1

mapp = [[0]*N for _ in range(N)]
bridge = [[[] for _ in range(N)] for _ in range(N)]
cows = []
dr = [1,-1,0,0]
dc = [0,0,1,-1]
for _ in range(R):
    r,c,x,y = map(minus_one, input().split())
    bridge[r][c].append([x,y])
    bridge[x][y].append([r,c])

for _ in range(K):
    r, c = map(minus_one, input().split())
    mapp[r][c] = 1
    cows.append((r,c))

def bfs(sr, sc):
    visited = [[False for _ in range(N)] for _ in range(N)]
    q = deque()
    q.append((sr,sc))
    visited[sr][sc] = True
    count = len(cows) - 1

    while q:
        cr, cc = q.popleft()

        for i in range(4):
            nr = cr + dr[i]
            nc = cc + dc[i]
            # 범위 내면서 방문하지 않은 곳
            if 0 <= nr < N and 0 <= nc < N and not visited[nr][nc]:
                # 가려는 곳에 길이 있으면 pass
                if [cr,cc] in bridge[nr][nc]:
                    continue

                q.append((nr,nc))
                visited[nr][nc] = True

                # 소가 있는경우 count 감소
                if mapp[nr][nc] == 1:
                    count -= 1
    
    return count

total = 0
for r,c in cows:
    total += bfs(r,c)

print(total // 2)
