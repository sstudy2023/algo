function solution(n, t, m, p) {
  let answer = '';
  let gameString = '001'
  
  // 총 문자 구하기
  let curNum = 2
  while (gameString.length < t * m) {
      gameString += curNum.toString(n)
      curNum += 1
  }
  
  // t개 만큼 튜브가 말해야 하는 숫자 차례대로 구하기
  let i = p
  while (answer.length < t) {
      answer += gameString[p]
      p += m
  }
  
  return answer.toUpperCase();
}