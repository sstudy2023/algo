function solution(land) {
  const DP = Array.from(Array(land.length), () => Array(4).fill(0))
  
  DP[0] = land[0]
  
  for (let i = 1; i < land.length; i += 1) {
      const [first, second, third, fourth] = DP[i - 1]
      // 각 칸에서 가질 수 있는 최대 점수는 해당 칸의 점수 + 이전 행에서 같은 칸을 제외한 칸들 중 가장 높은 값을 더한 점수
      DP[i][0] = land[i][0] + Math.max(second, third, fourth)
      DP[i][1] = land[i][1] + Math.max(first, third, fourth)
      DP[i][2] = land[i][2] + Math.max(first, second, fourth)
      DP[i][3] = land[i][3] + Math.max(first, second, third)
  }
  
  const answer = Math.max(...DP[land.length - 1])

  return answer;
}