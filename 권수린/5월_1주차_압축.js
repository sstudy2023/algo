function solution(msg) {
  const answer = [];
  const dictionary = {}
  let lastIndex = 26
  const alpha = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
  
  // 사전 초기화
  for (let i = 0; i < alpha.length; i += 1) {
      dictionary[alpha[i]] = i + 1
  }
  
  let i = 0
  
  // 압축
  while (i < msg.length) {
      let word = msg[i]
      let index
      
      // 색인 찾기
      while (i < msg.length && dictionary[word]) {
          index = dictionary[word]
          i += 1
          
          if (i < msg.length) {
              word += msg[i]
          }
      
      }
      
      // 색인 출력
      answer.push(index)
      
      // 단어 추가
      dictionary[word] = lastIndex + 1
      lastIndex += 1
  }
  
  return answer;
}