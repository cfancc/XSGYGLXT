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

public class QueryBianHaoAction extends HttpServlet {
	
	private static final long serialVersionUID = 5L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		    req.setCharacterEncoding("utf-8");
		    res.setContentType("application/json;charset=utf-8");
		    Connection cn = null;
		    String ssyq=req.getParameter("ssyq");
		    String ssld=req.getParameter("ssld");
		    String sslc=req.getParameter("sslc");
			String sql="select ssbh,ssid,zdrs from ssxxb where ssyq='"+ssyq+"' and ssld='"+ssld+"' and sslc='"+sslc+"'";
				try {
					cn = UtilConnection.getCn();
					PreparedStatement ps = cn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					JSONArray ja = new JSONArray();
					while(rs.next()){
						JSONObject json = new JSONObject();
						json.put("ssbh", rs.getString(1));
						json.put("ssid", rs.getInt(2));
						json.put("zdrs", rs.getInt(3));
						ja.add(json);
					}
					
					rs.close();
					ps.close();
					JSONObject json = new JSONObject();
					json.put("ssbhlist", ja);
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
