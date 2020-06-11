package jp.co.axiz.web.servlet.insert;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.axiz.web.service.User_infoService;

/**
 * Servlet implementation class InsertConfirmServlet
 */
@WebServlet("/insertConfirm")
public class InsertConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertConfirmServlet() {
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
		String roleId = (String)session.getAttribute("roleId");
//		String role_name = (String)session.getAttribute("loleName");

		Integer ri = Integer.parseInt(roleId);

		if(!pass.equals(rePass)) {

			request.setAttribute("msg", "前画面で入力したパスワードと一致しません。");

			request.getRequestDispatcher("insertConfirm.jsp").forward(request, response);

		}else {

			User_infoService service = new User_infoService();
			//dao.findInsert(info);
			int info = service.findInsert(loginId, userName, tel, pass, ri);

			request.getRequestDispatcher("insertResult.jsp").forward(request, response);

		}

	}

}
