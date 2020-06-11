package jp.co.axiz.web.entity;

public class User_info {

	private Integer user_id;
	private String login_id;
	private String user_name;
	private String telephone;
	private String password;
	private Integer role_id;
	private String role_name;


	public User_info() {

	}

	public User_info(Integer user_id, String login_id, String user_name, String telephone, String password, Integer role_id) {

		this.user_id = user_id;
		this.login_id = login_id;
		this.user_name = user_name;
		this.telephone = telephone;
		this.password = password;
		this.role_id = role_id;

	}

	public User_info(Integer user_id, String login_id, String user_name, String telephone, String password, Integer role_id, String role_name) {

		this.user_id = user_id;
		this.login_id = login_id;
		this.user_name = user_name;
		this.telephone = telephone;
		this.password = password;
		this.role_id = role_id;
		this.role_name = role_name;

	}

	public User_info(String login_id, String user_name, String telephone, String password, Integer role_id) {

		this.login_id = login_id;
		this.user_name = user_name;
		this.telephone = telephone;
		this.password = password;
		this.role_id = role_id;

	}


	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

}
