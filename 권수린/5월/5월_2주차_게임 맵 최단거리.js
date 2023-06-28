function bfs(map) {
  const v = Array.from(Array(map.length), () => Array(map[0].length).fill(0))
  const q = [[0, 0]]
  const dr = [0, 1, 0, -1]
  const dc = [1, 0, -1, 0]
  
  // 방문 처리
  v[0][0] = 1
  
  while (q.length) {
      const [cr, cc] = q.shift()
      
      // 도착했으면 칸 수 반환
      if (cr === map.length - 1 && cc === map[0].length - 1) {
          return v[cr][cc]
      }
      
      for (let d = 0; d < dr.length; d += 1) {
          const [nr, nc] = [cr + dr[d], cc + dc[d]]
          
          if (nr >= 0 && nr < map.length && nc >= 0 && nc < map[0].length && !v[nr][nc] && map[nr][nc]) {
              q.push([nr, nc])
              v[nr][nc] = v[cr][cc] + 1
          }
      }
  }
  
  // 도착할 수 없으면 -1 반환
  return -1
}

function solution(maps) {
  const answer = bfs(maps);
  return answer;
}