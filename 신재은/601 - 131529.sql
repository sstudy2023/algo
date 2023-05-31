-- 카테고리 코드(상품코드 앞 2자리)와 카테고리별 개수를 출력, 카테고리 코드 기준 오름차순
-- 문자열 자르기에서 LEFT, RIGHT 사용 가능, 중간부터 자르고 싶으면 SUBSTR 사용
SELECT LEFT(PRODUCT_CODE, 2) CATEGORY, COUNT(*) AS PRODUCTS
FROM PRODUCT
GROUP BY CATEGORY
ORDER BY CATEGORY