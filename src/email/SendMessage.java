package email;
import java.awt.EventQueue;

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class SendMessage  extends JFrame
{

	private JFrame frame;
	private JTextField to;
	private JTextField subject;
	JEditorPane messageArea;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SendMessage window = new SendMessage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public SendMessage() {
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
												   Transport.send(message());
											    } 
			                            	catch(AddressException e) 
			                            	    {
												   e.printStackTrace();
											    }
			                                catch(javax.mail.MessagingException e) 
			                            	    {
												   e.printStackTrace();
											    }
			                              }
		                              });
		frame.getContentPane().add(btnSend);
		
		 messageArea = new JEditorPane();
		 messageArea.setBounds(6, 93, 438, 179);
		frame.getContentPane().add(messageArea);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
	}
	
	
	
	public Message message() 
			throws AddressException, javax.mail.MessagingException
	{
	   
		Connection conn = new Connection();
		
		Message message = new MimeMessage(conn.authentication());
	       
	         message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to.getText()));

	         // Set Subject: header field
	         message.setSubject(subject.getText());

	        
			// Now set the actual message
	         message.setText(messageArea.getText());

	         // Send message
	        
     return message;
	     
	}
}
