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

/**
 * Servlet implementation class UpdateConfirmServlet
 */
@WebServlet("/updateConfirm")
public class UpdateConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateConfirmServlet() {
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

		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

		String pass = (String)session.getAttribute("pass");
		String rePass = request.getParameter("rePass");

		String loginId = (String)session.getAttribute("loginId");
		String userName = (String)session.getAttribute("userName");
		String tel = (String)session.getAttribute("telephone");
		Integer roleId = (Integer)session.getAttribute("RoleId");
		Integer userId = (Integer)session.getAttribute("userId");

//		Integer roleId = Integer.parseInt(roleid);
//		Integer userId = Integer.parseInt(userid);

		if(!pass.equals(rePass)) {

			request.setAttribute("msg1", "前画面で入力したパスワードと一致しません。");
			request.getRequestDispatcher("updateConfirm.jsp").forward(request, response);

		}else {

			User_infoService uis = new User_infoService();
			List<User_info> listui = uis.findUpdate(loginId, userName, tel, rePass, roleId, userId);

			session.setAttribute("listui", listui);

			request.getRequestDispatcher("updateResult.jsp").forward(request, response);
		}

	}

}
