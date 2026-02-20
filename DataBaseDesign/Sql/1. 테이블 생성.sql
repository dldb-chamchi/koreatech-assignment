CREATE TABLE station (
    station_number INTEGER PRIMARY KEY,
    station_name VARCHAR2(30) NOT NULL UNIQUE,
    operation_status CHAR(8) DEFAULT '영업종료' CHECK (operation_status IN ('영업중', '영업종료')),
    location CHAR(18) NOT NULL
);
COMMIT;
CREATE TABLE company (
    company_id INTEGER PRIMARY KEY,
    station_number INTEGER NOT NULL UNIQUE REFERENCES station(station_number),
    company_name VARCHAR2(30) NOT NULL UNIQUE,
    business_number CHAR(12) NOT NULL UNIQUE,
    contract_expiry_date DATE DEFAULT (SYSDATE + INTERVAL '1' YEAR)
);
COMMIT;
CREATE TABLE cabinet (
    cabinet_number INTEGER PRIMARY KEY,
    station_number INTEGER NOT NULL REFERENCES station(station_number),
    storage_time NUMBER(3) DEFAULT 0 CHECK (storage_time BETWEEN 0 AND 178),
    cabinet_password CHAR(4) DEFAULT 0,
    cabinet_size CHAR(4) NOT NULL CHECK (cabinet_size IN ('대형', '중형', '소형')),
    inspection_date DATE NOT NULL,
    storage_status CHAR(8) DEFAULT '사용가능' CHECK (storage_status IN ('예약', '잠김', '보관', '사용가능'))
);
COMMIT;
CREATE TABLE drone_model (
    drone_model_code CHAR(3) PRIMARY KEY,
    model_name VARCHAR2(30) NOT NULL UNIQUE,
    max_payload NUMBER(4) NOT NULL,
    max_range NUMBER(6) NOT NULL
);
COMMIT;
CREATE TABLE drone (
    drone_number INTEGER PRIMARY KEY,
    station_number INTEGER NOT NULL REFERENCES station(station_number),
    drone_model_code CHAR(3) NOT NULL REFERENCES drone_model(drone_model_code),
    current_location CHAR(18) NOT NULL,
    battery_status NUMBER(3) NOT NULL CHECK (battery_status BETWEEN 0 AND 100),
    operation_status CHAR(8) DEFAULT '사용가능' CHECK (operation_status IN ('사용가능', '고장', '예약'))
);
COMMIT;
CREATE TABLE manager (
    manager_number INTEGER PRIMARY KEY,
    name VARCHAR2(30) NOT NULL,
    phone_number CHAR(13) NOT NULL UNIQUE,
    responsibility CHAR(4) NOT NULL CHECK (responsibility IN ('드론', '시설', '배달'))
);
COMMIT;
CREATE TABLE region (
    region_code CHAR(5) PRIMARY KEY,
    region_name VARCHAR2(100) NOT NULL UNIQUE,
    last_updated_at DATE NOT NULL,
    weather VARCHAR2(10) NOT NULL,
    wind_speed NUMBER(4) NOT NULL,
    is_flight_allowed CHAR(6) DEFAULT '불가능' CHECK (is_flight_allowed IN ('불가능', '가능'))
);
COMMIT;
CREATE TABLE special_item (
    special_item_code CHAR(3) PRIMARY KEY,
    category VARCHAR2(30) NOT NULL UNIQUE,
    special_reason VARCHAR2(100) NOT NULL UNIQUE
);
COMMIT;
CREATE TABLE drone_accident (
    accident_id INTEGER PRIMARY KEY,
    drone_id INTEGER NOT NULL,
    location CHAR(18) NOT NULL,
    accident_video VARCHAR2(30),
    accident_date DATE NOT NULL,
    CONSTRAINT fk_drone_accident_drone FOREIGN KEY (drone_id)
        REFERENCES drone(drone_number)
);
COMMIT;
CREATE TABLE user_member (
    id VARCHAR2(15) PRIMARY KEY,
    pw VARCHAR2(20) NOT NULL,
    name VARCHAR2(30) NOT NULL,
    phone_number CHAR(13) NOT NULL UNIQUE,
    account_info VARCHAR2(30) NOT NULL UNIQUE,
    membership_level CHAR(6) DEFAULT '일반' NOT NULL 
        CHECK (membership_level IN ('일반', '브론즈', '실버', '골드')),
    points INTEGER DEFAULT 0 NOT NULL,
    profile_image VARCHAR2(30),
    last_login_at DATE NOT NULL,
    active_status CHAR(4) DEFAULT '활성' NOT NULL 
        CHECK (active_status IN ('활성', '휴면'))
);
COMMIT;
CREATE TABLE company_member (
    id VARCHAR2(15) PRIMARY KEY,
    pw VARCHAR2(20) NOT NULL,
    name VARCHAR2(30) NOT NULL,
    phone_number CHAR(13) NOT NULL UNIQUE,
    profile_image VARCHAR2(30),
    account_info VARCHAR2(30) NOT NULL UNIQUE,
    active_status CHAR(4) DEFAULT '활성' NOT NULL 
        CHECK (active_status IN ('활성', '휴면')),
    business_number CHAR(12) NOT NULL UNIQUE,
    company_name VARCHAR2(30) NOT NULL UNIQUE,
    last_login_at DATE NOT NULL
);
COMMIT;
CREATE TABLE activity (
    user_id VARCHAR2(15) NOT NULL,
    activity_number INTEGER NOT NULL,
    drone_departure_at DATE NOT NULL,
    drone_arrival_at DATE,
    departure_station_name VARCHAR2(30) NOT NULL,
    arrival_station_name VARCHAR2(30) NOT NULL,
    delivery_status CHAR(4) NOT NULL 
        CHECK (delivery_status IN ('사고', '완료', '반송')),
    item_name VARCHAR2(30) NOT NULL,
    item_category VARCHAR2(20) NOT NULL,
    delivery_fee INTEGER NOT NULL,
    CONSTRAINT pk_activity PRIMARY KEY (user_id, activity_number),
    CONSTRAINT fk_activity_user FOREIGN KEY (user_id)
        REFERENCES user_member(id)
);
COMMIT;
CREATE TABLE review (
    review_id INTEGER PRIMARY KEY,
    user_id VARCHAR2(15) NOT NULL,
    content VARCHAR2(1000) NOT NULL,
    photo_url VARCHAR2(30),
    rating INTEGER CHECK (rating >= 0 AND rating <= 5),
    created_at DATE NOT NULL,
    CONSTRAINT fk_review_user FOREIGN KEY (user_id)
        REFERENCES user_member(id)
);
COMMIT;
CREATE TABLE cabinet_maintenance (
    cabinet_id INTEGER NOT NULL,
    manager_id INTEGER NOT NULL,
    work_date DATE NOT NULL,
    work_type CHAR(4) NOT NULL 
        CHECK (work_type IN ('개방', '폐기')),
    PRIMARY KEY (cabinet_id, manager_id),
    CONSTRAINT fk_cabinet_maintenance_cabinet FOREIGN KEY (cabinet_id)
        REFERENCES cabinet(cabinet_number),
    CONSTRAINT fk_cabinet_maintenance_manager FOREIGN KEY (manager_id)
        REFERENCES manager(manager_number)
);
COMMIT;
CREATE TABLE drone_inspection (
    inspection_id INTEGER PRIMARY KEY,
    manager_id INTEGER NOT NULL,
    drone_id INTEGER NOT NULL,
    inspection_date DATE NOT NULL,
    battery_status CHAR(4) NOT NULL 
        CHECK (battery_status IN ('좋음', '보통', '나쁨')),
    propeller_status CHAR(4) NOT NULL 
        CHECK (propeller_status IN ('좋음', '보통', '나쁨')),
    gps_accuracy CHAR(4) NOT NULL 
        CHECK (gps_accuracy IN ('좋음', '보통', '나쁨')),
    sensor_operational CHAR(6) NOT NULL 
        CHECK (sensor_operational IN ('불가능', '가능')),
    CONSTRAINT fk_inspection_manager FOREIGN KEY (manager_id)
        REFERENCES manager(manager_number),
    CONSTRAINT fk_inspection_drone FOREIGN KEY (drone_id)
        REFERENCES drone(drone_number)
);
COMMIT;
CREATE TABLE item_delivery (
    delivery_id INTEGER PRIMARY KEY,
    cabinet_id INTEGER NOT NULL,
    drone_id INTEGER NOT NULL,
    delivery_route VARCHAR2(3000) NOT NULL,
    delivery_status CHAR(8) DEFAULT '배달준비' NOT NULL 
        CHECK (delivery_status IN ('배달준비', '배달중', '배달완료')),
    estimated_arrival_at DATE NOT NULL,
    departure_at DATE NOT NULL,
    arrival_at DATE NOT NULL,
    CONSTRAINT fk_delivery_cabinet FOREIGN KEY (cabinet_id)
        REFERENCES cabinet(cabinet_number),
    CONSTRAINT fk_delivery_drone FOREIGN KEY (drone_id)
        REFERENCES drone(drone_number)
);
COMMIT;
CREATE TABLE special_item_assignment (
    special_item_code CHAR(3) NOT NULL,
    drone_model_code CHAR(3) NOT NULL,
    PRIMARY KEY (special_item_code, drone_model_code),
    CONSTRAINT fk_assignment_special_item FOREIGN KEY (special_item_code)
        REFERENCES special_item(special_item_code),
    CONSTRAINT fk_assignment_drone_model FOREIGN KEY (drone_model_code)
        REFERENCES drone_model(drone_model_code)
);
COMMIT;
CREATE TABLE user_reservation (
    reservation_id INTEGER PRIMARY KEY,
    drone_id INTEGER NOT NULL,
    user_id VARCHAR2(15) NOT NULL,
    reservation_at DATE NOT NULL,
    departure_location VARCHAR2(30) NOT NULL,
    arrival_location VARCHAR2(30) NOT NULL,
    item_name VARCHAR2(30) NOT NULL,
    item_category VARCHAR2(20) NOT NULL,
    delivery_fee INTEGER DEFAULT 0 NOT NULL,
    CONSTRAINT fk_user_reservation_drone FOREIGN KEY (drone_id)
        REFERENCES drone(drone_number),
    CONSTRAINT fk_user_reservation_user FOREIGN KEY (user_id)
        REFERENCES user_member(id)
);
COMMIT;
CREATE TABLE company_reservation (
    company_reservation_id INTEGER PRIMARY KEY,
    drone_id INTEGER NOT NULL,
    company_user_id VARCHAR2(15) NOT NULL,
    reservation_at DATE NOT NULL,
    departure_location VARCHAR2(30) NOT NULL,
    arrival_location VARCHAR2(30) NOT NULL,
    item_name VARCHAR2(30) NOT NULL,
    item_category VARCHAR2(20) NOT NULL,
    CONSTRAINT fk_company_reservation_drone FOREIGN KEY (drone_id)
        REFERENCES drone(drone_number),
    CONSTRAINT fk_company_reservation_user FOREIGN KEY (company_user_id)
        REFERENCES company_member(id)
);
COMMIT;
CREATE TABLE drone_rental (
    rental_id INTEGER PRIMARY KEY,
    drone_id INTEGER NOT NULL,
    company_user_id VARCHAR2(15) NOT NULL,
    rental_end_date DATE NOT NULL,
    rental_start_date DATE NOT NULL,
    rental_fee INTEGER DEFAULT 0 NOT NULL,
    CONSTRAINT fk_rental_drone FOREIGN KEY (drone_id)
        REFERENCES drone(drone_number),
    CONSTRAINT fk_rental_company_user FOREIGN KEY (company_user_id)
        REFERENCES company_member(id)
);
SELECT count(table_name) FROM user_tables;


