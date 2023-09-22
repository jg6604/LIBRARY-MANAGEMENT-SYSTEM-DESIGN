import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.Color;

public class Main {

	private JFrame frame;
	private final Action action = new SwingAction();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btnUser = new JButton("User");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserLogin.main(new String[] {}); 
				frame.dispose();
			}
		});
		btnUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnAdmin = new JButton("Administrator");
		btnAdmin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdministratorLogin.main(new String[]{});		
				frame.dispose();
			}
		});

		JLabel bookSystem = new JLabel("Book Rental Management System");
		bookSystem.setBackground(Color.GRAY);
		bookSystem.setFont(new Font("Tahoma", Font.PLAIN, 23));

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(124, Short.MAX_VALUE)
						.addComponent(bookSystem, GroupLayout.PREFERRED_SIZE, 419,
								GroupLayout.PREFERRED_SIZE)
						.addGap(100))
				.addGroup(groupLayout.createSequentialGroup().addGap(211)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAdmin, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
								.addComponent(btnUser, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
						.addGap(200)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addComponent(bookSystem, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
				.addGap(30)
				.addComponent(btnUser, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE).addGap(30)
				.addComponent(btnAdmin, GroupLayout.PREFERRED_SIZE, 40,
				GroupLayout.PREFERRED_SIZE)
				.addGap(20)
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		frame.getContentPane().setLayout(groupLayout);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
}