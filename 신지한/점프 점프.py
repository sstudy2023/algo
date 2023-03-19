from sys import stdin
# https://www.acmicpc.net/problem/11060
input = stdin.readline

N = int(input().strip())
maze = list(map(int, input().split()))
dp = [1001] * N
dp[0] = 0

for i in range(N):
    for jump in range(1, maze[i]+1):
        if i+jump < N and dp[i] + 1 < dp[i+jump]:
            dp[i+jump] = dp[i] + 1

if dp[N-1] == 1001:
    dp[N-1] = -1

print(dp[N-1])