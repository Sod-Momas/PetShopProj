package cn.icloudit.pet.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
	private String className = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/petshop?useUnicode=true&characterEncoding=utf-8";
	private String username = "root";
	private String password = "root";
	
	private Connection con;
	
	public DBHelper(){
		try {
			Class.forName(className);
			con = DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("类没有找到");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("sql连接有问题");
		}
	}
	
	public Connection getConnection(){
		return con;
	}
	
	public static void close(ResultSet rs, PreparedStatement ps, Statement st, Connection con){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(ps != null){
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
