// 특정 지점에서 다른 지점까지 걸리는 최소 시간을 구하는 함수
function calTime(sr, sc, maps, gr, gc) {
    const dr = [0, 1, 0, -1]
    const dc = [1, 0, -1, 0]
    const q = [[sr, sc]]
    let front = 0
    const v = Array.from(Array(maps.length), v => new Array(maps[0].length).fill(0))
    v[sr][sc] = 1
    
    while (front < q.length) {
        const [cr, cc] = q[front]
        front += 1
        
        if (cr === gr && cc === gc) {
            return v[gr][gc] - 1
        }
        
        for (let d = 0; d < dr.length; d += 1) {
            const [nr, nc] = [cr + dr[d], cc + dc[d]]

            if (nr >= 0 && nr < maps.length && nc >= 0 && nc < maps[0].length && !v[nr][nc] && maps[nr][nc] !== 'X') {
                q.push([nr, nc])
                v[nr][nc] = v[cr][cc] + 1
            }
        }
    }
    
    // 갈 수 없다면 0 반환
    return 0
}

function solution(maps) {
    let answer = 0
    const start = [-1, -1]
    const exit = [-1, -1]
    const lever = [-1, -1]
    
    // 시작점, 출구, 레버의 위치 찾기
    for (let r = 0; r < maps.length; r += 1) {
        for (let c = 0; c < maps[0].length; c += 1) {
            if (maps[r][c] === 'S') {
                [start[0], start[1]] = [r, c]
            } else if (maps[r][c] === 'E') {
                [exit[0], exit[1]] = [r, c]
            } else if (maps[r][c] === 'L') {
                [lever[0], lever[1]] = [r, c]
            }
        }
    }
    
    // 시작점부터 레버까지의 시간
    const startToLever = calTime(start[0], start[1], maps, lever[0], lever[1])
    
    // 레버까지 갈 수 없으면 -1 반환
    if (!startToLever) {
        return -1
    } else {
        answer += startToLever
    }
    
    // 레버부터 출구까지의 시간
    const leverToExit = calTime(lever[0], lever[1], maps, exit[0], exit[1])
    
    // 출구까지 갈 수 없으면 -1 반환
    if (!leverToExit) {
        return -1
    } else {
        answer += leverToExit
    }
    
    return answer
}