CREATE SEQUENCE station_seq
START WITH 1
INCREMENT BY 1
NOCACHE;
CREATE OR REPLACE TRIGGER station_bir
BEFORE INSERT ON station
FOR EACH ROW
WHEN (new.station_number IS NULL)
BEGIN
    SELECT station_seq.NEXTVAL
    INTO :new.station_number
    FROM dual;
END;
/
CREATE SEQUENCE company_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
CREATE OR REPLACE TRIGGER trg_company_id
BEFORE INSERT ON company
FOR EACH ROW
BEGIN
    IF :NEW.company_id IS NULL THEN
        SELECT company_seq.NEXTVAL INTO :NEW.company_id FROM dual;
    END IF;
END;
/
CREATE SEQUENCE cabinet_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
CREATE OR REPLACE TRIGGER trg_cabinet_number
BEFORE INSERT ON cabinet
FOR EACH ROW
BEGIN
    IF :NEW.cabinet_number IS NULL THEN
        SELECT cabinet_seq.NEXTVAL INTO :NEW.cabinet_number FROM dual;
    END IF;
END;
/
CREATE SEQUENCE drone_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
CREATE OR REPLACE TRIGGER trg_drone_number
BEFORE INSERT ON drone
FOR EACH ROW
BEGIN
    IF :NEW.drone_number IS NULL THEN
        SELECT drone_seq.NEXTVAL INTO :NEW.drone_number FROM dual;
    END IF;
