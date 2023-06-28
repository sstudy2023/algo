function solution(cards1, cards2, goal) {
  var answer = 'Yes';
  
  let one_index = 0
  let two_index = 0
  
  // 인덱스 비교(서로 다른 단어들 만 있으므로 가능)
  for (let i = 0; i < goal.length; i += 1) {
      if (goal[i] === cards1[one_index]) {
          one_index += 1
      } else if (goal[i] === cards2[two_index]) {
          two_index += 1
      } else {
          return 'No'
      }
  }
  
  return answer;
}