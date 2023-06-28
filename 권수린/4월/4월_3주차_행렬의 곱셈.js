function solution(arr1, arr2) {
  let answer = [];
  
  for (let r1 = 0; r1 < arr1.length; r1 += 1) {
    let row = new Array(arr2[0].length).fill(0)
    answer.push(row)
  }

  for (let r = 0; r < answer.length; r += 1) {
    for (let c = 0; c < answer[0].length; c += 1) {

      for (let base = 0; base < arr1[0].length; base += 1){
        answer[r][c] += arr1[r][base] * arr2[base][c]
      }
    }
  }
  
  return answer;
}