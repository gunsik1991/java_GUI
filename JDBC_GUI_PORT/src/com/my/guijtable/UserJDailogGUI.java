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
		 String btnLabel =e.getActionCommand();//�̺�Ʈ��ü ���� Label ��������
		    
		   if(btnLabel.equals("���")){
			   if(booknum.getText().equals("")) {
				   JOptionPane.showMessageDialog(null, "å��ȣ �Է�");
				   return;
			   }
			   try {
				   if(!(Integer.parseInt(booknum.getText().trim())>=0)&&(Integer.parseInt(booknum.getText().trim())<=9)){
						
						return;
					}
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "å��ȣ�� �ùٸ��� �Է��ϼ���");
				return;
			}
			   if(bookname.getText().equals("")) {
				   JOptionPane.showMessageDialog(null, "å�̸� �Է�");
				   return;
			   }
			   try {
				   if (!bookname.getText().matches("[|��-��|a-z|A-Z]*")) {
						JOptionPane.showMessageDialog(null, "�߸��Է�");
						return;
					}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			   if(title.getText().equals("")) {
				   JOptionPane.showMessageDialog(null, "���ǻ� �Է�");
				   return;
			   }
			   try {
				   if (!title.getText().matches("[|��-��|a-z|A-Z]*")) {
						JOptionPane.showMessageDialog(null, "�߸��Է�");
						return;
					}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			   if(author.getText().equals("")) {
				   JOptionPane.showMessageDialog(null, "���� �Է�");
				   return;
			   }
			   try {
				   if (!title.getText().matches("[|��-��|a-z|A-Z]*")) {
						JOptionPane.showMessageDialog(null, "�߸��Է�");
						return;
					}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			   if(cost.getText().equals("")) {
				   JOptionPane.showMessageDialog(null, "�����Է�");
					return;
			   }
			   try {
				   if(!(Integer.parseInt(cost.getText().trim())>=0)&&(Integer.parseInt(cost.getText().trim())<=9)){
						
						return;
						
					}
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "������ �ùٸ��� �Է��ϼ���");
				return;
				
			}
			   if(inventory.getText().equals("")) {
				   JOptionPane.showMessageDialog(null, "��� �Է�");
				   return;
			   }
			   try {
				   if(!(Integer.parseInt(inventory.getText().trim())>=0)&&(Integer.parseInt(inventory.getText().trim())<=9)){
						
						return;
						
					}
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "����� �ùٸ��� �Է��ϼ���");
				return;
			}
			
		   }
		  
			   if(btnLabel.equals("���")) {
				
			   if(dao.userListInsert(this) > 0 ){//���Լ���
				   JOptionPane.showMessageDialog(null, bookname.getText()+"��� ����");
				   dispose();//JDialog â�ݱ�
				   //BookDAO dao1=new BookDAO();
				  try {
					dao.con.commit();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("register fail");
				}
				   dao.userSelectAll(me.dt);//��緹�ڵ尡���ͼ� DefaultTableModel�� �ø���
				  
				   if(me.dt.getRowCount() > 0) 
					   me.jt.setRowSelectionInterval(0, 0);//ù��° �� ����
			   } 
				   
			   else{//���Խ���
				   JOptionPane.showMessageDialog(null, "��Ͻ���");
				   return;
			   }
			   
			   }
		   
		    if(btnLabel.equals("����")){
		    	try {
		 			    if( dao.userUpdate(this) > 0){
		 			    	JOptionPane.showMessageDialog(null, "�����Ϸ�");
		 			    	dispose();
		 			    	dao.userSelectAll(me.dt);
		 			    	if(me.dt.getRowCount() > 0 ) me.jt.setRowSelectionInterval(0, 0);
		 			    }
				}catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "�������� �ʾҽ��ϴ�");
					return;
				}
		    }
		    else if(e.getSource()==reset){
			   dispose();
			   
		   }else if(e.getSource()==idCkBtn){
			   //id�ؽ�Ʈ�ڽ��� �� ������ �޼��� ��� ������ DB�����Ѵ�.
			   try {
				   if(!(Integer.parseInt(booknum.getText().trim())>=0)&&(Integer.parseInt(booknum.getText().trim())<=9)){
						
						return;
					}
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "å��ȣ�� �ùٸ��� �Է��ϼ���");
				return;
			}
			   if(booknum.getText().trim().equals("")){
				   JOptionPane.showMessageDialog(null, "�Ϲ�ȣ�� �Է����ּ���.");
				   booknum.requestFocus();//��Ŀ���̵�
			   }else{
				   
				  if(!booknum.getText().equals("")){ //�ߺ��ƴϴ�.(��밡��)
					  boolean list=dao.getbooknumCheck(booknum.getText());
					  if(list==false) {
						  JOptionPane.showMessageDialog(null, booknum.getText()+"�� �ߺ��Դϴ�.");		  
						  booknum.setText("");//text�ڽ������
						  booknum.requestFocus();//Ŀ������
						  return;
					  }else if(list==true) {
					  JOptionPane.showMessageDialog(null, booknum.getText()+"�� ��밡���մϴ�.");
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
		  p1.add(lable_Id=new JLabel("å��ȣ:"));
		  p1.add(booknum=new JTextField(15));
		  p1.add(idCkBtn=new JButton("�ߺ�üũ"));
		  p1.add(idCkBtn);
		  this.add(p1);
		  p2=new JPanel();
		  p2.add(lable_Id=new JLabel("å�̸�:"));
		  p2.add(bookname=new JTextField(15));	
		  this.add(p2);
		  p3=new JPanel();
		  p3.add(lable_Id=new JLabel("���ǻ�:"));
		  p3.add(title=new JTextField(15));	
		  this.add(p3);
		  p4=new JPanel();
		  p4.add(lable_Id=new JLabel("����:"));
		  p4.add(author=new JTextField(15));
		  this.add(p4);
		  p5=new JPanel();
		  p5.add(lable_Id=new JLabel("����:"));
		  p5.add(cost=new JTextField(15));
		  this.add(p5);
		  p6=new JPanel();
		  p6.add(lable_Id=new JLabel("����:"));
		  p6.add(inventory=new JTextField(15));
		  this.add(p6);
		  p7=new JPanel();
		  p7.add(lable_Id=new JLabel("�Ѱ���"));
		  p7.add(costtotal=new JTextField(15));
		  costtotal.setEditable(false);
		  this.add(p7);
		  p8=new JPanel();
		 p8.add(confirm=new JButton(index));
		  p8.add(reset=new JButton("���"));
		  this.add(p8); 
			 	
		  confirm.addActionListener(this); //����/���� �̺�Ʈ���
		  reset.addActionListener(this); //��� �̺�Ʈ���
		   idCkBtn.addActionListener(this);// ID�ߺ�üũ �̺�Ʈ ���
		this.me=me;
		if(index.equals("���")){
			confirm=new JButton(index);
			
		}
		if(index.equals("����")){
			try {
				confirm=new JButton("����");	
				
				//text�ڽ��� ���õ� ���ڵ��� ���� �ֱ�
				int row = me.jt.getSelectedRow();//���õ� ��
				booknum.setText( me.jt.getValueAt(row, 0).toString() );
				bookname.setText( me.jt.getValueAt(row, 1).toString() );
				title.setText( me.jt.getValueAt(row, 2).toString() );
				author.setText( me.jt.getValueAt(row, 3).toString() );
				cost.setText( me.jt.getValueAt(row, 4).toString() );
				inventory.setText( me.jt.getValueAt(row,5).toString() );
				costtotal.setText( me.jt.getValueAt(row, 6).toString() );
				
				//id text�ڽ� ��Ȱ��
				booknum.setEditable(false);
		
				//IDCheck��ư ��Ȱ��ȭ
				idCkBtn.setEnabled(false);
				
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null,"�����Ͱ� �����ϴ�");
				return;
			}
	
		}
					
			this.setVisible(true);
			this.setLocationRelativeTo(null);
		    this.setBounds(0,0,500,600);
		    this.setTitle("��������");
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