END;
/
CREATE SEQUENCE manager_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;
CREATE OR REPLACE TRIGGER trg_manager_number
BEFORE INSERT ON manager
FOR EACH ROW
BEGIN
    IF :NEW.manager_number IS NULL THEN
        SELECT manager_seq.NEXTVAL INTO :NEW.manager_number FROM dual;
    END IF;
END;
/
CREATE SEQUENCE drone_accident_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;
CREATE OR REPLACE TRIGGER trg_drone_accident_id
BEFORE INSERT ON drone_accident
FOR EACH ROW
BEGIN
    IF :NEW.accident_id IS NULL THEN
        SELECT drone_accident_seq.NEXTVAL
        INTO :NEW.accident_id
        FROM dual;
    END IF;
END;
/
CREATE SEQUENCE review_seq
  START WITH 1
  INCREMENT BY 1
  NOCACHE
  NOCYCLE;
CREATE OR REPLACE TRIGGER trg_review_id
BEFORE INSERT ON review
FOR EACH ROW
BEGIN
  IF :NEW.review_id IS NULL THEN
    SELECT review_seq.NEXTVAL INTO :NEW.review_id FROM dual;
  END IF;
END;
/
CREATE SEQUENCE drone_inspection_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
CREATE OR REPLACE TRIGGER trg_drone_inspection_id
BEFORE INSERT ON drone_inspection
FOR EACH ROW
BEGIN
    IF :NEW.inspection_id IS NULL THEN
        SELECT drone_inspection_seq.NEXTVAL
        INTO :NEW.inspection_id
        FROM dual;
    END IF;
