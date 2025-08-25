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



-- ---------------------- memberimg *250820 ---------------------------------- --
CREATE TABLE memberimg (
	mimgno    INT AUTO_INCREMENT,              -- 회원 이미지 번호 (기본키)
	mimgname  VARCHAR(255) NOT NULL,           -- 파일명
	mno       INT NOT NULL,                    -- 회원 번호 (FK)
	CONSTRAINT PRIMARY KEY (mimgno),
	CONSTRAINT FOREIGN KEY (mno) REFERENCES member(mno) ON DELETE CASCADE
);

select * from member;
select * from product;
select * from productimg;
select * from pointlog;
select * from memberimg;

select * from memberimg where mno=7 order by mimgno desc limit 1; 

update memberimg set mimgname = "coffee1.jpg" where mimgno=1;

-- ---------------------- payment *250821 ---------------------------------- --
-- 포인트 충전 기능 추가 
CREATE TABLE payment (
    pno       INT AUTO_INCREMENT PRIMARY KEY,  -- 결제번호
    mno       INT NOT NULL,                    -- 회원번호 (FK)
    pamount   INT NOT NULL,                    -- 결제금액
    pdate     DATETIME DEFAULT now(),          -- 결제일시
    CONSTRAINT FOREIGN KEY (mno) REFERENCES member(mno) ON DELETE CASCADE
);
select * from payment;
insert into payment(mno, pamount) values ( 1,1000 );

-- 250825 카테고리 / 댓글 / 게시물 기능 추가
-- 카테고리 테이블[ 카테고리번호 , 카테고리 이름 ( 공지사항 , 커뮤니티 , QnA , 노하우 등등 ) ]
create table category(
   cno      int auto_increment,
    cname   varchar(100) not null ,
    constraint primary key( cno)
);

insert into category (cname) values ('뉴스');
insert into category (cname) values ('이벤트');
insert into category (cname) values ('FAQ');
insert into category (cname) values ('튜토리얼');
insert into category (cname) values ('사용자 리뷰');

select * from category;

-- 게시물 테이블 [ 번호 , 제목 , 내용 , 작성일 , 조회수 , 작성자 , 카테고리번호 ]
-- 'board' -> 'post' 로 변경, 컬럼 접두사 'b' -> 'p' 로 변경
create table post(
   pno         int auto_increment,
    ptitle       varchar(1000) not null ,
    pcontent   longtext   not null ,
    pdate       datetime default now() ,
    pview      int default 0 ,
    mno         int , -- 회원번호 fk
    cno         int , -- 카테고리번호 fk
    constraint primary key( pno ) ,
    constraint foreign key ( mno ) references member( mno ) on update cascade on delete cascade ,
   constraint foreign key ( cno ) references category( cno ) on update cascade on delete cascade
);

-- 댓글 테이블 [ 댓글번호 , 내용 , 작성일 , 작성자 , 게시물번호 ]
-- 참조하는 테이블 및 컬럼 이름 변경: board(bno) -> post(pno)
create table reply(
   rno         int auto_increment primary key ,
    rcontent   longtext ,
    rdate      datetime default now(),
    mno         int,
    pno         int,
    foreign key( mno ) references member(mno) on update cascade on delete cascade ,
    foreign key( pno ) references post(pno) on update cascade on delete cascade
);


