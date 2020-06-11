package jp.co.axiz.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.axiz.web.entity.Role;

public class RoleDao {

	private static final String SQL_SELECT_ALL = "SELECT * FROM role";

	private Connection connection;

	public RoleDao(Connection connection) {

		this.connection = connection;

	}

	public List<Role> findAll() {

		List<Role> list = new ArrayList<Role>();

		try(PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {

				Role u = new Role(rs.getInt("role_id"), rs.getString("role_name"));
				list.add(u);

			}
		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

		return list;

	}




}
