from collections import deque

WIDTH = 9
HEIGHT = 10
dirs = [
    [[-1, 0], [-2, -1], [-3, -2]],
    [[-1, 0], [-2, 1], [-3, 2]],
    [[0, -1], [-1, -2], [-2, -3]],
    [[0, -1], [1, -2], [2, -3]],
    [[0, 1], [-1, 2], [-2, 3]],
    [[0, 1], [1, 2], [2, 3]],
    [[1, 0], [2, -1], [3, -2]],
    [[1, 0], [2, 1], [3, 2]]
]

def findWay(sang):
    q = deque()
    q.append(sang)
    v = [[0] * WIDTH for _ in range(HEIGHT)]
    v[sang[0]][sang[1]] = 1

    while q:
        cr, cc = q.popleft()

        if cr == king[0] and cc == king[1]:
            return v[cr][cc] - 1

        for wi in range(len(dirs)):
            can = True
            for d in range(len(dirs[wi])):
                dr, dc = dirs[wi][d]
                nr, nc = cr + dr, cc + dc
                
                # 판 밖을 벗어나면
                if nr < 0 or nr >= HEIGHT or nc < 0 or nc >= WIDTH:
                    can = 0
                    break
                # 가는 자리에 말이 있으면
                elif d != len(dirs[wi]) - 1 and nr == king[0] and nc == king[1]:
                    can = 0
                    break
            
            # 이동할 수 있으명 큐에 추가
            if can:
                q.append((nr, nc))
                v[nr][nc] = v[cr][cc] + 1

    return -1

sang = list(map(int, input().split()))
king = list(map(int, input().split()))

rlt = findWay(sang)
print(rlt)