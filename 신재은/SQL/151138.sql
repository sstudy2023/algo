-- 테이블에서 "대여시작일이 2022년 9월"에 속하는 대여기록에 대해 "대여기간이 30일 이상이면 '장기 대여', 아니면 '단기 대여'로 표시하는 컬럼 RENT_TYPE을 추가"해 "대여기록ID"를 기준으로 내림차순 정렬해 출력
-- 1. 대여시작일 표시를 위해 where 절에 like를 사용해 표기
-- 2. 대여기간을 계산하기 위해 DATEDIFF(종료일, 시작일) 활용 -> 그 사이의 일수 계산이므로 +1, 반드시 종료일부터 써야함(아니면 null 뜸)
-- 	  장,단기대여 분류를 위해 CASE를 사용, ELSE로 끝내고 END로 닫아주기 필수!
-- 3. 내림차순 정렬을 위해 orderby 절에 desc 사용
SELECT HISTORY_ID, CAR_ID, DATE_FORMAT(START_DATE, '%Y-%m-%d') START_DATE, DATE_FORMAT(END_DATE, '%Y-%m-%d') END_DATE,
    CASE WHEN DATEDIFF(END_DATE, START_DATE)+1>=30 THEN "장기 대여"
    ELSE "단기 대여" END AS RENT_TYPE
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE START_DATE LIKE '2022-09-%'
ORDER BY HISTORY_ID DESC
