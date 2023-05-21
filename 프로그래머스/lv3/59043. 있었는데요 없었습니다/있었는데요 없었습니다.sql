-- 코드를 입력하세요
SELECT O.ANIMAL_ID AS ANIMAL_ID, O.NAME AS NAME
FROM ANIMAL_INS AS I INNER JOIN ANIMAL_OUTS AS O
ON I.ANIMAL_ID = O.ANIMAL_ID
WHERE I.DATETIME > O.DATETIME
ORDER BY I.DATETIME