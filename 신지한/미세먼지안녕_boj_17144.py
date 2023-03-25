import sys

input = sys.stdin.readline
# https://www.acmicpc.net/problem/17144

R, C, T = map(int, input().split())
mapp = []
for _ in range(R):
    mapp.append(list(map(int, input().split())))

# 공기청정기 위치 저장(위쪽부분)
AR = -1
for r in range(R):
    if mapp[r][0] == -1:
        AR = r
        break

dr = [1,-1,0,0]
dc = [0,0,1,-1]
# 모든 칸에서 미세먼지 확산
def spread():
    global mapp
    temp = [[0 for _ in range(C)] for _ in range(R)]
    for r in range(R):
        for c in range(C):
            # 사방확산 후, 확산된 만큼 기존위치에서 차감한다
            if mapp[r][c] > 0:
                spread_cnt = 0
                for i in range(4):
                    nr = r + dr[i]
                    nc = c + dc[i]
                    dust = mapp[r][c] // 5

                    if 0 <= nr < R and 0 <= nc < C and mapp[nr][nc] != -1:
                        temp[nr][nc] += dust
                        spread_cnt += 1
                temp[r][c] += (mapp[r][c] - dust * spread_cnt)
            elif mapp[r][c] == -1:
                temp[r][c] = -1
    mapp = temp

# 공기청정기 위쪽바람
def circulate_up():
    global mapp, AR
    # 상
    for r in range(AR-1, 0, -1):
        mapp[r][0] = mapp[r-1][0]
    # 우
    for c in range(C-1):
        mapp[0][c] = mapp[0][c+1]
    # 하
    for r in range(AR):
        mapp[r][C-1] = mapp[r+1][C-1]
    # 좌
    for c in range(C-1, 1, -1):
        mapp[AR][c] = mapp[AR][c-1]
    # 공기청정기에서 나온바람
    mapp[AR][1] = 0

# 공기청정기 아래쪽바람
def circulate_down():
    global mapp, AR
    # 하
    for r in range(AR+2, R-1):
        mapp[r][0] = mapp[r+1][0]
    # 우
    for c in range(C-1):
        mapp[R-1][c] = mapp[R-1][c+1]
    # 상
    for r in range(R-1, AR+1, -1):
        mapp[r][C-1] = mapp[r-1][C-1]
    # 좌
    for c in range(C-1, 1, -1):
        mapp[AR+1][c] = mapp[AR+1][c-1]
    # 공기청정기에서 나온바람
    mapp[AR+1][1] = 0

# 미세먼지 총합 구하기
def sum_dust():
    total = 0
    for r in range(R):
        for c in range(C):
            total += mapp[r][c]
    return total + 2

for _ in range(T):
    spread()
    circulate_up()
    circulate_down()

print(sum_dust())