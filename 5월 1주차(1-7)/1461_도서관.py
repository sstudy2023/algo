# 원래 자리로 책을 가져다 놓는 함수
def bookReturn(coords, book_num, turn):
    coords.sort(reverse=True)
    # 먼저 가져다놓는 곳은 나중에 0번으로 돌아와야 하기 때문에
    # 0번째 좌표를 한 번 더 더해준다.
    rlt = coords[0] if turn == 'first' else 0

    for i in range(len(coords)):
        if i % book_num == 0 and i != 0:
            rlt += 2 * coords[i]
        elif i == 0:
            rlt += coords[i]

    return rlt

# 책 개수, 한 번에 들 수 있는 책 개수
N, M = map(int, input().split())

coords = list(map(int, input().split()))
minus_coords = []
plus_coords = []
rlt = 0

# 음수 좌표와 양수 좌표 분리
for coord in coords:
    if coord > 0:
        plus_coords.append(coord)
    else:
        minus_coords.append(-coord)

# 양수, 음수 중 좌표가 있는 쪽만 가져다 놓음
if minus_coords and not plus_coords:
    rlt += bookReturn(minus_coords, M, 'last')
elif not minus_coords and plus_coords:
    rlt += bookReturn(plus_coords, M, 'last')

# 양수, 음수 둘 다 있다면 둘 중 거리가 가장 먼 좌표가 있는 곳을 나중에 가져다 놓고
# 나머지 책을 먼저 가져다 놓음
elif max(minus_coords) >= max(plus_coords):
    rlt += bookReturn(plus_coords, M, 'first') + bookReturn(minus_coords, M, 'last')
else:
    rlt += bookReturn(minus_coords, M, 'first') + bookReturn(plus_coords, M, 'last')

print(rlt)