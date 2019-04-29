package cn.com.goolife.db.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.com.goolife.dao.UserDao;
import cn.com.goolife.dao.UserDaoRowMapper;

@Repository
public class DBControllerWithOutPool {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private UserDaoRowMapper userDaoRowMapper;
	
	@SuppressWarnings("finally")
	public List<UserDao> getUsers(){
		Connection connection = null;
		PreparedStatement statement = null;
		List<UserDao> list = new ArrayList<UserDao>();
		String sql = " select * from user";
		try {
			connection = this.dataSource.getConnection();
			statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				UserDao userDao = new UserDao();
				userDao.setId(resultSet.getString("id"));
				userDao.setUsername(resultSet.getString("username"));
				userDao.setPassword(resultSet.getString("password"));
				list.add(userDao);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(statement!=null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return list;
		}
	}
	
	public List<UserDao> getUsersByJdbcTemplate(){
		String sql = " select * from user  ";
		List<UserDao> list = jdbcTemplate.query(sql,userDaoRowMapper);
		return list;
	}
	
	public List<String> getUserNames(){
		String sql = " select username from user ";
		//只能用来查询单值数据
		List<String> list = jdbcTemplate.queryForList(sql, String.class);
	    return list;
	}
	
	public List<UserDao> getUsers(String name){
		String sql = " select * from user where username = ? ";
		String[] sbc = {name};
		List<UserDao> list = jdbcTemplate.query(sql, sbc, userDaoRowMapper);
		return list;
	}
}
