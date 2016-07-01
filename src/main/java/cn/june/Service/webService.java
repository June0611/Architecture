package cn.june.Service;

import cn.june.bean.UserBean;

public class webService {
	DBServer dbServer = new DBServer();
	public void rigester(UserBean user){
		if(dbServer.findUser(user.getUserName()) == null){//用户不存在,可以注册
			int result = dbServer.addUser(user);
			if(result == 1)
				System.out.println("注册成功");
		}
	}
	public void login(UserBean user){
		UserBean resutlUser = dbServer.findUser(user.getUserName(), user.getUserPassword());
		if(resutlUser == null){
			System.out.println("用户不存在");
			return;
		}
		System.out.println("登录成功");
	}
}
