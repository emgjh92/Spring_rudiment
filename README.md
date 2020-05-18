# Spring_rudiment
Spring_rudiment

Spring = (구조와 관련된, MVC구조) 프레임워크 = 뼈대 = 전자정부표준프레임워크
==> 설정이 반이다!


책 : 스프링 퀵 스타트 (이론용 서적) 
     코드로 배우는 스프링 웹 프로젝트 (실습용 서적)


STS (Spring Tool Suite) 3 다운로드 링크 : 

https://spring.io/tools => 
https://github.com/spring-projects/toolsuite-distribution/wiki/Spring-Tool-Suite-3 =>
https://download.springsource.com/release/STS/3.9.12.RELEASE/dist/e4.15/spring-tool-suite-3.9.12.RELEASE-e4.15.0-win32-x86_64.zip


windows=>preprences=>General=>workspace=>Text file encoding 을 other(UTF-8)으로, 

web 탭에서 CSS file, HTML file, JSP file 의 Encoding 방식을 UTF-8으로 설정

서버 더블클릭 후 =>
Server 부분의 기본 서버는 Delete 후, Tomcat v8.5 Server로 새로 설정

Server Locations 는
User Tomcat installation (takes control of Tomcat installation)으로

Server Options 는 2,3 번째 꺼 선택

Ports 탭에서 HTTP/1.1 부분을 8181로 변경 (개발 환경용)

Ctrl + s 로 설정 저장

퍼블리싱 클릭 후 => 서버 실행


--개념 :
결합도를 낮춘다 (클래스와 클래스의 관계를 최소화 시킨다.)

응집도를 높인다 (그 코드가 있어야 할 자리에 코드를 둔다. ex. 예를들어 가격이란 변수를 Tv 가 아닌 User에 선언할 경우 응집도가 낮아진다)





pom.xml, log4j.xml 은 서버를 localhost로 쓰므로 security alert 의 문제는 발생하지 않는다.






Final Reference DB 테이블


--멤버 
DROP TABLE FB_Member;
CREATE TABLE FB_Member(
    member_no NUMBER(8) PRIMARY KEY,
    member_id VARCHAR2(80),
    member_pw VARCHAR2(100),
    member_nick VARCHAR2(20),
    member_sex VARCHAR2(4),
    member_joindate DATE
);

DROP SEQUENCE FB_Member_seq;
CREATE SEQUENCE FB_Member_seq;

--취미 카테고리
DROP TABLE FB_Hobby_category;
CREATE TABLE FB_Hobby_category(
    hobby_category_no NUMBER(4) PRIMARY KEY,
    hobby_category_name VARCHAR2(20)
);

DROP SEQUENCE FB_Hobby_category_seq;
CREATE SEQUENCE FB_Hobby_category_seq;

INSERT INTO FB_Hobby_category VALUES(FB_Hobby_category_seq.nextval,'농구');
INSERT INTO FB_Hobby_category VALUES(FB_Hobby_category_seq.nextval,'축구');
INSERT INTO FB_Hobby_category VALUES(FB_Hobby_category_seq.nextval,'야구');
INSERT INTO FB_Hobby_category VALUES(FB_Hobby_category_seq.nextval,'독서');

COMMIT;


--멤버 취미
DROP TABLE FB_Hobby;
CREATE TABLE FB_Hobby(
    hobby_no NUMBER(8) PRIMARY KEY,
    member_no NUMBER(8),
    hobby_category_no NUMBER(8)
);

DROP SEQUENCE FB_Hobby_seq;
CREATE SEQUENCE FB_Hobby_seq;

--게시판 테이블
DROP TABLE FB_Board;
CREATE TABLE FB_Board(
    board_no NUMBER(8) PRIMARY KEY,
    member_no NUMBER(8),
    board_title VARCHAR2(200),
    board_content VARCHAR2(1000),
    board_readcount NUMBER(8),
    board_writedate DATE
);

DROP SEQUENCE FB_Board_seq;
CREATE SEQUENCE FB_Board_seq;




메일링---


CREATE TABLE FB_Member_Auth(
    auth_no NUMBER(8) PRIMARY KEY,
    member_no NUMBER(8),
    auth_certification VARCHAR2(4),
    auth_key VARCHAR2(100)
);

DROP SEQUENCE FB_Member_Auth_seq;
CREATE SEQUENCE FB_Member_Auth_seq;









--MVC 모델 파일 생성 순서


VO =>SQLMapper=>SQLMapper.xml=>Service=>Controller
