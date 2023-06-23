-- 2022년 5월에 예약한 환자수를 진료과코드 별로 출력, 진료과별 에약한 환자수 오름차순하고 같으면 진료과 코드 기준 오름차순
-- ORDER BY에는 ''식으로 묶은 ALIAS를 사용할 수 없음! 코드로 쓰거나 1, 2 이런식으로 사용해야 적용됨
SELECT MCDP_CD AS '진료과 코드', COUNT(*) '5월예약건수'
FROM APPOINTMENT
WHERE APNT_YMD LIKE '2022-05-%'
GROUP BY MCDP_CD
ORDER BY COUNT(*) ASC, MCDP_CD ASC