package cn.itcast.demo3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.junit.Test;

public class Demo3 {
	/**
	 * 登陆
	 * 使用username和password的数据进行相关的数据进行查询
	 * 若可以查询出结果集，说明正确！返回true
	 * 若是无法查询出结果，则说明了用户名或者密码错误，返回false
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean login(String username,String password) throws ClassNotFoundException , SQLException{
		String driverClassName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mydb1?useUnicode=true&characterEncoding=UTF-8";
		String mysqlUsername = "root";
		String mysqlPassword = "1234";
		
		Connection conn = null;
		//Statement stmt = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driverClassName);
			//conn = DriverManager.getConnection(url,mysqlUsername,mysqlPassword);
			conn = JdbcUtils.getConnection();
			//stmt = conn.createStatement();
			String sql = "select * from t_user where username = ? and password = ?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, username);
			ptmt.setString(2, password);
			rs = ptmt.executeQuery();
			return rs.next();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(rs!=null) rs.close();
			if(ptmt!=null) ptmt.close();
			if(conn!=null) conn.close();
		}
		return false;
	}
	@Test
	public void testLogin() throws Exception{
		String username = null;
		String password = null;
		Scanner scan = new Scanner(System.in);
		System.out.print("请输入用户名:");
		username = scan.nextLine();
		byte[] b = username.getBytes("gbk");//利用gbk解析为二进制代码
		username = new String(b,"utf-8");//将二进制代码利用utf-8重新编码
		System.out.print("请输入密码:");
		password = scan.nextLine();
		System.out.println("登陆状态："+login(username, password));
	}
}
