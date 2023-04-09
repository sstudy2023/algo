from sys import stdin

input = stdin.readline

# https://www.acmicpc.net/problem/12919
S = input().strip()
T = input().strip()

def check(length, current, string):
    global answer
    if length == current:
        if string == S:
            print(1)
            exit(0)
        return
    
    # 바꿀수 없는 경우    
    if string[0] == 'A' and string[-1] == 'B':
        return
    
    # 두 가지 연산 모두 가능
    elif string[0] == 'B' and string[-1] == 'A':
        check(length, current-1, string[:-1])
        add_B = string[1:]
        check(length, current-1, add_B[::-1])

    # A 추가한 연산
    elif string[0] == 'A' and string[-1] == 'A':
        check(length, current-1, string[:-1])

    # B 추가하고 뒤집은 연산
    elif string[0] == 'B' and string[-1] == 'B':
        add_B = string[1:]
        check(length, current-1, add_B[::-1])

if len(S) <= len(T):
    check(len(S), len(T), T)

print(0)