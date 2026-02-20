-- 드론 정류장
INSERT INTO station (station_name, location) 
VALUES ('이도이동 정류장', '33.4996:126.5312');
INSERT INTO station (station_name, operation_status, location) 
VALUES ('율량동 정류장', '영업중', '36.6439:127.4892');
INSERT INTO station (station_name, operation_status, location) 
VALUES ('봉명동 정류장', '영업중', '36.6295:127.4581');
INSERT INTO station (station_name, location) 
VALUES ('연남동 정류장', '37.5613:126.9259');
INSERT INTO station (station_name, operation_status, location) 
VALUES ('수유역 정류장', '영업중', '37.6380:127.0256');
INSERT INTO station (station_name, location) 
VALUES ('화곡동 정류장', '37.5410:126.8407');
INSERT INTO station (station_name, location) VALUES ('전주한옥마을 정류장', '35.8152:127.1531');
INSERT INTO station (station_name, location) VALUES ('울산공업탑 정류장', '35.5411:129.3006');
INSERT INTO station (station_name, location) VALUES ('춘천명동 정류장', '37.8814:127.7298');
INSERT INTO station (station_name, location) VALUES ('구로디지털단지 정류장', '37.4850:126.9015');
INSERT INTO station (station_name, location) VALUES ('수성못 정류장', '35.8346:128.6280');
INSERT INTO station (station_name, location) VALUES ('광화문 정류장', '37.5714:126.9768');
INSERT INTO station (station_name, location) VALUES ('판교역 정류장', '37.3946:127.1115');
INSERT INTO station (station_name, location) VALUES ('목동 정류장', '37.5264:126.8644');
INSERT INTO station (station_name, location) VALUES ('안양1번가 정류장', '37.3937:126.9276');
INSERT INTO station (station_name, location) VALUES ('대전역 정류장', '36.3326:127.4342');
INSERT INTO station (station_name, location) VALUES ('광주송정 정류장', '35.1391:126.7932');
INSERT INTO station (station_name, location) VALUES ('수원역 정류장', '37.2659:127.0002');
INSERT INTO station (station_name, operation_status, location) VALUES ('제주공항 정류장', '영업중', '33.5104:126.4915');
INSERT INTO station (station_name, operation_status, location) VALUES ('인천공항 정류장', '영업중', '37.4602:126.4407');
INSERT INTO station (station_name, operation_status, location) VALUES ('동탄호수공원 정류장', '영업중', '37.2008:127.1048');

-- 제휴업체
INSERT INTO company (station_number, company_name, business_number) VALUES 
(1, '이도이동 GS25', '123-56-89012');
INSERT INTO company (station_number, company_name, business_number, contract_expiry_date) 
VALUES (2, '율량동 7eleven', '234-67-90123', TO_DATE('2029-12-31', 'YYYY-MM-DD'));
INSERT INTO company (station_number, company_name, business_number) 
VALUES (3, '봉명동 CU', '345-78-01234');
INSERT INTO company (station_number, company_name, business_number, contract_expiry_date) 
VALUES (4, '연남동 미니스톱', '456-89-12345', TO_DATE('2030-05-30', 'YYYY-MM-DD'));
INSERT INTO company (station_number, company_name, business_number, contract_expiry_date) VALUES (5, '전주한옥마을 GS25', '100111122213', TO_DATE('2029-09-12','YYYY-MM-DD'));
INSERT INTO company (station_number, company_name, business_number, contract_expiry_date) VALUES (6, '울산공업탑 이마트24', '100111122214', TO_DATE('2032-04-25','YYYY-MM-DD'));
INSERT INTO company (station_number, company_name, business_number, contract_expiry_date) VALUES (7, '춘천명동 7eleven', '100111122215', TO_DATE('2033-12-29','YYYY-MM-DD'));
INSERT INTO company (station_number, company_name, business_number, contract_expiry_date) VALUES (8, '구로디지털단지 CU', '100111122216', TO_DATE('2029-11-11','YYYY-MM-DD'));
INSERT INTO company (station_number, company_name, business_number, contract_expiry_date) VALUES (9, '수성못 이마트', '100111122217', TO_DATE('2034-05-08','YYYY-MM-DD'));
INSERT INTO company (station_number, company_name, business_number, contract_expiry_date) VALUES (10, '광화문 GS25', '100111122218', TO_DATE('2027-08-17','YYYY-MM-DD'));
INSERT INTO company (station_number, company_name, business_number, contract_expiry_date) VALUES (11, '판교역 7eleven', '100111122219', TO_DATE('2032-03-03','YYYY-MM-DD'));
INSERT INTO company (station_number, company_name, business_number, contract_expiry_date) VALUES (12, '목동 CU', '100111122220', TO_DATE('2030-11-05','YYYY-MM-DD'));
INSERT INTO company (station_number, company_name, business_number, contract_expiry_date) VALUES (13, '안양1번가 GS25', '100111122221', TO_DATE('2028-06-18','YYYY-MM-DD'));
INSERT INTO company (station_number, company_name, business_number, contract_expiry_date) VALUES (14, '대전역 이마트24', '100111122222', TO_DATE('2031-09-14','YYYY-MM-DD'));
INSERT INTO company (station_number, company_name, business_number, contract_expiry_date) VALUES (15, '광주송정 7eleven', '100111122223', TO_DATE('2029-01-24','YYYY-MM-DD'));
INSERT INTO company (station_number, company_name, business_number, contract_expiry_date) VALUES (16, '수원역 CU', '100111122224', TO_DATE('2033-10-27','YYYY-MM-DD'));
INSERT INTO company (station_number, company_name, business_number, contract_expiry_date) VALUES (17, '제주공항 GS25', '100111122225', TO_DATE('2034-01-19','YYYY-MM-DD'));
INSERT INTO company (station_number, company_name, business_number, contract_expiry_date) VALUES (18, '인천공항 이마트', '100111122226', TO_DATE('2027-05-28','YYYY-MM-DD'));
INSERT INTO company (station_number, company_name, business_number, contract_expiry_date) VALUES (19, '동탄호수공원 CU', '100111122227', TO_DATE('2028-12-07','YYYY-MM-DD'));

