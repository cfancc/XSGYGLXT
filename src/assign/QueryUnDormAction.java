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

public class QueryUnDormAction extends HttpServlet {
	
	private static final long serialVersionUID = 5L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		    req.setCharacterEncoding("utf-8");
		    res.setContentType("application/json;charset=utf-8");
		    Connection cn = null;
		    String ssid=req.getParameter("ssid");
		    String sql="select x.xsid,x.xm,x.xh,x.zy,x.sfyyss " +
		    		"from xsxxb x,ssxxb s,xsssb xs " +
		    		"where x.xsid=xs.xsid and xs.ssid=s.ssid and s.ssid='"+ssid+"'";
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
						json.put("zy", rs.getString(4));
						json.put("sfyyss", rs.getString(5));
						ja.add(json);
					}
					
					rs.close();
					ps.close();
					JSONObject json = new JSONObject();
					json.put("undormlist", ja);
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
