-- PRODUCT 테이블(ID, CODE, 가격)과 SALE 테이블(ID, P_ID, 판매량, 판매일)에서 상품코드별 매출액(판매가*판매량) 합계를 출력
-- JOIN 할 때 , 사용말고 JOIN을 직접 표기하기!
-- P_ID로 묶어놨기 때문에 SUM으로 판매량합계 구한후 곱해서 매출액 계산
SELECT P.PRODUCT_CODE, P.PRICE*SUM(S.SALES_AMOUNT) SALES
FROM PRODUCT P JOIN OFFLINE_SALE S
ON P.PRODUCT_ID = S.PRODUCT_ID
GROUP BY P.PRODUCT_ID
ORDER BY SALES DESC, PRODUCT_CODE ASC
