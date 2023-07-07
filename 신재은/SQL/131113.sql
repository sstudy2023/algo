-- "주문ID, 제품ID, 출고일자, 출고여부"를 "주문ID 기준 오름차순 정렬"하여 조회
-- "출고여부"는 5월 1일을 기준으로 그 이전 날짜는 '출고완료', 이후 날짜는 '출고대기', 날짜가 없으면 '출고미정'으로 출력
-- 출고여부를 체크하기 위해 CASE WHEN을 사용, 날짜는 부등호 비교 가능하므로 >= 표기
SELECT ORDER_ID, PRODUCT_ID, DATE_FORMAT(OUT_DATE, '%Y-%m-%d') OUT_DATE,
        CASE WHEN '2022-05-01' >= OUT_DATE THEN '출고완료'
             WHEN OUT_DATE IS NULL THEN '출고미정'
             ELSE '출고대기' END AS '출고여부'
FROM FOOD_ORDER
ORDER BY ORDER_ID