package xyz.itwill08.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

//SpringDAO 기능을 이용하여 DAO 클래스 작성 - spring-jdbc 라이브러리 빌드 처리
// => JdbcTemplate 클래스의 템플릿 메소드를 호출하여 DAO 클래스 작성
//JdbcTemplate 객체를 제공받아 사용하는 방법(2가지 방법 중 하나를 선택)
// => JdbcTemplate 객체를 Spring Container에게 제공받아 필드에 저장 - DI
// => JdbcDaoSupport 클래스를 상속받아 JdbcTemplate 객체를 저장한 필드 사용
public class StudentDAOImpl implements StudentDAO {
	//Bean Configuration File에서 StudentDAOImpl 클래스를 Spring Bean으로 등록할 때 JdbcTemplate 
	//클래스의 Spring Bean을 필드에 저장하여 의존관계 설정 - DI(Dependency Injection)
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insertStudent(Student student) {
		String sql="insert into student values(?,?,?,?,?)";
		//JdbcTemplate.update(String sql, Object... args) : SQL 명령(INSERT,UPDATE,DELETE)를
		//DBMS 서버에 전달하여 실행하는 메소드 - 조작행의 갯수 반환
		// => 매개변수에 SQL 명령과 InParameter(?)에 저장될 값을 차례대로 나열하여 메소드 호출
		return jdbcTemplate.update(sql, student.getNo(), student.getName()
				, student.getPhone(), student.getAddress(), student.getBirthday());
	}

	@Override
	public int updateStudent(Student student) {
		String sql="update student set name=?,phone=?,address=?,birthday=? where no=?";
		return jdbcTemplate.update(sql, student.getName(), student.getPhone()
				, student.getAddress(), student.getBirthday(), student.getNo());
	}

	@Override
	public int deleteStudent(int no) {
		return jdbcTemplate.update("delete from student where no=?", no);
	}

	@Override
	public Student selectStudent(int no) {
		try {
			String sql="select * from student where no=?";
			//JdbcTemplate.queryForObject(String sql,RowMapper<T> rowMapper,Object... args) 
			// => SQL 명령(SELECT)을 DBMS 서버에 전달하여 실행하는 메소드
			// => 단일행의 검색결과를 하나의 객체(값)로 반환할 경우 사용되는 메소드
			// => SQL 명령, RowMapper 객체, InParameter 전달값을 매개변수에 차례대로 나열하여 전달
			//RowMapper : 검색결과를 Java 객체로 매핑하여 반환하기 위한 객체
			// => 제네릭은 검색행을 Java 객체로 표현하기 위한 자료형(클래스) 설정
			// => mapRow 메소드를 오버라이드 선언하여 매핑정보 제공
			/*
			return jdbcTemplate.queryForObject(sql, new RowMapper<Student>() {
				@Override
				public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
					//ResultSet 객체(검색결과)를 Java 객체로 매핑하여 반환
					Student student=new Student();
					student.setNo(rs.getInt("no"));
					student.setName(rs.getString("name"));
					student.setPhone(rs.getString("phone"));
					student.setAddress(rs.getString("address"));
					student.setBirthday(rs.getString("birthday"));
					return student;
				}
			}, no);
			*/
			return jdbcTemplate.queryForObject(sql, new StudentRowMapper(), no);
		} catch (EmptyResultDataAccessException e) {
			//EmptyResultDataAccessException : queryForObject() 메소드에 의해 전달된 SQL 명령의
			//검색결과가 존재하지 않는 경우 발생되는 예외
			return null;
		}
	}

	@Override
	public List<Student> selectStudentList() {
		String sql="select * from student order by no";
		//JdbcTemplate.query(String sql,RowMapper<T> rowMapper,Object... args) 
		// => SQL 명령(SELECT)을 DBMS 서버에 전달하여 실행하는 메소드
		// => 다중행의 검색결과를 List 객체로 반환할 경우 사용되는 메소드
		// => SQL 명령, RowMapper 객체, InParameter 전달값을 매개변수에 차례대로 나열하여 전달
		return jdbcTemplate.query(sql, new StudentRowMapper());
	}
	
	//RowMapper 인터페이스를 상속받은 자식클래스(내부클래스) - 매핑기능을 제공하는 클래스
	// => 익명의 내부클래스 대신 사용할 클래스 - 재사용성
	public class StudentRowMapper implements RowMapper<Student> {
		@Override
		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			Student student=new Student();
			student.setNo(rs.getInt("no"));
			student.setName(rs.getString("name"));
			student.setPhone(rs.getString("phone"));
			student.setAddress(rs.getString("address"));
			student.setBirthday(rs.getString("birthday"));
			return student;
		}
	}
}





