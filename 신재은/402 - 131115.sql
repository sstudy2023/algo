-- 가격이 제일 비싼 식품의 정보 출력하기 https://school.programmers.co.kr/learn/courses/30/lessons/131115
-- 가장 비싼 것 1개만 출력하므로 가격 내림차순 정렬 후 1개만 출력
SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_CD, CATEGORY, PRICE
FROM FOOD_PRODUCT
ORDER BY PRICE DESC
LIMIT 1;

-- 서브쿼리 이용해서 MAX가격을 구하고 그 가격을 조건으로 걸어서 출력
SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_CD, CATEGORY, PRICE
FROM FOOD_PRODUCT
WHERE PRICE = (SELECT MAX(PRICE) FROM FOOD_PRODUCT);