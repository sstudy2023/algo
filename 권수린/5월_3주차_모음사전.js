function solution(word) {
  let answer = 0
  const alpha = ["A", "E", "I", "O", "U"];
  let number = 0

  function makeWord(dictionaryWord) {
    if (dictionaryWord.length > 5 || answer) {
      return
    }

    if (word === dictionaryWord) {
      answer = number
    }

    number += 1

    for (let i = 0; i < alpha.length; i += 1) {
      makeWord(dictionaryWord + alpha[i])
    }
  }

  makeWord("")

  return answer
}