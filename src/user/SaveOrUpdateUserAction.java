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

public class SaveOrUpdateUserAction extends HttpServlet {
	private static final long serialVersionUID = 4L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("application/json;charset=utf-8");

		String yhid = req.getParameter("yhid");
		String dlm = req.getParameter("dlm");
		String mm = req.getParameter("mm");
		String yhlx = req.getParameter("yhlx");
		String lxfs = req.getParameter("lxfs");

		boolean check = false;
		String sql = null;
		Connection cn = null;

		if (yhid != null && !("").equals(yhid) && !("null").equals(yhid)) {
			sql = "update yhxxb set dlm=?,mm=?,yhlx=?,lxfs=? where yhid=?";
			try {
				cn = UtilConnection.getCn();
				PreparedStatement ps = cn.prepareStatement(sql);
				ps.setString(1, dlm);
				ps.setString(2, mm);
				ps.setString(3, yhlx);
				ps.setString(4, lxfs);
				ps.setString(5, yhid);

				int i = ps.executeUpdate();
				if (i != 0) {
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
				res.getWriter().print("{success:true,msg:'更新成功！'}");
			}
		} else {
			try {
				cn = UtilConnection.getCn();
				PreparedStatement ps = cn.prepareStatement("select * from yhxxb "
						+ "where dlm='" + dlm + "' and mm='" + mm + "'");
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					rs.close();
					ps.close();
					res.getWriter().print("{success:true,msg:'用户已存在,请更换登录名！'}");
					return;
				}

				
				sql = "insert into yhxxb(dlm,mm,yhlx,lxfs)values(?,?,?,?)";
				ps = cn.prepareStatement(sql);
				ps.setString(1, dlm);
				ps.setString(2, mm);
				ps.setString(3, yhlx);
				ps.setString(4, lxfs);
				int i = ps.executeUpdate();
				if (i != 0) {
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
				res.getWriter().print("{success:true,msg:'注册成功！'}");
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
