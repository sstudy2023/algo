-- "자동차 종류가 세단"인 자동차들 중 "10월에 대여를 시작한 기록이 있는" "자동차 ID"를 중복없이 내림차순으로 조회
-- 1. 테이블이 분리되어 있으므로 종류가 세단인 자동차의 ID를 SUBQUERY로 조회해서 ID 가져와서 조건에 걸어줌
-- 2. 10월에 대여 시작한 기록을 확인하기 위해 LIKE 사용해서 조건에 같이 걸어줌
-- 3. 중복 제거를 위해 SELECT에 DISTINCT 사용
-- 4. 내림차순 정렬을 위해 ORDER BY 사용
SELECT DISTINCT CAR_ID
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE CAR_ID IN (SELECT CAR_ID
                 FROM CAR_RENTAL_COMPANY_CAR
                 WHERE CAR_TYPE='세단')
      AND START_DATE LIKE '2022-10-%'
ORDER BY CAR_ID DESC