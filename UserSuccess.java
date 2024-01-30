import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

import net.proteanit.sql.DbUtils;


public class UserSuccess{
	public static String UID;
	//public void UID(String UID) {
		//this.UID=UID;
	//}

	public JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserSuccess window = new UserSuccess();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	public UserSuccess() {
		initialize();
	}

	private void initialize() {
		//String var=UserLogin().UID;
		
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel userSection = new JLabel("USER SECTION");
		userSection.setForeground(Color.GRAY);
		userSection.setFont(new Font("Tahoma", Font.PLAIN, 28));

	    JButton view_but=new JButton("View Books"); 
	    view_but.setBounds(20,20,120,25);
	    view_but.addActionListener(new ActionListener() { 
	        public void actionPerformed(ActionEvent e){
	             
	            JFrame f = new JFrame("Books Available"); //View books stored in database
	             
	            Connection connection = Connect.getConnection();
	            String sql="select * from BOOKS"; //Retrieve data from database
	            try {
	                Statement stmt = connection.createStatement(); //connect to database
	                stmt.executeUpdate("USE LIBRARY"); 
	                stmt=connection.createStatement();
	                ResultSet rs=stmt.executeQuery(sql);
	                JTable book_list= new JTable(); //show data in table format
	                book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
	                  
	                JScrollPane scrollPane = new JScrollPane(book_list); 
	 
	                f.add(scrollPane); 
	                f.setSize(800, 400); 
	                f.setVisible(true);
	                f.setLocationRelativeTo(null);
	            } catch (SQLException e1) {
	             
	                 JOptionPane.showMessageDialog(null, e1);
	            }               
	             
	    }
	    }
	    );
	    
	    
	    JButton my_book=new JButton("My Books"); 
	    my_book.setBounds(150,20,120,25);
	    my_book.addActionListener(new ActionListener() { 
	        public void actionPerformed(ActionEvent e){
	             
	               
	            JFrame f = new JFrame("My Books"); //View books issued by user
	           
	            int UID_int = Integer.parseInt(UID); //Pass user ID
	            
	            Connection connection = Connect.getConnection(); //connect to database
	            //retrieve data
	            String sql="select distinct issued.*,books.bname,books.genre,books.price from issued,books " + "where ((issued.uid=" + UID_int + ") and (books.bid in (select bid from issued where issued.uid="+UID_int+")))";
	            String sql1 = "select bid from issued where uid="+UID_int;
	            try {
	                Statement stmt = connection.createStatement();
	                //use database
	                stmt.executeUpdate("USE LIBRARY");
	                stmt=connection.createStatement();
	                //store in array
	                ArrayList books_list = new ArrayList();
	                ResultSet rs=stmt.executeQuery(sql);
	                JTable book_list= new JTable(); //store data in table format
	                book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
	                JScrollPane scrollPane = new JScrollPane(book_list);
	 
	                f.add(scrollPane); 
	                f.setSize(800, 400); 
	                f.setVisible(true);
	                f.setLocationRelativeTo(null);
	            } catch (SQLException e1) {
	                
	                 JOptionPane.showMessageDialog(null, e1);
	            }               
	                 
	    }
	    }
	    );  
	                
	    
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.main(new String[] {});
				frame.dispose();
			}
		});
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblNewLabel = new JLabel("");

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING,
						groupLayout.createSequentialGroup().addGap(251).addComponent(lblNewLabel).addContainerGap(221,
								Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(208, Short.MAX_VALUE)
						.addComponent(userSection).addGap(171))
				.addGroup(Alignment.LEADING,
						groupLayout.createSequentialGroup().addGap(237)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(view_but, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 204,
												Short.MAX_VALUE)
										.addComponent(my_book, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 204,
												Short.MAX_VALUE))
								.addGap(181))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(284, Short.MAX_VALUE)
						.addComponent(btnLogOut).addGap(241)));
		
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(userSection)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)	
						.addComponent(view_but).addGap(26)
						.addComponent(my_book).addGap(18)
						.addComponent(btnLogOut)
						.addContainerGap(29, Short.MAX_VALUE)));
		frame.getContentPane().setLayout(groupLayout);
	}

}
