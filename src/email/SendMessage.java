package email;
import java.awt.EventQueue;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Properties;


public class SendMessage  extends JFrame
{

	public JFrame frame;
	private JTextField to;
	private JTextField subject;
	private JEditorPane messageArea;
	private Message sendMessage;
	private Session session;
	
	public SendMessage(Session session)
	{
	    	   
		initialize(session);
	}
	
	private void initialize(final Session session) 
	{
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		to = new JTextField();
		to.setBounds(62, 11, 284, 20);
		frame.getContentPane().add(to);
		to.setColumns(10);
		
		subject = new JTextField();
		subject.setBounds(62, 42, 284, 20);
		subject.setColumns(10);
		frame.getContentPane().add(subject);
		
		JLabel lblTo = new JLabel("to:");
		lblTo.setBounds(36, 14, 29, 14);
		frame.getContentPane().add(lblTo);
		
		JLabel lblTitle = new JLabel("Subject:");
		lblTitle.setBounds(10, 45, 68, 14);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblTypeYourMessage = new JLabel("type your message here :");
		lblTypeYourMessage.setBounds(151, 67, 157, 14);
		frame.getContentPane().add(lblTypeYourMessage);
		
		JButton btnSend = new JButton("send");
		btnSend.setBounds(356, 11, 68, 51);
		btnSend.addActionListener(new ActionListener() 
		                              {
			                              public void actionPerformed(ActionEvent arg0) 
			                              {
			                            	 
			                            	  try
			                            	    {
			                            		  setMessage(session);
			                            		   
			                            		   transport();
			                            		  
												   frame.dispose(); 
											    } 
			                            	catch(AddressException e) 
			                            	    {
												  // e.printStackTrace();
												   JOptionPane.showMessageDialog( null ,"check the address", "error", JOptionPane.ERROR_MESSAGE);
												 //  return;
			                            	    }
			                                catch(javax.mail.MessagingException e) 
			                            	    {
												// e.printStackTrace();
												   JOptionPane.showMessageDialog(null  ,"check the address", "error", JOptionPane.ERROR_MESSAGE);
												   //return;
											    }
			                              }
		                              }
								 );
		
		frame.getContentPane().add(btnSend);
		
		messageArea = new JEditorPane();
	    messageArea.setBounds(6, 93, 438, 179);
	    
		frame.getContentPane().add(messageArea);
	}
	
	public void setMessage(Session session) 
			  
	{
		
		sendMessage = new MimeMessage(session);
		
		try {
			sendMessage.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to.getText()));
			sendMessage.setSubject(subject.getText());

			sendMessage.setText(messageArea.getText());
		} catch (javax.mail.MessagingException e) {
			 JOptionPane.showMessageDialog(null  ,"check the address", "error", JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		}

		
		
	}
	
	private Message getMessage() 
	{
		return sendMessage;
	}
	
	private void transport() 
			    throws AddressException, javax.mail.MessagingException
	{
		 Transport.send(getMessage());
	}
	
	 
}
	
	
	

