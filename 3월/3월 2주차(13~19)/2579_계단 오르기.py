stair_num = int(input())

if stair_num == 1:
    print(int(input()))
elif stair_num == 2:
    print(int(input()) + int(input()))
else:
    stairs = []
    dp = [0] * stair_num

    for _ in range(stair_num):
        stairs.append(int(input()))

    dp[0] = stairs[0]
    dp[1] = stairs[0] + stairs[1]
    dp[2] = max(stairs[0], stairs[1]) + stairs[2]

    for i in range(3, stair_num):
        dp[i] = max(stairs[i]+dp[i-2], stairs[i]+stairs[i-1]+dp[i-3])

    print(dp[-1])