// 1 개수 구하는 함수
function countingOne(number) {
  let cnt = 0
  
  while (number > 0) {
      cnt += number % 2
      
      number = parseInt(number/2)
  }

  return cnt
}

function solution(n) {
  const oneCnt = countingOne(n)
  let compareNumber = n + 1
  
  // 1 개수를 비교해가며 가장 처음 나오는 1의 개수가 같은 수를 반환
  while (true) {
      if (oneCnt === countingOne(compareNumber)) {
          return compareNumber
      }
      
      compareNumber += 1
  }
}