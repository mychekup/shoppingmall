-- ȸ�� ���̺�
CREATE TABLE MEMBER(
	MEMBER_ID 				VARCHAR2(20),
	MEMBER_PW 				VARCHAR2(15),
	MEMBER_NAME 			VARCHAR2(20),
	MEMBER_JUMIN1			NUMBER,
	MEMBER_JUMIN2			NUMBER,
	MEMBER_EMAIL			VARCHAR2(25),
	MEMBER_EMAIL_GET		VARCHAR2(7),
	MEMBER_MOBILE			VARCHAR2(13),
	MEMBER_PHONE			VARCHAR2(13),
	MEMBER_ZIPCODE			VARCHAR2(13),
	MEMBER_ADDR1			VARCHAR2(70),
	MEMBER_ADDR2			VARCHAR2(70),
	MEMBER_ADMIN			NUMBER,
	MEMBER_JOIN_DATE		DATE
);

-- ZIPCODE ���̺�
CREATE TABLE ZIPCODE(
	ID						NUMBER,
	ZIPCODE					VARCHAR2(7),
	SIDO					VARCHAR2(10),
	GUGUN					VARCHAR2(30),
	DONG					VARCHAR2(36),
	RI						VARCHAR2(60),
	BUNJI					VARCHAR2(50),
	PRIMARY	KEY (ID)
);

select * from member;

insert into ZIPCODE values(1, '137-868', '����Ư����', '���ϱ�', '������', '','83-1����');

select * from zipcode where dong like '%������%';