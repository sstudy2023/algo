from sys import stdin

input = stdin.readline

# https://www.acmicpc.net/problem/1027

# index를 좌우로 하나씩 증가시키면서 검사

# 기울기 최댓값보다 큰 값이면 추가
N = int(input().strip())
arr = list(map(int, input().split()))

def count_building(idx):
    count = 0
    left, right = 1e9, 1e9
    if idx != 0:
        left = arr[idx-1] - arr[idx]
        count += 1
    if idx != N-1:
        right = arr[idx+1] - arr[idx]
        count += 1

    # 왼쪽 검사
    for i in range(idx-2, -1, -1):
        angle = (arr[i] - arr[idx]) / (idx - i)
        if angle > left:
            left = angle
            count += 1

    # 오른쪽 검사
    for i in range(idx+2, N):
        angle = (arr[i] - arr[idx]) / (i - idx)
        if angle > right:
            right = angle
            count += 1

    return count

result = 0
for i in range(N):
    result = max(result, count_building(i))

print(result)