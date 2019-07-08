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
				
	JMenu m = new JMenu("관리");
	JMenuItem insert = new JMenuItem("등록");
	JMenuItem update = new JMenuItem("수정");
	JMenuItem delete = new JMenuItem("삭제");
	JMenuItem quit = new JMenuItem("종료");
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
	JButton serach = new JButton("검색");
	Object [][]data=new Object[1][comboName.length];
	//UserDefaultJTableDAO dao = new UserDefaultJTableDAO();
	BookDAO dao=new BookDAO();
	//List<BookDTO>list=dao.userListInsert(user);

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insert) {// 가입 메뉴아이템 클릭
			new UserJDailogGUI(this,"등록");
		
		} else if (e.getSource() == update) {// 수정 메뉴아이템 클릭
			new UserJDailogGUI(this,"수정");
		

		} else if (e.getSource() == delete) {// 삭제 메뉴아이템 클릭
			// 현재 Jtable의 선택된 행과 열의 값을 얻어온다.
			int row = jt.getSelectedRow();
			System.out.println("선택행 : " + row);

			Object obj = jt.getValueAt(row, 0);// 행 열에 해당하는 value
			System.out.println("값 : " + obj);

			if (dao.userDelete(obj.toString()) > 0) {
				JOptionPane.showMessageDialog(null, "레코드가 삭제되었습니다");
				
				//리스트 갱신
				dao.userSelectAll(dt);
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);

			} else {
				
				JOptionPane.showMessageDialog(null, "레코드가 삭제되지 않았습니다.");
			}

		} else if (e.getSource() == quit) {// 종료 메뉴아이템 클릭
			System.exit(0);

		} else if (e.getSource() == serach) {// 검색 버튼 클릭
			// JComboBox에 선택된 value 가져오기
			String fieldName = combo.getSelectedItem().toString();
			System.out.println("필드명 " + fieldName);
			
			if (fieldName.trim().equals("ALL")) {// 전체검색
				dao.userSelectAll(dt);
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			}else {
				if (jtf.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "검색단어를 입력해주세요!");
					jtf.requestFocus();
				} else {// 검색어를 입력했을경우
					dao.getUserSearch(dt, fieldName, jtf.getText());
					if (dt.getRowCount() > 0)
						jt.setRowSelectionInterval(0, 0);
				}
			}
		}

	}//actionPerformed()----------
	
public void content() {
		

		//메뉴아이템을 메뉴에 추가
		m.add(insert); 
		m.add(update);
		m.add(delete);
		m.add(quit);
		//메뉴를 메뉴바에 추가
		mb.add(m);
		
		//윈도우에 메뉴바 세팅
		setJMenuBar(mb);

		
		// South영역
		p.setBackground(Color.yellow);
		p.add(combo);
		p.add(jtf);
		p.add(serach);

		add(jsp, "Center");
		add(p, "South");

		setSize(500, 400);
		setVisible(true);

		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 이벤트등록
		insert.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);
		quit.addActionListener(this);
		serach.addActionListener(this);

		// 모든레코드를 검색하여 DefaultTableModle에 올리기
		//dao.userSelectAll(dt);
		jt.isCellEditable(1, comboName.length);
		jt.getTableHeader().setReorderingAllowed(false);
		jt.getTableHeader().setResizingAllowed(false); 
		//첫번행 선택.
		if (dt.getRowCount() > 0)
			jt.setRowSelectionInterval(0, 0);
	}

	public BookAdmin_GUI() {
		super("GUI 회원관리프로그램 - DB연동");
		content();
		this.setVisible(true);
		 this.setLocationRelativeTo(null);
		    this.setBounds(0,0,500,400);
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
		new BookAdmin_GUI();
	}
}
