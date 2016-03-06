package cn.itcast.demo5;

import org.junit.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.itcast.demo3.JdbcUtils;

/**
 * 批处理的使用
 * @author 杨谦
 * @date 2015-8-15 上午10:54:53
 *
 */
public class Demo5 {
	@Test
	public void fun1() throws SQLException {
		/*
		 * pstmt
		 * >添加参数到批中
		 * >执行批处理
		 */
		Connection conn = JdbcUtils.getConnection();
		String sql = "INSERT INTO t_stu VALUES(?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		//开始疯狂的添加参数
		for(int i=0;i<8000;i++){
			pstmt.setInt(1, i+1);
			pstmt.setString(2, "stu_"+i);
			pstmt.setInt(3, i);
			pstmt.setString(4, i%2==0?"M":"F");
			
			pstmt.addBatch();//添加批！否则又会重新添加到第一句中，永远只有一句
		}
		long start = System.currentTimeMillis();
		pstmt.executeBatch();
		long end = System.currentTimeMillis();
		System.out.println("共耗时："+(end-start));//337867,使用了rewriteBatchedStatement=true;
	}
}
