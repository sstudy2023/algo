N = int(input())
R = int(input())
recommended = list(map(int, input().split()))

recommended_num = [0] * 101
time = [0] * 101
frame = set()

for ri in range(len(recommended)):
    cs = recommended[ri]

    # 이미 추천이 되어 있을 시 추천수 증가
    if cs in frame:
        recommended_num[cs] += 1
        continue

    if len(frame) < N:
        frame.add(cs)
        recommended_num[cs] += 1
    else:
        # 틀이 꽉 찼을 때
        ps_list = list(frame)
        target = ps_list[0]

        # 가장 추천이 적은 학생 찾기
        for ps in ps_list:
            if recommended_num[ps] < recommended_num[target]:
                target = ps
            elif recommended_num[ps] == recommended_num[target] and time[ps] < time[target]:
                target = ps
        
        # 기존 학셍 제거 하고 새 학생 넣기
        recommended_num[target] = 0
        frame.discard(target)
        time[target] = 0

        frame.add(cs)
        recommended_num[cs] += 1
    
    time[cs] = ri

frame = list(frame)
frame.sort()
for i in range(len(frame)):
    print(frame[i], end=' ')