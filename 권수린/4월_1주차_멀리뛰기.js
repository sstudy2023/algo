function solution(n) {
  let DP = new Array(n+3).fill(0)
  
  DP[0] = 1
  
  for (let i = 0; i < n; i += 1) {
      // 오버플로우 방지
      DP[i] %= 1234567
      
      DP[i + 1] += DP[i]
      DP[i + 2] += DP[i]
  }
  
  return DP[n] % 1234567;
}