import sys

N = int(input())
node_connection = [[] for _ in range(N + 1)]

for _ in range(N - 1):
    sn, en = map(int, sys.stdin.readline().split())
    node_connection[sn].append(en)
    node_connection[en].append(sn)

Q = int(input())

for _ in range(Q):
    t, k = map(int, sys.stdin.readline().split())
    if t == 2:
        print('yes')
    else:
        if len(node_connection[k]) > 1:
            print('yes')
        else:
            print('no')