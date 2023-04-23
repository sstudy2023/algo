from sys import stdin
import heapq

stdin = open('input.txt', 'r')
input = stdin.readline

# https://www.acmicpc.net/problem/19598

N = int(input().strip())
arr = []
for _ in range(N):
    arr.append(list(map(int, input().split())))

# 회의시작 시간순으로 정렬
arr.sort(key=lambda x : x[0])

# heapq에는 끝나는 시간만 넣는다
# 새로운 회의의 시작시간이 끝나는 시간 이후라면 기존 회의시간은 pop한다
heap = []
for start, end in arr:
    if heap and heap[0] <= start:
        heapq.heappop(heap)
    heapq.heappush(heap, end)

print(len(heap))
