package dorm;

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

public class DeleteDormAction extends HttpServlet {
	private static final long serialVersionUID = 3L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		    req.setCharacterEncoding("utf-8");
		    res.setContentType("application/json;charset=utf-8");
		    Connection cn = null;
		    String ssid =req.getParameter("ssid");
			String sql="delete from ssxxb where ssid=?";
			String sql2="delete from xsssb where ssid=?";
			try {
				cn = UtilConnection.getCn();
				
				//更新学生在此宿舍的
				PreparedStatement ps=null;
				String sq = "update xsxxb set sfyyss='否' where xsid in(select xsid from xsssb where ssid=?)";
				ps = cn.prepareStatement(sq);
				ps.setString(1, ssid);
				ps.executeUpdate();
				
				
				ps = cn.prepareStatement(sql2);
				ps.setString(1, ssid);
				ps.executeUpdate();
				
				ps = cn.prepareStatement(sql);
				ps.setString(1, ssid);
				int number = ps.executeUpdate();
				if (number!=0) {
					res.getWriter().print("{success:true,msg:'宿舍删除成功！'}");
				}else{
					res.getWriter().print("{success:true,msg:'宿舍删除失败，请联系管理员！'}");
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