insert into post (ptitle, pcontent, mno, cno) values
('2025년 여름 시즌 인기 상품 발표', '2025년 여름 시즌에 가장 인기 있는 상품들이 발표되었습니다. 새로운 아이템들을 만나보세요.', 1, 1),
('새로운 할인 행사 시작!', '이번 주말부터 시작되는 할인 행사에서 다양한 제품을 할인된 가격으로 구매할 수 있습니다.', 2, 1),
('AI 기술 혁신, 최신 연구 결과 발표', 'AI 기술의 최신 연구 결과와 그 응용 가능성에 대해 알아보세요.', 3, 1),
('온라인 쇼핑몰 보안 강화 정책', '온라인 쇼핑몰에서 발생할 수 있는 보안 사고를 방지하기 위한 강화된 보안 정책이 발표되었습니다.', 4, 1),
('2025년 신제품 출시 예고', '2025년에 출시될 신제품에 대한 정보를 미리 공개합니다.', 5, 1),
('신규 브랜드 런칭 소식', '새로운 브랜드가 런칭되었습니다. 다양한 제품을 만나보세요.', 1, 1),
('2025년 가을 신제품 소개', '다가오는 가을 시즌에 맞춰 출시될 신제품을 소개합니다.', 2, 1),
('전자제품 최신 트렌드', '최신 전자제품 트렌드를 확인하고 빠르게 접해보세요.', 3, 1),
('온라인 마켓플레이스 성장', '온라인 마켓의 성장과 그에 따른 변화에 대해 알아봅니다.', 4, 1),
('스마트홈 기기 신제품 소식', '최신 스마트홈 기기들을 소개합니다.', 5, 1),
('AI 도입 사례', '기업들이 AI를 어떻게 도입하고 있는지 살펴봅니다.', 1, 1),
('5G 기술 발전', '5G 기술이 생활 속에서 어떻게 활용되고 있는지 알아봅니다.', 2, 1),
('친환경 제품 인기', '친환경 제품이 주목받고 있는 이유를 분석합니다.', 3, 1),
('가전제품 할인 행사', '다양한 가전제품을 할인된 가격에 만나보세요.', 4, 1),
('자율주행 기술 현황', '현재 자율주행 기술의 발전 수준을 점검합니다.', 5, 1),
('2025년 여름 시즌 인기 상품 발표', '2025년 여름 시즌에 가장 인기 있는 상품들이 발표되었습니다. 새로운 아이템들을 만나보세요.', 1, 1),
('새로운 할인 행사 시작!', '이번 주말부터 시작되는 할인 행사에서 다양한 제품을 할인된 가격으로 구매할 수 있습니다.', 2, 1),
('AI 기술 혁신, 최신 연구 결과 발표', 'AI 기술의 최신 연구 결과와 그 응용 가능성에 대해 알아보세요.', 3, 1),
('온라인 쇼핑몰 보안 강화 정책', '온라인 쇼핑몰에서 발생할 수 있는 보안 사고를 방지하기 위한 강화된 보안 정책이 발표되었습니다.', 4, 1),
('2025년 신제품 출시 예고', '2025년에 출시될 신제품에 대한 정보를 미리 공개합니다.', 5, 1),
('신규 브랜드 런칭 소식', '새로운 브랜드가 런칭되었습니다. 다양한 제품을 만나보세요.', 1, 1),
('2025년 가을 신제품 소개', '다가오는 가을 시즌에 맞춰 출시될 신제품을 소개합니다.', 2, 1),
('전자제품 최신 트렌드', '최신 전자제품 트렌드를 확인하고 빠르게 접해보세요.', 3, 1),
('온라인 마켓플레이스 성장', '온라인 마켓의 성장과 그에 따른 변화에 대해 알아봅니다.', 4, 1),
('스마트홈 기기 신제품 소식', '최신 스마트홈 기기들을 소개합니다.', 5, 1),
('AI 도입 사례', '기업들이 AI를 어떻게 도입하고 있는지 살펴봅니다.', 1, 1),
('5G 기술 발전', '5G 기술이 생활 속에서 어떻게 활용되고 있는지 알아봅니다.', 2, 1),
('친환경 제품 인기', '친환경 제품이 주목받고 있는 이유를 분석합니다.', 3, 1),
('가전제품 할인 행사', '다양한 가전제품을 할인된 가격에 만나보세요.', 4, 1),
('자율주행 기술 현황', '현재 자율주행 기술의 발전 수준을 점검합니다.', 5, 1),
('클라우드 컴퓨팅 도입 증가', '기업들의 클라우드 컴퓨팅 활용 사례를 소개합니다.', 1, 1),
('웨어러블 디바이스 최신 동향', '웨어러블 기기의 최신 트렌드를 분석합니다.', 2, 1),
('차세대 배터리 기술', '새로운 배터리 기술이 어떻게 변화하고 있는지 살펴봅니다.', 3, 1),
('로봇 산업 발전', '로봇 산업이 어떻게 변화하고 있는지 알아봅니다.', 4, 1),
('빅데이터 분석 활용법', '빅데이터 분석이 비즈니스에서 어떻게 활용되고 있는지 살펴봅니다.', 5, 1),
('신재생 에너지 기술', '태양광, 풍력 등 신재생 에너지 기술을 분석합니다.', 1, 1),
('온라인 교육 플랫폼 비교', '다양한 온라인 교육 플랫폼을 비교해 봅니다.', 2, 1),
('스마트 헬스케어 시스템', '스마트 헬스케어 기술이 의료 분야에 미치는 영향을 분석합니다.', 3, 1),
('보안 기술 발전', '사이버 보안 기술의 최신 발전을 살펴봅니다.', 4, 1),
('양자 컴퓨팅 연구 동향', '양자 컴퓨팅 기술이 어떻게 발전하고 있는지 알아봅니다.', 5, 1),
('NFT와 디지털 아트', 'NFT와 디지털 아트 시장의 현황을 점검합니다.', 1, 1),
('메타버스 경제', '메타버스가 경제에 미치는 영향을 분석합니다.', 2, 1),
('블록체인 기술 활용', '블록체인 기술이 다양한 산업에서 어떻게 활용되고 있는지 살펴봅니다.', 3, 1),
('자동화 기술 발전', '자동화 기술이 산업에 미치는 영향을 분석합니다.', 4, 1),
('전자상거래 시장 전망', '전자상거래 시장의 미래 전망을 분석합니다.', 5, 1),
('스마트시티 개발', '스마트시티 개발 사례와 미래 전망을 살펴봅니다.', 1, 1),
('드론 기술 발전', '드론 기술이 다양한 산업에 미치는 영향을 분석합니다.', 2, 1),
('자율주행 차량 기술', '자율주행 차량의 현재 기술 수준과 향후 전망을 살펴봅니다.', 3, 1),
('AR/VR 기술 발전', '증강현실과 가상현실 기술이 다양한 산업에서 어떻게 활용되는지 알아봅니다.', 4, 1),
('스마트팩토리 도입', '스마트팩토리 기술이 제조업에 미치는 영향을 분석합니다.', 5, 1),
('차세대 반도체 기술', '반도체 기술의 발전과 그에 따른 변화에 대해 살펴봅니다.', 1, 1),
('스마트폰 혁신과 미래', '스마트폰 산업의 최신 트렌드와 미래 전망을 분석합니다.', 2, 1),
('자동차 산업의 변화', '전기차와 자율주행 기술이 자동차 산업을 어떻게 변화시키고 있는지 살펴봅니다.', 3, 1),
('우주 탐사 기술 발전', '최신 우주 탐사 기술과 향후 계획에 대해 알아봅니다.', 4, 1),
('친환경 교통 수단', '전기차, 수소차 등 친환경 교통 수단의 발전과 보급 현황을 분석합니다.', 5, 1),
('디지털 전환과 비즈니스', '기업들이 디지털 전환을 통해 어떻게 성장하고 있는지 사례를 살펴봅니다.', 1, 1),
('헬스케어 AI 기술', 'AI가 의료 산업에서 어떻게 활용되고 있는지 살펴봅니다.', 2, 1),
('데이터 보안과 개인정보 보호', '데이터 보안의 중요성과 최신 보호 기술에 대해 분석합니다.', 3, 1),
('자율주행 드론', '드론 기술이 자율주행과 결합하여 어떻게 발전하고 있는지 알아봅니다.', 4, 1),
('양자 인터넷 연구', '양자 컴퓨팅을 활용한 인터넷 기술 연구가 어디까지 왔는지 살펴봅니다.', 5, 1),
('VR 교육 혁신', '가상현실 기술이 교육 시스템에 미치는 영향을 분석합니다.', 1, 1),
('블록체인 금융 혁신', '블록체인이 금융 산업에서 어떻게 활용되고 있는지 알아봅니다.', 2, 1),
('스마트 워크 환경 구축', '효율적인 원격 근무와 스마트 워크 환경을 위한 기술을 소개합니다.', 3, 1),
('AI 챗봇의 발전', 'AI 기반 챗봇이 고객 서비스와 비즈니스에 미치는 영향을 분석합니다.', 4, 1),
('5G와 사물인터넷(IoT)', '5G 기술이 IoT 기기와 결합하여 어떻게 활용되는지 살펴봅니다.', 5, 1),
('디지털 헬스 트렌드', '디지털 헬스 기술이 의료 서비스에 미치는 변화를 살펴봅니다.', 1, 1),
('스마트 농업 기술', 'AI, IoT를 활용한 스마트 농업 기술과 미래 전망을 분석합니다.', 2, 1),
('신소재 연구 동향', '미래 산업을 위한 신소재 연구와 그 활용 가능성을 알아봅니다.', 3, 1),
('전자상거래 AI 추천 시스템', 'AI를 활용한 개인 맞춤형 추천 시스템이 전자상거래에 미치는 영향을 분석합니다.', 4, 1),
('로봇공학 최신 연구', '로봇공학 기술이 다양한 산업에서 어떻게 활용되는지 살펴봅니다.', 5, 1);

