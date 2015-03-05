package student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import user.UtilConnection;

public class QueryStudentAction extends HttpServlet {

	private static final long serialVersionUID = 5L;
	private String query;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("application/json;charset=utf-8");
		Connection cn = null;
		query = req.getParameter("query");
		String sql = "select xsid,xm,xh,zy,bj,lxfs,sfyyss from xsxxb where sfyyss!='是' ";
		if (query != null && !("").equals(query)) {
			sql = sql + "and(xm like '%" + query + "%' or " + "xh like '%"
					+ query + "%' or " + "bj like '%" + query + "%' or "
					+ "zy like '%" + query + "%' or " + "sfyyss like '%"
					+ query + "%' or " + "lxfs like '%" + query + "%')";
		}
		try {
			cn = UtilConnection.getCn();
			PreparedStatement ps = cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			JSONArray ja = new JSONArray();
			while (rs.next()) {
				JSONObject json = new JSONObject();
				json.put("xsid", rs.getInt(1));
				json.put("xm", rs.getString(2));
				json.put("xh", rs.getString(3));
				json.put("zy", rs.getString(4));
				json.put("bj", rs.getString(5));
				json.put("lxfs", rs.getString(6));
				json.put("sfyyss", rs.getString(7));
				json.put("ssss", "");
				ja.add(json);
			}
			sql = "select x.xsid,x.xm,x.xh,x.zy,x.bj,x.lxfs,x.sfyyss,s.ssyq,s.ssld,ssbh " +
					"from xsxxb x,xsssb xs,ssxxb s where x.xsid=xs.xsid and s.ssid=xs.ssid ";
			if (query != null && !("").equals(query)) {
				sql = sql + "and (x.xm like '%" + query + "%' or "
						+ "x.xh like '%" + query + "%' or " + "x.bj like '%"
						+ query + "%' or " + "x.zy like '%" + query + "%' or "
						+ "x.sfyyss like '%" + query + "%' or "
						+ "x.lxfs like '%" + query + "%')";
			}
			ps = cn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				JSONObject json = new JSONObject();
				json.put("xsid", rs.getInt(1));
				json.put("xm", rs.getString(2));
				json.put("xh", rs.getString(3));
				json.put("zy", rs.getString(4));
				json.put("bj", rs.getString(5));
				json.put("lxfs", rs.getString(6));
				json.put("sfyyss", rs.getString(7));
				json.put("ssss", rs.getString(8)+","+rs.getString(9)+"栋,"+rs.getString(10)+"号");
				ja.add(json);
			}
			rs.close();
			ps.close();
			JSONObject json = new JSONObject();
			json.put("studentlist", ja);
			json.put("totalSize", ja.size());
			res.getWriter().print(json);

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
