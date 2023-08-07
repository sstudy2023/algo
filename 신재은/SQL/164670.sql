-- "중고거래 게시물을 3건 이상 등록"한 사용자의 "ID, 닉네임, 전체주소, 전화번호"를 회원ID 기준 내림차순으로 조회
-- 전체주소: 시, 도로명, 상세 주소가 함께 출력 / 전화번호: 하이픈(-) 넣어서 출력
-- 1. 중고거래 게시물 등록 횟수 확인을 위해 SUBQUERY 활용해서 구하고 해당 게시글 등록자와 USER_ID가 같을 때를 조건으로 걸어 가져옴
-- 2. 전체주소를 붙이기 위해 CONCAT을 활용, 사이사이에 띄어쓰기 한번만 입력하기 위해 CONCAT_WS 사용해서 한번만 정의
-- 3. 전화번호 사이에 하이픈 넣기 위해서 CONCAT_WS과 SUBSTR 활용
--    SUBSTR(쿼리명, 시작 위치, 원하는 문자열의 개수)로 사용, 시작 위치는 음수로 표현 가능(맨 끝이 -1)
--    3개-4개-4개 형태지만 가운데가 3개인 번호도 대비하기 위해 중간을 SUBSTR(TLNO, 4, 번호길이-7)로 작성
-- 4. ORDER BY로 내림차순 정렬
SELECT USER_ID, NICKNAME, CONCAT_WS(' ', CITY, STREET_ADDRESS1, STREET_ADDRESS2) '전체주소', CONCAT_WS('-', SUBSTR(TLNO, 1, 3), SUBSTR(TLNO, 4, LENGTH(TLNO)-7), SUBSTR(TLNO, -4, 4)) '전화번호'
FROM USED_GOODS_USER
WHERE USER_ID IN (SELECT WRITER_ID
                 FROM USED_GOODS_BOARD
                 GROUP BY WRITER_ID
                 HAVING COUNT(*) >= 3)
ORDER BY USER_ID DESC