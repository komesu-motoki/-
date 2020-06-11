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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
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


		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("loginId");

		if (ParamUtil.isNullOrEmpty(id)) {

			request.setAttribute("msg1", "IDは必須です。");
			request.getRequestDispatcher("update.jsp").forward(request, response);
			return;

		}

		User_infoService uis = new User_infoService();
		List<User_info> listu = uis.findById(id);

		if(listu.isEmpty()) {

			request.setAttribute("msg2", "入力されたデータは存在しません。");
			request.getRequestDispatcher("update.jsp").forward(request, response);
			return;

		}else {

			session.setAttribute("id", id);
			session.setAttribute("listu", listu);
			request.getRequestDispatcher("updateInput.jsp").forward(request, response);
			return;

		}


	}

}
