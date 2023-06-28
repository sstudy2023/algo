/**
 * DFS로 길찾는 함수
 * @param {number} cr 현재 행
 * @param {number} cc 현재 열
 * @param {number} cd 원래 자리에서 지금 자리까지의 거리
 * @returns 
 */
function findWay(cr, cc, cd) {
  if (cr === 0 && cc === C - 1 && cd === K) {
    rlt += 1
  } else if (cd >= K) {
    return
  } else {
    for (let d = 0; d < dirs.length; d += 1) {
      const [nr, nc] = [cr + dirs[d][0], cc + dirs[d][1]]
      if (0 <= nr && nr < R && 0 <= nc && nc < C && !v[nr][nc] && map[nr][nc] != 'T') {
        v[nr][nc] = 1
        findWay(nr, nc, cd + 1)
        v[nr][nc] = 0
      }
    }
  }
}

// 입력
const input = require('fs').readFileSync('/dev/stdin').toString().split('\n')
const [R, C, K] = input[0].trim().split(' ').map(v => Number(v))
const map = []
for (let i = 1; i < input.length; i += 1) {
  map.push(input[i].trim())
}

// 방향 배열, 방문 배열
const dirs = [[0, 1], [0, -1], [1, 0], [-1, 0]]
const v = Array.from(new Array(R), v => new Array(C).fill(0))
v[R - 1][0] = 1

let rlt = 0

findWay(R - 1, 0, 1)

console.log(rlt)