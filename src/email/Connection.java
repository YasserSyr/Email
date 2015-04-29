
package email;

import java.awt.EventQueue;

import javax.swing.*;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDesktopPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Properties;

import javax.swing.JToolBar;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.search.*;

public class Connection
{

	private Properties properties;
	private Session session;
   
	private JFrame frame;
    private JTextField userField;
    private JTextField passField;
    private JButton btnSignIn;
	
	
	
	public Connection()
	{
		initialize();
	}
	
	public void SignInWindow()
	{
		EventQueue.invokeLater(new Runnable() 
                                   {
                                       public void run() 
                                       {
                                          try{
                                                frame.setVisible(true);
                                             } 
                                        catch(Exception e) 
                                             {
                                               JOptionPane.showMessageDialog( null, "an error occur in display", "error", JOptionPane.ERROR_MESSAGE);
                                             }
                                        }
                                    });

	}
	
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 405, 158);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		userField = new JTextField();
		userField.setBounds(40, 18, 307, 28);
		frame.getContentPane().add(userField);
		userField.setColumns(10);
		
		passField = new JTextField();
		passField.setBounds(40, 61, 307, 28);
		frame.getContentPane().add(passField);
		passField.setColumns(10);
		
		
		
		btnSignIn = new JButton("Sign In");
		            btnSignIn.addActionListener(new ActionListener() 
                    {
                        public void actionPerformed(ActionEvent arg0)
                       {
                        	
         	                 frame.dispose();
         	               
         	                MainTable mainTable =new MainTable(userField.getText().trim(),passField.getText().trim());
         	                          mainTable.setVisible(true);
         	               
                       }
                    });
		            
		btnSignIn.setBounds(117, 101, 117, 29);
		frame.getContentPane().add(btnSignIn);
		                               
	}
	
	public void setPropertiesSMTP()
	{
		
		  properties = new Properties();
		  
		  properties.put("mail.smtp.host","smtp.gmail.com"); 
		  properties.put("mail.smtp.starttls.enable","true");
		  properties.put("mail.smtp.socketFactory.port", "587"); 
	      properties.put("mail.smtp.auth", "true"); 
	      properties.put("mail.smtp.port", "587"); 
	      
	}
	
	public void setPropertiesPOP()
	{
		  properties = new Properties();
		  
		  properties.put("mail.pop3.host","pop.gmail.com");
		  properties.put("mail.pop3.port", "995");
		  properties.put("mail.pop3.starttls.enable", "true");
	}
	
	private Properties getProperties()
	{
		return properties;
	}
	
	public void setSession(final String userName, final String password)
	{
		      session = Session.getDefaultInstance(getProperties(), new javax.mail.Authenticator() 
		                                                       {    
		                                                         	 
		                                                           protected PasswordAuthentication getPasswordAuthentication() 
		                                                           {
		                                                        	   
		                                                        	  return new PasswordAuthentication( userName, password);
		                                                           }
		                                                       }
						 						   	);
		    
	}
	public Session getSession()
	{
		return session;
	}
	
	
}


