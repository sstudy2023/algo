from sys import stdin

input = stdin.readline

# https://www.acmicpc.net/problem/1446
N, D = map(int, input().split())
shortcut = [list(map(int, input().split())) for _ in range(N)]
dp = [i for i in range(D+1)]

for i in range(D+1):
    dp[i] = min(dp[i], dp[i-1]+1)

    for start, end, dist in shortcut:
        if i == start and end <= D and dp[i] + dist < dp[end]:
            dp[end] = dp[i] + dist

print(dp[D])