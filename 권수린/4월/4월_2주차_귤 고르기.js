function solution(k, tangerine) {
  var answer = 0;
  
  // 귤 무게에 따른 개수를 셀 객체
  let tangerineNumber = {}
  
  // 귤 무게 별 개수 구하기
  for (const weight of tangerine) {
      if (tangerineNumber[weight]) {
          tangerineNumber[weight] += 1
      } else {
          tangerineNumber[weight] = 1
      }
  }
  
  // 귤 개수 별 내림차순 정렬
  let tangerineList = Object.entries(tangerineNumber).sort((a, b) => b[1] - a[1])
  
  let tangerineCnt = 0
  
  // k개가 될 때까지 더하기
  for (const tangerineData of tangerineList) {
      if (tangerineCnt >= k) {
          break
      } else {
          tangerineCnt += tangerineData[1]
          answer += 1
      }
  }
  
  return answer;
}