END;
/
CREATE SEQUENCE delivery_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
CREATE OR REPLACE TRIGGER trg_delivery_id
BEFORE INSERT ON item_delivery
FOR EACH ROW
BEGIN
    IF :NEW.delivery_id IS NULL THEN
        SELECT delivery_seq.NEXTVAL
        INTO :NEW.delivery_id
        FROM dual;
    END IF;
END;
/
CREATE SEQUENCE user_reservation_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
CREATE OR REPLACE TRIGGER trg_user_reservation_id
BEFORE INSERT ON user_reservation
FOR EACH ROW
BEGIN
    IF :NEW.reservation_id IS NULL THEN
        SELECT user_reservation_seq.NEXTVAL
        INTO :NEW.reservation_id
        FROM dual;
    END IF;
END;
/
CREATE SEQUENCE company_reservation_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
CREATE OR REPLACE TRIGGER trg_company_reservation_id
BEFORE INSERT ON company_reservation
FOR EACH ROW
BEGIN
    IF :NEW.company_reservation_id IS NULL THEN
        SELECT company_reservation_seq.NEXTVAL
        INTO :NEW.company_reservation_id
        FROM dual;
    END IF;
END;
/
CREATE SEQUENCE drone_rental_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
CREATE OR REPLACE TRIGGER trg_drone_rental_id
BEFORE INSERT ON drone_rental
FOR EACH ROW
BEGIN
    IF :NEW.rental_id IS NULL THEN
        SELECT drone_rental_seq.NEXTVAL
        INTO :NEW.rental_id
        FROM dual;
    END IF;
END;