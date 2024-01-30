import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class UserLogin {

	private static JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogin window = new UserLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UserLogin() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel newLabel = new JLabel("User Login Form");
		newLabel.setForeground(Color.GRAY);
		newLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));

		JLabel enterName = new JLabel("Enter User Name :");
		enterName.setFont(new Font("Tahoma", Font.PLAIN, 18));

		textField = new JTextField();
		textField.setColumns(10);

		JLabel enterPin = new JLabel("Enter PIN :");
		enterPin.setFont(new Font("Tahoma", Font.PLAIN, 18));

		passwordField = new JPasswordField();
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				
				String password = String.valueOf(passwordField.getPassword());
				//String UID = String.valueOf(passwordField.getPassword());
				
		        if(username.equals("")) //If username is null
		        {
		            JOptionPane.showMessageDialog(null,"Please enter username"); 
		        } 
		        else if(password.equals("")) //If password is null
		        {
		            JOptionPane.showMessageDialog(null,"Please enter password"); 
		        }
		        else { 
		            Connection connection=Connect.getConnection();  //Connect to the database
		            try
		            {
		            Statement stmt = connection.createStatement();
		              stmt.executeUpdate("USE LIBRARY"); //Use the database with the name 
		              String st = ("SELECT * FROM USERS WHERE USERNAME='"+username+"' AND PASSWORD='"+password+"'");
		              //UID= ("SELECT UID FROM USERS WHERE USERNAME='"+username+"' AND PASSWORD='"+password+"'");
		              //String UID="2";
		              ResultSet rs = stmt.executeQuery(st); //Execute query
		              
		              if(rs.next()==false) { //Move pointer below
		                  
		                  JOptionPane.showMessageDialog(null,"Wrong Username/Password!"); 
		 
		              }
		              else {

		                  frame.dispose();
		                rs.beforeFirst(); 
		                while(rs.next())
		                {
		                  String admin=rs.getString("ADMIN");
		                 
		            	  String UID=rs.getString("UID");
		                
		            	  if(admin.equals("1")) {
		            		  AdminSuccess.main(new String[]{});
		            	  }
		            	  else {
		    

		            	      UserSuccess.main(new String[] {});
		            	      
		            	  }
		                }
		            	  
		            	  
 
		              }
		              }
		            catch (Exception ex) {
		                 ex.printStackTrace();
		        }
		        }
			}


		});
		
		

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.main(new String[] {});
				frame.dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()						
								.addGroup(groupLayout.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addGroup(groupLayout.createSequentialGroup()
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(enterName).addComponent(enterPin))
														.addGap(42)
														.addGroup(groupLayout
																.createParallelGroup(Alignment.LEADING, false)
																.addComponent(passwordField).addComponent(textField,
																		GroupLayout.DEFAULT_SIZE, 148,
																		Short.MAX_VALUE)))
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 128,
																GroupLayout.PREFERRED_SIZE)
														.addGap(20))))
								.addGroup(groupLayout.createSequentialGroup().addGap(10).addComponent(newLabel,
										GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)))
								.addGap(40))
						.addGroup(groupLayout.createSequentialGroup().addComponent(btnBack).addContainerGap())));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(26)
								
						.addGroup(groupLayout.createSequentialGroup().addGap(67).addComponent(newLabel).addGap(55)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(enterName)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(46)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(enterPin)
										.addComponent(passwordField, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE).addComponent(btnLogin)
								.addGap(28).addComponent(btnBack)
				.addContainerGap())))));
		frame.getContentPane().setLayout(groupLayout);
	}

}
