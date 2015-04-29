package email;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FetchingEmail 
{
	 
	private Store store;
	
	
	public void setStore(Session session,final String u, final String p) 
			      throws NoSuchProviderException, MessagingException
	{
		   store = session.getStore("pop3s");

	      store.connect("pop.gmail.com", u,p);
	      
	}
	private Store getStore()
	{
		return store;
	}
	
	public  void fetch(DefaultTableModel model)
	{
		
        try
          {
            
              Folder inbox = getStore().getFolder("INBOX");
                     inbox.open(Folder.READ_WRITE);
                 
                 int  count =0;
             for(int i=inbox.getMessageCount(); i > 0 ;i--)
                {
                    Message msg = inbox.getMessage(i);
                    
                    Address[] in = msg.getFrom();
     
                    for(Address address : in) 
                      {
                    	model.addRow(new Object [] {i,msg.getSentDate(),address.toString(),msg.getSubject()});
                      }
                   
                }
   
          } 
      catch(Exception mex) 
        {
    	  JOptionPane.showMessageDialog( null ,"inbox empty", "error", JOptionPane.ERROR_MESSAGE);
        }
	}
	
    public void deleteMessage(Object ar)
    {
    	
    	Folder inbox;
		try 
		  {
			
			   inbox = getStore().getFolder("INBOX");
			   inbox.open(Folder.READ_WRITE);
		        
	            int r =  Integer.parseInt( ar.toString());
	          
	            System.out.println(r);
	          
	             Message message = inbox.getMessage(r);
	       
	             message.setFlag(Flags.Flag.DELETED, true);
		    } 
		catch(MessagingException e) 
		    {
			   JOptionPane.showMessageDialog( null ,"Not Deleted", "error", JOptionPane.ERROR_MESSAGE);
		    }
       
       }  
    

}
	



