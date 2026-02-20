SELECT *
FROM item_delivery

-- 나중에 실행 (1, 2번 행 삭제)
DELETE FROM item_delivery
WHERE drone_id IN (
	SELECT d.drone_number
	FROM drone d
			JOIN (
				SELECT ds.location
				FROM station ds
						JOIN user_reservation ur
							ON ur.arrival_location = ds.station_name
			) tmp
				ON
					ABS(  TO_NUMBER(SUBSTR(d.current_location, 1, INSTR(d.current_location, ':') - 1))
							-  TO_NUMBER(SUBSTR(tmp.location, 1, INSTR(tmp.location, ':') - 1))) <= 0.0004
					AND
					ABS(  TO_NUMBER(SUBSTR(d.current_location, INSTR(d.current_location, ':') + 1))
							-  TO_NUMBER(SUBSTR(tmp.location, INSTR(tmp.location, ':') + 1))) <= 0.0004
);

SELECT *
FROM item_delivery
