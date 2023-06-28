// 압축할 수 있는지 검사하는 함수
function canCompress(sr, sc, width, arr) {
    const firstNum = arr[sr][sc]

    // 영역이 모두 같은 수인지 검사
    for (let r = sr; r < sr + width; r += 1) {
        for (let c = sc; c < sc + width; c += 1) {
            if (arr[r][c] !== firstNum) {
                return false
            }
        }
    }
    
    return true
}

function solution(arr) {
    const answer = [0, 0]
    
    // 압축하는 함수
    function compress(sr, sc, width) {
        if (canCompress(sr, sc, width, arr)) {
            answer[arr[sr][sc]] += 1
        } else {
            const newWidth = parseInt(width / 2)
            compress(sr, sc, newWidth)
            compress(sr, sc + newWidth, newWidth)
            compress(sr + newWidth, sc, newWidth)
            compress(sr + newWidth, sc + newWidth, newWidth)
        }
        
    }
    
    compress(0, 0, arr.length)
    
    return answer
}