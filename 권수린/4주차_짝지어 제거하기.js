function solution(s)
{
    var answer = 1;
    let stack = Array(s.length).fill(0)
    let top = 0
    
    for (let i = 0; i < s.length; i += 1) {
        if (s[i] === stack[top]) {
            top -= 1
        } else {
            top += 1
            stack[top] = s[i]
        }
    }
    
    if (top > 0) {
        answer = 0
    }
    
    return answer;
}