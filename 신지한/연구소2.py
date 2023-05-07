from sys import stdin
from collections import deque
from itertools import combinations

input = stdin.readline

# https://www.acmicpc.net/problem/17141

N, M = map(int, input().split())
lab = []
start_position = []
blank = 0
for r in range(N):
    temp = list(map(int, input().split()))
    lab.append(temp)
    for c in range(N):
        if temp[c] == 0:
            blank += 1
        elif temp[c] == 2:
            start_position.append((r,c))
            blank += 1
# 시작위치 모든 경우
start = list(combinations(range(len(start_position)), M))

dr = [1,-1,0,0]
dc = [0,0,1,-1]
answer = 1e9

# 시작위치 배열의 idx
def bfs(idx):
    global answer
    visited = [[False]*N for _ in range(N)]
    q = deque()
    for s in start[idx]:
        sr, sc = start_position[s]
        q.append((sr,sc))
        visited[sr][sc] = True
    # 감염시켜야 할 개수
    todo = blank - M
    time = 0

    while q:
        if todo == 0:
            answer = min(answer, time)
            return
        size = len(q)
        while size > 0:
            cr, cc = q.popleft()
            size -= 1
            for i in range(4):
                nr = cr + dr[i]
                nc = cc + dc[i]
                if 0 <= nr < N and 0 <= nc < N and not visited[nr][nc] and lab[nr][nc] != 1:
                    q.append((nr,nc))
                    visited[nr][nc] = True
                    todo -= 1
        time += 1



for i in range(len(start)):
    bfs(i)

if answer == 1e9:
    answer = -1
print(answer)