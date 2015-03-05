package user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteUserAction extends HttpServlet {
	private static final long serialVersionUID = 3L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		    req.setCharacterEncoding("utf-8");
		    res.setContentType("application/json;charset=utf-8");
		    Connection cn = null;
		    String yhid =req.getParameter("yhid");
			String sql="delete from yhxxb where yhid=?";
			try {
				cn = UtilConnection.getCn();
				PreparedStatement ps = cn.prepareStatement(sql);
				ps.setString(1, yhid);
				int number = ps.executeUpdate();
				if (number!=0) {
					res.getWriter().print("{success:true,msg:'用户删除成功！'}");
				}else{
					res.getWriter().print("{success:true,msg:'用户删除失败，请联系管理员！'}");
				}
				ps.close();
				
			} catch (Exception e) {
				System.out.println("error:" + e.getMessage());
			} finally {
				try {
					cn.close();
				} catch (Exception e) {

				}
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
