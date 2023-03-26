function solution(wallpaper) {
  // 최소값 설정
  var answer = [wallpaper.length, wallpaper[0].length, -1, -1];
  
  for (let r = 0; r < wallpaper.length; r += 1) {
      for (let c = 0; c < wallpaper[0].length; c += 1) {
          if (wallpaper[r][c] === '#') {
              answer[0] = Math.min(answer[0], r)
              answer[1] = Math.min(answer[1], c)
              answer[2] = Math.max(answer[2], r)
              answer[3] = Math.max(answer[3], c)
          }
      }
  }
  
  answer[2] += 1
  answer[3] += 1
  
  return answer;
}