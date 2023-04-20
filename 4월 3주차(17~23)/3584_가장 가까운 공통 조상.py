T = int(input())

for _ in range(T):
    N = int(input())

    tree = [0] * (N + 1)

    for __ in range(N-1):
        parent, child = map(int, input().split())
        tree[child] = parent
    
    node1, node2 = map(int, input().split())
    v = [0] * (N + 1)
    
    # node1부터 부모들 방문
    v[node1] = 1
    child = node1

    # 부모가 존재하면 방문하고 방문처리
    while tree[child]:
        v[tree[child]] = 1
        child = tree[child]
    
    # node1의 부모 중 node2가 있으면 출력
    if v[node2]:
        print(node2)
        continue

    # 없으면 node2 기준으로 부모 방문
    child = node2

    while tree[child]:
        # 이미 부모를 방문 했으면 그 노드가 가장 가까운 공통 조상
        if v[tree[child]]:
            rlt = tree[child]
            break
        child = tree[child]
    
    print(rlt)