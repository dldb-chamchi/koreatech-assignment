-- 앞에서 삭제되었기때문에 검색을 보여주기 위해 다시 삽입
INSERT INTO drone_rental (drone_id, company_user_id, rental_end_date, rental_start_date, rental_fee) VALUES
(1, 'chllllsu', TO_DATE('2025-06-05 18:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-01 09:00:00','YYYY-MM-DD HH24:MI:SS'), 20000);

SELECT d.*
FROM drone_rental r
	JOIN drone d ON r.drone_id = d.drone_number
WHERE r.company_user_id = 'chllllsu';
