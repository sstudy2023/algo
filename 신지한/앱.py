from sys import stdin

input = stdin.readline

# https://www.acmicpc.net/problem/7579
N, M = map(int, input().split())

memory = list(map(int, input().split()))
cost = list(map(int, input().split()))
MAX = sum(cost)+1

dp = [[0] * (MAX) for _ in range(N+1)]

answer = int(1e9)

for i in range(N):
    for j in range(MAX):
        if j >= cost[i]:
            dp[i][j] = max(dp[i-1][j], dp[i-1][j-cost[i]] + memory[i])
        else:
            dp[i][j] = dp[i-1][j]

        if dp[i][j] >= M:
            answer = min(answer, j)

print(answer)