
package email;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;


public class MainTable extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	
	protected JFrame frame;

	/**
	 * Launch the application.
	 */
	
	

	/**
	 * Create the frame.
	 */
	public MainTable() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 38, 414, 212);
		contentPane.add(tabbedPane);
		
		JPanel EmailsName = new JPanel();
		tabbedPane.addTab("Current Email", null, EmailsName, null);
		EmailsName.setLayout(null);
		
		JButton Inbox = new JButton("Inbox");
		Inbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		Inbox.setBounds(10, 11, 89, 23);
		EmailsName.add(Inbox);
		
		JButton btnSent = new JButton("Sent");
		btnSent.setBounds(10, 44, 89, 23);
		EmailsName.add(btnSent);
		
		JButton btnJunk = new JButton("Junk");
		btnJunk.setBounds(10, 78, 89, 23);
		EmailsName.add(btnJunk);
		
		JButton btnTrash = new JButton("Trash");
		btnTrash.setBounds(10, 112, 89, 23);
		EmailsName.add(btnTrash);
		
		JButton btnDraft = new JButton("Draft");
		btnDraft.setBounds(10, 146, 89, 23);
		EmailsName.add(btnDraft);
		
		table = new JTable();
		table.setBounds(110, 0, 154, 184);
		EmailsName.add(table);
		
		textField = new JTextField();
		textField.setBounds(21, 7, 403, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("search");
		btnSearch.setBounds(432, 11, 89, 23);
		contentPane.add(btnSearch);
		
		JButton btnCompose = new JButton("compose");
		
		btnCompose.addActionListener(new ActionListener() 
		                                 {
		                                   	public void actionPerformed(ActionEvent arg0) 
		                                   	{
		                                   		
		                                   		SendMessage send = new SendMessage();
		                                   		send.setVisible(true);
		                                    }
		                                 });
		btnCompose.setBounds(20, 270, 89, 23);
		contentPane.add(btnCompose);
	}
}
