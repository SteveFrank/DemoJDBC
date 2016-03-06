package cn.itcast.demo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class Demo1 {
	/**
	 * ClassNotFoundException
	 * 没有导包会导致该错误
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void fun1() throws ClassNotFoundException,SQLException{
		
		Class.forName("com.mysql.jdbc.Driver");//加载驱动
		
		String url="jdbc:mysql://localhost:3306/exam";//获取数据库的连接
		String username = "root";
		String password = "1234";
		
		Connection conn = DriverManager.getConnection(url,username,password);
		
		
	}
}
