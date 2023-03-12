function solution(n) {
  var answer = 0;
  let numbers = new Array(n+1).fill(1)
  
  // 에라토스테네스 체
  for (let i = 2; i < parseInt(n ** 0.5) + 2; i += 1) {
      if (numbers[i]) {
          
          for (let j = i + i; j < n + 1; j += i) {
              numbers[j] = 0
          }
      }
  }
  
  answer = numbers.reduce((a, c) => a + c, -2)
  
  return answer;
}