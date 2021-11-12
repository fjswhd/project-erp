package hr.model;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class HrDao {
	private static SqlSessionFactory sqlSessionFactory;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			System.out.println("연결에러 : " + e.getMessage());
		}
	}
	private static HrDao instance;
	private HrDao() {}
	public static HrDao getInstance() {
		if (instance == null) {
			instance = new HrDao();
			
		}
		return instance;
	}
	
	public Emp selectEmp(String empNo) {
		SqlSession session = sqlSessionFactory.openSession(true);
		Emp emp = (Emp)session.selectOne("hrNS.selectEmp", empNo);
		
		session.close();
		
		return emp;
	}
	public List<Emp> selectEmpList() {
		SqlSession session = sqlSessionFactory.openSession(true);
		List<Emp> empList = session.selectList("hrNS.selectEmpList");
		
		session.close();
		
		return empList;
	}
	
}
