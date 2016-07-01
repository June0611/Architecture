package cn.june.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.june.bean.UserBean;

public class DBServer {

	private String dbDriver = "com.mysql.jdbc.Driver";
	private String dbUrl = "jdbc:mysql://localhost:3306/Architecture_DB";
	private String dbUser = "root";
	private String dbPass = "20120611";

	public int addUser(UserBean user) {

		PreparedStatement ps = null;
		int rs = 0;
		if((findUser(user.getUserName()) != null))
			return 0;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			String dateStr = df.format(new Date());
			Connection cn = getConn();
			String sql = "insert into User_TB(UserName,PassWord,NickName,Email,BirthDay,PhoneNumber,creatTime) values(?,?,?,?,?,?,?)";
			ps = cn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserPassword());
			ps.setString(3, user.getNickName());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getBirthDay());
			ps.setString(6, user.getPhoneNumber());
			ps.setString(6, dateStr);
			rs = ps.executeUpdate();
			cn.close();
		} catch (Exception e) {
			System.out.print("Error");
		}
		System.out.println("数据添加成功!");

		return rs;
	}

	public UserBean findUser(String userName, String password) {
		String sql = "select * from User_TB where UserName=" + "'"+ userName +"'"
				+ " and PassWord=" + "'"+password + "'";
		return findUserWithSqlString(sql);
	}

	public UserBean findUser(String userName) {
		String sql = "select * from User_TB where UserName=" +"'"+ userName+"'";
		return findUserWithSqlString(sql);
	}

	public UserBean findUserWithSqlString(String sqlStr) {
		Connection cnn = getConn();
		PreparedStatement pstmt;
		UserBean user = null;
		try {
			pstmt = (PreparedStatement) cnn.prepareStatement(sqlStr);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new UserBean();
				user.setUserID(rs.getString(1));
				user.setUserName(rs.getString(2));
				user.setUserPassword(rs.getString(3));
				user.setNickName(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setBirthDay(rs.getString(6));
				user.setPhoneNumber(rs.getString(7));
				user.setCreatTime(rs.getString(8));
				cnn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;

	}

	public Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);// 注意是三个参数
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
