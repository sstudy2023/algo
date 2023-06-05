// 원하는 대로 카드를 섞을 수 있는지 확인하는 함수
function check(cards, P) {
  for (let i = 0; i < P.length; i += 1) {
    const whichPlayer = P[cards[i]]
    if (i % 3 !== whichPlayer) {
      return false
    }
  }

  return true
}

const [number, list1, list2] = require('fs').readFileSync('/dev/stdin').toString().split('\n')
const N = Number(number)
const P = Array.from(list1.split(' '), v => Number(v))
const S = Array.from(list2.split(' '), v => Number(v))
let cards = [...new Array(N)].map((v, i) => i)
let cnt = 0
let rlt = -1

while (true) {
  // 카드 순서 확인
  if (check(cards, P)) {
    rlt = cnt
    break
  }

  // 카드 섞기
  const newCards = Array(N).fill(0)
  for (let i = 0; i < S.length; i += 1) {
    newCards[S[i]] = cards[i]
  }

  cnt += 1
  cards = [...newCards]

  // 어떻게 해도 카드를 원하는 대로 섞을 수 없다면 멈춤
  if (cards.every((v, idx) => v === idx)) {
    break
  }
}

console.log(rlt)