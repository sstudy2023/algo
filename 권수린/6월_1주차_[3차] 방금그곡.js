function solution(m, musicinfos) {
    let answer = '(None)'
    let maxPlaytime = 0

    // 악보별로 멜로디가 포함되어 있는지 검사
    for (const musicinfo of musicinfos) {
        let [start, end, name, sheet] = musicinfo.split(',')
        start = start.split(':')
        end = end.split(':')
        let notes = ''
        let extratime = 0
        let soundIndex = 0
        
        const playtime = (Number(end[0]) - Number(start[0])) * 60 + Number(end[1]) - Number(start[1])
        
        // 재생시간에 맞춰 새로 악보 생성
        while (notes.length < playtime + extratime) {
            if (sheet[(soundIndex + 1) % sheet.length] === '#') {
                extratime += 1
            }
            
            notes += sheet[soundIndex % sheet.length]
            soundIndex += 1
        }
        
        let endIndex = notes.length - 1
        
        // #이 있는 경우와 그렇지 않은 경우를 구분하기 위해 악보를 줄여가면서 검사
        while (endIndex > -1) {
            if (notes.lastIndexOf(m, endIndex) !== -1 && notes.lastIndexOf(m+'#', endIndex) !== notes.lastIndexOf(m, endIndex)) {
                if (maxPlaytime < playtime) {
                    maxPlaytime = playtime
                    answer = name
                }
                
                break
            }
            
            if (notes[endIndex] === '#') {
                endIndex -= 1
            }
            
            endIndex -= 1
        }
    }
    
    return answer
}