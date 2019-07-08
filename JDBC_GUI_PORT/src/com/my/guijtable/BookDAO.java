package com.my.guijtable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BookDAO {
    
	String driver="org.mariadb.jdbc.Driver";
	Connection con=null;
	PreparedStatement pstmt=null;
	Statement stmt=null;
	ResultSet rs=null;
	String url="jdbc:mariadb://127.0.0.1:3306/test";
	String ids="javabook";
	String pws="power";
	
	public BookDAO() {
		try {
			Class.forName(driver);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public int userListInsert(UserJDailogGUI user) {
		int result=0;
		
		String sql="INSERT INTO BOOK VALUES('"+Integer.parseInt(user.booknum.getText())+"','"+
				user.bookname.getText()+"','"+user.title.getText()+"','"+user.author.getText()
				+"','"+Integer.parseInt(user.cost.getText())+"','"+Integer.parseInt(user.inventory.getText())
				+"','"+Integer.parseInt(user.cost.getText())*Integer.parseInt(user.inventory.getText())+"')";
		
		try {
			con=DriverManager.getConnection(url,ids,pws);
			pstmt = con.prepareStatement(sql);
			result=pstmt.executeUpdate(sql);
			System.out.println(sql);
			con.commit();
			System.out.println("DB connection success");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e + "=> userListInsert fail");
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null)	stmt.close();
				if (pstmt != null)	pstmt.close();
			} catch (Exception e) {
				System.out.println(e + "=> dbClose fail");
			}
		}
		return result;
	}
	
	public boolean getbooknumCheck(String id) {
		String sql="SELECT BOOKNUM FROM BOOK WHERE BOOKNUM=?";
		boolean result = true;
		try {
			con=DriverManager.getConnection(url,ids,pws);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id.trim());
			rs = pstmt.executeQuery(); //실행
			if (rs.next())
				result = false; //레코드가 존재하면 false

		} catch (SQLException e) {
			System.out.println(e + "=>  getIdByCheck fail");
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null)	stmt.close();
				if (pstmt != null)	pstmt.close();
			} catch (Exception e) {
				System.out.println(e + "=> dbClose fail");
			}
		}

		return result;

	}//getIdByCheck()
	
	public void userSelectAll(DefaultTableModel t_model) {
		try {
			con=DriverManager.getConnection(url,ids,pws);
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from book order by booknum");
			
			// DefaultTableModel에 있는 기존 데이터 지우기
			for (int i = 0; i < t_model.getRowCount();) {
				t_model.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7) };

				t_model.addRow(data); //DefaultTableModel에 레코드 추가
				
			}
			con.commit();
		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		}finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null)	stmt.close();
				if (pstmt != null)	pstmt.close();
			} catch (Exception e) {
				System.out.println(e + "=> dbClose fail");
			}
		}
	}
	
	public void getUserSearch(DefaultTableModel dt, String fieldName,String word) {

	      String sql = "SELECT * FROM BOOK WHERE " + fieldName.trim()
	               + " LIKE '%" + word.trim() + "%'";

	       try {
	    	   con=DriverManager.getConnection(url,ids,pws);
	    	   pstmt = con.prepareStatement(sql);
	    	   stmt = con.createStatement();
	    	   rs = pstmt.executeQuery();

	          // DefaultTableModel에 있는 기존 데이터 지우기
	          for (int i = 0; i < dt.getRowCount();) {

	           dt.removeRow(0);
	           }
	           while (rs.next()) {

	        		Object data[] = { rs.getInt(1), rs.getString(2),
							rs.getString(3), rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7) };

	              dt.addRow(data);
	          }

	       } catch (SQLException e) {
	           System.out.println(e + "=> getUserSearch fail");
	       } finally {
				try {
					if (rs != null) rs.close();
					if (stmt != null)	stmt.close();
					if (pstmt != null)	pstmt.close();
				} catch (Exception e) {
					System.out.println(e + "=> dbClose fail");
				}
	   }//getUserSearch()
	}// 클래스끝
	

	public int userDelete(String id) {
		int result=0;
		try {
			String sql="DELETE FROM BOOK WHERE BOOKNUM=?;";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(id.trim()));
			result=pstmt.executeUpdate();
			con.commit();
			System.out.println("Delete success");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e + "=> userDelete fail");
		}finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null)	stmt.close();
				if (pstmt != null)	pstmt.close();
			} catch (Exception e) {
				System.out.println(e + "=> dbClose fail");
			}
		}
		return result;
	}
	public int userUpdate(UserJDailogGUI user) {
		int result=0;
		try {
			String sql="update book set booknum="+Integer.parseInt(user.booknum.getText())+", "
					+ "bookname='"+user.bookname.getText()+"',title='"+user.title.getText()+"',author='"+user.author.getText()+"',"
					+ "cost="+Integer.parseInt(user.cost.getText())+",inventory="+Integer.parseInt(user.inventory.getText())+
					",costtotal="+Integer.parseInt(user.cost.getText())*Integer.parseInt(user.inventory.getText())+" where booknum="+Integer.parseInt(user.booknum.getText())+";";
			con=DriverManager.getConnection(url,ids,pws);
			pstmt = con.prepareStatement(sql);
			result=pstmt.executeUpdate(sql);
			System.out.println(sql);
			con.commit();
			System.out.println("DB update success");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e + "=> dbClose fail");
		}finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null)	stmt.close();
				if (pstmt != null)	pstmt.close();
			} catch (Exception e) {
				System.out.println(e + "=> dbClose fail");
			}
		}
		return result;
	}

	
	
}
