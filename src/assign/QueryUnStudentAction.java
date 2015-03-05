package assign;

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

public class QueryUnStudentAction extends HttpServlet {

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
//		String sfyyss = req.getParameter("sfyyss");
		String sfyyss = "否";
		String sql = "select xsid,xm,xh,zy,bj,lxfs,sfyyss from xsxxb where ";
		if (query != null && !("").equals(query)) {
			sql = sql + " (xm like '%" + query + "%' or " + "xh like '%"
					+ query + "%' or " + "zy like '%" + query + "%' or "
					+ "sfyyss like '%" + query + "%') and ";
		}
		if(sfyyss!=null&&!("").equals(sfyyss)&&!("所有").equals(sfyyss)){
			sql = sql + " sfyyss='"+sfyyss+"'";
		}
		if(sql.endsWith("where "))
		{
		  sql=sql.substring(0,sql.length()-7);
		}
		if(sql.endsWith("and "))
		{
		  sql=sql.substring(0,sql.length()-5);
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
				ja.add(json);
			}

			rs.close();
			ps.close();
			JSONObject json = new JSONObject();
			json.put("unstudentlist", ja);
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
