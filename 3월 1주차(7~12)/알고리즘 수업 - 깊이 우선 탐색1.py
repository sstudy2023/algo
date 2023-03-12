# dfs 함수
def dfs(start):
    
    # 스택 생성
    stack = [start]
    visit_order = 1
    order[start] = visit_order

    while stack:
        cn = stack[-1]

        # 방문한 정점은 뛰어넘기
        while connect[cn] and order[connect[cn][-1]]:
            connect[cn].pop()

        # 방문하지 않았으면 스택에 추가
        if connect[cn]:
            nn = connect[cn].pop()
            visit_order += 1
            order[nn] = visit_order
            stack.append(nn)
        
        # 더 이상 해당 정점에서 방문할 곳이 없으면 스택에서 삭제
        else:
            stack.pop()


# 정점 수, 간선 수, 시작 정점
N, M, R = map(int, input().split())

# 방문 순서
order = [0] * N

# 연결 정보
connect = [[] for _ in range(N)]

# 연결 정보 등록
for _ in range(M):
    u, v = map(int, input().split())

    connect[u - 1].append(v-1)
    connect[v - 1].append(u-1)

# 오름차순으로 방문하므로 정렬(마지막부터 참고하기 위해 내림차순 정렬)
for i in range(N):
    connect[i].sort(reverse=True)

dfs(R-1)

for i in range(N):
    print(order[i])