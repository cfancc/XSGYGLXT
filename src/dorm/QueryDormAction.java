package dorm;

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

public class QueryDormAction extends HttpServlet {
	
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
		    query=req.getParameter("query");
			String sql="select SSID,SSBH,SSYQ,SSLD,SSLC,SSBZ,zdrs,sfym  from ssxxb ";
			if(query!=null&&!("").equals(query)){
				sql = sql+"where SSBH like '%"+query+"%' or " +
				                "SSYQ like '%"+query+"%' or " +
				                "SSLD like '%"+query+"%' or " +
				                "zdrs like '%"+query+"%' or " +
				                "sfym like '%"+query+"%' or " +
				                "SSBZ like '%"+query+"%' ";
				}
				try {
					cn = UtilConnection.getCn();
					PreparedStatement ps = cn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					JSONArray ja = new JSONArray();
					while(rs.next()){
						JSONObject json = new JSONObject();
						json.put("ssid", rs.getInt(1));
						json.put("ssbh", rs.getString(2));
						json.put("ssyq", rs.getString(3));
						json.put("ssld", rs.getString(4));
						json.put("sslc", rs.getString(5));
						json.put("ssbz", rs.getString(6));
						json.put("zdrs", rs.getString(7));
						json.put("sfym", rs.getString(8));
						ja.add(json);
					}
					
					rs.close();
					ps.close();
					JSONObject json = new JSONObject();
					json.put("dormlist", ja);
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
