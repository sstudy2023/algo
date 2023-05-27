def watch(cur, last, s):
    global room, min_blind_spot

    if cur == last:
        if min_blind_spot > N * M - (structure + s):
            min_blind_spot = N * M - (structure + s)
    else:
        cur_cctv, cr, cc = cctv[cur]

        # 해당 cctv의 방향 경우의 수
        for case in range(len(directions[cur_cctv])):

            # 각 방향마다 추가되는 감시구역 수 구하기
            direction = directions[cur_cctv][case]
            tmp = 0
            for d in range(len(direction)):
                nr, nc = cr + direction[d][0], cc + direction[d][1]
                
                while 0 <= nr < N and 0 <= nc < M and room[nr][nc] != 6:
                    if not room[nr][nc]:
                        room[nr][nc] = -1
                        cctv_area[cur].append((nr, nc))
                        tmp += 1
                    nr += direction[d][0]
                    nc += direction[d][1]
            
            watch(cur+1, last, s+tmp)

            # 감시 중지
            while len(cctv_area[cur]):
                nr, nc = cctv_area[cur].pop()
                room[nr][nc] = 0

# 각 cctv 종류마다의 방향 경우
directions = [
    0,
    [[(0, 1)], [(1, 0)], [(0, -1)], [(-1, 0)]],
    [[(0, 1), (0, -1)], [(1, 0), (-1, 0)]],
    [[(0, 1), (-1, 0)], [(0, 1), (1, 0)], [(0, -1), (-1, 0)], [(0, -1), (1, 0)]],
    [[(0, 1), (0, -1), (1, 0)], [(0, 1), (0, -1), (-1, 0)], [(1, 0), (-1, 0), (0, 1)], [(1, 0), (-1, 0), (0, -1)]],
    [[(0, 1), (1, 0), (0, -1), (-1, 0)]]
]

N, M = map(int, input().split())

room = [list(map(int, input().split())) for _ in range(N)]

min_blind_spot = N * M
structure = 0
cctv = []

# 벽, cctv 개수 파악 및 cctv 종류 저장
for r in range(N):
    for c in range(M):
        if 0 < room[r][c] < 6:
            cctv.append((room[r][c], r, c))
            structure += 1
        elif room[r][c] == 6:
            structure += 1

# 감시
cctv_area = [[] for _ in range(len(cctv))]
watch(0, len(cctv), 0)

print(min_blind_spot)