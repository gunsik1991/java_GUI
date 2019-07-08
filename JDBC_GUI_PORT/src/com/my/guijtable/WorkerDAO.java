package com.my.guijtable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class WorkerDAO {

	String driver="org.mariadb.jdbc.Driver";
	Connection con=null;
	PreparedStatement pstmt=null;
	Statement stmt=null;
	ResultSet rs=null;
	String url="jdbc:mariadb://127.0.0.1:3306/test";
	String ids="javabook";
	String pws="power";
	
	public WorkerDAO() {
		try {
			Class.forName(driver);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public ArrayList<WorkerDTO> insert(int workerno,String name,String address,String phonenum,String id,String pw){
		
		String sql="insert into worker values('"+workerno+"','"+name+"','"+address+"','"+phonenum+"','"+id+"','"+pw+"')";
		ArrayList<WorkerDTO> list=new ArrayList<WorkerDTO>();
				
		try {
			con=DriverManager.getConnection(url,ids,pws);
			stmt=con.createStatement();
			stmt.executeUpdate(sql);
			System.out.println(sql);
			System.out.println("DB connection success");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if (pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println("Exception");
				e2.printStackTrace();
			}
		}
		return list;	
	}
	
	public boolean checkselect(String id){
		String sql="SELECT ID FROM WORKER WHERE ID=?";
		boolean result=true;
		try {
			con=DriverManager.getConnection(url,ids,pws);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id.trim());
			rs=pstmt.executeQuery();
			System.out.println("success");
			if(rs.next()) {
				result=false;			
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("fail");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
		try {
			if(rs!=null) rs.close();
			if (pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println("Exception");
			e2.printStackTrace();
		}
		}
		return result;
	}
	
	public boolean checkidpwselect(String pw){
		String sql="SELECT PW FROM WORKER WHERE pw=?";
		boolean result=true;
		try {
			con=DriverManager.getConnection(url,ids,pws);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, pw.trim());
			rs=pstmt.executeQuery();
			System.out.println("success");
			if(rs.next()) {
				result=false;			
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("fail");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
		try {
			if(rs!=null) rs.close();
			if (pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println("Exception");
			e2.printStackTrace();
		}
		}
		return result;
	}
	
	
	
	
	
}
