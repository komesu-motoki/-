package jp.co.axiz.web.servlet.select;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.axiz.web.entity.User_info;
import jp.co.axiz.web.service.User_infoService;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/select")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		request.setCharacterEncoding("UTF-8");

		String name = request.getParameter("userName");
		String tel = request.getParameter("telephone");
		String msg = "";

			User_infoService user = new User_infoService();
			User_info info = new User_info(null, null, name, tel, null, null, null);
			List<User_info> list = user.find(info);


			if (list.isEmpty()) {

				msg =  "入力されたデータはありませんでした";
				request.setAttribute("msg",msg);
				request.getRequestDispatcher("select.jsp").forward(request, response);

			} else {

				request.setAttribute("list",list);
				request.getRequestDispatcher("selectResult.jsp").forward(request, response);

			}

			System.out.println(list.size());


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
