function solution(s, skip, index) {
  var answer = '';
  const BASE_INDEX = 97
  const BASE_LEN = 26 - skip.length
  let skip_alpha =  ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']
  
  // 스킵 배열에서 스킵되는 문자 제거
  for (let i = 0; i < skip.length; i += 1) {
      const skip_index = skip[i].charCodeAt(0) - BASE_INDEX
      skip_alpha[skip_index] = 0
  }
  
  for (let i = 0; i < BASE_LEN; i += 1) {
      if (!skip_alpha[i]) {
          skip_alpha.splice(i, 1)
          i -= 1
      }
  }

  // 문자 밀고 결과 얻기
  for (let i = 0; i < s.length; i += 1) {
      const new_alpha_index = (skip_alpha.indexOf(s[i]) + index) % BASE_LEN
      answer += skip_alpha[new_alpha_index]
  }
  
  return answer;
}