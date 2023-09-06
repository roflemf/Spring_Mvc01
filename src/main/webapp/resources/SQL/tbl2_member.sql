--tbl_member 테이블 생성
create table tbl_member(
    userid varchar2(50) primary key -- 회원아이디
    ,userpw varchar2(100) not null -- 비밀번호
    ,username varchar2(50) not null -- 회원이름
    ,email varchar2(100) --전자우편
    ,regdate timestamp default sysdate -- default sysdate 제약조건이 설정되어서 해당 컬럼에 굳이 레코드를 저장하지 않아도 기본 날짜값이 저장됌
    );
    
    select * from tbl_member order by userid asc;
    
    commit;