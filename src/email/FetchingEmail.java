package email;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
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
                     inbox.open(Folder.READ_ONLY);
   
             for(int i=inbox.getMessageCount(); i > 0 ;i--)
                {
                    Message msg = inbox.getMessage(i);
   
                    Address[] in = msg.getFrom();
     
                    for(Address address : in) 
                      {
                    	//System.err.println("=======================");
                    	//System.out.println("FROM:" + address.toString());
          
                    	Multipart mp = (Multipart) msg.getContent();
   
                    	BodyPart bp = mp.getBodyPart(0);
                    	//System.out.println("SENT DATE:" + msg.getSentDate());
                    	//System.out.println("SUBJECT:" + msg.getSubject());
                    	//System.out.println("CONTENT:" + bp.getContent());
                    	
                    	
                    	model.addRow(new Object [] {address.toString()});
                      }
                }
   
          } 
      catch(Exception mex) 
        {
    	  mex.printStackTrace();
        }
	}
	
	

}
