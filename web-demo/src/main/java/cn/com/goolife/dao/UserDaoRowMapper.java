package cn.com.goolife.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoRowMapper implements RowMapper<UserDao> {

	@Override
	public UserDao mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserDao userDao = new UserDao();
		userDao.setId(rs.getString("id"));
		userDao.setPassword(rs.getString("password"));
		userDao.setUsername(rs.getString("username"));
		return userDao;
	}

}
