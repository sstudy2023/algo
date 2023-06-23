-- PRODUCT 테이블에서 만원 단위의 가격대별(PRICE_GROUP)로 상품개수(PRODUCTS)를 출력, 가격대를 기준으로 오름차순
--  PRICE를 만원대로 끊기 위해서 만단위 미만은 모두 제거 -> 기존값-기존값의 천단위 이하값 -> 천단위 이하값은 10000으로 나눈 나머지
SELECT (PRICE - PRICE%10000) PRICE_GROUP, COUNT(*) PRODUCTS
FROM PRODUCT
GROUP BY 1
ORDER BY PRICE_GROUP
