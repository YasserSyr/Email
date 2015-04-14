
package email;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

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

	
	private JFrame frame;
	private JTextField userNameFeild;
	private JTextField passwordField;
	
	
	
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
                                               e.printStackTrace();
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
		
		userNameFeild = new JTextField();
		userNameFeild.setBounds(96, 30, 264, 20);
		frame.getContentPane().add(userNameFeild);
		userNameFeild.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email ");
		lblEmail.setBounds(21, 33, 46, 14);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(21, 69, 76, 14);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JTextField();
		passwordField.setBounds(96, 66, 264, 20);
		frame.getContentPane().add(passwordField);
		passwordField.setColumns(10);
		
		
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() 
		                                {
			                               public void actionPerformed(ActionEvent arg0) 
			                               {
			                            	   frame.dispose();
			                            	    MainTable table =new MainTable();
			                            	   
			                            	   table.setVisible(true);
			                            	   
			                               }
		                                });
		btnSignIn.setBounds(181, 101, 117, 29);
		frame.getContentPane().add(btnSignIn);
		                               
	}
	
	public Properties prop()
	{
		 Properties props = new Properties();
	      
	        props.put("mail.smtp.host","smtp.gmail.com"); //SMTP Host
	        props.put("mail.smtp.starttls.enable","true");
	        props.put("mail.smtp.socketFactory.port", "587"); //TLS Port
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
	        props.put("mail.smtp.port", "587"); //SMTP Port
	        
	        return props;
	}
	
	public Session authentication()
	{
		 Session session = Session.getInstance(prop(), new javax.mail.Authenticator() 
         {
           protected PasswordAuthentication getPasswordAuthentication() 
           {
             return new PasswordAuthentication(userNameFeild.getText(), passwordField.getText());
           }
          });
		 
		 return session;
	}
}
