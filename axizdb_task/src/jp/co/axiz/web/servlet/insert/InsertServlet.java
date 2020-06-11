package jp.co.axiz.web.servlet.insert;

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
 * Servlet implementation class InsertServlet
 */
@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

		String roleId = request.getParameter("roleId");
		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String tel = request.getParameter("telephone");
		//String roleName = request.getParameter("rolename");
		String pass = request.getParameter("pass");

		int ri = Integer.parseInt(roleId);

		if (ri == 1) {

			session.setAttribute("roleName", "管理者");

		} else if (ri == 2) {

			session.setAttribute("roleName", "一般");

		}

		User_infoService user = new User_infoService();
		List<User_info> login = user.findlogin(loginId);

		if (ParamUtil.isNullOrEmpty(loginId)) {

			request.setAttribute("msg1", "IDは必須です。");
			//request.getRequestDispatcher("insert.jsp").forward(request, response);

		}

		if (ParamUtil.isNullOrEmpty(userName)) {

			request.setAttribute("msg2", "名前は必須です。");
			//request.getRequestDispatcher("insert.jsp").forward(request, response);

		}

		if (ParamUtil.isNullOrEmpty(tel)) {

			request.setAttribute("msg3", "TELは必須です。");
			//request.getRequestDispatcher("insert.jsp").forward(request, response);

		}

		if (ParamUtil.isNullOrEmpty(pass)) {

			request.setAttribute("msg4", "PASSは必須です。");
			//request.getRequestDispatcher("insert.jsp").forward(request, response);

		}

		if (!login.isEmpty()) {

			request.setAttribute("msg5", "IDが重複しています。");

			request.getRequestDispatcher("insert.jsp").forward(request, response);

		} else {

			if (!ParamUtil.isNullOrEmpty(loginId) && !ParamUtil.isNullOrEmpty(userName) && !ParamUtil.isNullOrEmpty(tel)
					&& !ParamUtil.isNullOrEmpty(roleId) && !ParamUtil.isNullOrEmpty(pass)) {

				session.setAttribute("loginId", loginId);
				session.setAttribute("userName", userName);
				session.setAttribute("telephone", tel);
				session.setAttribute("pass", pass);
				session.setAttribute("roleId", roleId);

				request.getRequestDispatcher("insertConfirm.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("insert.jsp").forward(request, response);
				System.out.println("wwww");
			}
		}

	}

}
