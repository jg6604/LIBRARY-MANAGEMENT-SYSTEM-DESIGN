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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
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
		frame.setBounds(100, 100, 740, 560);
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
		
	    JButton view_but=new JButton("View Books");
	    view_but.setBounds(20,20,120,25);
	    view_but.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	             
	            JFrame f = new JFrame("Books Available"); 
	             	             
	            Connection connection = Connect.getConnection(); 
	            String sql="select * from BOOKS"; 
				
	            try {
	                Statement stmt = connection.createStatement();
	                stmt.executeUpdate("USE LIBRARY"); 
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
	    
	    JButton issued_but=new JButton("View Issued Books");//creating instance of JButton to view the issued books
	    issued_but.setBounds(280,20,160,25);//x axis, y axis, width, height 
	    issued_but.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	                 
	                JFrame f = new JFrame("Users List");
	                //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                 
	                 
	                Connection connection = Connect.getConnection();
	                String sql="select * from issued";
	                try {
	                    Statement stmt = connection.createStatement();
	                     stmt.executeUpdate("USE LIBRARY");
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
	                    // TODO Auto-generated catch block
	                     JOptionPane.showMessageDialog(null, e1);
	                }       
	                             
	    }
	        }
	    );
	    
	    JButton add_book=new JButton("Add Book"); //creating instance of JButton for adding books
	    add_book.setBounds(150,60,120,25); 
	     
	    add_book.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	                //set frame wot enter book details
	                JFrame g = new JFrame("Enter Book Details");
	                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                // set labels
	                JLabel l1,l2,l3;  
	                l1=new JLabel("Book Name");  //lebel 1 for book name
	                l1.setBounds(30,15, 100,30); 
	                 
	                 
	                l2=new JLabel("Genre");  //label 2 for genre
	                l2.setBounds(30,53, 100,30); 
	                 
	                l3=new JLabel("Price");  //label 2 for price
	                l3.setBounds(30,90, 100,30); 
	                 
	                //set text field for book name
	                JTextField F_bname = new JTextField();
	                F_bname.setBounds(110, 15, 200, 30);
	                 
	                //set text field for genre 
	                JTextField F_genre=new JTextField();
	                F_genre.setBounds(110, 53, 200, 30);
	                //set text field for price
	                JTextField F_price=new JTextField();
	                F_price.setBounds(110, 90, 200, 30);
	                         
	                 
	                JButton create_but=new JButton("Submit");//creating instance of JButton to submit details  
	                create_but.setBounds(130,130,80,25);//x axis, y axis, width, height 
	                create_but.addActionListener(new ActionListener() {
	                     
	                    public void actionPerformed(ActionEvent e){
	                    // assign the book name, genre, price
	                    String bname = F_bname.getText();
	                    String genre = F_genre.getText();
	                    String price = F_price.getText();
	                    //convert price of integer to int
	                    int price_int = Integer.parseInt(price);
	                     
	                    Connection connection = Connect.getConnection();
	                     
	                    try {
	                    Statement stmt = connection.createStatement();
	                     stmt.executeUpdate("USE LIBRARY");
	                     stmt.executeUpdate("INSERT INTO BOOKS(BNAME,GENRE,PRICE) VALUES ('"+bname+"','"+genre+"',"+price_int+")");
	                     JOptionPane.showMessageDialog(null,"Book added!");
	                     g.dispose();
	                      
	                    }
	                     
	                    catch (SQLException e1) {
	                        // TODO Auto-generated catch block
	                         JOptionPane.showMessageDialog(null, e1);
	                    }   
	                     
	                    }
	                     
	                });
	                                 
	                    g.add(l3);
	                    g.add(create_but);
	                    g.add(l1);
	                    g.add(l2);
	                    g.add(F_bname);
	                    g.add(F_genre);
	                    g.add(F_price);
	                    g.setSize(350,200);//400 width and 500 height  
	                    g.setLayout(null);//using no layout managers  
	                    g.setVisible(true);//making the frame visible 
	                    g.setLocationRelativeTo(null);
	                             
	    }
	    });
		
	    JButton issue_book=new JButton("Issue Book"); 
	    issue_book.setBounds(450,20,120,25); 
	     
	    issue_book.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	                
	                JFrame g = new JFrame("Enter Details");

	                JLabel l1,l2,l3;  
	                l1=new JLabel("Book ID(BID)");  
	                l1.setBounds(30,15, 100,30); 
	                 
	                 
	                l2=new JLabel("User ID(UID)");  
	                l2.setBounds(30,53, 100,30); 
	                 
	                //l3=new JLabel("Period(days)");  
	                //l3.setBounds(30,90, 100,30); 
	                // 
	                l3=new JLabel("Issued Date(DD-MM-YYYY)");  
	                l3.setBounds(30,127, 150,30); 
	                 
	                JTextField F_bid = new JTextField();
	                F_bid.setBounds(110, 15, 200, 30);
	                JTextField F_uid=new JTextField();
	                F_uid.setBounds(110, 53, 200, 30);
	                 
	                //JTextField F_period=new JTextField();
	                //F_period.setBounds(110, 90, 200, 30);
	                 
	                JTextField F_issue=new JTextField();
	                F_issue.setBounds(180, 130, 130, 30);   
	 
	                 
	                JButton create_but=new JButton("Submit"); 
	                create_but.setBounds(130,170,80,25);
	                create_but.addActionListener(new ActionListener() {
	                     
	                    public void actionPerformed(ActionEvent e){
	                     
	                     String uid = F_uid.getText();
	                     String bid = F_bid.getText();
	                     //String period = F_period.getText();
	                     String issued_date = F_issue.getText();
	 
	                     //int period_int = Integer.parseInt(period);
	                     
	                     Connection connection = Connect.getConnection();
	                     
	                     
	                     try {
	                         Statement stmt = connection.createStatement();
	                          stmt.executeUpdate("USE LIBRARY");
	                          stmt.executeUpdate("INSERT INTO ISSUED(UID,BID,ISSUED_DATE) VALUES ('"+uid+"','"+bid+"','"+issued_date+"')");
	                          JOptionPane.showMessageDialog(null,"Book Issued!");
	                          g.dispose();
	                           
	                         }
	                          
	                         catch (SQLException e1) {
	                             
	                              JOptionPane.showMessageDialog(null, e1);
	                         }   
	                          
	                         }
	                     
	                });
	                     	                 
	                    g.add(l3);
	                    //g.add(l4);
	                    g.add(create_but);
	                    g.add(l1);
	                    g.add(l2);
	                    g.add(F_uid);
	                    g.add(F_bid);
	                    //g.add(F_period);
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
	                     stmt.executeUpdate("USE LIBRARY");
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
	                     stmt.executeUpdate("USE LIBRARY"); //USE DATABASE
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
	                     stmt.executeUpdate("USE LIBRARY");	
	                     
	                     JOptionPane.showMessageDialog(null,"Book Returned!");
	                     stmt.executeUpdate("UPDATE ISSUED SET RETURN_DATE='"+return_date+"' WHERE IID="+iid);
	                     g.dispose();
	                      
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
								.addComponent(add_book, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
								.addComponent(view_but, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
								.addComponent(issued_but, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
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
					.addGap(10)
					.addComponent(create_but, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(add_user, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(users_but, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(issue_book, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(add_book, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(view_but, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(issued_but, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(return_book, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnLogOut)
					.addGap(10))
		);
		frame.getContentPane().setLayout(groupLayout);
	}

	
}
