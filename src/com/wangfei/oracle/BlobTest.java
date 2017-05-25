package com.wangfei.oracle;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BlobTest {

	public static void main(String[] args) {
		Connection con = null;
		InputStream fin = null;
		OutputStream outStream = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "wangfei", "root");
			// 处理事务
			con.setAutoCommit(false);
			Statement st = con.createStatement();
			// 插入一个空对象
			st.executeUpdate("insert into user_info  values(103,'dd',empty_blob())");
			// 用for update方式锁定数据行
			ResultSet rs = st.executeQuery("select image from  user_info  where  user_id=103 for update");
			if (rs.next()) {
				// 得到java.sql.Blob对象，然后Cast为oracle.sql.BLOB
				oracle.sql.BLOB blob = (oracle.sql.BLOB) rs.getBlob(1);
				// 到数据库的输出流
				outStream = blob.getBinaryOutputStream();
				// 这里用一个文件模拟输入流
				File file = new File("D:\\FileTempStorge\\storge.txt");
				fin = new FileInputStream(file);
				// 将输入流写到输出流
				byte[] b = new byte[blob.getBufferSize()];
				int len = 0;
				while ((len = fin.read(b)) != -1) {
					outStream.write(b, 0, len);
					// blob.putBytes(1,b);
				}
				// 依次关闭（注意顺序）
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fin.close();
				outStream.flush();
				outStream.close();
				con.commit();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
