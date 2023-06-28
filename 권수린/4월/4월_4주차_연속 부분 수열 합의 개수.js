function solution(elements) {    
  let numberSet = new Set()
  
  for (let gap = 1; gap < elements.length; gap += 1) {
      let i = 0
      let j = i + gap
      let sum = elements.reduce((a, c, idx) => {
          if (idx >= i && idx < j) {
              return a + c
          } else {
              return a
          }
      }, 0)
      
      while (i < elements.length) {
          numberSet.add(sum)
          
          sum -= elements[i]
          sum += elements[j]

          i += 1
          j = (j + 1) % elements.length
      }
  }
  
  numberSet.add(elements.reduce((a, c) => a + c, 0))
  
  return numberSet.size;
}