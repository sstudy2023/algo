from sys import stdin
# https://www.acmicpc.net/problem/10942
input = stdin.readline

N = int(input().strip())
arr = list(map(int, input().split()))
dp = [[False for _ in range(N)] for _ in range(N)]

# 한글자, 두글자 dp값 갱신
for i in range(N):
    dp[i][i] = True
    if i+1 < N and arr[i] == arr[i+1]:
        dp[i][i+1] = True

# 두개의 위치를 받아 펠린드롬인지 비교
def check(a, b):
    if arr[a] == arr[b] and dp[a+1][b-1]:
        dp[a][b] = True

# 세글자부터 갱신시작
for i in range(2, N):
    r = 0
    c = i
    for j in range(N-i):
        check(r+j, c+j)

# 입력받은 수를 정수로 변환하고 1 감소
def minus(num):
    return int(num) - 1

# 질문 처리
M = int(input().strip())
for _ in range(M):
    a, b = map(minus, input().split())
    if dp[a][b]:
        print(1)
    else:
        print(0)