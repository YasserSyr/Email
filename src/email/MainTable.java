
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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.table.*;
import java.util.*;
import javax.swing.*;

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
		setBounds(100, 100, 663, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(41, 73, 513, 311);
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
		table.setBounds(110, 0, 270, 272);
		EmailsName.add(table);
		
		textField = new JTextField();
		textField.setBounds(43, 42, 403, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("search");
		btnSearch.setBounds(465, 39, 89, 23);
		contentPane.add(btnSearch);
		
		JButton btnCompose = new JButton("compose");
		
		btnCompose.addActionListener(new ActionListener() 
		                                 {
		                                   	public void actionPerformed(ActionEvent arg0) 
		                                   	{
		                                   		
		                                   		SendMessage send = new SendMessage();
		                                   		send.frame.setVisible(true);
		                                    }
		                                 });
		btnCompose.setBounds(22, 410, 89, 23);
		contentPane.add(btnCompose);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 97, 21);
		contentPane.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmAddAnEmail = new JMenuItem("Add an Email");
		mntmAddAnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//jTabbedPane.add("test",new JScrollPane());
				tabbedPane.add("Email 2", EmailsName );
			}
		});
		mnFile.add(mntmAddAnEmail);
	}
}