package com.my.guijtable;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.Book;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BookAdmin_GUI extends JFrame implements ActionListener {

	//private JButton register,cancle,delete,search,modify;
	private JPanel p1,p2,p3;
	private JTextField searchbar;
	private JTable jtable_result;
				
	JMenu m = new JMenu("����");
	JMenuItem insert = new JMenuItem("���");
	JMenuItem update = new JMenuItem("����");
	JMenuItem delete = new JMenuItem("����");
	JMenuItem quit = new JMenuItem("����");
	JMenuBar mb = new JMenuBar();

	String[] name = { "booknum", "bookname", "title", "author","cost","inventory","costtotal" };
	//List<BookDTO> list2=BookDAO
	DefaultTableModel dt = new DefaultTableModel(name, 1);
	JTable jt = new JTable(dt) {
		public boolean isCellEditable(int row,int column) {
			return false;
		}
	};
	JScrollPane jsp = new JScrollPane(jt);
	JPanel p = new JPanel();
	String[] comboName = { "ALL", "bookname", "title","author"};
	
	JComboBox combo = new JComboBox(comboName);
	JTextField jtf = new JTextField(20);
	JButton serach = new JButton("�˻�");
	Object [][]data=new Object[1][comboName.length];
	//UserDefaultJTableDAO dao = new UserDefaultJTableDAO();
	BookDAO dao=new BookDAO();
	//List<BookDTO>list=dao.userListInsert(user);

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insert) {// ���� �޴������� Ŭ��
			new UserJDailogGUI(this,"���");
		
		} else if (e.getSource() == update) {// ���� �޴������� Ŭ��
			new UserJDailogGUI(this,"����");
		

		} else if (e.getSource() == delete) {// ���� �޴������� Ŭ��
			// ���� Jtable�� ���õ� ��� ���� ���� ���´�.
			int row = jt.getSelectedRow();
			System.out.println("������ : " + row);

			Object obj = jt.getValueAt(row, 0);// �� ���� �ش��ϴ� value
			System.out.println("�� : " + obj);

			if (dao.userDelete(obj.toString()) > 0) {
				JOptionPane.showMessageDialog(null, "���ڵ尡 �����Ǿ����ϴ�");
				
				//����Ʈ ����
				dao.userSelectAll(dt);
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);

			} else {
				
				JOptionPane.showMessageDialog(null, "���ڵ尡 �������� �ʾҽ��ϴ�.");
			}

		} else if (e.getSource() == quit) {// ���� �޴������� Ŭ��
			System.exit(0);

		} else if (e.getSource() == serach) {// �˻� ��ư Ŭ��
			// JComboBox�� ���õ� value ��������
			String fieldName = combo.getSelectedItem().toString();
			System.out.println("�ʵ�� " + fieldName);
			
			if (fieldName.trim().equals("ALL")) {// ��ü�˻�
				dao.userSelectAll(dt);
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			}else {
				if (jtf.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "�˻��ܾ �Է����ּ���!");
					jtf.requestFocus();
				} else {// �˻�� �Է��������
					dao.getUserSearch(dt, fieldName, jtf.getText());
					if (dt.getRowCount() > 0)
						jt.setRowSelectionInterval(0, 0);
				}
			}
		}

	}//actionPerformed()----------
	
public void content() {
		

		//�޴��������� �޴��� �߰�
		m.add(insert); 
		m.add(update);
		m.add(delete);
		m.add(quit);
		//�޴��� �޴��ٿ� �߰�
		mb.add(m);
		
		//�����쿡 �޴��� ����
		setJMenuBar(mb);

		
		// South����
		p.setBackground(Color.yellow);
		p.add(combo);
		p.add(jtf);
		p.add(serach);

		add(jsp, "Center");
		add(p, "South");

		setSize(500, 400);
		setVisible(true);

		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// �̺�Ʈ���
		insert.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);
		quit.addActionListener(this);
		serach.addActionListener(this);

		// ��緹�ڵ带 �˻��Ͽ� DefaultTableModle�� �ø���
		//dao.userSelectAll(dt);
		jt.isCellEditable(1, comboName.length);
		jt.getTableHeader().setReorderingAllowed(false);
		jt.getTableHeader().setResizingAllowed(false); 
		//ù���� ����.
		if (dt.getRowCount() > 0)
			jt.setRowSelectionInterval(0, 0);
	}

	public BookAdmin_GUI() {
		super("GUI ȸ���������α׷� - DB����");
		content();
		this.setVisible(true);
		 this.setLocationRelativeTo(null);
		    this.setBounds(0,0,500,400);
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
		new BookAdmin_GUI();
	}
}
