from sys import stdin

input = stdin.readline

# https://www.acmicpc.net/problem/1138

# 1부터 검사하여 자신보다 큰 수만큼 빈칸이 들어갈 수 있는 위치에 넣는다
N = int(input().strip())
people = list(map(int, input().split()))
answer = [0]*N

for i in range(N):
    taller = people[i]
    idx = 0
    count = 0
    while True:
        # 빈칸이 아니라면 다음으로
        if answer[idx] != 0:
            idx += 1
            continue
        # 왼쪽에 빈칸을 만족했다면 저장
        if count == taller:
            answer[idx] = i+1
            break
        # 왼쪽에 빈칸이 부족하면 다음으로
        else:
            idx += 1
            count += 1

print(*answer)