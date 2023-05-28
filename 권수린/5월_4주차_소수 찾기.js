// 에라토스테네스의 체로 소수 검사하는 함수
function isPrime(num) {
    const numbers = Array(num + 1).fill(1)
    numbers[0] = 0
    numbers[1] = 0
    
    for (let i = 2; i <= parseInt(Math.pow(num, 0.5)); i += 1) {
        if (numbers[i]) {
            for (let j = i * 2; j <= num; j += i) {
                numbers[j] = 0
            }
        }
    }
    
    return numbers[num]
}

function solution(numbers) {
    let answer = 0
    const checked = Array(numbers.length).fill(0)
    let possibleNumbers = new Set()
    
    // 순열 만드는 함수
    function getNumber(elements, num = '') {
        if (num.length === elements) {
            possibleNumbers.add(Number(num))
        } else {
            for (let i = 0; i < numbers.length; i += 1) {
                if (!checked[i]) {
                    checked[i] = 1
                    getNumber(elements, num + numbers[i])
                    checked[i] = 0
                }
            }
        }
    }
    
    for (let i = 1; i <= numbers.length; i += 1) {
        getNumber(i)
    }
    
    possibleNumbers = Array.from(possibleNumbers)
    
    for (let i = 0; i < possibleNumbers.length; i += 1) {
        answer += isPrime(possibleNumbers[i])
    }
    
    return answer
}