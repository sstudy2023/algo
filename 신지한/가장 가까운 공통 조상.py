from sys import stdin


input = stdin.readline

# https://www.acmicpc.net/problem/3584

def find_parents(parents, n):
    result = [n]
    while parents[n]:
        result.append(parents[n])
        n = parents[n]

    return result

T = int(input().strip())
for _ in range(T):
    N = int(input().strip())
    parents = [0] * (N+1)
    for _ in range(N-1):
        a, b = map(int, input().split())
        parents[b] = a

    x, y = map(int, input().split())
    px = find_parents(parents, x)
    py = find_parents(parents, y)

    i,j = 0,0
    if len(px) > len(py):
        i += len(px) - len(py)
    elif len(px) < len(py):
        j += len(py) - len(px)

    while px[i] != py[j]:
        i += 1
        j += 1

    print(px[i])