from sys import stdin

input = stdin.readline

# https://www.acmicpc.net/problem/13144

# 투 포인터를 사용해 경우의 수를 센다
N = int(input().strip())
arr = list(map(int, input().split()))
visited = [False] * 100001
answer = 0
left, right = 0, 0

while left < N and right < N:
    # 중복되지 않는다면 left ~ right 에서 나올 수 있는 경우의 수만큼 answer에 더하고 right + 1
    if not visited[arr[right]]:
        visited[arr[right]] = True
        right += 1
        answer += right - left
    else:
        visited[arr[left]] = False
        left += 1

print(answer)