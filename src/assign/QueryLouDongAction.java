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

public class QueryLouDongAction extends HttpServlet {
	
	private static final long serialVersionUID = 5L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		    req.setCharacterEncoding("utf-8");
		    res.setContentType("application/json;charset=utf-8");
		    Connection cn = null;
		    String ssyq=req.getParameter("ssyq");
			String sql="select distinct(SSLD) from ssxxb where ssyq='"+ssyq+"'";
				try {
					cn = UtilConnection.getCn();
					PreparedStatement ps = cn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					JSONArray ja = new JSONArray();
					while(rs.next()){
						JSONObject json = new JSONObject();
						json.put("ssld", rs.getString(1));
						ja.add(json);
					}
					
					rs.close();
					ps.close();
					JSONObject json = new JSONObject();
					json.put("ssldlist", ja);
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
