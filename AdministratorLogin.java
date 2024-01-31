import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class AdministratorLogin {

	private static JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministratorLogin window = new AdministratorLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public AdministratorLogin() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel enter = new JLabel("Enter Name :");
		enter.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel adminLogin = new JLabel("Administrator Login");
		adminLogin.setForeground(Color.GRAY);
		adminLogin.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel enterPassword = new JLabel("Enter Password :");
		enterPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		passwordField = new JPasswordField();
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=textField.getText();
				String password=String.valueOf(passwordField.getPassword());
				if(name.equals("")&&password.equals("")){
					AdminSuccess.main(new String[]{});
					frame.dispose();
				}else{
					JOptionPane.showMessageDialog(AdministratorLogin.frame, "Sorry, Username or Password Does Not Match","Login Error", JOptionPane.ERROR_MESSAGE);
					textField.setText("");
					passwordField.setText("");
				}
				
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.main(new String[]{});
				frame.dispose();
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(44)
								.addComponent(adminLogin))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(104)
								.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(enterPassword)
										.addGap(39)
										.addComponent(passwordField))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(enter)
										.addGap(68)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBack)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(adminLogin)
							.addGap(53)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(enter)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(55)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(enterPassword)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
							.addComponent(btnLogin)
							.addGap(29)
							.addComponent(btnBack)))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}

}
