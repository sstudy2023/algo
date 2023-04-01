from sys import stdin

input = stdin.readline

# https://www.acmicpc.net/problem/1092
N = int(input().strip())
crain = list(map(int,input().split()))
M = int(input().strip())
box = list(map(int,input().split()))

crain.sort(reverse=True)
box.sort(reverse=True)
answer = 0

while box:
    if crain[0] < box[0]:
        answer = -1
        break
    for c in crain:
        if box:
            idx = 0
            # 들어올릴수있는 물건이 나올때까지 idx증가
            while idx < len(box) and c < box[idx]:
                idx += 1
            if idx < len(box) and c >= box[idx]:
                box.pop(idx)
    # 모든 크레인 적재
    answer += 1

print(answer)