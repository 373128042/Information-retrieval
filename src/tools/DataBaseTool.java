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
		url+=Constant.MYSQL_ADDRESS+":"+Constant.MYSQL_PORT+"/"+Constant.MYSQL_SCHEME+"?";
		url+="user="+Constant.MYSQL_USERNAME+"&password="+Constant.MYSQL_PASSWORD;
		url+="&useUnicode=true&characterEncoding=UTF8";
		String url = "jdbc:oracle:" + "thin:@10.0.2.15:1521:ORCL";
		try {
			System.out.println("开始尝试连接数据库！");
			Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
			System.out.println("成功加载Oracle驱动程序");
			String user = "webspider";// 用户名,系统默认的账户名
	        String password = "webspider";// 你安装时选设置的密码
	        conn = DriverManager.getConnection(url, user, password);// 获取连接
	        System.out.println("连接成功！");
			conn = DriverManager.getConnection(url);
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
		String sql="select t.* from user t";
		ResultSet rs=dbInstance.executeQuery(sql);
		while (rs.next()) {
			System.out.println(rs.getObject(1).toString());
			//System.out.println(rs.getString("id") + "\t" + rs.getString("name")+ "\t" + rs.getString("face_pic"));// 入如果返回的是int类型可以用getInt()
		}
	}
	
//	public static void main(String[] args) {
//
//		Connection conn = null;
//		String sql;
//		String url = "jdbc:mysql://125.216.237.219:3306/humanface?"+ "user=root&password=lml&useUnicode=true&characterEncoding=UTF8";
//		try {
//			Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
//			System.out.println("成功加载MySQL驱动程序");
//			conn = DriverManager.getConnection(url);
//			Statement stmt = conn.createStatement();
//			sql = "create table student(NO char(20),name varchar(20),primary key(NO))";
//			//int result = stmt.executeUpdate(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
//			int result=0;
//			if (result != -1) {
//
//				System.out.println("创建数据表成功");
//				sql = "insert into student(NO,name) values('2012001','111')";
//				result = stmt.executeUpdate(sql);
//				sql = "insert into student(NO,name) values('2012002','周小俊')";
//				result = stmt.executeUpdate(sql);
//				sql = "select * from student";
//				ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
//				System.out.println("学号\t姓名");
//				while (rs.next()) {
//					System.out.println(rs.getString(1) + "\t" + rs.getString(2));// 入如果返回的是int类型可以用getInt()
//				}
//			}
//			conn.close();
//		} catch (SQLException e) {
//			System.out.println("MySQL操作错误");
//			e.printStackTrace();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}