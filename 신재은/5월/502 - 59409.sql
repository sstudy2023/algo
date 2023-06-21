-- 동물의 ID, 이름, 중성화 여부를 출력
-- 중성화 여부는 SEX_UPON_INTAKE에 Neutered나 Spayed가 들어가면 O, 아니면 X로 표기
SELECT ANIMAL_ID, NAME, CASE
        WHEN (SEX_UPON_INTAKE LIKE 'Neutered%' OR SEX_UPON_INTAKE LIKE 'Spayed%') THEN 'O'
        ELSE 'X'
    END AS '중성화'
FROM ANIMAL_INS