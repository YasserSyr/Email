
package email;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
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
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.border.CompoundBorder;

public class MainTable extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	protected JFrame frame;
	public JTable table;
    
	
	
	public MainTable(final String u, final String p) 
	{
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 38, 787, 277);
		contentPane.add(tabbedPane);
		
		JPanel EmailsName = new JPanel();
		tabbedPane.addTab("Current Email", null, EmailsName, null);
		EmailsName.setLayout(null);
		
		
		
		JButton Inbox = new JButton("Inbox");
		Inbox.addActionListener(new ActionListener() 
		                            {
			                             public void actionPerformed(ActionEvent arg0)
			                             {
			                            	 Connection conn = new Connection();
			                     		    
			                                            conn.setPropertiesPOP();
			                                            conn.setSession(u,p);
			                                            
			                            	 DefaultTableModel model =(DefaultTableModel) table.getModel();
			                            	 
			                            	 FetchingEmail check = new FetchingEmail();
			                            	 
			                            	    try 
			                            	      {
													check.setStore(conn.getSession(),u,p);
			                            	      }
			                            	  catch(NoSuchProviderException e)
			                            	      {
													e.printStackTrace();
			                            	      } 
			                            	  catch(MessagingException e) 
			                            	      {
													e.printStackTrace();
			                            	      }
			                            	    
			                            	    check.fetch(model);
				                              
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
		
		
		
		JButton btnCompose = new JButton("Compose");
		btnCompose.addActionListener(new ActionListener() 
		 								 {
			                                  public void actionPerformed(ActionEvent e) 
			                                  {
			                                	  Connection conn = new Connection();
			                          		    
			                                                 conn.setPropertiesSMTP();
			                                                 conn.setSession(u,p);
			                                	     
			                                   	  SendMessage sendMessage = new SendMessage(conn.getSession());
			                                  
			                                   				  sendMessage.frame.setVisible(true);
			                                   				
			                                  }
		 								 });
		
		btnCompose.setBounds(10, 181, 89, 29);
		EmailsName.add(btnCompose);
		
		JPanel panel = new JPanel();
		panel.setBounds(409, 6, 351, 219);
		EmailsName.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(98, 6, 305, 219);
		EmailsName.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				""
			}
		));
		
		table.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new LineBorder(new Color(0, 0, 0))));
		
		textField = new JTextField();
		textField.setBounds(21, 7, 403, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("search");
		btnSearch.setBounds(432, 11, 89, 23);
		contentPane.add(btnSearch);
		
		
	}
}
