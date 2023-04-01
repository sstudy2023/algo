from sys import stdin
import heapq

input = stdin.readline

# https://www.acmicpc.net/problem/11286
N = int(input().strip())
arr = []

for _ in range(N):
    order = int(input().strip())
    if order == 0:
        if arr:
            print(heapq.heappop(arr)[1])
        else:
            print(0)

    else:
        heapq.heappush(arr, (abs(order),order))