package cn.itcast.demo2;

import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Demo2 {
	@Test
	public void fun1() throws ClassNotFoundException, SQLException{
		/*
		 * 一、得到Connection
		 * 连接数据库，得到Connection就算成功
		 * 1、参数准备
		 * 2、利用Class.forName()加载类
		 * 3、得到Connection
		 */
		//准备四大参数
		String driverClassName = "com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/exam?useUnicode=true&characterEncoding=UTF-8";
		String username="root";
		String password="1234";
		
		//加载驱动类
		Class.forName(driverClassName);
		//打开Connection对象
		Connection conn = DriverManager.getConnection(url, username, password);
	
		/*
		 * 二、对数据库进行增删改查
		 * 1、通过Connection对象创建Statement
		 * >Statement项数据库发送SQL语句
		 * 2、调用他的int executeUpdate(String sql),他可以发送DML、DDL语句
		 */
		//1、通过connection获得Statement对象
		Statement stmt = conn.createStatement();
		//2、使用Statement发送Sql语句
		//String sql = "INSERT INTO stu VALUES ('56', '张晓燕', '23', '女', '成都', '4500');";
		//String sql = "Update stu set sname='张子凡',gander='男' where sid='56';"; 
		String sql = "delete from stu where sname='张子凡'";
		int r = stmt.executeUpdate(sql);
		System.out.println("影响的行数："+r);
		stmt.close();
		conn.close();
	}
	/**
	 * 执行查询
	 * @throws SQLException 
	 */
	@Test
	public void fun2() throws ClassNotFoundException, SQLException{
		String driverClassName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/exam";
		String username = "root";
		String password = "1234567890";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driverClassName);
			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
			String sql = "select * from stu;";
			rs = stmt.executeQuery(sql);
//			while(rs.next()){
//				System.out.println("ID号码："+rs.getString("sid")+"  姓名："+rs.getString("sname")
//						+"   工资："+rs.getString("tuition"));
//			}
			int count = rs.getMetaData().getColumnCount();
			while(rs.next()){
				for(int i = 1;i <= count;i++){
					System.out.print(rs.getString(i));
					if(i < count){
						System.out.print(",");
					}
				}
				System.out.println();
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		} finally {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		}
		
	}
}
