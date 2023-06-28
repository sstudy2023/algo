// 연결된 송전탑 개수 구하는 함수
function findNumber(s, e, connection, n) {
    const q = [s]
    const v = new Array(n + 1).fill(0)
    v[s] = 1
    let front = 0
    let cnt = 1
    
    while (front !== q.length) {
        const cn = q[front]
        front += 1
        
        for (let d = 0; d < connection[cn].length; d += 1) {
            const nn = connection[cn][d]
            
            // 방문하지 않았으면서 전선을 끊은 송전탑이 아닌 경우
            if (!v[nn] && nn !== e) {
                q.push(nn)
                v[nn] = 1
                cnt += 1
            }
        }
    }
    
    return cnt
}

function solution(n, wires) {
    let answer = n
    
    const connection = Array.from(Array(n + 1), v => new Array(0))
    
    // 연결 관계 저장
    for (const wire of wires) {
        const [v1, v2] = wire
        connection[v1].push(v2)
        connection[v2].push(v1)
    }
    
    
    // 연결을 하나씩 끊어가면서 개수 파악
    for (const wire of wires) {
        const [s, e] = wire
        const transmissionTowerNumber = findNumber(s, e, connection, n)
        
        answer = Math.min(answer, Math.abs(n - transmissionTowerNumber * 2))
    }
    
    return answer
}