SELECT dm.drone_model_code, dm.model_name
FROM special_item si
	JOIN special_item_assignment sa ON si.special_item_code = sa.special_item_code
	JOIN drone_model dm ON sa.drone_model_code = dm.drone_model_code
WHERE si.category LIKE '%' || '&category_keyword' || '%';




-- 입력
전자