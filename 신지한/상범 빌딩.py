from sys import stdin
from collections import deque

input = stdin.readline

# https://www.acmicpc.net/problem/6593

dl = [1,-1,0,0,0,0]
dr = [0,0,1,-1,0,0]
dc = [0,0,0,0,1,-1]

def bfs(sl,sr,sc):
    q = deque()
    q.append((sl,sr,sc,0))
    mapp[sl][sr][sc] = 'v'

    while q:
        cl, cr, cc, time = q.popleft()
        for i in range(6):
            nl = cl + dl[i]
            nr = cr + dr[i]
            nc = cc + dc[i]
            if 0 <= nl < L and 0 <= nr < R and 0 <= nc < C:
                if mapp[nl][nr][nc] == '.':
                    q.append((nl,nr,nc,time+1))
                    mapp[nl][nr][nc] = 'v'
                elif mapp[nl][nr][nc] == 'E':
                    return time + 1
    return -1

while True:
    L, R, C = map(int, input().split())
    if L == 0 and R == 0 and C == 0:
        break
    mapp = [[] for _ in range(L)]
    sl, sr, sc = 0, 0, 0

    for l in range(L):
        for r in range(R):
            temp = list(input())
            mapp[l].append(temp)
            if 'S' in temp:
                sl = l
                sr = r
                sc = temp.index('S')
        input()

    result = bfs(sl,sr,sc)
    if result == -1:
        print('Trapped!')
    else:
        print(f'Escaped in {result} minute(s).')