from collections import deque

dr = [0, 1, 0, -1]
dc = [1, 0, -1, 0]

# 녹일 양 계산하는 함수
def calMeltCount(r, c):
    cnt = 0
    
    for d in range(len(dr)):
        nr, nc = r + dr[d], c + dc[d]
        if 0 <= nr < N and 0 <= nc < M and ocean[nr][nc] == 0:
            cnt += 1
    
    return cnt

# 한 번에 이어져 있는 빙산 찾는 함수
def searchIceberg(sr, sc):
    q = deque()
    q.append((sr, sc))
    v = [[0] * M for _ in range(N)]
    v[sr][sc] = 1
    rlt_iceberg = [(sr, sc, calMeltCount(sr, sc))]

    while q:
        cr, cc = q.popleft()

        for d in range(len(dr)):
            nr, nc = cr + dr[d], cc + dc[d]

            if 0 <= nr < N and 0 <= nc < M and not v[nr][nc] and ocean[nr][nc]:
                cur_melt_count = calMeltCount(nr, nc)
                q.append((nr, nc))
                v[nr][nc] = 1
                rlt_iceberg.append((nr, nc, cur_melt_count))

    return rlt_iceberg

# 맨 처음 빙산 상태 판별 함수
def firstSearchIceberg():
    for r in range(N):
        for c in range(M):
            if ocean[r][c]:
                rlt = searchIceberg(r, c)
                return rlt

# 빙산 녹이는 함수
def melt(iceberg_to_melt):
    new_iceberg = []

    for i in range(len(iceberg_to_melt)):
        r, c, cnt = iceberg_to_melt[i]
        ocean[r][c] = max(0, ocean[r][c]-cnt)

        if ocean[r][c]:
            new_iceberg.append((r, c, cnt))
    
    return new_iceberg

N, M = map(int, input().split())

ocean = [list(map(int, input().split())) for _ in range(N)]

time = 0
iceberg = firstSearchIceberg()

is_not_separated = 1

# 분리될 때까지 빙산 녹이기
while is_not_separated:
    iceberg_after_melt = melt(iceberg)
    time += 1

    if len(iceberg_after_melt):
        iceberg = searchIceberg(iceberg_after_melt[0][0], iceberg_after_melt[0][1])

    # 빙산이 다 녹았거나, 하나로 이어진 빙산의 크기가 빙산 전체의 크기와 같지 않으면
    if len(iceberg_after_melt) == 0 or len(iceberg) != len(iceberg_after_melt):
        # 분리
        is_not_separated = 0
        # 다 녹으면 시간 0
        if len(iceberg_after_melt) == 0:
            time = 0

print(time)