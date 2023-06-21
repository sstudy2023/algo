-- 동물 중 고양이와 개가 각각 몇 마리인지 출력, 고양이를 개보다 먼저 출력
-- ORDER BY를 사용하는 것이 핵심!!!
SELECT ANIMAL_TYPE, COUNT(*) AS count
FROM ANIMAL_INS
GROUP BY ANIMAL_TYPE
HAVING ANIMAL_TYPE IN ('Cat', 'Dog')
ORDER BY ANIMAL_TYPE
