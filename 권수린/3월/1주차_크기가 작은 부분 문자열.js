function solution(t, p) {
  var answer = 0;
  const numberP = p
  
  t = String(t)
  p = String(p)
  
  for (let i = 0; i < t.length - p.length + 1; i += 1) {
      const num = parseInt(t.slice(i, i + p.length))
      
      if (numberP >= num) {
          answer += 1
      }
  }
  return answer;
}