package cn.itcast.demo3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
/**
 * Version 1.0
 * @author 杨谦
 * @date 2015-8-14 下午9:28:15
 *
 */
public class JdbcUtils {
	private static Properties props = null;
	
	static{
		//给Props进行初始化，即加载JdbcConfig
		try {
			//放在src下则自然可以加载该文件
			//加载配置文件
			InputStream in = JdbcUtils.class.getClassLoader()
					.getResourceAsStream("dbconfig.properties");
			props = new Properties();
			props.load(in);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("=============加载配置文件出错=============");
			throw new RuntimeException(e);
		}
		
		//加载驱动类
		try {
			//加载驱动类
			Class.forName(props.getProperty("driverClassName"));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("=============加载驱动时候出错=============");
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(props.getProperty("url"),
					props.getProperty("username"),props.getProperty("password"));
	}
	@Test
	public void testJDBCUtilsForConnection() throws Exception{
		System.out.println(getConnection());
	}
}
