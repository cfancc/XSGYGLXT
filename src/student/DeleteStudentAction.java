package student;

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

public class DeleteStudentAction extends HttpServlet {
	private static final long serialVersionUID = 3L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		    req.setCharacterEncoding("utf-8");
		    res.setContentType("application/json;charset=utf-8");
		    Connection cn = null;
		    String xsid =req.getParameter("xsid");
		    String ssid =req.getParameter("ssid");
		    String sfyyss =req.getParameter("sfyyss");
			String sql="delete from xsxxb where xsid=?";
			try {
				cn = UtilConnection.getCn();
				PreparedStatement ps = cn.prepareStatement(sql);
				ps.setString(1, xsid);
				int number = ps.executeUpdate();
				
				//对已经分配宿舍学生的宿舍信息更改
				if(sfyyss!=null&&!("").equals(sfyyss)&&!("null").equals(sfyyss)&&("是").equals(sfyyss)
						&&ssid!=null&&!("").equals(ssid)&&!("null").equals(ssid)){
					sql = "delete from xsssb where xsid=?";
					ps = cn.prepareStatement(sql);
					ps.setString(1, xsid);
				    ps.executeUpdate();
					
					sql = "update ssxxb set sfym='否' where ssid=?";
					ps = cn.prepareStatement(sql);
					ps.setString(1, ssid);
				    ps.executeUpdate();
				}
				
				
				if (number!=0) {
					res.getWriter().print("{success:true,msg:'学生删除成功！'}");
				}else{
					res.getWriter().print("{success:true,msg:'学生删除失败，请联系管理员！'}");
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
