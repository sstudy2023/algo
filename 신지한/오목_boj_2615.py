from sys import stdin

input = stdin.readline

# https://www.acmicpc.net/problem/2615
mapp = []
for _ in range(19):
    mapp.append(list(map(int, input().split())))

# 오른쪽으로 오목 검사
def check_col(r, c, n):
    # 이전에 같은 색의 돌이 있다면 pass(이전에 검사했으므로)
    if c - 1 >= 0 and mapp[r][c-1] == n:
        return False
    # 우측 4개까지 같은 돌인지 검사
    for i in range(1, 5):
        nc = c + i
        if not (nc < 19 and mapp[r][nc] == n):
            return False
    # 6번째 돌이 있는지 검사
    if c + 5 < 19 and mapp[r][c+5] == n:
        return False
    # 전부 통과하면 오목완성
    return True

# 아래쪽으로 오목 검사
def check_row(r, c, n):
    # 이전에 같은 색의 돌이 있다면 pass(이전에 검사했으므로)
    if r - 1 >= 0 and mapp[r-1][c] == n:
        return False
    # 아래 4개까지 같은 돌인지 검사
    for i in range(1, 5):
        nr = r + i
        if not (nr < 19 and mapp[nr][c] == n):
            return False
    # 6번째 돌이 있는지 검사
    if r + 5 < 19 and mapp[r+5][c] == n:
        return False
    # 전부 통과하면 오목완성
    return True

# 오른쪽아래로 오목 검사
def check_cross_down(r, c, n):
    # 이전에 같은 색의 돌이 있다면 pass(이전에 검사했으므로)
    if r - 1 >= 0 and c - 1 >= 0 and mapp[r-1][c-1] == n:
        return False
    # 대각선 4개까지 같은 돌인지 검사
    for i in range(1, 5):
        nr = r + i
        nc = c + i
        if not (nr < 19 and nc < 19 and mapp[nr][nc] == n):
            return False
    # 6번째 돌이 있는지 검사
    if r + 5 < 19 and c + 5 < 19 and mapp[r+5][c+5] == n:
        return False
    # 전부 통과하면 오목완성
    return True

# 오른쪽위로 오목 검사
def check_cross_up(r, c, n):
    # 이전에 같은 색의 돌이 있다면 pass(이전에 검사했으므로)
    if r + 1 < 19 and c - 1 >= 0 and mapp[r+1][c-1] == n:
        return False
    # 대각선 4개까지 같은 돌인지 검사
    for i in range(1, 5):
        nr = r - i
        nc = c + i
        if not (nr >= 0 and nc < 19 and mapp[nr][nc] == n):
            return False
    # 6번째 돌이 있는지 검사
    if r - 5 >= 0 and c + 5 < 19 and mapp[r-5][c+5] == n:
        return False
    # 전부 통과하면 오목완성
    return True

def find_five():
    for r in range(19):
        for c in range(19):
            cur = mapp[r][c]
            if cur != 0 and (
                check_col(r,c,cur) or check_row(r,c,cur) 
                             or check_cross_up(r,c,cur)
                             or check_cross_down(r,c,cur)
                             ):
                print(cur)
                print(f'{r+1} {c+1}')
                return
    print(0)

find_five()