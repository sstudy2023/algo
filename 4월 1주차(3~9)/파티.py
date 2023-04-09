from sys import stdin

input = stdin.readline

# https://www.acmicpc.net/problem/1238
N, M, X = map(int, input().split())

# 단방향 인접리스트 입력
graph = [[] for _ in range(N+1)]
for i in range(M):
    start, end, dist = map(int, input().split())
    graph[start].append((end, dist))

# 시작점에서 각 마을까지 얼마나 걸리는지 min_distance 배열 반환
def daik(start):
    min_distance = [100*M+1] * (N+1)
    visit = [False] * (N+1)
    visit[0] = True
    
    min_distance[start] = 0
    visit[start] = True
    cur = start

    for _ in range(N-1):
        # cur번 근처 노드의 최소비용 갱신
        for num, cost in graph[cur]:
            min_distance[num] = min(min_distance[num], min_distance[cur] + cost)
        
        min_cost = 100*M+1
        # 미방문 노드중에 최소비용인 노드 탐색
        for i in range(1, N+1):
            if not visit[i] and min_distance[i] < min_cost:
                cur = i
                min_cost = min_distance[i]
        visit[cur] = True
    
    return min_distance

spend_time = [0]* (N+1)
# 집에서 X까지 가는 최소거리 더하기
for student in range(1, N+1):
    spend_time[student] = daik(student)[X]

# X에서 집까지 가는 최소거리 더하기
go_home = daik(X)
for idx in range(1, N+1):
    spend_time[idx] += go_home[idx]

print(max(spend_time))