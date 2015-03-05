package user;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction extends HttpServlet {

	private static final long serialVersionUID = 110L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("application/json;charset=utf-8");
		System.out.println(req.getSession().getId());
		req.getSession().setAttribute("login", 0);
		Enumeration<String> em = req.getSession().getAttributeNames();
        while(em.hasMoreElements()){
            req.getSession().removeAttribute(em.nextElement().toString());
        }
        req.getSession().invalidate();
        req.getSession().setMaxInactiveInterval(0);
        res.getWriter().write("{success:true,msg:'注销成功！'}");

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}

	public void destroy() {
		super.destroy();
	}
}