-- 캐비닛
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (1, 24, '1234', '대형', TO_DATE('2025-05-12', 'YYYY-MM-DD'), '보관');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (1, 0, '0000', '중형', TO_DATE('2025-05-10', 'YYYY-MM-DD'), '사용가능');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (1, 0, '0001', '소형', TO_DATE('2025-05-15', 'YYYY-MM-DD'), '예약');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (1, 177, '2222', '대형', TO_DATE('2025-05-15', 'YYYY-MM-DD'), '잠김');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (1, 36, '3333', '중형', TO_DATE('2025-05-16', 'YYYY-MM-DD'), '사용가능');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (1, 12, '4444', '소형', TO_DATE('2025-05-17', 'YYYY-MM-DD'), '보관');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (1, 48, '5555', '대형', TO_DATE('2025-05-18', 'YYYY-MM-DD'), '예약');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (1, 0, '6666', '중형', TO_DATE('2025-05-19', 'YYYY-MM-DD'), '사용가능');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (1, 72, '7777', '소형', TO_DATE('2025-05-20', 'YYYY-MM-DD'), '잠김');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (1, 96, '8888', '대형', TO_DATE('2025-05-21', 'YYYY-MM-DD'), '보관');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (2, 20, '1235', '대형', TO_DATE('2025-05-12', 'YYYY-MM-DD'), '보관');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (2, 0, '0002', '중형', TO_DATE('2025-05-11', 'YYYY-MM-DD'), '사용가능');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (2, 0, '0003', '소형', TO_DATE('2025-05-15', 'YYYY-MM-DD'), '예약');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (2, 150, '2223', '대형', TO_DATE('2025-05-16', 'YYYY-MM-DD'), '잠김');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (2, 30, '3334', '중형', TO_DATE('2025-05-17', 'YYYY-MM-DD'), '사용가능');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (2, 10, '4445', '소형', TO_DATE('2025-05-18', 'YYYY-MM-DD'), '보관');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (2, 40, '5556', '대형', TO_DATE('2025-05-19', 'YYYY-MM-DD'), '예약');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (2, 0, '6667', '중형', TO_DATE('2025-05-20', 'YYYY-MM-DD'), '사용가능');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (2, 70, '7778', '소형', TO_DATE('2025-05-21', 'YYYY-MM-DD'), '잠김');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (2, 90, '8889', '대형', TO_DATE('2025-05-22', 'YYYY-MM-DD'), '보관');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (3, 18, '1236', '대형', TO_DATE('2025-05-13', 'YYYY-MM-DD'), '보관');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (3, 0, '0004', '중형', TO_DATE('2025-05-12', 'YYYY-MM-DD'), '사용가능');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (3, 0, '0005', '소형', TO_DATE('2025-05-16', 'YYYY-MM-DD'), '예약');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (3, 160, '2224', '대형', TO_DATE('2025-05-17', 'YYYY-MM-DD'), '잠김');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (3, 35, '3335', '중형', TO_DATE('2025-05-18', 'YYYY-MM-DD'), '사용가능');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (3, 11, '4446', '소형', TO_DATE('2025-05-19', 'YYYY-MM-DD'), '보관');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (3, 45, '5557', '대형', TO_DATE('2025-05-20', 'YYYY-MM-DD'), '예약');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (3, 0, '6668', '중형', TO_DATE('2025-05-21', 'YYYY-MM-DD'), '사용가능');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (3, 68, '7779', '소형', TO_DATE('2025-05-22', 'YYYY-MM-DD'), '잠김');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (3, 85, '8890', '대형', TO_DATE('2025-05-23', 'YYYY-MM-DD'), '보관');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (4, 22, '1237', '대형', TO_DATE('2025-05-14', 'YYYY-MM-DD'), '보관');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (4, 0, '0006', '중형', TO_DATE('2025-05-13', 'YYYY-MM-DD'), '사용가능');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (4, 0, '0007', '소형', TO_DATE('2025-05-17', 'YYYY-MM-DD'), '예약');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (4, 120, '2225', '대형', TO_DATE('2025-05-18', 'YYYY-MM-DD'), '잠김');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (4, 32, '3336', '중형', TO_DATE('2025-05-19', 'YYYY-MM-DD'), '사용가능');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (4, 13, '4447', '소형', TO_DATE('2025-05-20', 'YYYY-MM-DD'), '보관');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (4, 49, '5558', '대형', TO_DATE('2025-05-21', 'YYYY-MM-DD'), '예약');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (4, 0, '6669', '중형', TO_DATE('2025-05-22', 'YYYY-MM-DD'), '사용가능');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (4, 74, '7780', '소형', TO_DATE('2025-05-23', 'YYYY-MM-DD'), '잠김');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (4, 91, '8891', '대형', TO_DATE('2025-05-24', 'YYYY-MM-DD'), '보관');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (5, 25, '1238', '대형', TO_DATE('2025-05-15', 'YYYY-MM-DD'), '보관');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (5, 0, '0008', '중형', TO_DATE('2025-05-14', 'YYYY-MM-DD'), '사용가능');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (5, 0, '0009', '소형', TO_DATE('2025-05-18', 'YYYY-MM-DD'), '예약');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (5, 105, '2226', '대형', TO_DATE('2025-05-19', 'YYYY-MM-DD'), '잠김');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (5, 33, '3337', '중형', TO_DATE('2025-05-20', 'YYYY-MM-DD'), '사용가능');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (5, 14, '4448', '소형', TO_DATE('2025-05-21', 'YYYY-MM-DD'), '보관');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (5, 50, '5559', '대형', TO_DATE('2025-05-22', 'YYYY-MM-DD'), '예약');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (5, 0, '6670', '중형', TO_DATE('2025-05-23', 'YYYY-MM-DD'), '사용가능');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (5, 75, '7781', '소형', TO_DATE('2025-05-24', 'YYYY-MM-DD'), '잠김');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (5, 92, '8892', '대형', TO_DATE('2025-05-25', 'YYYY-MM-DD'), '보관');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (6, 23, '1239', '대형', TO_DATE('2025-05-16', 'YYYY-MM-DD'), '보관');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (6, 0, '0010', '중형', TO_DATE('2025-05-15', 'YYYY-MM-DD'), '사용가능');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (6, 0, '0011', '소형', TO_DATE('2025-05-19', 'YYYY-MM-DD'), '예약');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (6, 110, '2227', '대형', TO_DATE('2025-05-20', 'YYYY-MM-DD'), '잠김');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (6, 34, '3338', '중형', TO_DATE('2025-05-21', 'YYYY-MM-DD'), '사용가능');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (6, 15, '4449', '소형', TO_DATE('2025-05-22', 'YYYY-MM-DD'), '보관');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (6, 51, '5560', '대형', TO_DATE('2025-05-23', 'YYYY-MM-DD'), '예약');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (6, 0, '6671', '중형', TO_DATE('2025-05-24', 'YYYY-MM-DD'), '사용가능');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (6, 0, '7782', '소형', TO_DATE('2025-05-25', 'YYYY-MM-DD'), '예약');
INSERT INTO cabinet (station_number, storage_time, cabinet_password, cabinet_size, inspection_date, storage_status) VALUES (6, 120, '8893', '대형', TO_DATE('2025-05-26', 'YYYY-MM-DD'), '잠김');

-- 드론모델
INSERT INTO drone_model (drone_model_code, model_name, max_payload, max_range) VALUES ('DM1','Falcon X',200,8000);
INSERT INTO drone_model (drone_model_code, model_name, max_payload, max_range) VALUES ('DM2','Hawk 200',150,6000);
INSERT INTO drone_model (drone_model_code, model_name, max_payload, max_range) VALUES ('DM3','Eagle Pro',180,7000);

