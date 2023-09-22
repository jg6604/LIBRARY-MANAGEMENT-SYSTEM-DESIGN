import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import net.proteanit.sql.DbUtils;

public class AdminSuccess {
	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminSuccess window = new AdminSuccess();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminSuccess() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel newLabel = new JLabel("ADMINISTRATOR SECTION");
		newLabel.setForeground(Color.GRAY);
		newLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
	    JButton create_but=new JButton("Create/Reset");
	    create_but.setBounds(450,60,120,25); 
	    create_but.addActionListener(new ActionListener() { 
	        public void actionPerformed(ActionEvent e){
	             
	            Create.create(); //Call create function
	            JOptionPane.showMessageDialog(null,"Database Created/Reset!"); 
	             
	        }
	    });		
		
	    JButton view_but=new JButton("View Book");
	    view_but.setBounds(20,20,120,25);
	    view_but.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	             
	            JFrame f = new JFrame("Books Available"); 
	             	             
	            Connection connection = Connect.getConnection(); 
	            String sql="select * from BOOKS"; 
				
	            try {
	                Statement stmt = connection.createStatement();
	                stmt.executeUpdate("USE BOOKRENT"); 
	                stmt=connection.createStatement();
	                ResultSet rs=stmt.executeQuery(sql);
	                JTable book_list= new JTable(); 
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
		
	    JButton issue_book=new JButton("Issue Book"); 
	    issue_book.setBounds(450,20,120,25); 
	     
	    issue_book.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	                
	                JFrame g = new JFrame("Enter Details");

	                JLabel l1,l2,l3,l4;  
	                l1=new JLabel("Book ID(BID)");  
	                l1.setBounds(30,15, 100,30); 
	                 
	                 
	                l2=new JLabel("User ID(UID)");  
	                l2.setBounds(30,53, 100,30); 
	                 
	                l3=new JLabel("Period(days)");  
	                l3.setBounds(30,90, 100,30); 
	                 
	                l4=new JLabel("Issued Date(DD-MM-YYYY)");  
	                l4.setBounds(30,127, 150,30); 
	                 
	                JTextField F_bid = new JTextField();
	                F_bid.setBounds(110, 15, 200, 30);
	                JTextField F_uid=new JTextField();
	                F_uid.setBounds(110, 53, 200, 30);
	                 
	                JTextField F_period=new JTextField();
	                F_period.setBounds(110, 90, 200, 30);
	                 
	                JTextField F_issue=new JTextField();
	                F_issue.setBounds(180, 130, 130, 30);   
	 
	                 
	                JButton create_but=new JButton("Submit"); 
	                create_but.setBounds(130,170,80,25);
	                create_but.addActionListener(new ActionListener() {
	                     
	                    public void actionPerformed(ActionEvent e){
	                     
	                     String uid = F_uid.getText();
	                     String bid = F_bid.getText();
	                     String period = F_period.getText();
	                     String issued_date = F_issue.getText();
	 
	                     int period_int = Integer.parseInt(period);
	                     
	                     Connection connection = Connect.getConnection();
	                     
	                     
	                     try {
	                         Statement stmt = connection.createStatement();
	                          stmt.executeUpdate("USE BOOKRENT");
	                          stmt.executeUpdate("INSERT INTO ISSUED(UID,BID,ISSUED_DATE,PERIOD) VALUES ('"+uid+"','"+bid+"','"+issued_date+"',"+period_int+")");
	                          JOptionPane.showMessageDialog(null,"Book Issued!");
	                          g.dispose();
	                           
	                         }
	                          
	                         catch (SQLException e1) {
	                             
	                              JOptionPane.showMessageDialog(null, e1);
	                         }   
	                          
	                         }
	                     
	                });
	                     	                 
	                    g.add(l3);
	                    g.add(l4);
	                    g.add(create_but);
	                    g.add(l1);
	                    g.add(l2);
	                    g.add(F_uid);
	                    g.add(F_bid);
	                    g.add(F_period);
	                    g.add(F_issue);
	                    g.setSize(350,250); 
	                    g.setLayout(null); 
	                    g.setVisible(true);
	                    g.setLocationRelativeTo(null);
	                    
	                    
	        }
	    });
	    
	    JButton add_user=new JButton("Add User"); 
	    add_user.setBounds(20,60,120,25); 
	     
	    add_user.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	                 
	                JFrame g = new JFrame("Enter User Details"); 
	                //Create label 
	                JLabel l1,l2;  
	                l1=new JLabel("Username");  //label 1 for username
	                l1.setBounds(30,15, 100,30); 
	                 
	                 
	                l2=new JLabel("Password");  //label 2 for password
	                l2.setBounds(30,50, 100,30); 
	                 
	                //set text field for username 
	                JTextField F_user = new JTextField();
	                F_user.setBounds(110, 15, 200, 30);
	                 
	                //set text field for password
	                JPasswordField F_pass=new JPasswordField();
	                F_pass.setBounds(110, 50, 200, 30);
	                //set radio button for admin
	                JRadioButton a1 = new JRadioButton("Admin");
	                a1.setBounds(55, 80, 200,30);
	                //set radio button for user
	                JRadioButton a2 = new JRadioButton("User");
	                a2.setBounds(130, 80, 200,30);
	                //add radio buttons
	                ButtonGroup bg=new ButtonGroup();    
	                bg.add(a1);
	                bg.add(a2);  
	                 
	                                 
	                JButton create_but=new JButton("Create");
	                create_but.setBounds(130,130,80,25);
	                create_but.addActionListener(new ActionListener() {
	                     
	                    public void actionPerformed(ActionEvent e){
	                     
	                    String username = F_user.getText();
	                    String password=String.valueOf(F_pass.getPassword());
	                    Boolean admin = false;
	                     
	                    if(a1.isSelected()) {
	                        admin=true;
	                    }
	                     
	                    Connection connection = Connect.getConnection();
	                     
	                    try {
	                    Statement stmt = connection.createStatement();
	                     stmt.executeUpdate("USE BOOKRENT");
	                     stmt.executeUpdate("INSERT INTO USERS(USERNAME,PASSWORD,ADMIN) VALUES ('"+username+"','"+password+"',"+admin+")");
	                     JOptionPane.showMessageDialog(null,"User added!");
	                     g.dispose();
	                      
	                    }
	                     
	                    catch (SQLException e1) {
	                         JOptionPane.showMessageDialog(null, e1);
	                    }   
	                     
	                    }
	                     
	                });
	                     	                 
	                    g.add(create_but);
	                    g.add(a2);
	                    g.add(a1);
	                    g.add(l1);
	                    g.add(l2);
	                    g.add(F_user);
	                    g.add(F_pass);
	                    g.setSize(350,200); 
	                    g.setLayout(null); 
	                    g.setVisible(true);
	                    g.setLocationRelativeTo(null);
	                 
	                 
	    }
	    });
	    
	    JButton users_but=new JButton("View Users");//creating instance of JButton to view users
	    users_but.setBounds(150,20,120,25); 
	    users_but.addActionListener(new ActionListener() { 
	        public void actionPerformed(ActionEvent e){
	                 
	                JFrame f = new JFrame("Users List");
	                 
	                Connection connection = Connect.getConnection();
	                String sql="select * from users"; 
	                try {
	                    Statement stmt = connection.createStatement();
	                     stmt.executeUpdate("USE BOOKRENT"); 
	                    stmt=connection.createStatement();
	                    ResultSet rs=stmt.executeQuery(sql);
	                    JTable book_list= new JTable();
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
	    
	    
	    JButton return_book=new JButton("Return Book"); //creating instance of JButton to return books
	    return_book.setBounds(280,60,160,25); 
	     
	    return_book.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	                 
	                JFrame g = new JFrame("Enter Details");
	                JLabel l1,l2,l3,l4;  
	                l1=new JLabel("Issue ID(IID)");  //Label 1 for Issue ID
	                l1.setBounds(30,15, 100,30); 
	                
	                 
	                l4=new JLabel("Return Date(DD-MM-YYYY)");  
	                l4.setBounds(30,50, 150,30); 
	                 
	                JTextField F_iid = new JTextField();
	                F_iid.setBounds(110, 15, 200, 30);
	                 
	                 
	                JTextField F_return=new JTextField();
	                F_return.setBounds(180, 50, 130, 30);
	             
	 
	                JButton create_but=new JButton("Return");
	                create_but.setBounds(130,170,80,25);
	                create_but.addActionListener(new ActionListener() {
	                     
	                    public void actionPerformed(ActionEvent e){                 
	                     
	                    String iid = F_iid.getText();
	                    String return_date = F_return.getText();
	                     
	                    Connection connection = Connect.getConnection();
	                     
	                    try {
	                    Statement stmt = connection.createStatement();
	                     stmt.executeUpdate("USE BOOKRENT");	 
	                     JOptionPane.showMessageDialog(null,"Book Returned!");
	                      
	                    }
	                             	                     
	                    catch (SQLException e1) {
	                         JOptionPane.showMessageDialog(null, e1);
	                    }   
	                     
	                    }
	                     
	                }); 
	                    g.add(l4);
	                    g.add(create_but);
	                    g.add(l1);
	                    g.add(F_iid);
	                    g.add(F_return);
	                    g.setSize(350,250); 
	                    g.setLayout(null); 
	                    g.setVisible(true); 
	                    g.setLocationRelativeTo(null);              
	    }
	    });

	    
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.main(new String[]{});
				frame.dispose();
			}
		});
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 18));

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(182)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(newLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(create_but, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(add_user, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(users_but, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
								.addComponent(issue_book, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
								.addComponent(view_but, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
								.addComponent(return_book, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(20)))
					.addContainerGap(179, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(268, Short.MAX_VALUE)
					.addComponent(btnLogOut)
					.addGap(257))
		);
		
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(newLabel)
					.addGap(20)
					.addComponent(create_but, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(add_user, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(users_but, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(issue_book, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(view_but, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(return_book, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(btnLogOut)
					.addGap(10))
		);
		frame.getContentPane().setLayout(groupLayout);
	}

	
}