'''
투 포인터, 구간합
'''

N, M = map(int, input().split())

nums = list(map(int, input().split()))

if N == 1:
    # N이 1일 때는 따로 처리해줘야 인덱스 에러 나지 않음
    if nums[0] == M:
        print(1)
    else:
        print(0)
else:
    # 두 개의 포인터 두고 움직여가며 검사
    p1 = p2 = 0
    total = nums[p1]
    cnt = 0

    while p2 < N:
        # 구간의 합이 목적한 값과 같으면 세어주고 두 포인터 모두 증가
        if total == M:
            cnt += 1
            total -= nums[p1]
            p1 += 1
            p2 += 1

            # 인덱스 에러 방지
            if p2 < N:
                total += nums[p2]

        # 구간합이 목적한 값보다 작으면 숫자를 하나 더 더해줘야하므로 뒤의 포인터만 증가
        elif total < M:
            p2 += 1

            if p2 < N:
                total += nums[p2]
        
        # 구간합이 목적한 값보다 크면 숫자를 하나 빼줘야하므로 앞의 포인터만 증가
        # 두 포인터가 같은 지점에 있으면 뒤 포인터도 증가
        else:
            total -= nums[p1]
            
            if p1 == p2:
                p2 += 1

                if p2 < N:
                    total += nums[p2]
            p1 += 1

    print(cnt)