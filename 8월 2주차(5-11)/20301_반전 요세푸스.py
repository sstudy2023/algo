from collections import deque

N, K, M = map(int, input().split())
people = deque([i + 1 for i in range(N)])   # 사람 덱
answer = [K]    # 답 배열
cur_index = K - 1   # 현재 인덱스
people.remove(K)    # 답 배열에 시작하는 사람 번호를 넣고 사람 덱에서 제거
direction = True    # 도는 방향
dir_cnt = 1     # 한 방향으로 진행한 횟수

while len(answer) < N:
    if dir_cnt == M:
        direction = not direction
        dir_cnt = 0
    
    # 정방향일 때
    if direction:
        cur_index = (cur_index - 1 + K) % len(people)
    
    # 역방향일 때
    else :
        for _ in range(K):
            cur_index = (cur_index + len(people) - 1) % len(people)
    
    answer.append(people[cur_index])
    people.remove(people[cur_index])
    dir_cnt += 1

for num in answer:
    print(num)