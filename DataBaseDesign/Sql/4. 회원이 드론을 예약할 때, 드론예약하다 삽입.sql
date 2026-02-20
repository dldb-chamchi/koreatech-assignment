SELECT *
FROM drone_rental
WHERE drone_id = 9;



-- 나중에 실행
INSERT INTO drone_rental (drone_id, company_user_id, rental_end_date, rental_start_date, rental_fee)
VALUES (9, 'chllllsu', TO_DATE('2025-06-05 18:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-01 09:00:00','YYYY-MM-DD HH24:MI:SS'), 20000);

SELECT *
FROM drone_rental
WHERE drone_id = 9;