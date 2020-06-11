package jp.co.axiz.web.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import jp.co.axiz.web.dao.User_infoDao;
import jp.co.axiz.web.entity.User_info;
import jp.co.axiz.web.util.DbUtil;

public class User_infoService {

	public List<User_info> findAll() {

		List<User_info> res = new ArrayList<User_info>();

		try (Connection con = DbUtil.getConnection()) {

			User_infoDao userdao = new User_infoDao(con);

			res = userdao.findAll();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return res;

	}

	public List<User_info> find(User_info ui) {

		List<User_info> res = new ArrayList<User_info>();

		try (Connection con = DbUtil.getConnection()) {

			User_infoDao userDao = new User_infoDao(con);

			res = userDao.find(ui);

		}catch (Exception e) {

			e.printStackTrace();

		}

		return res;

	}


	public List<User_info> findById(String id) {

		List<User_info> res = new ArrayList<User_info>();

		try (Connection con = DbUtil.getConnection()) {

			User_infoDao userdao = new User_infoDao(con);

			res = userdao.findById(id);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return res;

	}

	public User_info findByIdpass(String login_id, String password) {

		//List<User_info> res = new ArrayList<User_info>();

		try (Connection con = DbUtil.getConnection()) {

			User_infoDao userdao = new User_infoDao(con);

			User_info ui = userdao.findByIdpass(login_id, password);

			return ui;

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	public List<User_info> findlogin(String login_id) {

		List<User_info> res = new ArrayList<User_info>();

		try (Connection con = DbUtil.getConnection()) {

			User_infoDao userdao = new User_infoDao(con);

			res = userdao.findlogin(login_id);

			return res;

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;
	}

	public int findInsert(String login_id, String user_name, String telephone, String password, Integer role_id) {

		//List<User_info> res = new ArrayList<User_info>();

		int i = 0;

		try (Connection con = DbUtil.getConnection()) {

			User_infoDao userdao = new User_infoDao(con);

			i = userdao.findInsert(login_id, user_name, telephone, password, role_id);


		} catch (Exception e) {

			e.printStackTrace();

		}

		return i;

	}

	public List<User_info> findUpdate(String login_id, String user_name, String telephone, String password, Integer role_id, Integer user_id) {

		List<User_info> res = new ArrayList<User_info>();

		//int i = 0;

		try (Connection con = DbUtil.getConnection()) {

			User_infoDao userdao = new User_infoDao(con);

			res = userdao.findUpdate(login_id, user_name, telephone, password, role_id, user_id);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return res;

	}

	public List<User_info> findDelete(String login_id) {

		List<User_info> res = new ArrayList<User_info>();

		try (Connection con = DbUtil.getConnection()) {

			User_infoDao userdao = new User_infoDao(con);

			res = userdao.findDelete(login_id);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return res;

	}

	public List<User_info> findByUserId(Integer user_id, String login_id) {

		//List<User_info> res = new ArrayList<User_info>();

		try (Connection con = DbUtil.getConnection()) {

			User_infoDao userdao = new User_infoDao(con);

			return userdao.findByUserId(user_id, login_id);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}


}
