from sys import stdin

input = stdin.readline

# https://www.acmicpc.net/problem/15989
N = int(input().strip())
arr = []
for _ in range(N):
    arr.append(int(input().strip()))

MAX = max(arr)
dp = [0] * (MAX+1)
dp[1] = 1

# [n-1을 만드는 경우의수] + [n을 2와 3으로만 만드는 경우의수]
for i in range(2, MAX+1):
    divide = i // 3
    temp = 0
    if i % 2 == 0:
        temp = divide // 2 + 1
    else:
        temp = (divide - 1) // 2 + 1
    dp[i] = dp[i-1] + temp

for n in arr:
    print(dp[n])