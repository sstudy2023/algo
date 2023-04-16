from sys import stdin

input = stdin.readline
# https://www.acmicpc.net/problem/20164

N = input().strip()
minn = int(1e9)
maxx = 0
# 각 자리수에서 홀수 개수 찾기
def count_odd(string):
    cnt = 0
    for s in string:
        if int(s) % 2 != 0:
            cnt += 1
    return cnt

def dfs(num, count):
    global minn, maxx
    count += count_odd(num)

    if len(num) == 1:
        minn = min(minn, count)
        maxx = max(maxx, count)
        return
    
    elif len(num) == 2:
        temp = str(int(num[0]) + int(num[1]))
        dfs(temp, count)
    # 3개로 나누는 2개의 인덱스(i,j)를 모두 시도해보기
    else:
        for i in range(2, len(num)):
            for j in range(1, i):
                temp = str(int(num[:j]) + int(num[j:i]) + int(num[i:]))
                dfs(temp, count)

dfs(N, 0)
print(minn, maxx)