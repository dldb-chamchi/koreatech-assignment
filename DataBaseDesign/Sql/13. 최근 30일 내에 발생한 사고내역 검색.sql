SELECT	a.accident_id, a.drone_id, a.location, a.accident_date,
	d.drone_model_code,
	dm.model_name
FROM drone_accident a
	JOIN drone d ON a.drone_id = d.drone_number
	JOIN drone_model dm ON d.drone_model_code = dm.drone_model_code
WHERE a.accident_date >= SYSDATE - 30
ORDER BY a.accident_date DESC;
