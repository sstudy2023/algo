-- 테이블에서 "평균 대여기간이 7일 이상"인 자동차들의 id와 평균 대여기간 리스트를 출력
-- 평균대여기간(AVERAGE_DURATION)은 소수점 두번째 자리에서 반올림 -> ROUND(숫자, 1) 사용
-- 평균대여기간을 기준으로 내림차순, 같으면 자동차ID를 기준으로 내림차순 -> ORDER BY 적용
-- 평균을 구하기 위해 ID를 기준으로 묶어주고 AVG 사용, 대여기간을 구하기 위해 DATEDIFF를 사용(실제 기간을 구하기 위해서는 +1이 필수!!!)
-- 대여기간이 7일 이상이어야하므로 ALIAS 사용해서 HAVING에 조건 걸어줌
SELECT CAR_ID, ROUND(AVG(DATEDIFF(END_DATE, START_DATE)+1), 1) AVERAGE_DURATION
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
GROUP BY CAR_ID
HAVING AVERAGE_DURATION >= 7
ORDER BY 2 DESC, 1 DESC