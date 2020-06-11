package jp.co.axiz.web.servlet.delete;

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
 * Servlet implementation class DeleteConfirmServlet
 */
@WebServlet("/deleteConfirm")
public class DeleteConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteConfirmServlet() {
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

		String loginId = request.getParameter("loginId");

		System.out.println(loginId);

		User_infoService user = new User_infoService();
		List<User_info>listde = user.findDelete(loginId);

		session.setAttribute("listde", listde);

		request.getRequestDispatcher("deleteResult.jsp").forward(request, response);

	}

}
