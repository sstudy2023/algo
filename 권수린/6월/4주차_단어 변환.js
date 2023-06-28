/**
 * 타겟 글자를 만드는 데 최소 몇 번이 걸리는지 계산하는 함수
 * @param {string} begin 시작 글자
 * @param {string} target 타겟 글자
 * @param {string[]} words 글자 배열
 * @returns 최소 횟수
 */
function makeTarget(begin, target, words){
    // 타겟 글자가 글자 배열에 없으면 0 반환
    if (words.indexOf(target) === -1) {
        return 0
    }
    
    // 글자 배열에 시작 글자 추가
    words.unshift(begin)
    const q = [[begin, 0]]  // 현재 글자가 몇 번째 글자인지 알아야 하므로 인덱스도 함께 넣음
    const v = new Array(words.length).fill(0)
    v[0] = 1
    let f = 0
    
    while (f < q.length) {
        const [cw, ci] = q[f]
        f += 1
        
        // 현재 글자가 타겟 글자면 횟수 반환
        if (cw === target) {
            return v[ci] - 1
        }
        
        for (let i = 1; i < words.length; i += 1) {
            if (!v[i]) {
                const nw = words[i]
                let dif = 0
                
                // 몇 글자 차이 나는지 검사
                for (let j = 0; j < nw.length; j += 1) {
                    if (nw[j] !== cw[j]) {
                        dif += 1
                    }
                }
                
                if (dif < 2) {
                    q.push([nw, i])
                    v[i] = v[ci] + 1
                }
            }
        }
    }
    
    return 0
}

function solution(begin, target, words) {
    const answer = makeTarget(begin, target, words)
    
    return answer
}