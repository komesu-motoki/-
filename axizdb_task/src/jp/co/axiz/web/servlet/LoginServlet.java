package jp.co.axiz.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.axiz.web.entity.Role;
import jp.co.axiz.web.entity.User_info;
import jp.co.axiz.web.service.RoleService;
import jp.co.axiz.web.service.User_infoService;
import jp.co.axiz.web.util.ParamUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		//doGet(request, response);

		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("loginId");
		String pass = request.getParameter("pass");
		String msg1 = "";
		String msg2 = "";
		String msg3 = "";

		HttpSession session = request.getSession();

		if(ParamUtil.isNullOrEmpty(id) && ParamUtil.isNullOrEmpty(pass)) {

			request.setAttribute("msg1", "IDは必須です");
			request.setAttribute("msg2", "PASSは必須です");

			request.getRequestDispatcher("login.jsp").forward(request, response);

		}

		if(ParamUtil.isNullOrEmpty(id)) {

			msg1 = "IDは必須です";
			request.setAttribute("msg1", msg1);

			request.getRequestDispatcher("login.jsp").forward(request, response);



		}
		if(ParamUtil.isNullOrEmpty(pass)) {

			msg2 = "PASSは必須です";
			request.setAttribute("msg2", msg2);

			request.getRequestDispatcher("login.jsp").forward(request, response);



		}

		User_infoService info = new User_infoService();
		User_info login = info.findByIdpass(id,pass);

		List<User_info> list  = info.findById(id);
		//System.out.println(list);

		String user_name = "";
		Integer role_id = null;

		for(User_info u : list) {

			user_name = u.getUser_name();
			role_id = u.getRole_id();

		}
		session.setAttribute("user_name", user_name);
		session.setAttribute("role_id", role_id);

		//System.out.println(role_id);

		if(login != null) {

			session.setAttribute("result", login);
			session.setAttribute("id", id);

			RoleService rs = new RoleService();
			List<Role> role = rs.findAll();

			session.setAttribute("role",role);

			request.getRequestDispatcher("menu.jsp").forward(request, response);


		}else {

			msg3 = "IDまたはPASSが間違っています";
			request.setAttribute("msg3", msg3);
			request.getRequestDispatcher("login.jsp").forward(request, response);

			return;

		}

	}

}
