-- ---------------------- web ---------------------------------- --
DROP DATABASE IF EXISTS springweb;
CREATE DATABASE springweb;
USE springweb;

-- ---------------------- member ---------------------------------- --
CREATE TABLE member (
    mno     INT AUTO_INCREMENT,                    -- 회원 번호 (기본키)
    mid     VARCHAR(50) NOT NULL UNIQUE,           -- 회원 아이디
    mpwd    VARCHAR(50) NOT NULL,                  -- 비밀번호
    mname   VARCHAR(30) NOT NULL,                  -- 회원 이름
    mphone  VARCHAR(13) UNIQUE NOT NULL,                  -- 회원 연락처
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

-- ---------------------- product ---------------------------------- --
CREATE TABLE product (
    pno        INT AUTO_INCREMENT,                 -- 제품 코드 (기본키)
    pname      VARCHAR(100) NOT NULL,              -- 제품명
    pprice     INT NOT NULL,                       -- 제품 가격
    pcomment   TEXT,                               -- 제품 설명
    pdate      DATETIME DEFAULT NOW(),             -- 등록일/시간
    plat       DECIMAL(10,7),                      -- 위도 (예: 37.1234567)
    plng       DECIMAL(10,7),                      -- 경도 (예: 127.1234567)
    mno        INT NOT NULL,                       -- 등록자 (회원 번호 - FK)
    CONSTRAINT  PRIMARY KEY (pno),
    CONSTRAINT  FOREIGN KEY (mno) REFERENCES member(mno)  ON DELETE CASCADE
);

-- ---------------------- productimg ---------------------------------- --
CREATE TABLE productimg (
    pimgno   INT AUTO_INCREMENT,                 -- 사진 코드 (기본키)
    pimgname   VARCHAR(255) NOT NULL,              -- 사진 파일명
    pno        INT NOT NULL,                       -- 제품 코드 (FK)
    CONSTRAINT  PRIMARY KEY (pimgno),
    CONSTRAINT  FOREIGN KEY (pno) REFERENCES product(pno) ON DELETE CASCADE
);

-- 샘플 데이터 삽입
INSERT INTO product (pname, pprice, pcomment, pdate, plat, plng, mno) VALUES
('그린핏 쉐이크', 25000, '100% 식물성 단백질 쉐이크', '2025-08-10 10:00:00', 37.566535, 126.977969, 1),
('프리미엄 커피', 4500, '원두 향이 진한 아메리카노', '2025-08-11 09:30:00', 37.501234, 127.012345, 2);

INSERT INTO productimg (pimgname, pno) VALUES
('greenfit1.jpg', 1),
('greenfit2.jpg', 1),
('coffee1.jpg', 2);

-- ---------------------- pointlog *250819 ---------------------------------- --
CREATE TABLE pointlog (
    plno      INT AUTO_INCREMENT,
    mno       INT NOT NULL,
    plpoint   INT NOT NULL,
    plcomment VARCHAR(100) NOT NULL,
    pldate    DATETIME DEFAULT NOW(),
    CONSTRAINT PRIMARY KEY (plno),
    CONSTRAINT FOREIGN KEY (mno) REFERENCES member(mno) ON DELETE CASCADE
);
-- ---------------------- 포인트 적립 샘플 ---------------------- --
-- 회원 1이 회원가입 포인트 지급 (+1000)
INSERT INTO pointlog (mno, plpoint, plcomment) VALUES (1, 1000, '회원가입');
-- 회원 1이 로그인 포인트 지급 (+100) 3회
INSERT INTO pointlog (mno, plpoint, plcomment) VALUES (1, 100, '로그인');
INSERT INTO pointlog (mno, plpoint, plcomment) VALUES (1, 100, '로그인');
INSERT INTO pointlog (mno, plpoint, plcomment) VALUES (1, 100, '로그인');
-- ---------------------- 포인트 충전/차감 샘플 ---------------------- --
-- 회원 1이 포인트 충전 (+5000)
INSERT INTO pointlog (mno, plpoint, plcomment) VALUES (1, 5000, '포인트충전');
-- 회원 1이 제품 구매로 포인트 차감 (-6000)
INSERT INTO pointlog (mno, plpoint, plcomment) VALUES (1, -6000, '상품구매');
-- 회원 1이 이벤트 참여로 포인트 적립 (+500)
INSERT INTO pointlog (mno, plpoint, plcomment) VALUES (1, 500, '이벤트적립');

select * from member;
select * from product;
select * from productimg;
select * from pointlog;

select * from pointlog where mno = 1 order by pldate desc; 

-- ---------------------- memberimg *250820 ---------------------------------- --
CREATE TABLE memberimg (
	mimgno    INT AUTO_INCREMENT,              -- 회원 이미지 번호 (기본키)
	mimgname  VARCHAR(255) NOT NULL,           -- 파일명
	mno       INT NOT NULL,                    -- 회원 번호 (FK)
	CONSTRAINT PRIMARY KEY (mimgno),
	CONSTRAINT FOREIGN KEY (mno) REFERENCES member(mno) ON DELETE CASCADE
);

select * from memberimg;

select * from memberimg where mno=7 order by mimgno desc limit 1; 

update memberimg set mimgname = "coffee1.jpg" where mimgno=1;