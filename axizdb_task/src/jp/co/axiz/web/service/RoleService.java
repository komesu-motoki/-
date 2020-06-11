package jp.co.axiz.web.service;

import java.sql.Connection;
import java.util.List;

import jp.co.axiz.web.dao.RoleDao;
import jp.co.axiz.web.entity.Role;
import jp.co.axiz.web.util.DbUtil;

public class RoleService {

	public List<Role> findAll() {

		//List<Role> res = new ArrayList<Role>();

		try (Connection con = DbUtil.getConnection()) {

			RoleDao roledao = new RoleDao(con);

			return roledao.findAll();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;


	}





}
