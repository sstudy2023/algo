function solution(n, left, right) {
  var answer = [];
  
  for (let i = left; i <= right; i += 1) {
      const row = parseInt(i/n)
      const col = i % n
      
      if (col <= row) {
          answer.push(row+1)
      } else {
          answer.push(row+1+(col-row))
      }
  }
  
  return answer;
}