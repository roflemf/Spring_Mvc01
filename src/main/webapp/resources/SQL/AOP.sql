--스프링 AOP와 트렌젝션 실습을 위한 샘플 테이블 설계(생성)
create table tbl_user(
    uid2 varchar2(50) primary key --회원 아이디
    ,upw varchar2(100) not null --비번
    ,uname varchar2(20) not null --회원 이름
    ,upoint number(38)default 0 --메세지가 보내지면 포인트 점수 10점 업데이트
);

insert into tbl_user(uid2,upw,uname) values('user00','12345','홍길동');
insert into tbl_user(uid2,upw,uname) values('user01','56789','이순신');

select * from tbl_user order by uid2 asc;

--tbl_message 테이블 생성
create table tbl_message(
    mid number(38) primary key
    ,targetid varchar2(50) not null --외래키(foreign key)추가 설정 => tbl_user uid2컬럼 레코드 아이디값만 저장됌.
    ,sender varchar2(50) not null --메세지를 보낸 사람
    ,message varchar2(4000) --보낸 메세지
    ,senddate timestamp --보낸 날짜
);

select * from tbl_message order by mid asc;

--target 외래키 추가 설정
alter table tbl_message add constraint tbl_message_targetid_fk
foreign key(targetid) references tbl_user(uid2); 

--mid_no_seq 시퀀스 생성
create sequence mid_no_seq
start with 1 --1부터 시작
increment by 1 --1씩 증가
nocache --임시 메모리 사용 안함
nocycle; --시퀀스 최대값 번호 발생 후 번호값 발생 안함. 다시 처음부터 반복안함
