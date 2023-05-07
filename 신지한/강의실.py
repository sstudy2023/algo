from sys import stdin
import heapq

input = stdin.readline

# https://www.acmicpc.net/problem/1374

N = int(input().strip())
lectures = []
for _ in range(N):
    num, start, end = map(int, input().split())
    lectures.append((start, end))
# 강의 시작시간순으로 오름차순으로 정렬한다
lectures.sort()
heap = []
start, end = lectures.pop(0)

# 우선순위 큐에 종료시간을 오름차순으로 넣는다
heapq.heappush(heap, end)
while lectures:
    start, end = lectures.pop(0)
    # 새로 추가될 강의 시작시간이 우선순위 큐의 종료시간 이상이면 pop
    if heap[0] <= start:
        heapq.heappop(heap)
    heapq.heappush(heap, end)

print(len(heap))