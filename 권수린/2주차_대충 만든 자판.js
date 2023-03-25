function solution(keymap, targets) {
  var answer = [];
  const MAX_COUNT = 101
  let min_count = {'A': MAX_COUNT, 'B': MAX_COUNT, 'C': MAX_COUNT, 'D': MAX_COUNT, 'E': MAX_COUNT, 'F': MAX_COUNT, 'G': MAX_COUNT, 'H': MAX_COUNT, 'I': MAX_COUNT, 'J': MAX_COUNT, 'K': MAX_COUNT, 'L': MAX_COUNT, 'M': MAX_COUNT, 'N': MAX_COUNT, 'O': MAX_COUNT, 'P': MAX_COUNT, 'Q': MAX_COUNT, 'R': MAX_COUNT, 'S': MAX_COUNT, 'T': MAX_COUNT, 'U': MAX_COUNT, 'V': MAX_COUNT, 'W': MAX_COUNT, 'X': MAX_COUNT, 'Y': MAX_COUNT, 'Z': MAX_COUNT}
  
  // 각 알파벳 별 최소 누르는 횟수 찾기
  for (let i = 0; i < keymap.length; i += 1) {
      for (let count = 0; count < keymap[i].length; count += 1) {
          if (count + 1 < min_count[keymap[i][count]]) {
              min_count[keymap[i][count]] = count + 1
          }
      }
  }
  
  // 각 타겟 별 최소 횟수 구하기
  for (let i = 0; i < targets.length; i += 1) {
      let count = 0
      let can_make = true
      for (let j = 0; j < targets[i].length; j += 1) {
          if (min_count[targets[i][j]] !== MAX_COUNT) {
              count += min_count[targets[i][j]]
          } else {
              can_make = false
              break
          }
      }
      
      // 만들 수 있는지 판별
      if (can_make) {
          answer.push(count)
      } else {
          answer.push(-1)
      }
  }
  
  return answer;
}