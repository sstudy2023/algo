function solution(board) {
    const dr = [0, 1, 0, -1]
    const dc = [1, 0, -1, 0]

    const N = board.length
    const INF = 1000000000
    const q = []
    let f = 0
    
    // 초기값 설정
    const v = Array.from(new Array(N), v => Array.from(new Array(N), v => new Array(4).fill(INF)))
    v[0][0] = [0, 0, 0, 0]
    
    // 맨 첫 번째 도로 건설
    if (!board[0][1]) {
        q.push([0, 1, 0])
        v[0][1][0] = 100
    }
    
    if (!board[1][0]) {
        q.push([1, 0, 1])
        v[1][0][1] = 100
    }
    
    while (f < q.length) {
        const [cr, cc, cd] = q[f]
        f += 1
        
        for (let nd = 0; nd < dr.length; nd += 1) {
            const [nr, nc] = [cr + dr[nd], cc + dc[nd]]
            
            if (0 <= nr && nr < N && 0 <= nc && nc < N && !board[nr][nc]) {
                // 직선일 때
                if (cd === nd) {
                    if (v[nr][nc][nd] >= v[cr][cc][cd] + 100) {
                        q.push([nr, nc, nd])
                        v[nr][nc][nd] = v[cr][cc][cd] + 100
                    }
                }
                
                // 뒤돌아 가지 않으면서 코너일 때
                else if (nd !== (cd + 2) % dr.length) {
                    if (v[nr][nc][nd] >= v[cr][cc][cd] + 600) {
                        q.push([nr, nc, nd])
                        v[nr][nc][nd] = v[cr][cc][cd] + 600
                    }
                }
            }
        }
    }
    
    return Math.min(...v[N - 1][N - 1])
}