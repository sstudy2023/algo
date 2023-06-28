direction = {'E': [0, 1], 'W': [0, -1], 'S': [1, 0], 'N': [-1, 0]}

def pullDown(r: int, c: int, fr: int, fc: int, d:str) -> int:
    """도미노를 쓰러트리고 점수를 계산하는 함수

    Parameters
    ----------
    r
        공격할 도미노의 행
    c
        공격할 도미노의 열
    fr
        파악한 공격 범위 끝의 행
    fc
        파악한 공격 범위 끝의 열
    d
        공격 방향
    
    Returns
    -------
    점수
    """
    cnt = 0

    if d == 'E' or d == 'W':
        if d == 'E':
            sr, sc = r, c
            er, ec = fr + 1, fc + 1
        else:
            sr, sc = r, fc
            er, ec = fr + 1, c + 1

        for cr in range(sr, er):
            for cc in range(sc, ec):
                if domino_status[cr][cc] == 'S':
                    cnt += 1
                    domino_status[cr][cc] = 'F'
    else:
        if d == 'S':
            sr, sc = r, c
            er, ec = fr + 1, fc + 1
        else:
            sr, sc = fr, c
            er, ec = r + 1, fc + 1

        for cc in range(sc, ec):
            for cr in range(sr, er):
                if domino_status[cr][cc] == 'S':
                    cnt += 1
                    domino_status[cr][cc] = 'F'

    return cnt

def figureOutArea(r: int, c: int, h: int, d: str) -> tuple:
    """공격 범위를 파악하는 함수

    Parameters
    ----------
    r
        공격할 도미노의 행
    c
        공격할 도미노의 열
    h
        공격할 도미노의 높이
    d
        공격 방향
    
    Returns
    -------
    파악한 공격 범위 끝의 행과 열
    """
    fr, fc = r, c
    dr, dc = direction[d]

    for p in range(h - 1, 0, -1):
        cr, cc = r + p * dr, c + p * dc
        
        # 현재 도미노가 이후 다른 도미노를 더 쓰러트릴 수 있으면
        if 0 <= cr < N and 0 <= cc < M and domino_status[cr][cc] == 'S':
            ch = domino_board[cr][cc]
            nr, nc = figureOutArea(cr, cc, ch, d)

            if d == 'E' or d == 'S':
                fr = max(fr, nr)
                fc = max(fc, nc)
            else:
                fr = min(fr, nr)
                fc = min(fc, nc)

    return fr, fc

N, M, R = map(int, input().split())
domino_board = [list(map(int, input().split())) for _ in range(N)]
domino_status = [['S'] * M for _ in range(N)]
score = 0

for _ in range(R):
    # 공격
    ar, ac, ad = input().split()
    ar = int(ar) - 1
    ac = int(ac) - 1

    if domino_status[ar][ac] == 'S':
        # 공격 범위 파악
        fr, fc = figureOutArea(ar, ac, domino_board[ar][ac], ad)
        
        # 쓰러트리기
        score += pullDown(ar, ac, fr, fc, ad)
    
    # 수비
    dr, dc = map(int, input().split())
    dr -= 1
    dc -= 1
    domino_status[dr][dc] = 'S'

print(score)
for r in range(N):
    for c in range(M):
        print(domino_status[r][c], end=' ')
    
    print()