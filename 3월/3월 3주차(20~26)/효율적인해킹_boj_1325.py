from collections import deque
import sys

# bfs
def hacking(start):
    nv = [0] * (N + 1)
    q = deque()
    q.append(start)
    nv[start] = 1
    cnt = 1

    while q:
        cn = q.popleft()

        for d in range(len(connection[cn])):
            nn = connection[cn][d]
            if not nv[nn]:
                cnt += 1
                nv[nn] = 1
                q.append(nn)

    return cnt

N, M = map(int, input().split())
connection = [[] for _ in range(N + 1)]

# 신뢰 정보 저장
for _ in range(M):
    end, start = map(int, sys.stdin.readline().split())
    connection[start].append(end)

max_hack_num = 0
max_hack_list = []

for i in range(1, N + 1):
        rlt = hacking(i)
        if rlt == max_hack_num:
            max_hack_list.append(i)
        elif rlt > max_hack_num:
            max_hack_num = rlt
            max_hack_list = [i]

for i in range(len(max_hack_list)):
    print(max_hack_list[i], end=' ')