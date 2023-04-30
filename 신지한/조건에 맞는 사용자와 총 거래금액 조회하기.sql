SELECT u.USER_ID, u.NICKNAME, SUM(b.price) AS TOTAL_SALES
    FROM used_goods_board b
    JOIN used_goods_user u
    ON b.writer_id = u.user_id
WHERE b.status = 'DONE'
GROUP BY u.user_id
HAVING TOTAL_SALES >= 700000
ORDER BY TOTAL_SALES