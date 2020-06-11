package jp.co.axiz.web.servlet.update;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.axiz.web.entity.User_info;
import jp.co.axiz.web.service.User_infoService;
import jp.co.axiz.web.util.ParamUtil;

/**
 * Servlet implementation class UpdateInputServlet
 */
@WebServlet("/updateInput")
public class UpdateInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInputServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

		@SuppressWarnings("unchecked")
		List<User_info> listu = (List<User_info>)session.getAttribute("listu");

		String loginId = (String)session.getAttribute("id");

		Integer userId = null;
		String userName = "";
		String telephone = "";
		Integer roleid = null;
		String passWord = "";

		for(User_info u : listu) {

			userId = u.getUser_id();
			userName = u.getUser_name();
			telephone = u.getTelephone();
			roleid = u.getRole_id();
			passWord = u.getPassword();

		}

		String id = request.getParameter("loginId");
		String name = request.getParameter("userName");
		String tel = request.getParameter("tel");
		String roleId = request.getParameter("roleId");
		String pass = request.getParameter("pass");

		Integer RoleId = Integer.parseInt(roleId);

		if (ParamUtil.isNullOrEmpty(id)) {

			request.setAttribute("msg2", "IDは必須です。");

		}

		if (ParamUtil.isNullOrEmpty(name)) {

			request.setAttribute("msg3", "名前は必須です。");
		}

		if (ParamUtil.isNullOrEmpty(tel)) {

			request.setAttribute("msg4", "TELは必須です。");
		}

		if (ParamUtil.isNullOrEmpty(pass)) {

			request.setAttribute("msg5", "PASSは必須です。");

		}

		User_infoService user = new User_infoService();
		//List<User_info> login = user.findlogin(id);

		//Integer user_id = login.get(0).getUser_id();
		List<User_info> UserId = user.findByUserId(userId, id);

		System.out.println(UserId);

		String roleName = "";
		String rePass = "";

		System.out.println(pass);
		System.out.println(rePass);

		if (loginId.equals(id) && userName.equals(name) && telephone.equals(tel) && roleid.equals(RoleId) && passWord.equals(pass)) {

			request.setAttribute("msg1", "1項目以上変更してください。");

			request.getRequestDispatcher("updateInput.jsp").forward(request, response);

		}

		if (!UserId.isEmpty()) {

			request.setAttribute("msg6", "IDが重複しています。");

			request.getRequestDispatcher("updateInput.jsp").forward(request, response);

		}else {

			if (!ParamUtil.isNullOrEmpty(id) && !ParamUtil.isNullOrEmpty(name) && !ParamUtil.isNullOrEmpty(tel)
					&& !ParamUtil.isNullOrEmpty(roleId) && !ParamUtil.isNullOrEmpty(pass)) {

				if (RoleId == 1) {

					roleName = "管理者";

				} else if (RoleId == 2) {

					roleName = "一般";

				}

				if(pass.equals(passWord)) {

					rePass += pass;
					System.out.println("a");

				}

				request.setAttribute("rePass", rePass);

				session.setAttribute("userId", userId);
				session.setAttribute("loginId", id);
				session.setAttribute("userName", name);
				session.setAttribute("telephone", tel);
				session.setAttribute("pass", pass);
				session.setAttribute("RoleId", RoleId);
				session.setAttribute("roleName", roleName);

				request.getRequestDispatcher("updateConfirm.jsp").forward(request, response);

			}else {

				request.getRequestDispatcher("updateInput.jsp").forward(request, response);

			}

		}

	}

}
