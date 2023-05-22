function solution(bridge_length, weight, truck_weights) {
  let answer = 0
  let curWeight = 0
  let truckIndex = 0
  let queueFront = 0
  let queueRear = 0
  const queue = []
  
  while (queueFront === 0 || queueFront !== queueRear) {
      answer += 1
      
      // 트럭이 다리에서 내려옴
      if (queueFront !== queueRear && answer >= queue[queueFront][1] + bridge_length) {
          curWeight -= queue[queueFront][0]
          queueFront += 1
      }
      
      // 트럭이 다리로 이동
      if (truckIndex < truck_weights.length && curWeight + truck_weights[truckIndex] <= weight) {
          curWeight += truck_weights[truckIndex]
          queue.push([truck_weights[truckIndex], answer])
          queueRear += 1
          truckIndex += 1
      }
      
  }
  
  return answer;
}