from sys import stdin

input = stdin.readline

# 0,0 부터 시작하여 R-1, C-1까지 가면서 어떤 경로로 가는 것이 사탕을 더 많이 가질 수 있는지 기록한다

# ex) 0,0 에서 1,1까지 가는 경우는 2가지 -> 비교하여 더 큰값을 1,1에 저장
# 이것을 R-1, C-1까지 반복한다

R, C = map(int, input().split())
mapp = []
dp = [[0 for _ in range(C)] for _ in range(R)]
for r in range(R):
    mapp.append(list(map(int, input().split())))

dp[0][0] = mapp[0][0]
for r in range(1,R):
    dp[r][0] = mapp[r][0] + dp[r-1][0]
for c in range(1,C):
    dp[0][c] = mapp[0][c] + dp[0][c-1]

for r in range(1,R):
    for c in range(1,C):
        dp[r][c] = max(dp[r-1][c] + mapp[r][c], dp[r][c-1] + mapp[r][c])

print(dp[R-1][C-1])