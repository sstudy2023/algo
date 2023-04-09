function solution(arr) {
  var answer = 1;
  // 정렬
  arr.sort((a, b) => a - b)
  
  let devide = 2
  
  // 서로소 될 때까지 나누기
  while (devide <= Math.max(...arr)) {
      let canDevide = 0
      
      for (let j = 0; j < arr.length; j += 1) {
          if (arr[j] % devide === 0) {
              canDevide += 1
          }
      }
      
      if (!canDevide) {
          devide += 1 
      } else {
          // 나눌 수 있는 수가 두 개 이상이면 약수 곱하고 나눠주기
          answer *= devide
          for (let j = 0; j < arr.length; j += 1) {
              if (arr[j] % devide === 0) {
                  arr[j] = parseInt(arr[j]/devide)
              }
          }
      }
  }
  
  // 서로소들 곱하기
  for (let i = 0; i < arr.length; i += 1) {
      answer *= arr[i]
  }
  
  return answer;
}