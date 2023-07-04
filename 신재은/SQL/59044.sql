-- 아직 입양을 못 간 동물 중, 가장 오래 보호소에 있었던 동물 "3마리"의 "이름"과 "보호 시작일"을 조회
-- 입양을 못 간 동물 = 보호소 목록-입양 간 목록 -> not in 을 사용해 subquery를 넣어줌
-- 주의) subquery 사용시 where 절의 조건과 query에서 select한 데이터가 같아야함!!!
-- limit를 사용해 불러올 데이터(동물) 수 제한 & order by로 정렬
SELECT NAME, DATETIME
FROM ANIMAL_INS
WHERE ANIMAL_ID NOT IN (SELECT ANIMAL_ID FROM ANIMAL_OUTS)
ORDER BY DATETIME
LIMIT 3