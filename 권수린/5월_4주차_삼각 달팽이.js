function solution(n) {
    const answer = []
    let i = 1
    
    // 달팽이 껍데기 만들기
    const triangle = Array.from(Array(n), v => {
        i += 1
        return Array(i - 1).fill(0)
    })
    
    let [r, c] = [0, 0]
    let num = 1
    
    // 달팽이 채우기, 한 변씩 돌아가면서 채우고 그것을 반복
    while (n > 0) {
        for (let i = 0; i < n; i += 1) {
            triangle[r][c] = num
            num += 1
            r += 1
        }
        
        r -= 1
        n -= 1
        
        for (let i = 0; i < n; i += 1) {
            c += 1
            triangle[r][c] = num
            num += 1
        }
        
        n -= 1
        
        for (let i = 0; i < n; i += 1) {
            c -= 1
            r -= 1
            triangle[r][c] = num
            num += 1
        }
        
        r += 1
        n -= 1
    }
    
    // 삼각형 정답 배열에 옮겨 담기
    for (let i = 0; i < triangle.length; i += 1) {
        for (let j = 0; j < triangle[i].length; j += 1) {
            answer.push(triangle[i][j])
        }
    }
    
    return answer
}