from sys import stdin
# https://www.acmicpc.net/problem/2294
input = stdin.readline

n, k = map(int, input().split())
coins = set()
for _ in range(n):
    coins.add(int(input().strip()))

coins = list(coins)
coins.sort()

# 모든 값을 최대값보다 높은 10001로 할당한다
dp = [10001] * (k+1)
dp[0] = 0

# i > coin 이면 dp[i-coin] + 1 과 dp[i] 중 작은 값을 선택한다
for coin in coins:
    for i in range(k+1):
        if i >= coin:
            dp[i] = min(dp[i], dp[i-coin] + 1)

# 불가능한 경우
if dp[k] == 10001:
    dp[k] = -1

print(dp[k])