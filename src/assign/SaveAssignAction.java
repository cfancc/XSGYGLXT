package assign;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
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

import net.sf.json.JSONObject;

import user.UtilConnection;

public class SaveAssignAction extends HttpServlet {
	private static final long serialVersionUID = 4L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		    req.setCharacterEncoding("utf-8");
		    res.setContentType("application/json;charset=utf-8");
		    
		    StringBuffer info = new StringBuffer();
			InputStream in = req.getInputStream();
			BufferedInputStream buf = new BufferedInputStream(in);
			byte[] buffer = new byte[1024];
			int iRead;
			while ((iRead = buf.read(buffer)) != -1) {
				info.append(new String(buffer, 0, iRead, "utf-8"));
			}
			JSONObject json = JSONObject.fromObject(info.toString());
			String addstr=null;
			if(json.containsKey("addarray")){
				addstr=json.getString("addarray");
			}
			String delstr=null;
			if(json.containsKey("delarray")){
				delstr=json.getString("delarray");
			}
		    String[] add=null;
		    if(addstr!=null&&addstr.length()>0&&!("[]").equals(addstr))
		    	add = addstr.substring(1,addstr.length()-1).split(",");
		    String[] del=null;
		    if(delstr!=null&&delstr.length()>0&&!("[]").equals(delstr))
		    	del = delstr.substring(1,delstr.length()-1).split(",");
		    int ssid=Integer.parseInt(json.getString("ssid"));
			String sql = null;
			Connection cn = null;
			PreparedStatement ps=null;
			try {
				cn = UtilConnection.getCn();
				
				for(int i=0;del!=null&&i<del.length;i++){
					int xsid=Integer.parseInt(del[i].trim());
					sql="delete from xsssb where xsid=?";
					ps = cn.prepareStatement(sql);
					ps.setInt(1, xsid);
					ps.executeUpdate();
					
					sql="update xsxxb set sfyyss='否' where xsid=?";
					ps = cn.prepareStatement(sql);
					ps.setInt(1, xsid);
					ps.executeUpdate();
				}
				for(int i=0;add!=null&&i<add.length;i++){
					int xsid=Integer.parseInt(add[i].trim());
					sql="insert into xsssb(xsid,ssid)values(?,?)";
					ps = cn.prepareStatement(sql);
					ps.setInt(1, xsid);
					ps.setInt(2, ssid);
					ps.executeUpdate();
					
					sql="update xsxxb set sfyyss='是' where xsid=?";
					ps = cn.prepareStatement(sql);
					ps.setInt(1, xsid);
					ps.executeUpdate();
				}
				sql="select count(xs.ssid),s.zdrs from xsssb xs,ssxxb s where xs.ssid=s.ssid and s.ssid=? group by s.ssid,s.zdrs ";
				ps = cn.prepareStatement(sql);
				ps.setInt(1, ssid);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					if(rs.getInt(1)==rs.getInt(2)){
						sql="update ssxxb set sfym='是' where ssid =?";
						ps = cn.prepareStatement(sql);
						ps.setInt(1, ssid);
						ps.executeUpdate();
					}else{
						sql="update ssxxb set sfym='否' where ssid =?";
						ps = cn.prepareStatement(sql);
						ps.setInt(1, ssid);
						ps.executeUpdate();
					}
				}else{
					sql="update ssxxb set sfym='否' where ssid =?";
					ps = cn.prepareStatement(sql);
					ps.setInt(1, ssid);
					ps.executeUpdate();
				}
				rs.close();
				ps.close();
			} catch (Exception e) {
				System.out.println("error:" + e.getMessage());
			} finally {
				try {
					cn.close();
				} catch (Exception e) {

				}
			}
			res.getWriter().print("{success:true,msg:'宿舍分配成功！'}");
			
			
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}

	public void destroy() {
		super.destroy();
	}
}
