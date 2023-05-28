function solution(n) {
    let answer = ''
    const ref = {0: '4', 1: '1', 2: '2'}
    
    while (n > 0) {
        answer = ref[n % 3] + answer
        
        // 나머지가 0일 때는 n - 1의 124 숫자와 같은 패턴
        if (n % 3 === 0 ) {
            n -= 1
        }
        
        n = parseInt(n / 3)
    }
    
    return answer
}