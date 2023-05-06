function solution(want, number, discount) {
  const DISCOUNT_DATE = 10
  let answer = 0;

  // 각 상품의 인덱스를 담을 객체, 각 상품의 할인 개수를 담을 배열
  const wantIndex = {}
  const matchingNumber = Array(want.length).fill(0)
  
  // 상품 인덱스 저장
  for (let i = 0; i < want.length; i += 1) {
      wantIndex[want[i]] = i
  }
  
  // 0일 부터 할인 날짜까지 할인 상품 개수 각각 저장
  for (let i = 0; i < DISCOUNT_DATE; i += 1) {
      const index = wantIndex[discount[i]]
      
      if (index !== undefined) {
          matchingNumber[index] += 1
      }
  }
  
  let start = 0
  let end = DISCOUNT_DATE - 1
  
  // 회원 가입할 수 있는 날 계산
  while (end < discount.length) {
      // 원하는 상품의 원하는 할인 개수가 같은지 검사
      const match = matchingNumber.reduce((a, c, i) => {
          if (c === number[i]) {
              return a + 1
          } else {
              return a
          }
      }, 0)
      
      // 같으면 회원 가입할 수 있음
      if (match === want.length) {
          answer += 1
      }
      
      // 다음 날로 넘어가기
      matchingNumber[wantIndex[discount[start]]] -= 1
      start += 1
      end += 1
      if (end < discount.length) {
          matchingNumber[wantIndex[discount[end]]] += 1
      }
  }
  
  return answer;
}