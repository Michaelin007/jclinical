/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bigg;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;


import javax.inject.Named;
import javax.mail.MessagingException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Michael
 */
@Named 
@ManagedBean 
@RequestScoped
public class AskD implements Serializable {
    String question;
    String name;
    String emailadd;
    String re;
     private static final String USERNAME    = "root";
    private static final String PASSWORD    = "z1DVzUsPhn";
    private static final String CONN_STRING = "jdbc:mysql://node224124-env-7413922.j.layershift.co.uk/ask";
    /* private static final String USERNAME    = "root";
    private static final String PASSWORD    = "";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/askan?zeroDateTimeBehavior=convertToNull";*/
    
    public String getName(){
        return name;
    }
    public void setName(String namex){
        name=namex;
    }
    public String getQuestion(){
        return question;
    }
    public void setQuestion(String ques){
        question=ques;
    }
     public String getEmailadd(){
        return emailadd;
    }
    public void setEmailadd(String quest){
        emailadd=quest;
    }
     public String getRe(){
        //String i=(Double.toString((double) height));
        return re;
    }
  
    public void setRe(String rr1){
        re=rr1;
    }
    
    public String  sendmail() throws NamingException, SQLException {
       /*  try {
            SendM mike= new SendM();
            mike.sendMessage(name, emailadd, question);
     re="Message sent";
}       catch (MessagingException ex) {
           Logger.getLogger(AskD.class.getName()).log(Level.SEVERE, null, ex);
           re="Message not sent";
            
        }
        return null;
    }*/
      // Connection conn=null;

                  try {
                      //conn =(Connection) DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
                     // databas.setText("Connected");
                     InitialContext ctx=new InitialContext();
                     DataSource ds= (DataSource)ctx.lookup("askclinical");
                        Connection conn=ds.getConnection();
                      Statement ad=(Statement) conn.createStatement();
                      String p_name= name;
                      String emi=emailadd;
                      String questions=question;
                      String insert= "INSERT INTO ask.client(name,emailadd,question) VALUES('"+p_name+"','"+emi+"',+'"+questions+"')";
                      ad.executeUpdate(insert);
                      re= "Successfully Saved into database ";
                  } catch (SQLException ex) {
                      Logger.getLogger(AskD.class.getName()).log(Level.SEVERE, null, ex);
                      //databas.setText("Not connected");
                      re= "Not Save, try again ";
                      
                  }
                  
         return null;
    }
   
  

     public void clear(AjaxBehaviorEvent event)
            throws AbortProcessingException {
        name = "";
        emailadd = "";
        question = "";
        
    }


}  

