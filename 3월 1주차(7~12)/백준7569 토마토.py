from sys import stdin
from collections import deque
input = stdin.readline

# 1. 안익은 토마토 개수(new_tomatoes)를 세고 queue에 익은 토마토 좌표를 추가
# 2. BFS로 주변 토마토 익히면서 한 cycle 돌때마다 day 1증가
# 3. 처음부터 익어있으면 0, 모두 익지 못하면 -1, 그외 day 출력

C,R,H = map(int, input().split())
box = [[] for _ in range(H)]
# 안익은 토마토 개수
new_tomatoes = 0
q = deque()

# 1익음 0안익음 -1없음
# 3차원 배열 box 입력받기
for h in range(H):
    for r in range(R):
        temp = list(map(int, input().split()))
        box[h].append(temp)
        for c in range(C):
            if temp[c] == 1:
                q.append((h,r,c))
            elif temp[c] == 0:
                new_tomatoes += 1

# 익히는 방향
dir = [(0,0,1), (0,0,-1), (1,0,0), (-1,0,0), (0,1,0), (0,-1,0)]
# 경과한 날짜
day = 0

if new_tomatoes == 0:
    print(0)
else:
    # BFS 시작
    while q:
        # 더 이상 익힐 토마토 없으면 루프탈출
        if new_tomatoes == 0:
            break
        
        # 하루동안 주변 토마토 익히기
        size = len(q)
        while size > 0:
            size -= 1
            h,r,c = q.popleft()
            for i in range(6):
                nh = h + dir[i][0]
                nr = r + dir[i][1]
                nc = c + dir[i][2]

                if 0 <= nh < H and 0 <= nr < R and 0 <= nc < C and box[nh][nr][nc] == 0:
                    box[nh][nr][nc] = 1
                    new_tomatoes -= 1
                    q.append((nh,nr,nc))
        day += 1
    # BFS 종료
    
    # 모두 익었으면 날짜 출력
    if new_tomatoes == 0:
        print(day)
    # 덜익은 토마토 남으면 -1 출력
    else:
        print(-1)