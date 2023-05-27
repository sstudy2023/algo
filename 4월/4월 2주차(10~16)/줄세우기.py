from sys import stdin

input = stdin.readline

# https://www.acmicpc.net/problem/2631
N = int(input().strip())
arr = [0]
for _ in range(N):
    arr.append(int(input().strip()))

dp = [1]*(N+1)

# LIS문제
# i까지의 최장수열 개수 구하기
for i in range(1, N+1):
    for j in range(1, i):
        # j번째 수가 i번째 수보다 작다 => 수열에 포함가능 => dp + 1
        if arr[j] < arr[i] and dp[i] < dp[j]+1:
            dp[i] = dp[j] + 1

print(N - max(dp))