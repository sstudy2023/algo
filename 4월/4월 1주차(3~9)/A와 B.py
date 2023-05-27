S = input()
T = input()

start = 0
end = len(T) - 1

while abs(end - start) >= len(S):
    last = T[end]

    if end > start:
        end -= 1
    else:
        end += 1

    # 끝이 B였으면 문자열 뒤집기
    if last != 'A':
        start, end = end, start

new_s = ''

if start <= end:
    new_s = T[start:end+1]
else:
    new_s = T[end:start+1][::-1]

if new_s == S:
    print(1)
else:
    print(0)