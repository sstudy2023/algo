from sys import stdin

input = stdin.readline

# https://www.acmicpc.net/problem/14499

class Dice:
    def __init__(self,r,c):
        self.top = 0
        self.bottom = 0
        self.up = 0
        self.down = 0
        self.left = 0
        self.right = 0
        self.r = r
        self.c = c
    def move_right(self):
        if self.c + 1 == C:
            return False
        temp = self.top
        self.top = self.left
        self.left = self.bottom
        self.bottom = self.right
        self.right = temp
        self.c += 1
        return True
    def move_left(self):
        if self.c - 1 == -1:
            return False
        temp = self.top
        self.top = self.right
        self.right = self.bottom
        self.bottom = self.left
        self.left = temp
        self.c -= 1
        return True
    def move_up(self):
        if self.r - 1 == -1:
            return False
        temp = self.top
        self.top = self.down
        self.down = self.bottom
        self.bottom = self.up
        self.up = temp
        self.r -= 1
        return True
    def move_down(self):
        if self.r + 1 == R:
            return False
        temp = self.top
        self.top = self.up
        self.up = self.bottom
        self.bottom = self.down
        self.down = temp
        self.r += 1
        return True
    # 주사위 아랫면 복사
    def duplicate(self, m):
        if m[self.r][self.c] == 0:
            m[self.r][self.c] = self.bottom
        else:
            self.bottom = m[self.r][self.c]
            m[self.r][self.c] = 0

R, C, x, y, K = map(int, input().split())
mapp = [list(map(int, input().split())) for _ in range(R)]
dice = Dice(x,y)
orders = list(map(int,input().split()))

for order in orders:
    # 동서북남 1234
    if order == 1 and dice.move_right():
        dice.duplicate(mapp)
        print(dice.top)
    elif order == 2 and dice.move_left():
        dice.duplicate(mapp)
        print(dice.top)
    elif order == 3 and dice.move_up():
        dice.duplicate(mapp)
        print(dice.top)
    elif order == 4 and dice.move_down():
        dice.duplicate(mapp)
        print(dice.top)