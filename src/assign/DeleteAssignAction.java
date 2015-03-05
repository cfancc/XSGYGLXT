package assign;

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

import user.UtilConnection;

public class DeleteAssignAction extends HttpServlet {
	private static final long serialVersionUID = 3L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		    req.setCharacterEncoding("utf-8");
		    res.setContentType("application/json;charset=utf-8");
		    Connection cn = null;
		    String xsid =req.getParameter("xsid");
		    String ssid =req.getParameter("ssid");
			String sql="delete from xsssb where xsid=?";
			String sql1="update xsssb set sfyyss='否' where xsid=?";
			String sql2="update ssxxb set sfym='否' where ssid=?";
			try {
				cn = UtilConnection.getCn();
				PreparedStatement ps = cn.prepareStatement(sql);
				ps.setString(1, xsid);
				int number = ps.executeUpdate();
				
				ps = cn.prepareStatement(sql1);
				ps.setString(1, xsid);
				int number1 = ps.executeUpdate();
				
				ps = cn.prepareStatement(sql2);
				ps.setString(1, ssid);
				int number2 = ps.executeUpdate();
				
				if (number!=0&&number1!=0&&number2!=0) {
					res.getWriter().print("{success:true,msg:'删除成功！'}");
				}else{
					res.getWriter().print("{success:true,msg:'删除失败，请联系管理员！'}");
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
