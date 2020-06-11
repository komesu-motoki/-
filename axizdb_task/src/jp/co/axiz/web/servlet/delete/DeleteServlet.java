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
import jp.co.axiz.web.util.ParamUtil;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
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

		String id = request.getParameter("loginId");

		String loginId = (String)session.getAttribute("id");

		Integer roleId = null;
		String roleName = "";

		User_infoService uis = new User_infoService();
		List<User_info> list = uis.findById(id);

//		String loginId = "";
//
		for(User_info u : list) {

			roleId = u.getRole_id();

		}

		//session.setAttribute("loginId", loginId);

		System.out.println(id);
		System.out.println(loginId);
//		System.out.println(list);

		if(ParamUtil.isNullOrEmpty(id)) {

			request.setAttribute("msg1", "IDは必須です。");
			request.getRequestDispatcher("delete.jsp").forward(request, response);

//			System.out.println("1");

		}

		if(loginId.equals(id)) {

			request.setAttribute("msg2", "ログインユーザーは削除できません。");
			request.getRequestDispatcher("delete.jsp").forward(request, response);
//			System.out.println("2");

		}

		if(list.isEmpty()) {

			request.setAttribute("msg3", "入力されたデータは存在しません。");
			request.getRequestDispatcher("delete.jsp").forward(request, response);

//			System.out.println("3");

		}else {

//			System.out.println("4");

			if (roleId == 1) {

				roleName = "管理者";

			} else if (roleId == 2) {

				roleName = "一般";

			}

			request.setAttribute("id", id);
			request.setAttribute("roleName", roleName);
			session.setAttribute("list", list);

			request.getRequestDispatcher("deleteConfirm.jsp").forward(request, response);

		}



	}

}
