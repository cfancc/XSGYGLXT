package user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAction extends HttpServlet {

	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("application/json;charset=utf-8");
			String dlm=req.getParameter("dlm");
			String mm=req.getParameter("mm");
			String sql = "select * from yhxxb where dlm='"+dlm+"' and mm='"+mm+"'";
			Connection cn = UtilConnection.getCn();
			PreparedStatement ps;
			try {
				ps = cn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					req.getSession().setAttribute("login", 1);
					System.out.println(req.getSession().getId());
					res.getWriter().print("{success:true,msg:'登陆成功！'}");
				}else{
					res.getWriter().print("{success:true,msg:'用户名或密码错误！'}");
				}
				rs.close();
				ps.close();
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}

	public void destroy() {
		super.destroy();
	}
}
