package com.my.guijtable;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserJDailogGUI extends JFrame implements ActionListener {
	
	private JPanel p1,p2,p3,p4,p5,p6,p7,p8;

	JLabel lable_Id;
	JLabel lable_Name;
	JLabel lable_Age;
	JLabel lable_Addr;
	JLabel lable_cost;
	JLabel lable_inven;
	JLabel lable_total;

	JTextField booknum,bookname,title,cost,inventory,author,costtotal;
	JButton confirm,reset,update;
	static BookAdmin_GUI  me;
	static String index;

   JPanel idCkP =new JPanel(new BorderLayout());
   JButton idCkBtn;
   BookDAO dao =new BookDAO();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 String btnLabel =e.getActionCommand();//이벤트주체 대한 Label 가져오기
		    
		   if(btnLabel.equals("등록")){
			   if(booknum.getText().equals("")) {
				   JOptionPane.showMessageDialog(null, "책번호 입력");
				   return;
			   }
			   try {
				   if(!(Integer.parseInt(booknum.getText().trim())>=0)&&(Integer.parseInt(booknum.getText().trim())<=9)){
						
						return;
					}
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "책번호를 올바르게 입력하세요");
				return;
			}
			   if(bookname.getText().equals("")) {
				   JOptionPane.showMessageDialog(null, "책이름 입력");
				   return;
			   }
			   try {
				   if (!bookname.getText().matches("[|가-힝|a-z|A-Z]*")) {
						JOptionPane.showMessageDialog(null, "잘못입력");
						return;
					}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			   if(title.getText().equals("")) {
				   JOptionPane.showMessageDialog(null, "출판사 입력");
				   return;
			   }
			   try {
				   if (!title.getText().matches("[|가-힝|a-z|A-Z]*")) {
						JOptionPane.showMessageDialog(null, "잘못입력");
						return;
					}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			   if(author.getText().equals("")) {
				   JOptionPane.showMessageDialog(null, "저자 입력");
				   return;
			   }
			   try {
				   if (!title.getText().matches("[|가-힝|a-z|A-Z]*")) {
						JOptionPane.showMessageDialog(null, "잘못입력");
						return;
					}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			   if(cost.getText().equals("")) {
				   JOptionPane.showMessageDialog(null, "가격입력");
					return;
			   }
			   try {
				   if(!(Integer.parseInt(cost.getText().trim())>=0)&&(Integer.parseInt(cost.getText().trim())<=9)){
						
						return;
						
					}
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "가격을 올바르게 입력하세요");
				return;
				
			}
			   if(inventory.getText().equals("")) {
				   JOptionPane.showMessageDialog(null, "재고량 입력");
				   return;
			   }
			   try {
				   if(!(Integer.parseInt(inventory.getText().trim())>=0)&&(Integer.parseInt(inventory.getText().trim())<=9)){
						
						return;
						
					}
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "재고량을 올바르게 입력하세요");
				return;
			}
			
		   }
		  
			   if(btnLabel.equals("등록")) {
				
			   if(dao.userListInsert(this) > 0 ){//가입성공
				   JOptionPane.showMessageDialog(null, bookname.getText()+"등록 성공");
				   dispose();//JDialog 창닫기
				   //BookDAO dao1=new BookDAO();
				  try {
					dao.con.commit();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("register fail");
				}
				   dao.userSelectAll(me.dt);//모든레코드가져와서 DefaultTableModel에 올리기
				  
				   if(me.dt.getRowCount() > 0) 
					   me.jt.setRowSelectionInterval(0, 0);//첫번째 행 선택
			   } 
				   
			   else{//가입실패
				   JOptionPane.showMessageDialog(null, "등록실패");
				   return;
			   }
			   
			   }
		   
		    if(btnLabel.equals("수정")){
		    	try {
		 			    if( dao.userUpdate(this) > 0){
		 			    	JOptionPane.showMessageDialog(null, "수정완료");
		 			    	dispose();
		 			    	dao.userSelectAll(me.dt);
		 			    	if(me.dt.getRowCount() > 0 ) me.jt.setRowSelectionInterval(0, 0);
		 			    }
				}catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "수정되지 않았습니다");
					return;
				}
		    }
		    else if(e.getSource()==reset){
			   dispose();
			   
		   }else if(e.getSource()==idCkBtn){
			   //id텍스트박스에 값 없으면 메세지 출력 있으면 DB연동한다.
			   try {
				   if(!(Integer.parseInt(booknum.getText().trim())>=0)&&(Integer.parseInt(booknum.getText().trim())<=9)){
						
						return;
					}
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "책번호를 올바르게 입력하세요");
				return;
			}
			   if(booknum.getText().trim().equals("")){
				   JOptionPane.showMessageDialog(null, "북번호를 입력해주세요.");
				   booknum.requestFocus();//포커스이동
			   }else{
				   
				  if(!booknum.getText().equals("")){ //중복아니다.(사용가능)
					  boolean list=dao.getbooknumCheck(booknum.getText());
					  if(list==false) {
						  JOptionPane.showMessageDialog(null, booknum.getText()+"는 중복입니다.");		  
						  booknum.setText("");//text박스지우기
						  booknum.requestFocus();//커서놓기
						  return;
					  }else if(list==true) {
					  JOptionPane.showMessageDialog(null, booknum.getText()+"는 사용가능합니다.");
					  return;
					  }
				  }			   
			   }			   
		   }		
	}
	
	public UserJDailogGUI(BookAdmin_GUI me, String index) {
		//content();
		this.setLayout(new GridLayout(8,1));
		  p1=new JPanel();
		  p1.add(lable_Id=new JLabel("책번호:"));
		  p1.add(booknum=new JTextField(15));
		  p1.add(idCkBtn=new JButton("중복체크"));
		  p1.add(idCkBtn);
		  this.add(p1);
		  p2=new JPanel();
		  p2.add(lable_Id=new JLabel("책이름:"));
		  p2.add(bookname=new JTextField(15));	
		  this.add(p2);
		  p3=new JPanel();
		  p3.add(lable_Id=new JLabel("출판사:"));
		  p3.add(title=new JTextField(15));	
		  this.add(p3);
		  p4=new JPanel();
		  p4.add(lable_Id=new JLabel("저자:"));
		  p4.add(author=new JTextField(15));
		  this.add(p4);
		  p5=new JPanel();
		  p5.add(lable_Id=new JLabel("가격:"));
		  p5.add(cost=new JTextField(15));
		  this.add(p5);
		  p6=new JPanel();
		  p6.add(lable_Id=new JLabel("재고수:"));
		  p6.add(inventory=new JTextField(15));
		  this.add(p6);
		  p7=new JPanel();
		  p7.add(lable_Id=new JLabel("총가격"));
		  p7.add(costtotal=new JTextField(15));
		  costtotal.setEditable(false);
		  this.add(p7);
		  p8=new JPanel();
		 p8.add(confirm=new JButton(index));
		  p8.add(reset=new JButton("취소"));
		  this.add(p8); 
			 	
		  confirm.addActionListener(this); //가입/수정 이벤트등록
		  reset.addActionListener(this); //취소 이벤트등록
		   idCkBtn.addActionListener(this);// ID중복체크 이벤트 등록
		this.me=me;
		if(index.equals("등록")){
			confirm=new JButton(index);
			
		}
		if(index.equals("수정")){
			try {
				confirm=new JButton("수정");	
				
				//text박스에 선택된 레코드의 정보 넣기
				int row = me.jt.getSelectedRow();//선택된 행
				booknum.setText( me.jt.getValueAt(row, 0).toString() );
				bookname.setText( me.jt.getValueAt(row, 1).toString() );
				title.setText( me.jt.getValueAt(row, 2).toString() );
				author.setText( me.jt.getValueAt(row, 3).toString() );
				cost.setText( me.jt.getValueAt(row, 4).toString() );
				inventory.setText( me.jt.getValueAt(row,5).toString() );
				costtotal.setText( me.jt.getValueAt(row, 6).toString() );
				
				//id text박스 비활성
				booknum.setEditable(false);
		
				//IDCheck버튼 비활성화
				idCkBtn.setEnabled(false);
				
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null,"데이터가 없습니다");
				return;
			}
	
		}
					
			this.setVisible(true);
			this.setLocationRelativeTo(null);
		    this.setBounds(0,0,500,600);
		    this.setTitle("도서관리");
		    this.setResizable(false);
		    this.setLocationRelativeTo(null);
		    this.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							// TODO Auto-generated method stub
							System.exit(0);
						}
				    	
					});
		
	}
	public static void main(String[] args) {
		
	}
	
}
