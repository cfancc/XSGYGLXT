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

public class QueryAssignAction extends HttpServlet {
	
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
			String sql="select x.xsid,xm,xh,s.ssid,ssbh,ssyyss from xsxxb x,ssxxb s,xsssb xs where x.xsid=xs.xsid and s.ssid=xs.ssid ";
			if(query!=null&&!("").equals(query)){
				sql = sql+"and( xm like '%"+query+"%' or " +
				                "xh like '%"+query+"%' or " +
				                "ssyq like '%"+query+"%' or " +
				                "ssbh like '%"+query+"%' ";
				}
				try {
					cn = UtilConnection.getCn();
					PreparedStatement ps = cn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					JSONArray ja = new JSONArray();
					while(rs.next()){
						JSONObject json = new JSONObject();
						json.put("xsid", rs.getInt(1));
						json.put("xm", rs.getString(2));
						json.put("xh", rs.getString(3));
						json.put("ssid", rs.getInt(4));
						json.put("ssbh", rs.getString(5));
						json.put("ssyq", rs.getString(6));
						ja.add(json);
					}
					
					rs.close();
					ps.close();
					JSONObject json = new JSONObject();
					json.put("student_dormlist", ja);
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
