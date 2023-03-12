from sys import stdin

input = stdin.readline

# 0-1 knapsack 문제
# dp 이차원 배열에 넣는 물건과 가방제한 무게에 따라 최고가치인 경우를 기록한다

N, K = map(int, input().split())
bag = []
for _ in range(N):
    bag.append(list(map(int, input().split())))

dp = [[0 for _ in range(K+1)] for _ in range(N+1)]

for r in range(1, N+1):
    w = bag[r-1][0]
    v = bag[r-1][1]
    for c in range(1, K+1):
        # 새로 넣으려는 물건이 무게제한을 넘으면 이전 값을 그대로 가져온다
        if w > c:
            dp[r][c] = dp[r-1][c]
        # 물건을 넣을 수 있는 경우 이전 값과 비교하여 더 큰 값을 기록한다
        else:
            dp[r][c] = max(dp[r-1][c], dp[r-1][c-w] + v)

print(dp[N][K])