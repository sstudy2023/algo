from sys import stdin

input = stdin.readline

# https://www.acmicpc.net/problem/1976
# 자기자신이 부모가 아니라면 최댓값의 부모를 찾는 함수
def find(x):
    if x != parents[x]:
        parents[x] = find(parents[x])
    return parents[x]
# 연결된 두 지점을 받아 크기를 비교해서 큰 수의 부모를 최종부모로 설정
def union(x,y):
    x = find(x)
    y = find(y)
    if x > y:
        parents[y] = x
    else:
        parents[x] = y

N = int(input().strip())
M = int(input().strip())
parents = [i for i in range(N+1)]

for r in range(N):
    temp = list(map(int, input().split()))
    for c in range(N):
        if temp[c] == 1:
            union(r+1, c+1)

for i in range(1,N+1):
    find(i)

travel_list = list(map(int,input().split()))
answer = True
start = parents[travel_list[0]]

# 여행지목록이 모두 같은 그룹이면 모든 곳을 돌아다닐수 있음
for i in range(1, M):
    if parents[travel_list[i]] != start:
        answer = False
        break

if answer:
    print('YES')
else:
    print('NO')