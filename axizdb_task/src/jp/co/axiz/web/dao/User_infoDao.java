package jp.co.axiz.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.axiz.web.entity.User_info;
import jp.co.axiz.web.util.ParamUtil;

public class User_infoDao {

	private static final String SQL_SELECT_ALL = "SELECT * FROM user_info";
	private static final String SQL_SELECT_JOIN = "SELECT * FROM user_info u JOIN role r ON u.role_id = r.role_id WHERE ";
	private static final String SQL_SELECT_user = "SELECT * FROM user_info u JOIN role r ON u.role_id = r.role_id ";
	//private static final String ORDER_BY = " ORDER BY user_id";
	private static final String SQL_SELECT_ID = "SELECT * FROM user_info WHERE login_id = ?";
	private static final String SQL_SELECT_IDPASS = "SELECT * FROM user_info WHERE login_id  = ? AND password = ?";
	private static final String SQL_SELECT_LOGINID = "SELECT * FROM user_info WHERE login_id = ?";
	private static final String SQL_INSERT = "INSERT INTO user_info (login_id, user_name, telephone, password, role_id) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE ="UPDATE user_info SET login_id = ?, user_name = ?, telephone = ?, password = ?, role_id = ? WHERE user_id = ?";
	private static final String SQL_DELETE = "DELETE FROM user_info WHERE login_id = ?";
	private static final String SQL_SELECT_USERID = "SELECT * FROM user_info WHERE user_id <> ? AND login_id = ?";

	Connection connection;

	public User_infoDao() {

	}

	public User_infoDao(Connection connection) {
		this.connection = connection;
	}

	public List<User_info> findAll() {

		List<User_info> list = new ArrayList<User_info>();

		try(PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {

				User_info u = new User_info(rs.getInt("user_id"), rs.getString("login_id"), rs.getString("user_name"), rs.getString("telephone"), rs.getString("password"), rs.getInt("role_id"));
				list.add(u);

			}
		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

		return list;

	}

	public List<User_info> find(User_info ui) {

		ArrayList<String> conList = new ArrayList<>();
		ArrayList<Object> paramList = new ArrayList<>();

			String user_name = null;
			String telephone = null;

//			System.out.println(ui.getUser_name());
			if(ui != null ) {

				telephone = ui.getTelephone();
				user_name = ui.getUser_name();

			}
//			System.out.println(user_name);
//			System.out.println(telephone);


			if(ParamUtil.isNullOrEmpty(user_name) && ParamUtil.isNullOrEmpty(telephone)) {

				return  join();

			}

			if(!ParamUtil.isNullOrEmpty(user_name)) {

				conList.add("user_name = ?");
	            paramList.add(user_name);

			}

			if(!ParamUtil.isNullOrEmpty(telephone)) {

				conList.add("telephone = ?");
	        	paramList.add(telephone);

			}

			String whereString = String.join(" AND ", conList.toArray(new String[] {}));

			List<User_info> list = new ArrayList<>();

	        String sql = SQL_SELECT_JOIN + whereString;

			//System.out.println(sql);
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {

				for (int i = 0; i < paramList.size(); i++) {

	                stmt.setObject(i + 1, paramList.get(i));

	            }

				ResultSet rs = stmt.executeQuery();

				while(rs.next()) {

					User_info u = new User_info(rs.getInt("user_id"), rs.getString("login_id"), rs.getString("user_name"), rs.getString("telephone"), rs.getString("password"), rs.getInt("role_id"), rs.getString("role_name"));
					list.add(u);
				}

				return list;

			}catch (SQLException e) {

			throw new RuntimeException(e);

			}

	}

	public List<User_info> join() {

        List<User_info> list = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_user)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                User_info u = new User_info(rs.getInt("user_id"), rs.getString("login_id"), rs.getString("user_name"), rs.getString("telephone"), rs.getString("password"), rs.getInt("role_id"), rs.getString("role_name"));
                list.add(u);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return list;
    }


	public List<User_info> findById(String id) {

		List<User_info> list = new ArrayList<User_info>();

		try(PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ID)) {

			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {

				User_info u = new User_info(rs.getInt("user_id"), rs.getString("login_id"), rs.getString("user_name"), rs.getString("telephone"), rs.getString("password"), rs.getInt("role_id"));
				list.add(u);

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

		return list;

	}


	public User_info findByIdpass(String login_id, String password) {

		//List<User_info> list = new ArrayList<User_info>();

		try(PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_IDPASS)) {

			stmt.setString(1, login_id);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();

			if(rs.next()) {

				return new User_info(rs.getInt("user_id"), rs.getString("login_id"), rs.getString("user_name"), rs.getString("telephone"), rs.getString("password"), rs.getInt("role_id"));
				//list.add(u);

			}else {

				return null;

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public List<User_info> findlogin(String login_id) {

		List<User_info> list = new ArrayList<User_info>();

		try(PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_LOGINID)) {

			stmt.setString(1, login_id);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {

				User_info u = new User_info(rs.getInt("user_id"), rs.getString("login_id"), rs.getString("user_name"), rs.getString("telephone"), rs.getString("password"), rs.getInt("role_id"));
				list.add(u);

			}

			return list;

		}catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public int findInsert(String login_id, String user_name, String telephone, String password, Integer role_id) {

		//List<User_info> list = new ArrayList<User_info>();

		try(PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {

			stmt.setString(1, login_id);
			stmt.setString(2, user_name);
			stmt.setString(3, telephone);
			stmt.setString(4, password);
			stmt.setInt(5, role_id);

			return stmt.executeUpdate();



		}catch (SQLException e) {

			throw new RuntimeException(e);

		}



	}

	public List<User_info> findUpdate(String login_id, String user_name, String telephone, String password, Integer role_id, Integer user_id) {

		List<User_info> list = new ArrayList<User_info>();

		try (PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE)) {

			stmt.setString(1, login_id);
			stmt.setString(2, user_name);
			stmt.setString(3, telephone);
			stmt.setString(4, password);
			stmt.setInt(5, role_id);
			stmt.setInt(6, user_id);

			 int i = stmt.executeUpdate();

		}catch (SQLException e) {

			throw new RuntimeException(e);

		}

		return list;

	}

	public List<User_info> findDelete(String login_id) {

		List<User_info> list = new ArrayList<User_info>();

		try(PreparedStatement stmt = connection.prepareStatement(SQL_DELETE)) {

			stmt.setString(1, login_id);

			int i = stmt.executeUpdate();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

		return list;

	}

	public List<User_info> findByUserId(Integer user_id, String login_id) {

		List<User_info> list = new ArrayList<User_info>();

		try(PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_USERID)) {

			stmt.setInt(1, user_id);
			stmt.setString(2, login_id);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {

				User_info u = new User_info(rs.getString("login_id"), rs.getString("user_name"), rs.getString("telephone"), rs.getString("password"), rs.getInt("role_id"));
				list.add(u);

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

		return list;

	}

}
