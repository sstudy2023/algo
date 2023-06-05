const [n, input] = require('fs').readFileSync('./test.txt').toString().split('\n')
const P = Array.from(input.split(' '), v => Number(v))
const N = Number(n)
P.unshift(0)
const DP = Array(N + 1).fill(0)

for (let i = 1; i < N + 1; i += 1) {
  let maxPrice = P[i]

  for (let j = 1; j <= i / 2; j += 1) {
    maxPrice = Math.max(maxPrice, DP[j] + DP[i - j])
  }

  // 짝수일 때
  if (i % 2 === 0) {
    maxPrice = Math.max(maxPrice, DP[parseInt(i / 2) * 2])
  }
  DP[i] = maxPrice
}

console.log(DP[N]);