from sys import stdin
from collections import deque

input = stdin.readline
# https://www.acmicpc.net/problem/1325

N, M = map(int, input().split())

answer = []
visit = [False] * (N+1)
maxx = 1
graph = [[] for _ in range(N+1)]

for _ in range(M):
    a, b = map(int, input().split())
    # b를 신뢰하는 목록에 a추가
    graph[b].append(a)

def bfs(start):
    global maxx, answer
    visit = [False] * (N+1)
    q = deque()
    q.append(start)
    visit[start] = True
    # 해킹한 컴퓨터 개수 cnt
    cnt = 0

    while q:
        cur = q.popleft()
        cnt += 1
        for n in graph[cur]:
            if not visit[n]:
                q.append(n)
                visit[n] = True
    # cnt가 최댓값보다 크면 answer 새로만들기
    if cnt > maxx:
        answer = [start]
        maxx = cnt
    # cnt가 최댓값과 같으면 answer에 추가
    elif cnt == maxx:
        answer.append(start)

for i in range(1, N+1):
    bfs(i)
answer.sort()
print(*answer)