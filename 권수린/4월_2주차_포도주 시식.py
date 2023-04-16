def drink(wines):
    # 각 순서에 마실 수 있는 최대 포도주 값을 담을 리스트
    DP = [0] * N

    if N == 1:
        return wines[0]
    elif N == 2:
        return wines[0] + wines[1]
    else:
        DP[0] = wines[0]
        DP[1] = wines[0] + wines[1]
        DP[2] = max(DP[1], wines[0]+wines[2], wines[1]+wines[2])

        for i in range(3, N):
            # 현재 순서 포도주를 마시지 않는 경우, 
            # 현재 순서와 이전 순서 포도주를 마시는 경우, 
            # 현재 순서와 전전 순서 포도주를 마시는 경우 중 가장 큰 값을 저장
            DP[i] = max(DP[i-1], DP[i-3]+wines[i-1]+wines[i], DP[i-2]+wines[i])
    
    return DP[-1]

N = int(input())
wines = []

for _ in range(N):
    wines.append(int(input()))

print(drink(wines))