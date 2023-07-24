-- "음식 종류별로 즐겨찾기가 가장 많은 식당"의 음식종류, ID, 식당이름, 즐겨찾기수를 "음식종류를 기준으로 내림차순 정렬"해서 조회
-- 1. 음식 종류별로 즐겨찾기가 가장 많은 식당
-- -> 처음에는 SUBQUERY를 쓰지 않고 GROUP BY에 HAVING으로 MAX 조건을 걸었는데 이 경우 결과가 제대로 나오지 않음
-- -> SUM, COUNT, AVG 등이 아니라 MAX는 HAVING에서 제대로 작동되지 않음, GROUP BY에서 조건으로 걸지 않은 값은 임의의 값만 가져오기 때문임(FAVORITES)
-- -> 따라서 GROUP BY에서 사용하지 않은 값의 집계함수 결과를 알고 싶으면 SELECT에서 걸어줘야함
-- -> 그러므로 SUBQUERY를 사용해 SELECT로 가져와서 조건에 걸어주고 그 값을 출력하는 방식으로 가야함
-- 2. ORDER BY 음식종류 내림차순DESC
SELECT FOOD_TYPE, REST_ID, REST_NAME, FAVORITES
FROM REST_INFO
WHERE (FOOD_TYPE, FAVORITES) IN (SELECT FOOD_TYPE, MAX(FAVORITES) FAVORITES
                                 FROM REST_INFO
                                 GROUP BY FOOD_TYPE)
ORDER BY FOOD_TYPE DESC

