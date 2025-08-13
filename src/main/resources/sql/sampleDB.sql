-- ---------------------- web ---------------------------------- --
DROP DATABASE IF EXISTS springweb;
CREATE DATABASE springweb;
USE springweb;

CREATE TABLE member (
    mno     INT AUTO_INCREMENT,                    -- 회원 번호 (기본키)
    mid     VARCHAR(50) NOT NULL UNIQUE,           -- 회원 아이디
    mpwd    VARCHAR(50) NOT NULL,                  -- 비밀번호
    mname   VARCHAR(30) NOT NULL,                  -- 회원 이름
    mphone  VARCHAR(13) NOT NULL,                  -- 회원 연락처
    mdate   DATETIME DEFAULT NOW(),                 -- 가입일
    CONSTRAINT PRIMARY KEY (mno)
);

-- 샘플 데이터 삽입
INSERT INTO member (mid, mpwd, mname, mphone, mdate) VALUES
('qwe123',  'asd1234', '유재석', '010-1234-1234', '2025-08-01 14:10:00'),
('hong123',  'pass1234', '홍길동', '010-1234-5678', '2025-08-01 14:20:00'),
('kimlove',  'qwer5678', '김사랑', '010-9876-5432', '2025-08-02 09:15:00'),
('leepeace', 'abcd1111', '이평화', '010-2222-3333', '2025-08-03 17:45:00'),
('parkjoy',  'pass9999', '박기쁨', '010-5555-6666', '2025-08-04 08:05:00'),
('choifit',  'fit2025',  '최건강', '010-7777-8888', '2025-08-05 19:30:00');
