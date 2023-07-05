-- "입양을 간 동물" 중, "보호기간이 가장 길었던" 동물 "2마리"의 "ID와 이름"을 "보호기간이 긴 순"으로 조회
-- 1. 입양을 간 동물: join 했을 때 out에 존재 -> is not null 활용
-- 2. 보호기간이 가장 길었던 동물: 보호기간 계산, 내림차순 정렬 -> datediff 활용
-- 3. 2마리: limit 활용
SELECT O.ANIMAL_ID, O.NAME
FROM ANIMAL_INS I JOIN ANIMAL_OUTS O ON I.ANIMAL_ID=O.ANIMAL_ID
WHERE O.ANIMAL_ID IS NOT NULL
ORDER BY DATEDIFF(O.DATETIME, I.DATETIME) DESC
LIMIT 2