-- 드론
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (1, 'DM1', '33.4996:126.5312', 20, '고장');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (5, 'DM1', '37.6380:127.0256', 15, '고장');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (3, 'DM2', '36.6295:127.4581', 10, '고장');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (1, 'DM1', '33.4996:126.5312', 85, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (1, 'DM1', '33.4996:126.5312', 65, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (2, 'DM1', '36.6439:127.4892', 75, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (2, 'DM1', '36.6439:127.4892', 90, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (3, 'DM1', '36.6295:127.4581', 55, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (3, 'DM1', '36.6295:127.4581', 80, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (4, 'DM1', '37.5613:126.9259', 95, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (4, 'DM1', '37.5613:126.9259', 70, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (5, 'DM1', '37.6380:127.0256', 60, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (5, 'DM1', '37.6380:127.0256', 85, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (6, 'DM1', '37.5410:126.8407', 70, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (6, 'DM1', '37.5410:126.8407', 88, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (7, 'DM1', '37.4840:126.9291', 65, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (7, 'DM1', '37.4840:126.9291', 55, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (1, 'DM1', '33.4996:126.5312', 75, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (2, 'DM1', '36.6439:127.4892', 68, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (3, 'DM1', '36.6295:127.4581', 90, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (4, 'DM1', '37.5613:126.9259', 60, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (5, 'DM1', '37.6380:127.0256', 82, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (6, 'DM2', '37.5410:126.8407', 78, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (1, 'DM2', '33.4996:126.5312', 80, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (1, 'DM3', '33.4996:126.5312', 75, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (2, 'DM1', '36.6439:127.4892', 65, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (2, 'DM2', '36.6439:127.4892', 85, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (3, 'DM3', '36.6295:127.4581', 70, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (4, 'DM1', '37.5613:126.9259', 90, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (4, 'DM2', '37.5613:126.9259', 60, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (5, 'DM3', '37.6380:127.0256', 75, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (5, 'DM1', '37.6380:127.0256', 65, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (6, 'DM3', '37.5410:126.8407', 80, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (6, 'DM1', '37.5410:126.8407', 70, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (7, 'DM2', '37.4840:126.9291', 55, '사용가능');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (1, 'DM1', '33.4800:126.5200', 45, '예약');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (2, 'DM3', '36.6500:127.5000', 50, '예약');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (3, 'DM2', '36.6400:127.4700', 55, '예약');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (4, 'DM1', '37.5700:126.9300', 60, '예약');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (5, 'DM3', '37.6400:127.0400', 40, '예약');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (6, 'DM2', '37.5500:126.8300', 35, '예약');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (7, 'DM1', '37.4700:126.9100', 50, '예약');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (1, 'DM1', '33.6000:126.6000', 40, '예약');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (2, 'DM3', '36.7000:127.5500', 55, '예약');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (3, 'DM2', '36.7000:127.4000', 60, '예약');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (4, 'DM1', '37.6000:126.9000', 50, '예약');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (5, 'DM3', '37.7000:127.1000', 45, '예약');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (6, 'DM2', '37.6000:126.9000', 55, '예약');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (7, 'DM1', '37.5000:126.9500', 65, '예약');
INSERT INTO drone (station_number, drone_model_code, current_location, battery_status, operation_status) VALUES (7, 'DM3', '37.4800:126.9200', 70, '예약');

-- 드론사고내역
INSERT INTO drone_accident (drone_id, location, accident_video, accident_date)
VALUES (1, '33.4996:126.5312', 'accident_dm1_1.mp4', TO_DATE('2025-04-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO drone_accident (drone_id, location, accident_video, accident_date)
VALUES (2, '37.6380:127.0256', 'accident_dm1_1.mp4', TO_DATE('2025-04-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO drone_accident (drone_id, location, accident_video, accident_date)
VALUES (3, '36.6295:127.4581', NULL, TO_DATE('2025-04-15 09:20:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO drone_accident (drone_id, location, accident_video, accident_date)
VALUES (4, '36.6295:127.4581', NULL, TO_DATE('2025-05-15 09:20:00', 'YYYY-MM-DD HH24:MI:SS'));

-- 관리자
INSERT INTO manager (name, phone_number, responsibility)
VALUES ('김은경', '010-9999-0000', '배달');
INSERT INTO manager (name, phone_number, responsibility)
VALUES ('이용현', '010-3456-7890', '배달');
INSERT INTO manager (name, phone_number, responsibility)
VALUES ('임성아', '010-5678-9012', '시설');
INSERT INTO manager (name, phone_number, responsibility)
VALUES ('김경표', '010-2345-6789', '시설');
INSERT INTO manager (name, phone_number, responsibility)
VALUES ('이주상', '010-1234-5678', '드론');
INSERT INTO manager (name, phone_number, responsibility) 
VALUES ('김아리', '010-4567-8901', '드론');

-- 지역
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG001', '서울특별시 종로구 청운효자동', SYSDATE, '맑음', 5, '가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG002', '부산광역시 해운대구 우동', SYSDATE, '흐림', 4, '가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG003', '대구광역시 중구 동성로', SYSDATE, '맑음', 6, '가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG004', '인천광역시 남동구 구월동', SYSDATE, '안개', 3, '가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG005', '광주광역시 서구 쌍촌동', SYSDATE, '맑음', 5, '가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG006', '대전광역시 유성구 봉명동', SYSDATE, '흐림', 4, '가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG007', '울산광역시 남구 삼산동', SYSDATE, '맑음', 5, '가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG008', '세종특별자치시 고운동', SYSDATE, '안개', 2, '가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG009', '경기도 성남시 분당구 야탑동', SYSDATE, '맑음', 3, '가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG010', '강원도 춘천시 효자동', SYSDATE, '흐림', 4, '가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG011', '충청북도 청주시 상당구 문의면', SYSDATE, '맑음', 5, '가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG012', '충청남도 천안시 동남구 신부동', SYSDATE, '안개', 3, '가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG013', '전라북도 전주시 완산구 중노송동', SYSDATE, '맑음', 4, '가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG014', '전라남도 여수시 문수동', SYSDATE, '흐림', 3, '가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG015', '경상북도 포항시 남구 대잠동', SYSDATE, '맑음', 5, '가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG016', '경상남도 창원시 성산구 상남동', SYSDATE, '맑음', 6, '가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG017', '제주특별자치도 제주시 연동', SYSDATE, '흐림', 5, '가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG018', '경기도 고양시 일산동구 장항동', SYSDATE, '맑음', 4, '가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG019', '경기도 수원시 영통구 원천동', SYSDATE, '맑음', 3, '가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG020', '서울특별시 강남구 역삼동', SYSDATE, '비', 15, '불가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG021', '부산광역시 사하구 하단동', SYSDATE, '눈', 12, '불가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG022', '대구광역시 달서구 월성동', SYSDATE, '폭우', 18, '불가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG023', '인천광역시 서구 청라동', SYSDATE, '비', 14, '불가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG024', '광주광역시 북구 용봉동', SYSDATE, '강풍', 20, '불가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG025', '대전광역시 동구 판암동', SYSDATE, '비', 16, '불가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG026', '울산광역시 북구 연암동', SYSDATE, '눈', 13, '불가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG027', '세종특별자치시 소담동', SYSDATE, '폭우', 19, '불가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG028', '경기도 용인시 수지구 풍덕천동', SYSDATE, '비', 15, '불가능');
INSERT INTO region (region_code, region_name, last_updated_at, weather, wind_speed, is_flight_allowed) VALUES ('RG029', '강원도 원주시 무실동', SYSDATE, '강풍', 21, '불가능');

-- 특수물품
INSERT INTO special_item (special_item_code, category, special_reason) VALUES ('S01', '음식물', '냄새 때문에 밀봉되지 않은 음식물은 특수 드론을 통해서만 배달 가능합니다.');
INSERT INTO special_item (special_item_code, category, special_reason) VALUES ('S02', '유리', '적은 충격으로도 깨질 수 있기 때문에 유리 제품은 특수 드론을 통해서만 배달 가능합니다.');
INSERT INTO special_item (special_item_code, category, special_reason) VALUES ('S03', '의약품', '식품의약품안전관리 법으로 인해 의사 처방이 필요한 의약품은 특수 드론을 통해서만 배달 가능합니다.');
INSERT INTO special_item (special_item_code, category, special_reason) VALUES ('S04', '현금/귀금속', '도난 위험이 있으므로 고가의 제품은 특수 드론을 통해서만 배달 가능합니다.');
INSERT INTO special_item (special_item_code, category, special_reason) VALUES ('S05', '화학약품', '누출 시 환경 오염 위험 때문에 화학약품은 특수 드론을 통해서만 배달 가능합니다.');
INSERT INTO special_item (special_item_code, category, special_reason) VALUES ('S06', '폭발물', '폭발 위험이 있어 특수 드론으로만 안전하게 운송 가능합니다.');
INSERT INTO special_item (special_item_code, category, special_reason) VALUES ('S07', '생물체', '생명체 안전을 위해 특수 환경을 갖춘 드론에서만 운송 가능합니다.');
INSERT INTO special_item (special_item_code, category, special_reason) VALUES ('S08', '전자기기', '정전기 및 충격에 민감하여 특수 드론으로만 배달 가능합니다.');
INSERT INTO special_item (special_item_code, category, special_reason) VALUES ('S09', '미술품', '파손 위험이 크므로 특수 완충 장치가 있는 드론으로만 배달 가능합니다.');
INSERT INTO special_item (special_item_code, category, special_reason) VALUES ('S10', '의료기기', '정밀기기 특성상 특수 드론으로만 안전하게 배달 가능합니다.');

-- 일반회원
INSERT INTO user_member (id, pw, name, phone_number, account_info, membership_level, points, profile_image, last_login_at, active_status)
VALUES ('kimjiwoo11', 'jiwoo#123', '김지우', '010-2345-6789', '222-333-444555', '일반', 430, 'kimjiwoo.jpg', TO_DATE('2025-06-01 10:20:00','YYYY-MM-DD HH24:MI:SS'), '활성');
INSERT INTO user_member (id, pw, name, phone_number, account_info, membership_level, points, profile_image, last_login_at, active_status)
VALUES ('sunypark00', 'sunny12!', '박선영', '010-4321-8765', '111-999-333222', '일반', 9800, 'sunpark.png', TO_DATE('2024-11-10 09:10:00','YYYY-MM-DD HH24:MI:SS'), '활성');
INSERT INTO user_member (id, pw, name, phone_number, account_info, membership_level, points, profile_image, last_login_at, active_status)
VALUES ('shinny88', 'nyny88', '신현우', '010-6969-5656', '664-221-983247', '일반', 850, 'shinny.png', TO_DATE('2022-01-14 06:11:00','YYYY-MM-DD HH24:MI:SS'), '활성');
INSERT INTO user_member (id, pw, name, phone_number, account_info, membership_level, points, profile_image, last_login_at, active_status)
VALUES ('leehye12', 'passhey1', '이혜진', '010-9292-8181', '888-555-444333', '브론즈', 12400, 'leehye.png', TO_DATE('2022-06-01 19:57:00','YYYY-MM-DD HH24:MI:SS'), '활성');
INSERT INTO user_member (id, pw, name, phone_number, account_info, membership_level, points, profile_image, last_login_at, active_status)
VALUES ('ahnbyul95', 'starb95*', '안별', '010-7474-1515', '555-333-222111', '브론즈', 20350, 'ahnbyul.jpg', TO_DATE('2025-02-28 23:15:00','YYYY-MM-DD HH24:MI:SS'), '활성');
INSERT INTO user_member (id, pw, name, phone_number, account_info, membership_level, points, profile_image, last_login_at, active_status)
VALUES ('kangmj20', 'kangpass', '강민재', '010-3939-9494', '991-128-338321', '브론즈', 24100, 'kangmj.png', TO_DATE('2025-01-07 08:00:00','YYYY-MM-DD HH24:MI:SS'), '활성');
INSERT INTO user_member (id, pw, name, phone_number, account_info, membership_level, points, profile_image, last_login_at, active_status)
VALUES ('banahanam', 'hanaBan1', '배하나', '010-9191-1717', '373-383-848294', '브론즈', 20000, 'banahana.png', TO_DATE('2025-03-03 18:22:00','YYYY-MM-DD HH24:MI:SS'), '활성');
INSERT INTO user_member (id, pw, name, phone_number, account_info, membership_level, points, profile_image, last_login_at, active_status)
VALUES ('kimjae123', 'kimjae$1', '김재훈', '010-2255-1122', '313-212-232323', '실버', 25300, 'kimjae.jpg', TO_DATE('2024-09-02 10:05:00','YYYY-MM-DD HH24:MI:SS'), '활성');
INSERT INTO user_member (id, pw, name, phone_number, account_info, membership_level, points, profile_image, last_login_at, active_status)
VALUES ('junghee95', 'jhhee95', '조정희', '010-7777-9999', '555-888-333222', '실버', 43000, 'junghee.jpg', TO_DATE('2022-09-12 22:01:00','YYYY-MM-DD HH24:MI:SS'), '활성');
INSERT INTO user_member (id, pw, name, phone_number, account_info, membership_level, points, profile_image, last_login_at, active_status)
VALUES ('jooyoung44', 'jyoung44!', '주영은', '010-2323-8585', '483-294-349282', '실버', 31200, 'jooyoung.jpg', TO_DATE('2024-01-01 03:02:00','YYYY-MM-DD HH24:MI:SS'), '활성');
INSERT INTO user_member (id, pw, name, phone_number, account_info, membership_level, points, profile_image, last_login_at, active_status)
VALUES ('ohyejin01', 'yejin01!', '오예진', '010-6464-3434', '838-181-939494', '실버', 29999, 'ohyejin.png', TO_DATE('2021-11-11 11:11:00','YYYY-MM-DD HH24:MI:SS'), '활성');
INSERT INTO user_member (id, pw, name, phone_number, account_info, membership_level, points, profile_image, last_login_at, active_status)
VALUES ('songminji8', 'mj2023#', '송민지', '010-1313-2424', '414-515-616171', '실버', 40000, 'songmj.png', TO_DATE('2024-08-16 18:13:00','YYYY-MM-DD HH24:MI:SS'), '활성');
INSERT INTO user_member (id, pw, name, phone_number, account_info, membership_level, points, profile_image, last_login_at, active_status)
VALUES ('hwangsu17', 'goldenH$', '황수진', '010-1122-3344', '333-222-111000', '골드', 51100, 'hwangsu.jpg', TO_DATE('2023-04-15 16:50:00','YYYY-MM-DD HH24:MI:SS'), '활성');
INSERT INTO user_member (id, pw, name, phone_number, account_info, membership_level, points, profile_image, last_login_at, active_status)
VALUES ('parkjs82', 'jsPark!11', '박지수', '010-1212-3434', '989-898-777666', '골드', 80000, 'parkjs.jpg', TO_DATE('2025-04-18 12:32:00','YYYY-MM-DD HH24:MI:SS'), '활성');
INSERT INTO user_member (id, pw, name, phone_number, account_info, membership_level, points, profile_image, last_login_at, active_status)
VALUES ('gohyeon03', 'gohey3', '고현주', '010-8585-9595', '117-222-333444', '골드', 99999, 'gohyeon.jpg', TO_DATE('2023-08-11 16:00:00','YYYY-MM-DD HH24:MI:SS'), '휴면');
INSERT INTO user_member (id, pw, name, phone_number, account_info, membership_level, points, profile_image, last_login_at, active_status)
VALUES ('moonseo90', 'msmoon1', '문서영', '010-3737-6363', '484-378-494949', '골드', 55000, 'moonseo.jpg', TO_DATE('2024-02-08 11:04:00','YYYY-MM-DD HH24:MI:SS'), '휴면');
INSERT INTO user_member (id, pw, name, phone_number, account_info, membership_level, points, profile_image, last_login_at, active_status)
VALUES ('limhayul', 'lhayul2', '임하율', '010-1919-8282', '333-555-777888', '골드', 62000, 'limhayul.jpg', TO_DATE('2024-12-20 12:12:00','YYYY-MM-DD HH24:MI:SS'), '휴면');
INSERT INTO user_member (id, pw, name, phone_number, account_info, membership_level, points, profile_image, last_login_at, active_status)
VALUES ('yoonsj01', 'yunpass8', '윤서진', '010-5678-1234', '123-456-789123', '일반', 0, 'yoonseo.jpg', TO_DATE('2024-07-05 13:40:00','YYYY-MM-DD HH24:MI:SS'), '휴면');
INSERT INTO user_member (id, pw, name, phone_number, account_info, membership_level, points, profile_image, last_login_at, active_status)
VALUES ('choiseok0', 'sksk1234', '최석호', '010-8282-3939', '616-313-212122', '일반', 120, 'choiseok.png', TO_DATE('2021-10-22 14:11:00','YYYY-MM-DD HH24:MI:SS'), '휴면');
INSERT INTO user_member (id, pw, name, phone_number, account_info, membership_level, points, profile_image, last_login_at, active_status)
VALUES ('seojs777', 'sjseo777', '서지수', '010-8585-1818', '292-384-882342', '브론즈', 10000, 'seojs.png', TO_DATE('2023-03-14 21:54:00','YYYY-MM-DD HH24:MI:SS'), '휴면');

-- 업체회원
INSERT INTO company_member (id, pw, name, phone_number, profile_image, account_info, active_status, business_number, company_name, last_login_at) VALUES ('chllllsu', 'younghee!', '김철수', '010-1234-5678', 'profile1124431.jpg', '카카오 123-456-789012', '활성', '123456789012', '맛있는 치킨', TO_DATE('2025-06-02 18:45:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO company_member (id, pw, name, phone_number, profile_image, account_info, active_status, business_number, company_name, last_login_at) VALUES ('myIQ1800', '1plus1=1#', '이영희', '010-8765-4321', 'profile384882.png', '신한 987-654-321098', '휴면', '987654321098', '한솥도시락 한기대점', TO_DATE('2021-05-29 09:20:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO company_member (id, pw, name, phone_number, profile_image, account_info, active_status, business_number, company_name, last_login_at) VALUES ('seehum', 'mangham:(', '정약용', '010-5555-6666', 'profile901002.png', '우리 555-666-777888', '활성', '555666777888', 'BHC 한기대점', TO_DATE('2025-05-28 22:10:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO company_member (id, pw, name, phone_number, profile_image, account_info, active_status, business_number, company_name, last_login_at) VALUES ('happyfood', 'LoveChicken@123', '최영호', '010-1111-2222', 'profile_happyfood.jpg', '국민 111-222-333444', '활성', '112233445566', '해피푸드', TO_DATE('2025-05-31 08:20:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO company_member (id, pw, name, phone_number, profile_image, account_info, active_status, business_number, company_name, last_login_at) VALUES ('techguru', 'Gadget!2025', '송지은', '010-3333-4444', 'profile_techguru.png', '신한 222-333-444555', '활성', '223344556677', '구루구루', TO_DATE('2025-04-15 14:50:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO company_member (id, pw, name, phone_number, profile_image, account_info, active_status, business_number, company_name, last_login_at) VALUES ('greenfarm', 'GreenLife#1', '박민정', '010-5555-7777', 'profile_greenfarm.png', '하나 333-444-555666', '활성', '334455667788', '그린팜', TO_DATE('2025-05-29 19:05:00','YYYY-MM-DD HH24:MI:SS'));

-- 활동내역
INSERT INTO activity (user_id, activity_number, drone_departure_at, drone_arrival_at, departure_station_name, arrival_station_name, delivery_status, item_name, item_category, delivery_fee) VALUES
('kimjiwoo11', 1, TO_DATE('2025-06-01 08:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-01 09:45:00','YYYY-MM-DD HH24:MI:SS'), '중흥동 정류장', '이도이동 정류장', '완료', '노트북', '전자제품', 5000);
INSERT INTO activity (user_id, activity_number, drone_departure_at, drone_arrival_at, departure_station_name, arrival_station_name, delivery_status, item_name, item_category, delivery_fee) VALUES                                  
('kimjiwoo11', 2, TO_DATE('2025-06-02 10:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-02 11:30:00','YYYY-MM-DD HH24:MI:SS'), '이도이동 정류장', '율량동 정류장', '완료', '책', '도서', 4500);
INSERT INTO activity (user_id, activity_number, drone_departure_at, drone_arrival_at, departure_station_name, arrival_station_name, delivery_status, item_name, item_category, delivery_fee) VALUES                                  
('kimjiwoo11', 3, TO_DATE('2025-06-02 12:15:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-02 13:45:00','YYYY-MM-DD HH24:MI:SS'), '율량동 정류장', '봉명동 정류장', '완료', '의류', '패션', 7000);
INSERT INTO activity (user_id, activity_number, drone_departure_at, drone_arrival_at, departure_station_name, arrival_station_name, delivery_status, item_name, item_category, delivery_fee) VALUES                                  
('kimjiwoo11', 4, TO_DATE('2025-06-03 09:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-03 09:45:00','YYYY-MM-DD HH24:MI:SS'), '봉명동 정류장', '연남동 정류장', '완료', '화장품', '뷰티', 3500);
INSERT INTO activity (user_id, activity_number, drone_departure_at, drone_arrival_at, departure_station_name, arrival_station_name, delivery_status, item_name, item_category, delivery_fee) VALUES                                  
('kimjiwoo11', 5, TO_DATE('2025-06-03 10:30:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-03 12:00:00','YYYY-MM-DD HH24:MI:SS'), '연남동 정류장', '수유역 정류장', '완료', '전자책', '전자제품', 6000);
INSERT INTO activity (user_id, activity_number, drone_departure_at, drone_arrival_at, departure_station_name, arrival_station_name, delivery_status, item_name, item_category, delivery_fee) VALUES                                  
('kimjiwoo11', 6, TO_DATE('2025-06-04 14:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-04 15:30:00','YYYY-MM-DD HH24:MI:SS'), '수유역 정류장', '화곡동 정류장', '완료', '장난감', '기타', 4000);
INSERT INTO activity (user_id, activity_number, drone_departure_at, drone_arrival_at, departure_station_name, arrival_station_name, delivery_status, item_name, item_category, delivery_fee) VALUES                                  
('kimjiwoo11', 7, TO_DATE('2025-06-04 16:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-04 16:45:00','YYYY-MM-DD HH24:MI:SS'), '화곡동 정류장', '중흥동 정류장', '완료', '스마트폰', '전자제품', 8500);
INSERT INTO activity (user_id, activity_number, drone_departure_at, drone_arrival_at, departure_station_name, arrival_station_name, delivery_status, item_name, item_category, delivery_fee) VALUES                                  
('kimjiwoo11', 8, TO_DATE('2025-06-05 08:30:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-05 09:15:00','YYYY-MM-DD HH24:MI:SS'), '중흥동 정류장', '율량동 정류장', '완료', '악세사리', '패션', 3000);
INSERT INTO activity (user_id, activity_number, drone_departure_at, drone_arrival_at, departure_station_name, arrival_station_name, delivery_status, item_name, item_category, delivery_fee) VALUES                                  
('kimjiwoo11', 9, TO_DATE('2025-06-01 08:30:00','YYYY-MM-DD HH24:MI:SS'), NULL, '역삼동 정류장', '우동 정류장', '사고', '문서', '기타', 3000);
INSERT INTO activity (user_id, activity_number, drone_departure_at, drone_arrival_at, departure_station_name, arrival_station_name, delivery_status, item_name, item_category, delivery_fee) VALUES                                  
('sunypark00', 1, TO_DATE('2025-06-05 10:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-05 11:00:00','YYYY-MM-DD HH24:MI:SS'), '율량동 정류장', '연남동 정류장', '완료', '책', '도서', 4500);
INSERT INTO activity (user_id, activity_number, drone_departure_at, drone_arrival_at, departure_station_name, arrival_station_name, delivery_status, item_name, item_category, delivery_fee) VALUES                                  
('sunypark00', 2, TO_DATE('2025-06-05 11:30:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-05 12:30:00','YYYY-MM-DD HH24:MI:SS'), '봉명동 정류장', '수유역 정류장', '완료', '의류', '패션', 7000);
INSERT INTO activity (user_id, activity_number, drone_departure_at, drone_arrival_at, departure_station_name, arrival_station_name, delivery_status, item_name, item_category, delivery_fee) VALUES                                  
('leehye12', 1, TO_DATE('2025-06-06 13:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-06 14:30:00','YYYY-MM-DD HH24:MI:SS'), '연남동 정류장', '중흥동 정류장', '완료', '화장품', '뷰티', 3500);
INSERT INTO activity (user_id, activity_number, drone_departure_at, drone_arrival_at, departure_station_name, arrival_station_name, delivery_status, item_name, item_category, delivery_fee) VALUES                                  
('leehye12', 2, TO_DATE('2025-06-06 15:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-06 16:00:00','YYYY-MM-DD HH24:MI:SS'), '수유역 정류장', '봉명동 정류장', '완료', '전자책', '전자제품', 6000);
INSERT INTO activity (user_id, activity_number, drone_departure_at, drone_arrival_at, departure_station_name, arrival_station_name, delivery_status, item_name, item_category, delivery_fee) VALUES                                  
('junghee95', 1, TO_DATE('2025-06-07 09:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-07 09:45:00','YYYY-MM-DD HH24:MI:SS'), '화곡동 정류장', '이도이동 정류장', '완료', '장난감', '기타', 4000);
INSERT INTO activity (user_id, activity_number, drone_departure_at, drone_arrival_at, departure_station_name, arrival_station_name, delivery_status, item_name, item_category, delivery_fee) VALUES                                  
('kangmj20', 1, TO_DATE('2025-06-07 10:15:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-07 11:00:00','YYYY-MM-DD HH24:MI:SS'), '중흥동 정류장', '우동 정류장', '완료', '스마트폰', '전자제품', 8500);
INSERT INTO activity (user_id, activity_number, drone_departure_at, drone_arrival_at, departure_station_name, arrival_station_name, delivery_status, item_name, item_category, delivery_fee) VALUES                                  
('kangmj20', 2, TO_DATE('2025-06-07 12:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-07 13:30:00','YYYY-MM-DD HH24:MI:SS'), '우동 정류장', '율량동 정류장', '완료', '악세사리', '패션', 3000);
INSERT INTO activity (user_id, activity_number, drone_departure_at, drone_arrival_at, departure_station_name, arrival_station_name, delivery_status, item_name, item_category, delivery_fee) VALUES                                  
('kimjae123', 1, TO_DATE('2025-06-08 08:30:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-08 09:45:00','YYYY-MM-DD HH24:MI:SS'), '율량동 정류장', '봉명동 정류장', '완료', '책', '도서', 4500);
INSERT INTO activity (user_id, activity_number, drone_departure_at, drone_arrival_at, departure_station_name, arrival_station_name, delivery_status, item_name, item_category, delivery_fee) VALUES                                  
('kimjae123', 2, TO_DATE('2025-06-08 10:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-08 11:15:00','YYYY-MM-DD HH24:MI:SS'), '봉명동 정류장', '연남동 정류장', '완료', '의류', '패션', 7000);
INSERT INTO activity (user_id, activity_number, drone_departure_at, drone_arrival_at, departure_station_name, arrival_station_name, delivery_status, item_name, item_category, delivery_fee) VALUES                                  
('kimjae123', 3, TO_DATE('2025-06-08 12:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-08 13:00:00','YYYY-MM-DD HH24:MI:SS'), '연남동 정류장', '수유역 정류장', '완료', '화장품', '뷰티', 3500);
INSERT INTO activity (user_id, activity_number, drone_departure_at, drone_arrival_at, departure_station_name, arrival_station_name, delivery_status, item_name, item_category, delivery_fee) VALUES                                  
('kimjae123', 4, TO_DATE('2025-06-08 14:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-08 15:30:00','YYYY-MM-DD HH24:MI:SS'), '수유역 정류장', '화곡동 정류장', '완료', '전자책', '전자제품', 6000);
INSERT INTO activity (user_id, activity_number, drone_departure_at, drone_arrival_at, departure_station_name, arrival_station_name, delivery_status, item_name, item_category, delivery_fee) VALUES                                  
('parkjs82', 1, TO_DATE('2025-05-28 11:30:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-05-29 11:50:00','YYYY-MM-DD HH24:MI:SS'), '역삼동 정류장', '우동 정류장', '반송', '마우스', '전자제품', 8000);

-- 리뷰
INSERT INTO review (user_id, content, photo_url, rating, created_at) VALUES ('kimjiwoo11', '노트북이 안전하게 잘 도착했어요. 만족합니다!', 'review4.jpg', 5, TO_DATE('2025-06-01 10:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO review (user_id, content, photo_url, rating, created_at) VALUES ('kimjiwoo11', '책 상태가 깔끔하고, 시간도 잘 지켜졌어요.', NULL, 5, TO_DATE('2025-06-02 12:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO review (user_id, content, photo_url, rating, created_at) VALUES ('kimjiwoo11', '배송은 빠른데 의류 포장이 조금 아쉬웠어요.', 'review6.png', 4, TO_DATE('2025-06-02 14:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO review (user_id, content, photo_url, rating, created_at) VALUES ('kimjiwoo11', '화장품 배송이 정말 빨랐어요. 감사합니다.', NULL, 5, TO_DATE('2025-06-03 10:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO review (user_id, content, photo_url, rating, created_at) VALUES ('kimjiwoo11', '전자책이라 걱정했는데 문제없이 잘 받았어요.', NULL, 5, TO_DATE('2025-06-03 12:30:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO review (user_id, content, photo_url, rating, created_at) VALUES ('kimjiwoo11', '장난감 배송이 정확했어요. 아이가 좋아해요!', 'review9.jpg', 5, TO_DATE('2025-06-04 16:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO review (user_id, content, photo_url, rating, created_at) VALUES ('kimjiwoo11', '스마트폰 포장도 꼼꼼하고 빠르게 받았습니다.', NULL, 5, TO_DATE('2025-06-04 17:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO review (user_id, content, photo_url, rating, created_at) VALUES ('kimjiwoo11', '악세사리도 예쁘게 잘 받았어요. 만족합니다.', NULL, 5, TO_DATE('2025-06-05 09:30:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO review (user_id, content, photo_url, rating, created_at) VALUES ('kimjiwoo11', '사고가 나서 제 문서들이 걸래짝이 됐어요.', NULL, 1, TO_DATE('2025-06-01 10:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO review (user_id, content, photo_url, rating, created_at) VALUES ('sunypark00', '책 상태도 좋고, 정시에 도착했어요!', 'review12.jpg', 5, TO_DATE('2025-06-05 12:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO review (user_id, content, photo_url, rating, created_at) VALUES ('sunypark00', '의류 배송이 깔끔하게 이루어졌습니다.', NULL, 4, TO_DATE('2025-06-05 13:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO review (user_id, content, photo_url, rating, created_at) VALUES ('leehye12', '화장품이 잘 도착했어요. 향도 좋아요.', NULL, 5, TO_DATE('2025-06-06 15:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO review (user_id, content, photo_url, rating, created_at) VALUES ('leehye12', '전자책 배송이 정말 신속해서 놀랐어요.', NULL, 5, TO_DATE('2025-06-06 17:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO review (user_id, content, photo_url, rating, created_at) VALUES ('junghee95', '장난감 배송 잘 받았습니다. 감사합니다!', NULL, 5, TO_DATE('2025-06-07 10:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO review (user_id, content, photo_url, rating, created_at) VALUES ('kangmj20', '스마트폰 잘 받았습니다. 포장도 좋았어요.', 'review17.jpg', 5, TO_DATE('2025-06-07 12:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO review (user_id, content, photo_url, rating, created_at) VALUES ('kangmj20', '악세사리 배송 빠르고 안전했어요.', NULL, 5, TO_DATE('2025-06-07 14:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO review (user_id, content, photo_url, rating, created_at) VALUES ('kimjae123', '책 배송이 꼼꼼하게 잘 왔습니다.', NULL, 5, TO_DATE('2025-06-08 10:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO review (user_id, content, photo_url, rating, created_at) VALUES ('kimjae123', '의류 배송이 예상보다 빨랐어요!', 'review20.png', 5, TO_DATE('2025-06-08 12:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO review (user_id, content, photo_url, rating, created_at) VALUES ('kimjae123', '화장품이 잘 포장돼서 도착했어요.', NULL, 4, TO_DATE('2025-06-08 14:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO review (user_id, content, photo_url, rating, created_at) VALUES ('kimjae123', '전자책도 무사히 도착했습니다. 만족해요.', NULL, 5, TO_DATE('2025-06-08 16:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO review (user_id, content, photo_url, rating, created_at) VALUES ('parkjs82', '반송 처리까지 빠르게 대응해주셔서 괜찮았습니다.', NULL, 3, TO_DATE('2025-05-29 13:00:00','YYYY-MM-DD HH24:MI:SS'));

-- 캐비닛관리하다
INSERT INTO cabinet_maintenance (cabinet_id, manager_id, work_date, work_type) VALUES
(4, 3, TO_DATE('2025-06-04 10:00:00','YYYY-MM-DD HH24:MI:SS'), '개방');
INSERT INTO cabinet_maintenance (cabinet_id, manager_id, work_date, work_type) VALUES
(5, 3, TO_DATE('2025-06-04 15:00:00','YYYY-MM-DD HH24:MI:SS'), '폐기');
INSERT INTO cabinet_maintenance (cabinet_id, manager_id, work_date, work_type) VALUES
(6, 4, TO_DATE('2025-06-05 09:00:00','YYYY-MM-DD HH24:MI:SS'), '개방');
INSERT INTO cabinet_maintenance (cabinet_id, manager_id, work_date, work_type) VALUES
(7, 4, TO_DATE('2025-06-05 11:30:00','YYYY-MM-DD HH24:MI:SS'), '폐기');
INSERT INTO cabinet_maintenance (cabinet_id, manager_id, work_date, work_type) VALUES
(8, 3, TO_DATE('2025-06-06 13:45:00','YYYY-MM-DD HH24:MI:SS'), '개방');
INSERT INTO cabinet_maintenance (cabinet_id, manager_id, work_date, work_type) VALUES
(21, 4, TO_DATE('2025-06-07 08:20:00','YYYY-MM-DD HH24:MI:SS'), '폐기');

-- 드론점검하다
INSERT INTO drone_inspection (manager_id, drone_id, inspection_date, battery_status, propeller_status, gps_accuracy, sensor_operational) VALUES
(5, 4, TO_DATE('2025-06-03 09:00:00','YYYY-MM-DD HH24:MI:SS'), '좋음', '좋음', '좋음', '가능');
INSERT INTO drone_inspection (manager_id, drone_id, inspection_date, battery_status, propeller_status, gps_accuracy, sensor_operational) VALUES
(6, 5, TO_DATE('2025-06-03 10:30:00','YYYY-MM-DD HH24:MI:SS'), '보통', '보통', '보통', '가능');
INSERT INTO drone_inspection (manager_id, drone_id, inspection_date, battery_status, propeller_status, gps_accuracy, sensor_operational) VALUES
(5, 6, TO_DATE('2025-06-03 14:20:00','YYYY-MM-DD HH24:MI:SS'), '나쁨', '나쁨', '나쁨', '불가능');
INSERT INTO drone_inspection (manager_id, drone_id, inspection_date, battery_status, propeller_status, gps_accuracy, sensor_operational) VALUES
(6, 7, TO_DATE('2025-06-04 08:00:00','YYYY-MM-DD HH24:MI:SS'), '좋음', '좋음', '보통', '가능');
INSERT INTO drone_inspection (manager_id, drone_id, inspection_date, battery_status, propeller_status, gps_accuracy, sensor_operational) VALUES
(5, 8, TO_DATE('2025-06-04 09:45:00','YYYY-MM-DD HH24:MI:SS'), '보통', '좋음', '좋음', '가능');
INSERT INTO drone_inspection (manager_id, drone_id, inspection_date, battery_status, propeller_status, gps_accuracy, sensor_operational) VALUES
(6, 9, TO_DATE('2025-06-04 11:15:00','YYYY-MM-DD HH24:MI:SS'), '나쁨', '보통', '보통', '불가능');
INSERT INTO drone_inspection (manager_id, drone_id, inspection_date, battery_status, propeller_status, gps_accuracy, sensor_operational) VALUES
(5, 10, TO_DATE('2025-06-04 13:00:00','YYYY-MM-DD HH24:MI:SS'), '좋음', '보통', '좋음', '가능');
INSERT INTO drone_inspection (manager_id, drone_id, inspection_date, battery_status, propeller_status, gps_accuracy, sensor_operational) VALUES
(6, 11, TO_DATE('2025-06-04 15:30:00','YYYY-MM-DD HH24:MI:SS'), '보통', '나쁨', '보통', '불가능');
INSERT INTO drone_inspection (manager_id, drone_id, inspection_date, battery_status, propeller_status, gps_accuracy, sensor_operational) VALUES
(5, 12, TO_DATE('2025-06-05 10:00:00','YYYY-MM-DD HH24:MI:SS'), '좋음', '좋음', '좋음', '가능');
INSERT INTO drone_inspection (manager_id, drone_id, inspection_date, battery_status, propeller_status, gps_accuracy, sensor_operational) VALUES
(6, 13, TO_DATE('2025-06-05 11:30:00','YYYY-MM-DD HH24:MI:SS'), '나쁨', '나쁨', '나쁨', '불가능');

-- 물품배달하다
INSERT INTO item_delivery (cabinet_id, drone_id, delivery_route, delivery_status, estimated_arrival_at, departure_at, arrival_at) VALUES (4, 4, '37.5700:126.9300/37.5710:126.9310/37.5720:126.9320/37.5730:126.9330/37.5740:126.9340', '배달중',
TO_DATE('2025-06-03 11:30:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-03 10:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-03 11:30:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO item_delivery (cabinet_id, drone_id, delivery_route, delivery_status, estimated_arrival_at, departure_at, arrival_at) VALUES (5, 5, '37.5700:126.9300', '배달준비',
TO_DATE('2025-06-03 13:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-03 11:20:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-03 13:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO item_delivery (cabinet_id, drone_id, delivery_route, delivery_status, estimated_arrival_at, departure_at, arrival_at) VALUES (6, 6, '37.5500:126.8300/37.5510:126.8310/37.5520:126.8320/37.5530:126.8330/37.5540:126.8340', '배달중',
TO_DATE('2025-06-03 14:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-03 12:15:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-03 14:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO item_delivery (cabinet_id, drone_id, delivery_route, delivery_status, estimated_arrival_at, departure_at, arrival_at) VALUES (7, 7, '37.4700:126.9100/37.4710:126.9110/37.4720:126.9120/37.4730:126.9130/37.4740:126.9140', '배달중',
TO_DATE('2025-06-03 15:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-03 13:30:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-03 15:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO item_delivery (cabinet_id, drone_id, delivery_route, delivery_status, estimated_arrival_at, departure_at, arrival_at) VALUES (8, 8, '33.6000:126.6000/33.6010:126.6010/33.6020:126.6020/33.6030:126.6030/33.6040:126.6040', '배달중',
TO_DATE('2025-06-03 16:30:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-03 15:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-03 16:30:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO item_delivery (cabinet_id, drone_id, delivery_route, delivery_status, estimated_arrival_at, departure_at, arrival_at) VALUES (9, 9, '36.7000:127.5500/36.7010:127.5510/36.7020:127.5520/36.7030:127.5530/36.7040:127.5540', '배달중',
TO_DATE('2025-06-03 18:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-03 16:30:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-03 18:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO item_delivery (cabinet_id, drone_id, delivery_route, delivery_status, estimated_arrival_at, departure_at, arrival_at) VALUES (10, 10, '36.7000:127.4000/36.7010:127.4010/36.7020:127.4020/36.7030:127.4030/36.7040:127.4040', '배달중',
TO_DATE('2025-06-03 19:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-03 17:45:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-03 19:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO item_delivery (cabinet_id, drone_id, delivery_route, delivery_status, estimated_arrival_at, departure_at, arrival_at) VALUES (11, 11, '37.6000:126.9000/37.6010:126.9010/37.6020:126.9020/37.6030:126.9030/37.6040:126.9040', '배달중',
TO_DATE('2025-06-03 20:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-03 18:30:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-03 20:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO item_delivery (cabinet_id, drone_id, delivery_route, delivery_status, estimated_arrival_at, departure_at, arrival_at) VALUES (12, 12, '37.7000:127.1000/37.7010:127.1010/37.7020:127.1020/37.7030:127.1030/37.7040:127.1040', '배달중',
TO_DATE('2025-06-04 08:30:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-04 07:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-04 08:30:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO item_delivery (cabinet_id, drone_id, delivery_route, delivery_status, estimated_arrival_at, departure_at, arrival_at) VALUES (13, 13, '37.6000:126.9000/37.6010:126.9010/37.6020:126.9020/37.6030:126.9030/37.6040:126.9040', '배달중',
TO_DATE('2025-06-04 10:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-04 08:30:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-04 10:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO item_delivery (cabinet_id, drone_id, delivery_route, delivery_status, estimated_arrival_at, departure_at, arrival_at) VALUES (14, 14, '37.5000:126.9500/37.5010:126.9510/37.5020:126.9520/37.5030:126.9530/37.5040:126.9540', '배달중',
TO_DATE('2025-06-04 11:30:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-04 10:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-04 11:30:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO item_delivery (cabinet_id, drone_id, delivery_route, delivery_status, estimated_arrival_at, departure_at, arrival_at) VALUES (15, 15, '37.4800:126.9200/37.4810:126.9210/37.4820:126.9220/37.4830:126.9230/37.4840:126.9240', '배달중',
TO_DATE('2025-06-04 13:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-04 11:15:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-04 13:00:00','YYYY-MM-DD HH24:MI:SS'));

-- 물품담당하다
INSERT INTO special_item_assignment (special_item_code, drone_model_code) VALUES ('S01', 'DM1');
INSERT INTO special_item_assignment (special_item_code, drone_model_code) VALUES ('S08', 'DM2');
INSERT INTO special_item_assignment (special_item_code, drone_model_code) VALUES ('S02', 'DM2');
INSERT INTO special_item_assignment (special_item_code, drone_model_code) VALUES ('S03', 'DM2');
INSERT INTO special_item_assignment (special_item_code, drone_model_code) VALUES ('S04', 'DM3');
INSERT INTO special_item_assignment (special_item_code, drone_model_code) VALUES ('S05', 'DM3');
INSERT INTO special_item_assignment (special_item_code, drone_model_code) VALUES ('S06', 'DM3');
INSERT INTO special_item_assignment (special_item_code, drone_model_code) VALUES ('S07', 'DM3');
INSERT INTO special_item_assignment (special_item_code, drone_model_code) VALUES ('S08', 'DM3');
INSERT INTO special_item_assignment (special_item_code, drone_model_code) VALUES ('S09', 'DM3');
INSERT INTO special_item_assignment (special_item_code, drone_model_code) VALUES ('S10', 'DM3');

-- 일반회원예약하다
INSERT INTO user_reservation (drone_id, user_id, reservation_at, departure_location, arrival_location, item_name, item_category, delivery_fee) VALUES (1, 'kimjiwoo11', TO_DATE('2025-06-01 09:00:00','YYYY-MM-DD HH24:MI:SS'), '중흥동 정류장', '이도이동 정류장', '노트북', '전자제품', 5000);
INSERT INTO user_reservation (drone_id, user_id, reservation_at, departure_location, arrival_location, item_name, item_category, delivery_fee) VALUES (2, 'kimjiwoo11', TO_DATE('2025-06-02 10:30:00','YYYY-MM-DD HH24:MI:SS'), '역삼동 정류장', '우동 정류장', '문서', '기타', 3000);
INSERT INTO user_reservation (drone_id, user_id, reservation_at, departure_location, arrival_location, item_name, item_category, delivery_fee) VALUES (1, 'kimjiwoo11', TO_DATE('2025-06-03 11:15:00','YYYY-MM-DD HH24:MI:SS'), '역삼동 정류장', '우동 정류장', '마우스', '전자제품', 8000);
INSERT INTO user_reservation (drone_id, user_id, reservation_at, departure_location, arrival_location, item_name, item_category, delivery_fee) VALUES (2, 'kimjiwoo11', TO_DATE('2025-06-04 14:45:00','YYYY-MM-DD HH24:MI:SS'), '중흥동 정류장', '중앙동 정류장', '책', '기타', 4000);
INSERT INTO user_reservation (drone_id, user_id, reservation_at, departure_location, arrival_location, item_name, item_category, delivery_fee) VALUES (1, 'sunypark00', TO_DATE('2025-06-05 09:30:00','YYYY-MM-DD HH24:MI:SS'), '우동 정류장', '역삼동 정류장', '스마트폰', '전자제품', 6000);
INSERT INTO user_reservation (drone_id, user_id, reservation_at, departure_location, arrival_location, item_name, item_category, delivery_fee) VALUES (2, 'sunypark00', TO_DATE('2025-06-06 10:00:00','YYYY-MM-DD HH24:MI:SS'), '중앙동 정류장', '중흥동 정류장', '의류', '기타', 4500);
INSERT INTO user_reservation (drone_id, user_id, reservation_at, departure_location, arrival_location, item_name, item_category, delivery_fee) VALUES (1, 'sunypark00', TO_DATE('2025-06-07 11:20:00','YYYY-MM-DD HH24:MI:SS'), '이도이동 정류장', '중흥동 정류장', '이어폰', '전자제품', 3500);
INSERT INTO user_reservation (drone_id, user_id, reservation_at, departure_location, arrival_location, item_name, item_category, delivery_fee) VALUES (2, 'sunypark00', TO_DATE('2025-06-08 15:10:00','YYYY-MM-DD HH24:MI:SS'), '우동 정류장', '역삼동 정류장', '문서', '기타', 3000);
INSERT INTO user_reservation (drone_id, user_id, reservation_at, departure_location, arrival_location, item_name, item_category, delivery_fee) VALUES (1, 'shinny88', TO_DATE('2025-06-09 08:45:00','YYYY-MM-DD HH24:MI:SS'), '역삼동 정류장', '중앙동 정류장', '키보드', '전자제품', 7500);
INSERT INTO user_reservation (drone_id, user_id, reservation_at, departure_location, arrival_location, item_name, item_category, delivery_fee) VALUES (2, 'shinny88', TO_DATE('2025-06-10 12:30:00','YYYY-MM-DD HH24:MI:SS'), '중앙동 정류장', '이도이동 정류장', '책', '기타', 4000);
INSERT INTO user_reservation (drone_id, user_id, reservation_at, departure_location, arrival_location, item_name, item_category, delivery_fee) VALUES (1, 'shinny88', TO_DATE('2025-06-11 13:00:00','YYYY-MM-DD HH24:MI:SS'), '중흥동 정류장', '우동 정류장', '노트북', '전자제품', 5000);
INSERT INTO user_reservation (drone_id, user_id, reservation_at, departure_location, arrival_location, item_name, item_category, delivery_fee) VALUES (2, 'kimjiwoo11', TO_DATE('2025-06-12 09:00:00','YYYY-MM-DD HH24:MI:SS'), '이도이동 정류장', '역삼동 정류장', '문서', '기타', 3000);
INSERT INTO user_reservation (drone_id, user_id, reservation_at, departure_location, arrival_location, item_name, item_category, delivery_fee) VALUES (1, 'sunypark00', TO_DATE('2025-06-13 10:45:00','YYYY-MM-DD HH24:MI:SS'), '우동 정류장', '중앙동 정류장', '스마트워치', '전자제품', 6500);
INSERT INTO user_reservation (drone_id, user_id, reservation_at, departure_location, arrival_location, item_name, item_category, delivery_fee) VALUES (2, 'shinny88', TO_DATE('2025-06-14 14:00:00','YYYY-MM-DD HH24:MI:SS'), '역삼동 정류장', '중흥동 정류장', '책', '기타', 4000);
INSERT INTO user_reservation (drone_id, user_id, reservation_at, departure_location, arrival_location, item_name, item_category, delivery_fee) VALUES (1, 'kimjiwoo11', TO_DATE('2025-06-15 09:30:00','YYYY-MM-DD HH24:MI:SS'), '중앙동 정류장', '우동 정류장', '마우스', '전자제품', 8000);
INSERT INTO user_reservation (drone_id, user_id, reservation_at, departure_location, arrival_location, item_name, item_category, delivery_fee) VALUES (2, 'sunypark00', TO_DATE('2025-06-16 11:15:00','YYYY-MM-DD HH24:MI:SS'), '이도이동 정류장', '역삼동 정류장', '문서', '기타', 3000);
INSERT INTO user_reservation (drone_id, user_id, reservation_at, departure_location, arrival_location, item_name, item_category, delivery_fee) VALUES (1, 'shinny88', TO_DATE('2025-06-17 13:45:00','YYYY-MM-DD HH24:MI:SS'), '우동 정류장', '중앙동 정류장', '키보드', '전자제품', 7500);
INSERT INTO user_reservation (drone_id, user_id, reservation_at, departure_location, arrival_location, item_name, item_category, delivery_fee) VALUES (2, 'kimjiwoo11', TO_DATE('2025-06-18 15:30:00','YYYY-MM-DD HH24:MI:SS'), '중흥동 정류장', '이도이동 정류장', '노트북', '전자제품', 5000);
INSERT INTO user_reservation (drone_id, user_id, reservation_at, departure_location, arrival_location, item_name, item_category, delivery_fee) VALUES (1, 'sunypark00', TO_DATE('2025-06-19 10:00:00','YYYY-MM-DD HH24:MI:SS'), '역삼동 정류장', '우동 정류장', '문서', '기타', 3000);
INSERT INTO user_reservation (drone_id, user_id, reservation_at, departure_location, arrival_location, item_name, item_category, delivery_fee) VALUES (2, 'shinny88', TO_DATE('2025-06-20 12:15:00','YYYY-MM-DD HH24:MI:SS'), '중앙동 정류장', '중흥동 정류장', '스마트폰', '전자제품', 6000);

-- 업체회원예약하다
INSERT INTO company_reservation (drone_id, company_user_id, reservation_at, departure_location, arrival_location, item_name, item_category) VALUES
(1, 'chllllsu', TO_DATE('2025-06-01 10:00:00','YYYY-MM-DD HH24:MI:SS'), '서울역 정류장', '광화문 정류장', '문서팩', '문서');
INSERT INTO company_reservation (drone_id, company_user_id, reservation_at, departure_location, arrival_location, item_name, item_category) VALUES
(2, 'chllllsu', TO_DATE('2025-06-02 14:30:00','YYYY-MM-DD HH24:MI:SS'), '강남역', '코엑스', '도시락 세트', '음식');
INSERT INTO company_reservation (drone_id, company_user_id, reservation_at, departure_location, arrival_location, item_name, item_category) VALUES
(3, 'seehum', TO_DATE('2025-06-03 09:00:00','YYYY-MM-DD HH24:MI:SS'), '홍대입구', '이태원', '치킨 한 마리', '음식');
INSERT INTO company_reservation (drone_id, company_user_id, reservation_at, departure_location, arrival_location, item_name, item_category) VALUES
(1, 'happyfood', TO_DATE('2025-06-04 12:15:00','YYYY-MM-DD HH24:MI:SS'), '신촌', '홍대입구', '생선회 세트', '음식');
INSERT INTO company_reservation (drone_id, company_user_id, reservation_at, departure_location, arrival_location, item_name, item_category) VALUES
(2, 'techguru', TO_DATE('2025-06-05 16:00:00','YYYY-MM-DD HH24:MI:SS'), '삼성역', '잠실역', '스마트폰 부품', '전자제품');
INSERT INTO company_reservation (drone_id, company_user_id, reservation_at, departure_location, arrival_location, item_name, item_category) VALUES
(3, 'greenfarm', TO_DATE('2025-06-06 11:45:00','YYYY-MM-DD HH24:MI:SS'), '마포역', '서강대', '신선 채소', '식품');
INSERT INTO company_reservation (drone_id, company_user_id, reservation_at, departure_location, arrival_location, item_name, item_category) VALUES
(1, 'chllllsu', TO_DATE('2025-06-07 13:00:00','YYYY-MM-DD HH24:MI:SS'), '광화문', '종로3가', '책자 10권', '문서');
INSERT INTO company_reservation (drone_id, company_user_id, reservation_at, departure_location, arrival_location, item_name, item_category) VALUES
(2, 'seehum', TO_DATE('2025-06-08 15:30:00','YYYY-MM-DD HH24:MI:SS'), '강남역', '서울숲', '도시락 5개', '음식');
INSERT INTO company_reservation (drone_id, company_user_id, reservation_at, departure_location, arrival_location, item_name, item_category) VALUES
(3, 'seehum', TO_DATE('2025-06-09 10:20:00','YYYY-MM-DD HH24:MI:SS'), '이태원', '한남동', '치킨 2마리', '음식');
INSERT INTO company_reservation (drone_id, company_user_id, reservation_at, departure_location, arrival_location, item_name, item_category) VALUES
(1, 'happyfood', TO_DATE('2025-06-10 09:50:00','YYYY-MM-DD HH24:MI:SS'), '신촌', '연세대', '회 세트', '음식');
INSERT INTO company_reservation (drone_id, company_user_id, reservation_at, departure_location, arrival_location, item_name, item_category) VALUES
(2, 'techguru', TO_DATE('2025-06-11 14:00:00','YYYY-MM-DD HH24:MI:SS'), '잠실역', '강동구청', '노트북 부품', '전자제품');
INSERT INTO company_reservation (drone_id, company_user_id, reservation_at, departure_location, arrival_location, item_name, item_category) VALUES
(3, 'greenfarm', TO_DATE('2025-06-12 12:30:00','YYYY-MM-DD HH24:MI:SS'), '서강대', '마포역', '과일 바구니', '식품');
INSERT INTO company_reservation (drone_id, company_user_id, reservation_at, departure_location, arrival_location, item_name, item_category) VALUES
(1, 'chllllsu', TO_DATE('2025-06-13 11:10:00','YYYY-MM-DD HH24:MI:SS'), '종로3가', '광화문', '문서팩 5개', '문서');
INSERT INTO company_reservation (drone_id, company_user_id, reservation_at, departure_location, arrival_location, item_name, item_category) VALUES
(2, 'seehum', TO_DATE('2025-06-14 16:45:00','YYYY-MM-DD HH24:MI:SS'), '서울숲', '강남역', '도시락 세트', '음식');
INSERT INTO company_reservation (drone_id, company_user_id, reservation_at, departure_location, arrival_location, item_name, item_category) VALUES
(3, 'seehum', TO_DATE('2025-06-15 08:30:00','YYYY-MM-DD HH24:MI:SS'), '한남동', '이태원', '치킨 한 마리', '음식');

-- 드론대여하다
INSERT INTO drone_rental (drone_id, company_user_id, rental_end_date, rental_start_date, rental_fee) VALUES
(1, 'chllllsu', TO_DATE('2025-06-05 18:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-01 09:00:00','YYYY-MM-DD HH24:MI:SS'), 20000);
INSERT INTO drone_rental (drone_id, company_user_id, rental_end_date, rental_start_date, rental_fee) VALUES
(2, 'chllllsu', TO_DATE('2025-06-10 17:30:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-03 11:15:00','YYYY-MM-DD HH24:MI:SS'), 25000);
INSERT INTO drone_rental (drone_id, company_user_id, rental_end_date, rental_start_date, rental_fee) VALUES
(1, 'myIQ1800', TO_DATE('2025-06-07 20:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-02 14:00:00','YYYY-MM-DD HH24:MI:SS'), 18000);
INSERT INTO drone_rental (drone_id, company_user_id, rental_end_date, rental_start_date, rental_fee) VALUES
(3, 'myIQ1800', TO_DATE('2025-06-15 19:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-08 10:00:00','YYYY-MM-DD HH24:MI:SS'), 22000);
INSERT INTO drone_rental (drone_id, company_user_id, rental_end_date, rental_start_date, rental_fee) VALUES
(2, 'seehum', TO_DATE('2025-06-12 18:30:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-05 09:30:00','YYYY-MM-DD HH24:MI:SS'), 21000);
INSERT INTO drone_rental (drone_id, company_user_id, rental_end_date, rental_start_date, rental_fee) VALUES
(1, 'seehum', TO_DATE('2025-06-14 16:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-09 13:45:00','YYYY-MM-DD HH24:MI:SS'), 23000);
INSERT INTO drone_rental (drone_id, company_user_id, rental_end_date, rental_start_date, rental_fee) VALUES
(3, 'happyfood', TO_DATE('2025-06-20 20:30:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-12 15:00:00','YYYY-MM-DD HH24:MI:SS'), 27000);
INSERT INTO drone_rental (drone_id, company_user_id, rental_end_date, rental_start_date, rental_fee) VALUES
(2, 'happyfood', TO_DATE('2025-06-25 17:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-18 10:30:00','YYYY-MM-DD HH24:MI:SS'), 25000);
INSERT INTO drone_rental (drone_id, company_user_id, rental_end_date, rental_start_date, rental_fee) VALUES
(1, 'techguru', TO_DATE('2025-06-22 19:15:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-16 09:00:00','YYYY-MM-DD HH24:MI:SS'), 26000);
INSERT INTO drone_rental (drone_id, company_user_id, rental_end_date, rental_start_date, rental_fee) VALUES
(3, 'techguru', TO_DATE('2025-06-30 18:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-20 12:00:00','YYYY-MM-DD HH24:MI:SS'), 24000);
INSERT INTO drone_rental (drone_id, company_user_id, rental_end_date, rental_start_date, rental_fee) VALUES
(2, 'greenfarm', TO_DATE('2025-06-28 17:30:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-21 11:15:00','YYYY-MM-DD HH24:MI:SS'), 28000);
INSERT INTO drone_rental (drone_id, company_user_id, rental_end_date, rental_start_date, rental_fee) VALUES
(1, 'greenfarm', TO_DATE('2025-07-02 16:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-24 14:45:00','YYYY-MM-DD HH24:MI:SS'), 23000);
INSERT INTO drone_rental (drone_id, company_user_id, rental_end_date, rental_start_date, rental_fee) VALUES
(3, 'chllllsu', TO_DATE('2025-07-05 20:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-28 09:30:00','YYYY-MM-DD HH24:MI:SS'), 29000);
INSERT INTO drone_rental (drone_id, company_user_id, rental_end_date, rental_start_date, rental_fee) VALUES
(2, 'myIQ1800', TO_DATE('2025-07-07 18:45:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-06-30 15:00:00','YYYY-MM-DD HH24:MI:SS'), 25000);
INSERT INTO drone_rental (drone_id, company_user_id, rental_end_date, rental_start_date, rental_fee) VALUES
(1, 'seehum', TO_DATE('2025-07-10 17:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2025-07-02 13:00:00','YYYY-MM-DD HH24:MI:SS'), 22000);


CREATE INDEX contract_expiry_date_idx ON company(contract_expiry_date);
CREATE INDEX max_payload_idx ON drone_model(max_payload);
CREATE INDEX rental_end_date_idx ON drone_rental(rental_end_date);