insert into post (ptitle, pcontent, mno, cno) values
('회원 가입 이벤트!', '회원 가입 시 포인트 500점을 지급하는 이벤트가 시작되었습니다. 지금 가입하고 혜택을 누리세요!', 1, 2),
('친구 초대 이벤트', '친구를 초대하고, 친구가 첫 구매 시 보너스 포인트를 지급하는 이벤트를 진행 중입니다.', 2, 2),
('구매 후 리뷰 작성 이벤트', '구매 후 리뷰를 작성하면 포인트를 추가로 지급하는 이벤트에 참여하세요.', 3, 2),
('기프트 카드 추첨 이벤트', '기프트 카드 추첨 이벤트에 참여하고, 행운을 잡으세요!', 4, 2),
('여름 세일 이벤트', '여름 시즌 제품을 최대 50% 할인하는 세일 이벤트를 놓치지 마세요!', 5, 2);


insert into post (ptitle, pcontent, mno, cno) values
('회원 가입 방법 안내', '회원 가입 절차에 대한 자세한 설명을 드립니다.', 1, 3),
('비밀번호 변경 방법', '비밀번호를 안전하게 변경하는 방법에 대해 설명드립니다.', 2, 3),
('배송 조회 방법', '구매한 상품의 배송 현황을 확인하는 방법에 대해 안내드립니다.', 3, 3),
('포인트 사용 방법', '적립된 포인트를 사용하는 방법을 알아봅니다.', 4, 3),
('상품 반품 절차', '상품 반품 및 환불 절차에 대한 정보를 제공합니다.', 5, 3);


