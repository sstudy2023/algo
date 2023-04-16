from sys import stdin

input = stdin.readline

# https://www.acmicpc.net/problem/12100
N = int(input().strip())
arr = []

for _ in range(N):
    arr.append(list(map(int, input().split())))

# 같은 숫자끼리 합치는 함수
def fusion(num):
    idx = 0
    while idx < len(num)-1:
        if num[idx] == 0:
            break
        if num[idx] == num[idx+1]:
            num[idx] *= 2
            num.pop(idx+1)
            num.append(0)
        idx += 1
    return num

# 상하좌우(1234) 방향으로 이동시키면 모두 그쪽으로 봍이기
def up(mapp):
    for c in range(N):
        temp = []
        t = N
        for r in range(N):
            if mapp[r][c] != 0:
                temp.append(mapp[r][c])
                t -= 1
        # 추가하지 않은만큼 0으로 채우기
        temp += [0] * t
        temp = fusion(temp)
        for tr in range(N):
            mapp[tr][c] = temp[tr]
    return mapp

def down(mapp):
    for c in range(N):
        temp = []
        t = N
        for r in range(N-1, -1, -1):
            if mapp[r][c] != 0:
                temp.append(mapp[r][c])
                t -= 1
        # 추가하지 않은만큼 0으로 채우기
        temp += [0] * t
        temp = fusion(temp)
        for tr in range(N):
            mapp[N-1-tr][c] = temp[tr]
    return mapp

def left(mapp):
    for r in range(N):
        temp = []
        t = N
        for c in range(N):
            if mapp[r][c] != 0:
                temp.append(mapp[r][c])
                t -= 1
        # 추가하지 않은만큼 0으로 채우기
        temp += [0] * t
        temp = fusion(temp)
        for tc in range(N):
            mapp[r][tc] = temp[tc]
    return mapp

def right(mapp):
    for r in range(N):
        temp = []
        t = N
        for c in range(N-1, -1, -1):
            if mapp[r][c] != 0:
                temp.append(mapp[r][c])
                t -= 1
        # 추가하지 않은만큼 0으로 채우기
        temp += [0] * t
        temp = fusion(temp)
        for tc in range(N):
            mapp[r][N-1-tc] = temp[tc]
    return mapp

answer = 0
def maximize(mapp):
    global answer
    for m in mapp:
        answer = max(answer, max(m))

def dfs(cnt, mapp):
    maximize(mapp)
    if cnt == 5:
        return

    origin = [m[:] for m in mapp]
    dfs(cnt+1, up(origin))
    origin = [m[:] for m in mapp]
    dfs(cnt+1, down(origin))
    origin = [m[:] for m in mapp]
    dfs(cnt+1, left(origin))
    origin = [m[:] for m in mapp]
    dfs(cnt+1, right(origin))

dfs(0, arr)
print(answer)