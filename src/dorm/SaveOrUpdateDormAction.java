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

public class SaveOrUpdateDormAction extends HttpServlet {
	private static final long serialVersionUID = 4L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("application/json;charset=utf-8");

		String ssid = req.getParameter("ssid");
		String ssbh = req.getParameter("ssbh");
		String ssyq = req.getParameter("ssyq");
		String ssld = req.getParameter("ssld");
		String sslc = req.getParameter("sslc");
		String ssbz = req.getParameter("ssbz");
		int zdrs = Integer.parseInt(req.getParameter("zdrs"));
		String sfym = req.getParameter("sfym");

		boolean check = false;
		String sql = null;
		Connection cn = null;

		if (ssid != null && !("").equals(ssid) && !("null").equals(ssid)) {
			sql = "update ssxxb set ssbh=?,ssyq=?,ssld=?,sslc=?,ssbz=?,zdrs=?,sfym=? where ssid=?";
			try {
				cn = UtilConnection.getCn();
				PreparedStatement ps = cn.prepareStatement(sql);
				ps.setString(1, ssbh);
				ps.setString(2, ssyq);
				ps.setString(3, ssld);
				ps.setString(4, sslc);
				ps.setString(5, ssbz);
				ps.setInt(6, zdrs);
				ps.setString(7, sfym);
				ps.setString(8, ssid);
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
				res.getWriter().print("{success:true,msg:'宿舍信息更新成功！'}");
			}
		} else {

			sql = "insert into ssxxb(ssbh,ssyq,ssld,sslc,ssbz,zdrs,sfym)values(?,?,?,?,?,?,?)";
			try {
				cn = UtilConnection.getCn();
				PreparedStatement ps = cn.prepareStatement(sql);
				ps.setString(1, ssbh);
				ps.setString(2, ssyq);
				ps.setString(3, ssld);
				ps.setString(4, sslc);
				ps.setString(5, ssbz);
				ps.setInt(6, zdrs);
				ps.setString(7, sfym);
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
				res.getWriter().print("{success:true,msg:'宿舍添加成功！'}");
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
