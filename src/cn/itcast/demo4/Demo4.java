package cn.itcast.demo4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import cn.itcast.demo3.JdbcUtils;

import com.mysql.jdbc.Blob;
public class Demo4 {
	/**
	 * 保存mp3文件到数据库中
	 */
	@Test
	public void fun1() throws SQLException,IOException{
		Connection conn = JdbcUtils.getConnection();
		String sql = "insert into tab_bin values(?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, 1);
		pstmt.setString(2, "孙子涵 - 唐人.mp3");
		/**
		 * 从硬盘上获取文件得到blob
		 * 1、具有文件名，目标是Blob
		 * 2、先把文件变成byte[]
		 * 3、在使用byte[]创建Blob
		 */
		//将文件转换成为byte[]
		byte[] bytes = IOUtils.toByteArray(new FileInputStream("F:/孙子涵 - 唐人.mp3"));
		//使用字节数组文件创建Blob
		SerialBlob blob = new SerialBlob(bytes);
		//设置参数
		pstmt.setBlob(3, blob);
		//执行语句
		pstmt.executeUpdate();
	}
	
	/**
	 * 保存mp3文件到数据库中
	 * @throws IOException 
	 */
	@Test
	public void fun2() throws SQLException, IOException{
		Connection conn = JdbcUtils.getConnection();
		String sql = "select * from tab_bin";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		/**
		 * 获取rs中的名为data的列数据
		 */
		if(rs.next()){
			Blob blob = (Blob) rs.getBlob("data");
			/**
			 * 变成为硬盘上的文件
			 * 1、通过Blob得到输入流对象
			 * 2、自己创建输出流对象
			 * 3、吧输入流的数据写入到输出流中
			 */
			InputStream in = blob.getBinaryStream();
			OutputStream out = new FileOutputStream("F:/音乐.mp3");
			IOUtils.copy(in, out);
			
		}
	}
}
