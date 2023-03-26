function solution(park, routes) {
  var answer = [];
  const directions = {'N': [-1, 0], 'S': [1, 0], 'W': [0, -1], 'E': [0, 1]}
  
  // 시작 위치 저장
  for (let r = 0; r < park.length; r += 1) {
      for (let c = 0; c < park[0].length; c += 1) {
          if (park[r][c] === 'S') {
              answer = [r, c]
              break
          }
      }
      
      if (answer.length > 0) {
          break
      }
  }
  
  // 명령 수행
  for (let value of routes) {
      const [dir, dis] = value.split(' ')
      const [dr, dc] = directions[dir]
      
      // 공원 벗어나는지 확인
      const width = answer[1] + dc * dis
      const height = answer[0] + dr * dis
      if (width >= park[0].length || width < 0 || height >= park.length || height < 0) {
          continue
      }
      
      // 장애물 만나는지 확인
      let obstacleExist = 0
      for (let p = 1; p <= dis; p += 1) {
          const nr = answer[0] + dr * p
          const nc = answer[1] + dc * p
          
          if (park[nr][nc] === 'X') {
              obstacleExist = 1
              break
          }
      }
      
      if (!obstacleExist) {
          answer = [height, width]
      }
  }

  return answer;
}