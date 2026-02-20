SELECT *
FROM user_member
WHERE id = 'kimironso';



-- 나중에 실행
INSERT INTO user_member (id, pw, name, phone_number, account_info, membership_level, points, profile_image, last_login_at, active_status) 
VALUES ('kimironso', 'kimkimiron', '김철수', '010-3842-1843', '999-000-111222', '골드', 120000, 'park.png', TO_DATE('2022-04-05 15:18:00','YYYY-MM-DD HH24:MI:SS'), '활성');

SELECT *
FROM user_member
WHERE id = 'kimironso';