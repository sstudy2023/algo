function solution(n, m, section) {
    var answer = 1;
    let curWallNum = section[0]
    
    for (let i = 0; i < section.length; i += 1) {
        if (section[i] >= curWallNum + m) {
            answer += 1
            curWallNum = section[i]
        }
    }
    
    return answer;
}