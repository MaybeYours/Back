create table suggest(word varchar(50) primary key,
  url varchar2(100));
  
insert into suggest values('AJAX','http://www.itwill.xyz/');
insert into suggest values('AJAX 예제','http://www.itwill.xyz/');
insert into suggest values('AJAX 프로그래밍','http://www.itwill.xyz/');
insert into suggest values('AJA','http://www.itwill.xyz/');
insert into suggest values('자바','https://www.oracle.com/');
insert into suggest values('자바교재','http://www.itwill.xyz/');
insert into suggest values('자장가','http://www.itwill.xyz/');
insert into suggest values('자유','http://www.itwill.xyz/');
insert into suggest values('Spring','https://spring.io/');
insert into suggest values('Spring Framework','https://spring.io/');
insert into suggest values('Servlet','https://www.servlets.com/');
insert into suggest values('네이버','https://www.naver.com/');
insert into suggest values('다음','https://www.daum.net/');
insert into suggest values('구글','https://www.google.com/');
commit;
select * from suggest;

select rownum,temp.* from (select * from suggest where upper(word) like '%'||upper('')||'%' 
    order by word) temp where rownum<=10;










