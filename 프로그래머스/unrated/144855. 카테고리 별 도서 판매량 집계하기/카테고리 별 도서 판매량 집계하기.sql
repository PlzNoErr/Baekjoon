-- 코드를 입력하세요
SELECT B.CATEGORY AS CATEGORY, SUM(S.SALES) AS TOTAL_SALES
FROM BOOK AS B, BOOK_SALES AS S WHERE B.BOOK_ID = S.BOOK_ID
AND DATE_FORMAT(S.SALES_DATE,'%Y-%m') = '2022-01'
GROUP BY B.CATEGORY
ORDER BY B.CATEGORY