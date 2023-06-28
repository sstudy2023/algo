// 카드 개수를 세는 함수
function countCardNum(cards, start, v) {
    v[start] = 1
    let nextBoxNum = cards[start] - 1
    let cnt = 1
    
    while (!v[nextBoxNum]) {
        cnt += 1
        v[nextBoxNum] = 1
        nextBoxNum = cards[nextBoxNum] - 1
    }
    
    return [cnt, v]
}

// start번째 상자를 열었을 때 구할 수 있는 최고 점수를 구하는 함수
function calScore(cards, start, maxScore) {
    // 1번 상자 그룹에 속한 상자 개수를 구하고 해당 상자들을 열어놓음
    const [firstNum, v] = countCardNum(cards, start, new Array(cards.length).fill(0))
    
    // 1번 상자 그룹에 속한 상자 개수와 나머지 상자 개수를 구해도 최고점수를 넘을 수 없으면 멈춤
    if (firstNum * (cards.length - firstNum) <= maxScore) {
        return maxScore
    }
    
    // 2번 상자 그룹에 속한 상자 개수 구하기
    for (let i = 0; i < cards.length; i += 1) {
        if (!v[i]) {
            const [secondNum, visited] = countCardNum(cards, i, v)
            maxScore = Math.max(maxScore, firstNum * secondNum)
        }
    }
    
    return maxScore
}

function solution(cards) {
    let maxScore = 0
    
    for (let i = 0; i < cards.length; i += 1) {
        maxScore = calScore(cards, i, maxScore)
    }
    
    return maxScore
}