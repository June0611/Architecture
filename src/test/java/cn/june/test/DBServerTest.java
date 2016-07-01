package cn.june.test;

import org.junit.Test;

import cn.june.Service.DBServer;
import cn.june.bean.UserBean;

public class DBServerTest {

	@Test
	public void testDBConnct() {
		DBServer dbServer = new DBServer();
		System.out.println(dbServer.getConn());
	}

	@Test
	public void testDBAddUser() {
		DBServer dbServer = new DBServer();
		UserBean user = new UserBean();
		user.setUserName("fanhaiwei");
		int result = dbServer.addUser(user);
		System.out.println(result + "");
	}

	@Test
	public void testDBFindUserByUserName() {
		DBServer dbServer = new DBServer();
		UserBean user = dbServer.findUser("qugaigai");
		if (user != null)
			System.out.println(user.getUserName());
		else
			System.out.println(user);
	}
	@Test
	public void testDBFindUserByUserNameAndPwd() {
		DBServer dbServer = new DBServer();
		UserBean user = dbServer.findUser("qugaigai","123");
		if (user != null)
			System.out.println(user.getUserName()+user.getUserPassword());
		else
			System.out.println(user);
	}

}
