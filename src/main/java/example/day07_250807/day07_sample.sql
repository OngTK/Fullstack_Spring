DROP DATABASE IF EXISTS spring07;
CREATE DATABASE spring07;
USE spring07;

CREATE TABLE student (
    sno   INT AUTO_INCREMENT,
    sname VARCHAR(50) NOT NULL,
    skor  INT,
    smath INT,
    sdate DATETIME DEFAULT NOW(),
    CONSTRAINT PRIMARY KEY ( sno )
);

-- 10명의 학생 데이터 추가
INSERT INTO student (sname, skor, smath) VALUES ('김철수', 95, 76);
INSERT INTO student (sname, skor, smath) VALUES ('이영희', 100, 98);
INSERT INTO student (sname, skor, smath) VALUES ('박지성', 78, 89);
INSERT INTO student (sname, skor, smath) VALUES ('최민준', 88, 82);
INSERT INTO student (sname, skor, smath) VALUES ('정서연', 92, 95);
INSERT INTO student (sname, skor, smath) VALUES ('강현우', 75, 71);
INSERT INTO student (sname, skor, smath) VALUES ('조은지', 85, 90);
INSERT INTO student (sname, skor, smath) VALUES ('윤도현', 98, 94);
INSERT INTO student (sname, skor, smath) VALUES ('임하늘', 81, 83);
INSERT INTO student (sname, skor, smath) VALUES ('한유나', 93, 88);

SELECT * FROM student;
