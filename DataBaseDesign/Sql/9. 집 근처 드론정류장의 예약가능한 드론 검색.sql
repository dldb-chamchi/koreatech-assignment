UNDEFINE 집
SELECT *
FROM drone
WHERE  operation_status = '사용가능'  
	AND station_number IN (
		SELECT station_number 
		FROM station ds
		WHERE operation_status = '영업중' 
			AND
				ABS(TO_NUMBER(SUBSTR(ds.location, 1, INSTR(ds.location, ':') - 1))
					- TO_NUMBER(SUBSTR('&&집', 1, INSTR('&&집', ':') - 1))) <= 0.02
			AND
				ABS(TO_NUMBER(SUBSTR(ds.location, INSTR(ds.location, ':') + 1))
					- TO_NUMBER(SUBSTR('&&집', INSTR('&&집', ':') + 1))) <= 0.02  );



-- 입력
36.6439:127.4892