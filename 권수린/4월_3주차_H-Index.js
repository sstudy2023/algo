function solution(citations) {
  let answer = 0;
  
  // 버킷 배열
  let numbers = new Array(Math.max(...citations)+1).fill(0)
  
  // 각 인덱스마다 해당 인덱스 이상 인용된 논문 개수 계산
  for (const citation of citations) {
      numbers[citation] += 1
  }
  
  for (let i = numbers.length - 2; i > -1; i -= 1) {
      numbers[i] += numbers[i + 1]
  }
  
  // h인덱스 구하기
  for (let i = 0; i < numbers.length; i += 1) {
      if (numbers[i] >= i) {
          answer = i
      } else {
          break
      }
  }
  
  return answer;
}