-- "2022년 1월"의 "카테고리별 도서 판매량을 합산"한 리스트를 "카테고리 오름차순"으로 조회
-- 1. 카테고리별 도서 판매량 합산: SALES에는 ID기준 판매량만 있으므로 BOOK과 JOIN해 구해야함
--    카테고리별로 합산하기 위해 GROUP BY로 묶어주고 SELECT에 SUM으로 표시
-- 2. 2022년 1월로 한정이므로 WHERE 절에 LIKE 사용
-- 중요) WHERE -> GROUP BY -> HAVING 순으로 실행되므로 쿼리 전체에 대한 조건은 WHERE에, 묶은 데이터에 대한 조건은 HAVING에 걸어야함!!!
-- 3. ORDER BY로 카테고리 오름차순 정렬
SELECT CATEGORY, SUM(S.SALES) TOTAL_SALES
FROM BOOK B JOIN BOOK_SALES S ON B.BOOK_ID=S.BOOK_ID
WHERE S.SALES_DATE LIKE '2022-01-%'
GROUP BY B.CATEGORY
ORDER BY CATEGORY