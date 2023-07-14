-- "완료"된 중고 거래의 "총금액이 70만원 이상"인 사람의 "회원ID, 닉네임, 총거래금액"을 "총거래금액을 기준으로 오름차순"으로 조회
-- 0. 사용자 정보를 찾기 위해 두 테이믈을 JOIN, 게시판에는 USER_ID가 아닌 WRITER_ID로 표기
-- 1. 총거래금액이 70만원 이상 -> 총거래금액을 찾기위해 GROUP BY로 USER를 묶은 후 HAVING으로 조건 걸기
-- 2. 거래가 완료된 기준이므로 WHERE절에 조건 걸기
-- 3. ORDER_BY로 총거래금액 기준 정렬, 순서상 SELECT 뒤에 실행되므로 ALIAS로 정렬 가능
SELECT U.USER_ID, U.NICKNAME, SUM(B.PRICE) TOTAL_SALES
FROM USED_GOODS_BOARD B JOIN USED_GOODS_USER U ON B.WRITER_ID = U.USER_ID
WHERE B.STATUS = "DONE"
GROUP BY U.USER_ID
HAVING SUM(B.PRICE) >= 700000
ORDER BY TOTAL_SALES