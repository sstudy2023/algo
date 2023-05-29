from sys import stdin

input = stdin.readline

N, M = map(int, input().split())
arr = []

for _ in range(N):
    arr.append(int(input().strip()))
arr.sort()

left, right = 0, 0
diff = 0
answer = 1e10
# 차이가 작으면 right를 전진
# 차이가 크면 left를 전진
while left < N:
    diff = arr[right] - arr[left]
    if diff >= M:
        left += 1
        answer = min(answer, diff)
    elif right < N - 1:
        right += 1
    else:
        break

print(answer)