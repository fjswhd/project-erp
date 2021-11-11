package emp.model;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class EmpDao {
	private static SqlSessionFactory sessionFactory;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			System.out.println("연결에러 : " + e.getMessage());
		}
	}
	private static EmpDao instance;
	private EmpDao() {}
	public static EmpDao getInstance() {
		if (instance == null) {
			instance = new EmpDao();
		}
		return instance;
	}
	
	public Emp select(String empno) {
		SqlSession session = sessionFactory.openSession(true);
		
		Emp emp = (Emp)session.selectOne("empNS.select", empno);
		
		session.close();
		
		return emp;
	}
}
