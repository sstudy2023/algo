function solution(today, terms, privacies) {
    
    // 파기해야 할 개인 정보
    var answer = [];
    
    const termInfo = {}
    
    today = today.split('.')
    const todayYear = parseInt(today[0])
    const todayMonth = parseInt(today[1])
    const todayDate = parseInt(today[2])
    
    // 약관 정보 저장
    for (let i = 0; i < terms.length; i += 1) {
        const [term, month] = terms[i].split(' ')
        termInfo[term] = parseInt(month)
    }
    
    // 개인정보 만료 계산
    for (let i = 0; i < privacies.length; i += 1) {
        const [date, term] = privacies[i].split(' ')
        
        // 만료날짜 계산
        let year = parseInt(date.split('.')[0])
        let month = parseInt(date.split('.')[1])
        let day = parseInt(date.split('.')[2])
        
        year += parseInt((termInfo[term]+month)/12)
        month = (termInfo[term] + month) % 12
        day -= 1
        
        if (day < 1) {
            month -= 1
            day += 28
        }
        
        if (month < 1) {
            year -= 1
            month += 12
        }
        
        // 만료 날짜와 오늘 날짜 비교
        const newFormToday = `${todayYear}${todayMonth < 10 ? `0${todayMonth}` : todayMonth}${todayDate < 10 ? `0${todayDate}` : todayDate}`
        const privacyDate = `${year}${month < 10 ? `0${month}` : month}${day < 10 ? `0${day}` : day}`
        
        if (newFormToday > privacyDate) {
            answer.push(i+1)
        }
    }
    
    answer.sort((a, b) => a - b)
    
    return answer;
}