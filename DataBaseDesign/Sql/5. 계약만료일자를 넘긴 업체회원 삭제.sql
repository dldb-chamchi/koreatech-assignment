SELECT *
FROM company_member
WHERE active_status = '휴면';


-- 나중에 실행
DELETE FROM drone_rental
WHERE company_user_id IN (
	SELECT company_user_id
	FROM company_member 
	WHERE active_status = '휴면'
		AND last_login_at < ADD_MONTHS(SYSDATE, -24)
);

DELETE FROM company_member
WHERE active_status = '휴면'
AND last_login_at < ADD_MONTHS(SYSDATE, -24);

SELECT *
FROM company_member
WHERE active_status = '휴면';