package com.my.guijtable;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.my.guijtable.WorkerDAO;
import com.my.guijtable.WorkerDTO;

public class Register extends JFrame implements ActionListener{

	private JTextField id,name,phonenum1,phonenum2,phonenum3,worknum,address,phonecheck;
	private JPasswordField pw;
	private JLabel workernumchar,namechar,addresss,phonenumchar,idchar,pwchar;
	private JLabel  hy1,hy2;
	private JButton register,cancle,check;
	private JPanel p1,p2,p3,p4,p5,p6,p7;
 
 
  Dialog dialog = new JDialog(this, Dialog.ModalityType.APPLICATION_MODAL);

  public void registerinit() {
	  dialog.setLayout(new GridLayout(7,1));
		p1=new JPanel();
		p1.add(workernumchar=new JLabel("직원번호:"));
		p1.add(worknum=new JTextField(10));
		dialog.add(p1);
		p2=new JPanel();
		p2.add(namechar=new JLabel("이름:"),BorderLayout.WEST);
		p2.add(name=new JTextField(10),BorderLayout.WEST);
		dialog.add(p2);
		p3=new JPanel();
		p3.add(addresss=new JLabel("주소"));
		p3.add(address=new JTextField(20));
		dialog.add(p3);
		p4=new JPanel();
		p4.add(phonenumchar=new JLabel("전화번호"));
		p4.add(phonenum1=new JTextField(5));
		p4.add(hy1=new JLabel("-"));
		p4.add(phonenum2=new JTextField(5));
		p4.add(hy2=new JLabel("-"));
		p4.add(phonenum3=new JTextField(5));
		p4.add(phonecheck=new JTextField(10));
		phonecheck.setEnabled(false);
		dialog.add(p4);
		p5=new JPanel();
		p5.add(idchar=new JLabel("ID"));
		p5.add(id=new JTextField(10));
		p5.add(check=new JButton("CHECK"));
		dialog.add(p5);
		p6=new JPanel();
		p6.add(pwchar=new JLabel("PW"),BorderLayout.WEST);
		p6.add(pw=new JPasswordField(10),BorderLayout.WEST);
		dialog.add(p6);
		p7=new JPanel();
		p7.add(register=new JButton("등록"));
		p7.add(cancle=new JButton("취소"));
		dialog.add(p7);
		
		register.addActionListener(this);
		cancle.addActionListener(this);
	    check.addActionListener(this);
 
   }
  
  @SuppressWarnings("deprecation")
@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
		if(e.getSource()==register) {
			if(worknum.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "직원번호를 입력하세요");
				return;
			}
			try {
				if(!(Integer.parseInt(worknum.getText().trim())>=0)&&(Integer.parseInt(worknum.getText().trim())<=9)){
					
					return;
				}
			} catch (NumberFormatException e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "직원번호를 올바르게 입력하세요");
				return;
			}
			
			if(name.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "이름 입력하세요");
				
				return;
			}
			try {
				if (!name.getText().matches("[|가-힝]*")) {
					JOptionPane.showMessageDialog(null, "잘못입력");
					return;
				}
				if(name.getText().length()>=8) {
					JOptionPane.showMessageDialog(null, "너무김");
					return;
				}
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println(e2.getMessage());
				
			}
			if(address.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "주소 입력하세요");
				return;
			}
			try {
				if (!name.getText().matches("[|가-힝]*")) {
					JOptionPane.showMessageDialog(null, "잘못입력");
					return;
				}
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println(e2.getMessage());
			}
			if((phonenum1.getText().trim().equals("")||(phonenum2.getText().trim().equals("")||phonenum3.getText().trim().equals("")))) {
				JOptionPane.showMessageDialog(null, "전화번호를 올바르게 입력하세요");
				return;
			}
			try {
				if(!(Integer.parseInt(phonenum1.getText().trim())>=0)&&(Integer.parseInt(phonenum1.getText().trim())<=9)){
					
					return;
				}
				if (phonenum1.getText().trim().length()>5) {
					JOptionPane.showMessageDialog(null, "너무김");
					return;
				}
				if(!(Integer.parseInt(phonenum2.getText().trim())>=0)&&(Integer.parseInt(phonenum2.getText().trim())<=9)) {
					return;
				}
				if (phonenum2.getText().trim().length()>5) {
					JOptionPane.showMessageDialog(null, "너무김");
					return;
				}
				if(!(Integer.parseInt(phonenum3.getText().trim())>=0)&&(Integer.parseInt(phonenum3.getText().trim())<=9)) {
					return;
				}
				if (phonenum3.getText().trim().length()>5) {
					JOptionPane.showMessageDialog(null, "너무김");
					return;
				}
					
			} catch (NumberFormatException e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "전화번호를 올바르게 입력하세요");
				return;
			}
			if(phonecheck.getText().equals("")) {
				phonecheck.setText(phonenum1.getText()+hy1.getText()+phonenum2.getText()+hy2.getText()
				+phonenum3.getText());
			}
			if(id.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "아이디를 입력하세요");
				return;
			}
			try {
				String a=id.getText().trim();
				//char bigalphabet=0;
				if (!a.matches("[|0-9|a-z|A-Z|]*")) {
					JOptionPane.showMessageDialog(null, "잘못입력");
					return;
				}
				if(a.length()>8) {
					JOptionPane.showMessageDialog(null, "너무김");
					return;
				}
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println(e2.getMessage());
				return;
			}
			/*
			 * if(!id.getText().equals("")) { JOptionPane.showMessageDialog(null, "중복체크");
			 * return; }
			 */
			
			if(pw.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "패스워드를 입력하세요");
				return;
			}
			
			JOptionPane.showMessageDialog(null,worknum.getText()+" "+name.getText()+" "+address.getText()+" "+phonecheck.getText()
					+" "+id.getText()+" "+pw.getText());
			//DB INSERT부분
			 WorkerDAO workDAO1=new WorkerDAO();
			 workDAO1.insert(Integer.parseInt(worknum.getText()), name.getText(), address.getText(), phonecheck.getText(), id.getText(), pw.getText());
			
		}
		if(e.getSource()==check) {
			if(id.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "아이디를 입력하세요");
				return;
			}	
			  if(!id.getText().trim().equals(""))  { 
				 WorkerDAO workDAO=new WorkerDAO();
			   boolean list=workDAO.checkselect(id.getText());
			  if(list==false) { 
				  JOptionPane.showMessageDialog(null,
			  "아이디 존재"); return;
			  }else if(list==true) {
			  JOptionPane.showMessageDialog(null, "아이디 생성 가능"); return;
			  }
					 
			 }		 
		}	
		
	}
  
  public Register() {
	  
	  registerinit();
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     dialog.setLocationRelativeTo(null);
     dialog.setBounds(0,0,300,400);
     dialog.setTitle("로그인");
     dialog.setVisible(true);
     dialog.setResizable(false);
    
   
  }
  
  public static void main(String[] args) {
  new Register();
 }
  
}