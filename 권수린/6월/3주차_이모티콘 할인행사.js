/**
 * 각 이모티콘 할인 비율을 토대로 구매자들의 구매 비용을 구하는 함수
 * @param {number[][]} users 구매자들의 구매 기준을 담은 배열
 * @param {number[]} emoticons 이모티콘의 가격을 담은 배열
 * @param {number[]} discounts 이모티콘 할인 비율을 담은 배열
 * @returns 구독자 수와 매출액을 반환
 */
function calSubAndIncome(users, emoticons, discounts) {
    const userPurchase = new Array(users.length).fill(0)
    let sub = 0
    let income = 0
    
    // 각 유저들의 구매 비용 구하기
    for (let i = 0; i < emoticons.length; i += 1) {
        const discount = discounts[i]
        const emoticon = emoticons[i]
        for (let ui = 0; ui < users.length; ui += 1) {
            const [wantDiscount, cost] = users[ui]
            if (discount >= wantDiscount) {
                userPurchase[ui] += emoticon - (emoticon * (discount / 100))
            }
        }
    }
    
    // 구독자와 매출액 구하기
    for (let i = 0; i < users.length; i += 1) {
        const cost = users[i][1]
        if (userPurchase[i] >= cost) {
            sub += 1
        } else {
            income += userPurchase[i]
        }
    }
    
    return [sub, income]
}

function solution(users, emoticons) {
    const answer = [0, 0]
    
    /**
     * 각 이모티콘의 할인 비율을 구하는 함수
     * @param {number} i i번째 이모티콘
     * @param {number[]} discounts 할인 비율을 담은 배열
     */
    function setDiscount(i, discounts) {
        if (i === emoticons.length) {
            const [subscribers, income] = calSubAndIncome(users, emoticons, discounts)
            if (answer[0] < subscribers) {
                answer[0] = subscribers
                answer[1] = income
            } else if (answer[0] === subscribers && answer[1] < income) {
                answer[1] = income
            }
        } else {
            for (let discount = 40; discount >= 10; discount -= 10) {
                setDiscount(i + 1, [...discounts, discount])
            }
        }
    }
    
    setDiscount(0, [])
    
    return answer
}