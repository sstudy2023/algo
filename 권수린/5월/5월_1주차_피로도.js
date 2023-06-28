function solution(k, dungeons) {
  let answer = 0;
  const check = Array(dungeons.length).fill(0)
  
  // 던전 탐험하는 함수
  const raid = (curNumber, dungeonNumber, restFatigue) => {
      
      // 모든 던전을 고려해 봤으면 더 많은 던전을 탐험했을 때 값 갱신
      if (curNumber === dungeons.length) {
          if (answer < dungeonNumber) {
              answer = dungeonNumber
          }
      }
      
      for (let i = 0; i < dungeons.length; i += 1) {
          if (!check[i]) {
              check[i] = 1
              // 해당 던전을 탐험할 수 있으면 탐험
              if (dungeons[i][0] <= restFatigue) {
                  raid(curNumber+1, dungeonNumber+1, restFatigue-dungeons[i][1])
              } else {
                  // 탐험할 수 없으면 그냥 지나감
                  raid(curNumber+1, dungeonNumber, restFatigue)
              }
              check[i] = 0
              
          }
      }
  }
  
  raid(0, 0, k)
  
  return answer;
}