function isRightString(s) {
  let stack = []
  const open = ['(', '{', '[']
  const close = {')': '(', '}': '{', ']': '['}
  
  // 여닫는 괄호가 짝에 맞게 나오는지 검사
  for (const letter of s) {
      if (open.includes(letter)) {
          stack.push(letter)
      } else {
          const openLetter = stack.pop()
          if (close[letter] !== openLetter) {
              return 0
          }
      }
  }
  
  // 검사가 다 끝났는데 스택에 괄호가 남아있으면 올바른 괄호 문자가 아니다.
  if (stack.length > 0) {
      return 0
  }
  
  return 1
}

function solution(s) {
  let answer = 0
  
  for (i = 0; i < s.length; i += 1) {
      // 문자 돌려서 올바른 괄호 검사
      const newString =  s.slice(i + 1, s.length) + s.slice(0, i + 1)
      answer += isRightString(newString)
  }
  
  return answer;
}