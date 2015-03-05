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

public class SaveOrUpdateStudentAction extends HttpServlet {
	private static final long serialVersionUID = 4L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		    req.setCharacterEncoding("utf-8");
		    res.setContentType("application/json;charset=utf-8");
		    
		    String xsid=req.getParameter("xsid");
			String xm=req.getParameter("xm");
			String xh=req.getParameter("xh");
			String zy=req.getParameter("zy");
			String bj=req.getParameter("bj");
			String lxfs=req.getParameter("lxfs");
			String sfyyss=req.getParameter("sfyyss");
			
			
		    boolean check = false;
			String sql = null;
			Connection cn = null;
			
			if(xsid!=null&&!("").equals(xsid)&&!("null").equals(xsid)){

				sql="update xsxxb set xm=?,xh=?,zy=?,bj=?,lxfs=?,sfyyss=? where xsid=?";
				try {
					cn = UtilConnection.getCn();
					PreparedStatement ps = cn.prepareStatement(sql);
					ps.setString(1, xm);
					ps.setString(2, xh);
					ps.setString(3, zy);
					ps.setString(4, bj);
					ps.setString(5, lxfs);
					ps.setString(6, sfyyss);
					ps.setString(7, xsid);
					int i = ps.executeUpdate();
					if (i!=0) {
						check = true;
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
				if (check) {
					res.getWriter().print("{success:true,msg:'学生信息更新成功！'}");
				}
			}else{
				sql="insert into xsxxb(xm,xh,zy,bj,lxfs,sfyyss)values(?,?,?,?,?,?)";
				try {
					cn = UtilConnection.getCn();
					PreparedStatement ps = cn.prepareStatement(sql);
					ps.setString(1, xm);
					ps.setString(2, xh);
					ps.setString(3, zy);
					ps.setString(4, bj);
					ps.setString(5, lxfs);
					ps.setString(6, sfyyss);
					int i = ps.executeUpdate();
					if (i!=0) {
						check = true;
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
				if (check) {
					res.getWriter().print("{success:true,msg:'学生添加成功！'}");
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
