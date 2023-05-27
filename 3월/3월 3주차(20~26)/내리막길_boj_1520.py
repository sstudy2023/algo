from sys import stdin

input = stdin.readline

# https://www.acmicpc.net/problem/1520
R,C = map(int, input().split())
mapp = []
for _ in range(R):
    mapp.append(list(map(int, input().split())))

dr = [1,-1,0,0]
dc = [0,0,1,-1]
dp = [[-1 for _ in range(C)] for _ in range(R)]

def dfs(r, c):
    # 다음이 도착지이면 1 반환
    if r == R-1 and c == C-1:
        return 1
    # 이미 방문한 곳이면 그 횟수만큼 더함
    if dp[r][c] != -1:
        return dp[r][c]
    # 미방문이면 0으로 초기화
    dp[r][c] = 0

    # 사방탐색하면서 내리막길이면 다음칸의 도달할 수 있는 수[ dfs(nr, nc) ]를 더함
    for i in range(4):
        nr = r + dr[i]
        nc = c + dc[i]
        if 0 <= nr < R and 0 <= nc < C and mapp[r][c] > mapp[nr][nc]:
            dp[r][c] += dfs(nr, nc)
    # 현재 칸에 도달할 수 있는 수를 반환
    return dp[r][c]

print(dfs(0,0))
