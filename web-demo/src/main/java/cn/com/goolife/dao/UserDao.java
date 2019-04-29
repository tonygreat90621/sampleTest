package cn.com.goolife.dao;

import org.springframework.beans.factory.annotation.Autowired;

public class UserDao implements Dao{

	private String id;
	
	private String username;
	
	private String password;

	public String getId() {
		return id;
	}

	@Autowired
	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	@Autowired
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	@Autowired
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
