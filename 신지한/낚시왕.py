from sys import stdin

input = stdin.readline

# https://www.acmicpc.net/problem/17143
R,C,M = map(int, input().split())

class Shark:
    def __init__(self, r,c,s,d,z):
        self.row = r
        self.col = c
        self.speed = s
        self.dir = d
        self.size = z

        # 큰 speed인 경우 같은결과가 나오게 값 감소
        if self.dir == 1 or self.dir == 2 and self.speed > (R-1)*2:
            self.speed %= (R-1)*2
        elif self.dir == 3 or self.dir == 4 and self.speed > (C-1)*2:
            self.speed %= (C-1)*2
        
    # 상어 이동 상하우좌 1234
    def move(self):
        if self.speed == 0:
            return
        # 상
        if self.dir == 1:
            self.row -= self.speed
            # 끝에 도달해서 방향바꾸는 경우
            if -R < self.row < 0:
                self.row = abs(self.row)
                self.dir = 2
            elif self.row <= -R:
                self.row += (R-1)*2
        # 하
        elif self.dir == 2:
            self.row += self.speed
            # 끝에 도달해서 방향바꾸는 경우
            if R <= self.row <= (R-1)*2:
                self.row = abs(self.row - (R-1)*2)
                self.dir = 1
            elif (R-1)*2 < self.row:
                self.row -= (R-1)*2
        # 우
        elif self.dir == 3:
            self.col += self.speed
            # 끝에 도달해서 방향바꾸는 경우
            if C <= self.col <= (C-1)*2:
                self.col = abs(self.col - (C-1)*2)
                self.dir = 4
            elif (C-1)*2 < self.col:
                self.col -= (C-1)*2
        # 좌
        else:
            self.col -= self.speed
            # 끝에 도달해서 방향바꾸는 경우
            if -C < self.col < 0:
                self.col = abs(self.col)
                self.dir = 3
            elif self.col <= -C:
                self.col += (C-1)*2

sharks = []

for _ in range(M):
    r,c,s,d,z = map(int, input().split())
    r -= 1
    c -= 1
    sharks.append(Shark(r,c,s,d,z))

answer = 0
for fisher in range(C):
    # 낚시꾼 1칸이동 = fisher

    # 가까운 상어 잡기
    sharks.sort(key = lambda x : (x.col, x.row))
    for s in range(len(sharks)):
        if sharks[s].col == fisher:
            answer += sharks[s].size
            sharks.pop(s)
            break

    # 상어 이동
    for shark in sharks:
        shark.move()
    
    # 겹치면 큰 상어가 잡아먹기
    sharks.sort(key = lambda x : (x.row, x.col, -x.size))
    idx = 0
    while idx < len(sharks)-1:
        cur = sharks[idx]
        next = sharks[idx+1]
        if cur.row == next.row and cur.col == next.col:
            sharks.pop(idx+1)
            continue
        else:
            idx += 1

print(answer)