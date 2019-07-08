package com.my.guijtable;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginGui extends JFrame implements ActionListener {

	private JTextField id;
	private JPasswordField pw;
	private JLabel jemok,loginchara,passwordchara;
	private JPanel p1,p2,p3;
	private JButton login,register,cancle;
	
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==register) new Register();
		if(e.getSource()==cancle) {
			id.setText(" ");
			pw.setText(" ");
		}
		else if(e.getSource()==login) {
			
			if(id.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "ID 확인 요망");
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
			if(pw.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "패스워드 확인 요망");
				return;
			}			
			 try {
				WorkerDAO workDAO=new WorkerDAO();
				 boolean list=workDAO.checkselect(id.getText());
				  boolean list2=workDAO.checkidpwselect(pw.getText());
				  if(list==false&&list2==false) { 
					  JOptionPane.showMessageDialog(null,
				  "로그인 성공"); 
					  new BookAdmin_GUI();
				  }
				  else {
					  JOptionPane.showMessageDialog(null,
							  "로그인 실패"); 
					  return;
				  }
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println(e2.getMessage());
			}
		}
	}
	public void init() {
		  this.setLayout(new GridLayout(3,1));
			p1=new JPanel();
			p1.add(loginchara=new JLabel("ID:"));
			p1.add(id=new JTextField(15));
			this.add(p1);
			p2=new JPanel();
			p2.add(passwordchara=new JLabel("PW:"),BorderLayout.SOUTH);
			p2.add(pw=new JPasswordField(15),BorderLayout.SOUTH);
			this.add(p2);
			p3=new JPanel();
			p3.add(login=new JButton("로그인"));
			p3.add(register=new JButton("회원가입"));
			p3.add(cancle=new JButton("취소"));
			this.add(p3);
			
			login.addActionListener(this);
			register.addActionListener(this);
			cancle.addActionListener(this);
	}
	
	public LoginGui() {
	
		init();
		this.setVisible(true);
		 this.setLocationRelativeTo(null);
		    this.setBounds(0,0,300,150);
		    this.setTitle("로그인");
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
		new LoginGui();
	}
}
