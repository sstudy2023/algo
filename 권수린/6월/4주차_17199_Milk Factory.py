from collections import deque

def visit(i: int):
    """ i(출발점)으로부터 갈 수 있는 지점을 찾는 함수
    Parameters
    ----------
    i
        출발하는 지점
    """
    q = deque()
    q.append(i)
    visited = [0] * (N + 1)
    visited[i] = 1

    while q:
        ci = q.popleft()

        for d in range(len(connected[ci])):
            ni = connected[ci][d]

            # 방문한 적이 없으면 방문하고, 방문 표시하고
            # 해당 지점으로 올 수 있는 출발점에 i 저장
            if not visited[ni]:
                q.append(ni)
                visited[ni] = 1
                can_visit[ni].add(i)

N = int(input())
connected = [[] for _ in range(N + 1)]
can_visit = [set() for _ in range(N + 1)]

# 연결 리스트
for _ in range(N - 1):
    a, b = map(int, input().split())
    connected[a].append(b)

# 1번 부터 방문
for i in range(1, N + 1):
    visit(i)

exist = 0

for i in range(1, N + 1):
    if len(can_visit[i]) == N - 1:
        print(i)
        exist = 1
        break

if not exist:
    print(-1)