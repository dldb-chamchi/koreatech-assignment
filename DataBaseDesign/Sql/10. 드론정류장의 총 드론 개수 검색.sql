SELECT drone_model_code, COUNT(*) AS drone_count
FROM drone
WHERE station_number = &station
	AND battery_status >= 80 
	AND operation_status = '사용가능'
GROUP BY drone_model_code;




-- 입력
1