function solution(people, limit) {
  var answer = 0;
  
  // 정렬
  people.sort((a, b) => a - b)
  
  let [i, j] = [0, people.length - 1]
  
  while(i <= j) {
      if (people[i] + people[j] <= limit) {
          i += 1
          j -= 1
      } else {
          j -= 1
      }
      
      answer += 1
  }
  
  return answer;
}