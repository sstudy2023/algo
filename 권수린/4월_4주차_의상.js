function solution(clothes) {
    let answer = 1;
    
    let clothesObj = {}
    
    // 의상 종류 별 개수 구하기
    for (const clothData of clothes) {
        const [cloth, kind] = clothData
        if (clothesObj[kind]) {
            clothesObj[kind] += 1
        } else {
            clothesObj[kind] = 1
        }
    }
    
    const clothesList = Object.values(clothesObj)
    
    // 각 의상 종류를 선택 or 선택하지 않는 경우 고려해 개수 구하기
    answer = clothesList.reduce((a, c) => a * (c + 1), 1) - 1
    
    return answer;
}