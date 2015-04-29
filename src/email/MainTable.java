
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
import javax.swing.JOptionPane;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainTable extends JFrame 
{

	private JPanel contentPane;
	private JTextField textField;
	protected JFrame frame;
	public JTable table;
	private JPanel client;
	private int row;
    
	
	
	public MainTable(final String user, final String pass) 
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 808, 529);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 38, 787, 463);
		contentPane.add(tabbedPane);
		
	    client = new JPanel();
		tabbedPane.addTab("Current Email", null, client, null);
		client.setLayout(null);
		
		
		inbox(user, pass);
		
		
		JButton btnSent = new JButton("Sent");
		btnSent.setBounds(10, 44, 89, 23);
		client.add(btnSent);
		
		JButton btnJunk = new JButton("Junk");
		btnJunk.setBounds(10, 78, 89, 23);
		client.add(btnJunk);
		
		JButton btnTrash = new JButton("Trash");
		btnTrash.setBounds(10, 112, 89, 23);
		client.add(btnTrash);
		
		JButton btnDraft = new JButton("Draft");
		btnDraft.setBounds(10, 146, 89, 23);
		client.add(btnDraft);
		
		compose(user,pass);
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(511, 6, 249, 405);
		client.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(98, 6, 401, 405);
		client.add(scrollPane);
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() 
								   {
										@Override
										public void mouseClicked(MouseEvent arg0) 
										{
											row = table.getSelectedRow();
											
										}
								   });
		delete(user, pass,row);
		
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] { }, new String[] {"#","Date","From", "Subject"}));
		
		table.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new LineBorder(new Color(0, 0, 0))));
		
				
		textField = new JTextField();
		textField.setBounds(21, 7, 403, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("search");
		btnSearch.setBounds(432, 11, 89, 23);
		contentPane.add(btnSearch);
		
		
	}
	
	private void delete(final String user, final String pass, final int row)
	{
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() 
										{
			                                 public void actionPerformed(ActionEvent arg0)
			                                 {
			                                	 Connection conn = new Connection();
      		    
			                                	            conn.setPropertiesPOP();
			                                	            conn.setSession(user,pass);
                 
			                                	 FetchingEmail check = new FetchingEmail();
 	 
			                                	 try 
			                                	   {
			                                		 check.setStore(conn.getSession(),user,pass);
			                                		 check.deleteMessage(table.getModel().getValueAt(row, 0));
			                                	   }
			                                   catch(NoSuchProviderException e)
			                                	   {
			                                	   		JOptionPane.showMessageDialog( null ,"no such Provider", "error", JOptionPane.ERROR_MESSAGE);
			                                	   } 
			                                   catch(MessagingException e) 
			                                	   {
			                                	   		JOptionPane.showMessageDialog( null ,"check the message", "error", JOptionPane.ERROR_MESSAGE);
			                                	   }
 	    
				
			                                 }
										});
		
		btnDelete.setBounds(6, 221, 80, 29);
		client.add(btnDelete);

	}
	
	private void compose(final String user, final String pass)
	{
		JButton btnCompose = new JButton("Compose");
		btnCompose.addActionListener(new ActionListener() 
		 								 {
			                                  public void actionPerformed(ActionEvent e) 
			                                  {
			                                	  Connection conn = new Connection();
			                          		    
			                                                 conn.setPropertiesSMTP();
			                                                 conn.setSession(user,pass);
			                                	     
			                                   	  SendMessage sendMessage = new SendMessage(conn.getSession());
			                                  
			                                   				  sendMessage.frame.setVisible(true);
			                                   				
			                                  }
		 								 });
		
		btnCompose.setBounds(10, 181, 89, 29);
		client.add(btnCompose);
	}
	private void inbox(final String user, final String pass)
	{
		JButton Inbox = new JButton("Inbox");
		Inbox.addActionListener(new ActionListener() 
		                            {
			                             public void actionPerformed(ActionEvent arg0)
			                             {
			                            	 Connection conn = new Connection();
			                     		    
			                                            conn.setPropertiesPOP();
			                                            conn.setSession(user,pass);
			                                            
			                            	 DefaultTableModel model =(DefaultTableModel) table.getModel();
			                            	 
			                            	 FetchingEmail check = new FetchingEmail();
			                            	 
			                            	    try 
			                            	      {
													check.setStore(conn.getSession(),user,pass);
													check.fetch(model);
			                            	      }
			                            	  catch(NoSuchProviderException e)
			                            	      {
													JOptionPane.showMessageDialog( null ,"no such Provider", "error", JOptionPane.ERROR_MESSAGE);
			                            	      } 
			                            	  catch(MessagingException e) 
			                            	      {
													JOptionPane.showMessageDialog( null ,"check the message", "error", JOptionPane.ERROR_MESSAGE);
			                            	      }
			                            	    
			                            	    
				                              
			                             }
		                             });
		
		Inbox.setBounds(10, 11, 89, 23);
		client.add(Inbox);
		
	}
}
