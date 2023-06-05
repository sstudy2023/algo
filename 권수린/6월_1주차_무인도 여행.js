function solution(maps) {
    const answer = []
    const v = Array.from(Array(maps.length), v => Array(maps[0].length).fill(0))
    
    /**
     * 머무르는 날자 계산하는 함수
     * @param {Array} map 섬이 그려진 지도 
     * @param {number} sr 
     * @param {number} sc 
     * @returns {number}
     */
    function calDates(sr, sc) {
        let dates = Number(maps[sr][sc])
        const dr = [0, -1, 0, 1]
        const dc = [1, 0, -1, 0]
        const q = [[sr, sc]]
        v[sr][sc] = 1

        while (q.length) {
            const [cr, cc] = q.shift()

            for (let d = 0; d < dr.length; d += 1) {
                const [nr, nc] = [cr + dr[d], cc + dc[d]]

                // 연결된 땅이면서 방문하지 않았으면 방문
                if (nr >= 0 && nr < maps.length && nc >= 0 && nc < maps[0].length && maps[nr][nc] !== 'X' && !v[nr][nc]) {
                    q.push([nr, nc])
                    dates += Number(maps[nr][nc])
                    v[nr][nc] = 1
                }
            }
        }

        return dates
    }
    
    // 여태 방문하지 않은 섬이면 날짜 계산
    for (let r = 0; r < maps.length; r += 1) {
        for (let c = 0; c < maps[0].length; c += 1) {
            if (maps[r][c] !== 'X' && !v[r][c]) {
                const days = calDates(r, c)
                answer.push(days)
            }
        }
    }
    
    answer.sort((a, b) => a - b)
    
    if (!answer.length) {
        answer.push(-1)
    }
    
    return answer
}