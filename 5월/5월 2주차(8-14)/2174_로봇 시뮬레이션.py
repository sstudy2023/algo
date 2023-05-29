# 앞으로 이동하는 함수
def moveFoward(robotNum, repeat):
    r, c, d = robots[robotNum]
    field[r][c] = 0

    # 이동
    for _ in range(repeat):
        r, c = r + dr[d], c + dc[d]

        # 벽에 부딪히면
        if r < 0 or r >= B or c < 0 or c >= A:
            return f'Robot {robotNum} crashes into the wall'
        # 다른 로봇과 부딪히면
        elif field[r][c]:
            return f'Robot {robotNum} crashes into robot {field[r][c]}'
    
    # 이동 후 로봇 위치 저장
    field[r][c] = robotNum
    robots[robotNum] = [r, c, d]
    return 'OK'

# 오른쪽으로 도는 함수
def turnRight(robotNum, repeat):
    d = robots[robotNum][2]

    for _ in range(repeat):
        d = (d + 1) % 4

    robots[robotNum][2] = d

    return 'OK'

# 왼쪽으로 도는 함수
def turnLeft(robotNum, repeat):
    d = robots[robotNum][2]

    for _ in range(repeat):
        d = (d + 3) % 4

    robots[robotNum][2] = d

    return 'OK'

A, B = map(int, input().split())
N, M = map(int, input().split())
robots = [0]
field = [[0] * A for _ in range(B)]
rlt = 'OK'

dr = [0, 1, 0, -1]
dc = [1, 0, -1, 0]

direction = {'N': 3, 'W': 2, 'E': 0, 'S': 1}

# 로봇 위치 저장
for _ in range(N):
    x, y, d = input().split()
    x = int(x) - 1
    y = B - int(y)
    field[y][x] = len(robots)
    robots.append([y, x, direction[d]])

# 명령 수행
for _ in range(M):
    robot, order, repeat = input().split()

    if rlt != 'OK':
        continue

    if order == 'F':
        rlt = moveFoward(int(robot), int(repeat))
    elif order == 'L':
        rlt = turnLeft(int(robot), int(repeat))
    else:
        rlt = turnRight(int(robot), int(repeat))

print(rlt)