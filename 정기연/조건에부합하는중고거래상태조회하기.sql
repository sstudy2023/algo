SELECT BOARD_ID, WRITER_ID, TITLE, PRICE, IF(STATUS="DONE","거래완료",IF(STATUS="RESERVED","예약중",IF(STATUS="SALE","판매중",NULL))) AS STATUS
FROM USED_GOODS_BOARD
WHERE DATE_FORMAT(CREATED_DATE,"%Y-%m-%d")="2022-10-05"
ORDER BY 1 DESC