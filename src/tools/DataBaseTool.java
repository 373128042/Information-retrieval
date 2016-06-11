package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import constant.Constant;

public class DataBaseTool {
	
	public Connection conn=null;
	String url="jdbc:mysql://";
	
	/**
	 * 初始化数据库链接
	 */
	public DataBaseTool(){
		String url = "jdbc:oracle:" + "thin:@"+Constant.ORACLE_ADDRESS+":"+Constant.ORACLE_PORT+":"+Constant.ORACLE_SCHEME;
		try {
			System.out.println("开始尝试连接数据库！");
			Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
			System.out.println("成功加载Oracle驱动程序");
			String user = Constant.ORACLE_USERNAME;// 用户名,系统默认的账户名
	        String password = Constant.ORACLE_PASSWORD;// 你安装时选设置的密码
	        conn = DriverManager.getConnection(url, user, password);// 获取连接
	        System.out.println("连接成功！");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询集合
	 * @param sql
	 * @return
	 */
	public ResultSet executeQuery(String sql){
		ResultSet rs=null;
		try{
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 查询集合
	 * @param sql
	 * @return
	 */
	public int executeUpdate(String sql){
		int result=0;
		try{
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public void destroy(){
		try{
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		DataBaseTool dbInstance=new DataBaseTool();
		String sql="select t.* from Spy_Info t";
		//String sql="INSERT INTO Spy_Info VALUES (spy_sequence.nextval,'http://www.baidu.com','HHAA阿','sdf','xzc')";

		ResultSet rs=dbInstance.executeQuery(sql);
		while (rs.next()) {
			System.out.println(rs.getObject(4).toString());
			//System.out.println(rs.getString("id") + "\t" + rs.getString("name")+ "\t" + rs.getString("face_pic"));// 入如果返回的是int类型可以用getInt()
		}
	}
}