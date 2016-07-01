package cn.june.test;

import org.junit.Test;

import cn.june.Service.DBServer;
import cn.june.bean.UserBean;

public class DBServerTest {

	@Test
	public void testDBConnct()
	{
		DBServer dbServer = new DBServer();
		System.out.println(dbServer.getConn());
	}
	@Test
	public void testDBAddUser()
	{
		DBServer dbServer = new DBServer();
		UserBean user = new UserBean();
		user.setUserName("fanhaiwei");
		int result = dbServer.addUser(user);
		System.out.println(result + "");
	}
	@Test
	public void testDBFindUser()
	{
		DBServer dbServer = new DBServer();
		System.out.println(dbServer.findUser("fanhaiwei"));
	}
	
}
