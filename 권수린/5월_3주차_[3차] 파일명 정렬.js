function getParts(file, index) {
  const rlt = ['', 0, index]
  let i = 0
  let part = ''
  
  // HEAD 추출
  // 공백은 숫자와 같이 false 반환하므로 따로 처리
  while (isNaN(file[i]) || file[i] === ' ') {
      part += file[i]
      i += 1
  }
  
  rlt[0] = part.toLowerCase()
  part = ''
  
  // NUMBER 추출
  while (i < file.length && !isNaN(file[i]) && part.length < 5) {
      part += file[i]
      i += 1
  }
  
  rlt[1] = parseInt(part)
  
  return rlt
}

function solution(files) {
  const answer = [];
  const partsOfFiles = []
  
  for (let i = 0; i < files.length; i += 1) {
      partsOfFiles.push(getParts(files[i], i))
  }
  
  // 정렬
  partsOfFiles.sort((a, b) => {
      const [aHead, bHead] = [a[0], b[0]]
      const [aNumber, bNumber] = [a[1], b[1]]
      const [aIndex, bIndex] = [a[2], b[2]]
      
      if (aHead === bHead && aNumber === bNumber) {
          // 헤드와 넘버가 모두 같으면 입력받은 순서대로 정렬
          return aIndex - bIndex
      } else if (aHead === bHead) {
          // 헤드가 같으면 넘버 기준 오름차순 정렬
          return aNumber - bNumber
      } else {
          // 헤드 기준 정렬
          // 문자끼리는 -연산이 불가능하기 때문에 <, >, ===를 이용해 정렬
          // 음수를 반환하면 앞에 위치
          if (aHead < bHead) {
              return -1
          } else if (aHead > bHead) {
              return 1
          } else {
              return 0
          }
      }
  })
  
  for (const partsOfFile of partsOfFiles) {
      answer.push(files[partsOfFile[2]])
  }
  
  return answer;
}