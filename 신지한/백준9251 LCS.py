from sys import stdin

input = stdin.readline

first = input().strip()
second = input().strip()

R = len(first) + 1
C = len(second) + 1

arr = [[0 for _ in range(C)] for _ in range(R)]

# 두 문자열을 비교하여 같다면 r-1, c-1보다 +1
# 두 문자열이 다르다면 r-1,c 와 r,c-1 두 숫자를 비교하여 큰 것으로 결정
def lcs(r,c):
    global first, second
    if first[r-1] == second[c-1]:
        return arr[r-1][c-1] + 1
    else:
        return max(arr[r-1][c] , arr[r][c-1])

# 1,1 부터 R-1, C-1까지 lcs점화식을 이용하여 배열채우기
for r in range(1, R):
    for c in range(1, C):
        arr[r][c] = lcs(r,c)

print(arr[R-1][C-1])