insert into post (ptitle, pcontent, mno, cno) values
('HTML5 기초 튜토리얼', 'HTML5의 기본적인 문법과 요소들을 소개하는 튜토리얼입니다.', 1, 4),
('CSS 레이아웃 기초', 'CSS를 활용하여 다양한 레이아웃을 구성하는 방법을 배워봅시다.', 2, 4),
('자바스크립트 함수 사용법', '자바스크립트에서 함수를 정의하고 활용하는 방법을 설명합니다.', 3, 4),
('React 기본 사용법', 'React를 사용하여 동적인 웹 애플리케이션을 만드는 방법을 소개합니다.', 4, 4),
('파이썬 기초 문법', '파이썬 프로그래밍의 기초 문법을 배워보세요.', 5, 4);


insert into post (ptitle, pcontent, mno, cno) values
('최고의 스마트폰 사용 후기', '최근에 구매한 스마트폰에 대한 리뷰를 공유합니다. 성능과 디자인에 대해 설명합니다.', 1, 5),
('이전에 사용했던 노트북 후기', '내가 사용해본 노트북에 대한 후기를 남겨봅니다. 속도와 디자인을 비교해봤습니다.', 2, 5),
('온라인 쇼핑몰 이용 후기', '최근에 이용한 온라인 쇼핑몰에 대한 후기를 남깁니다. 배송과 서비스 품질을 평가합니다.', 3, 5),
('가성비 좋은 이어폰 추천', '가성비 좋은 이어폰을 찾고 있다면, 이 제품을 추천드립니다. 가격 대비 성능이 뛰어나요.', 4, 5),
('전문적인 카메라 리뷰', '프로페셔널 카메라를 사용해본 후기를 남깁니다. 사용감과 화질을 평가해보았습니다.', 5, 5);

select * from post;

INSERT INTO reply (rcontent, mno, pno) VALUES
-- 1번 게시물의 댓글 10개
('이 게시물 정말 유익하네요!', 1, 1),
('좋은 정보 감사합니다.', 2, 1),
('질문이 있습니다. 자세한 설명 부탁드려요.', 3, 1),
('이 부분에 대한 추가 자료가 있나요?', 4, 1),
('잘 읽었습니다! 도움이 많이 됐어요.', 5, 1),
('설명이 명확해서 이해하기 쉬웠습니다.', 1, 1),
('내용이 흥미롭네요. 공유해 주셔서 감사합니다.', 2, 1),
('혹시 관련된 추천 자료가 있을까요?', 3, 1),
('이 주제에 대해 더 알고 싶어요.', 4, 1),
('좋은 의견 감사합니다! 많은 도움이 되었습니다.', 5, 1),

-- 2번 게시물의 댓글 4개
('이 글도 정말 좋은 정보네요!', 1, 2),
('도움이 되었습니다. 감사합니다!', 3, 2),
('이 부분이 특히 흥미로웠어요.', 5, 2),
('더 많은 자료를 찾을 수 있을까요?', 4, 2);

select * from reply;

select pno, ptitle, pcontent, pdate, pview, mid, cno from post, member where (post.mno = member.mno);

select * from post, category where (post.cno = category.cno);

select post.pno, post.ptitle, post.pcontent, post.pdate, post.pview, member.mid, category.cname
from post join member on post.mno = member.mno join category ON post.cno = category.cno order by pno;

select * from post p inner join member m on p.mno = m.mno order by pno desc;