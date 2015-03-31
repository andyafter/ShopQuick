/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.manager;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.io.FileWriter;
import java.io.PrintWriter;

import sg.edu.nus.iss.ussa.domain.Member;

/**
 *
 * @author A0134543L
 */
public class MemberManager{    

    public MemberManager() {
    }
    
    private ArrayList<Member> itemList = new ArrayList<Member>();
    
    
 
      public void SearchMember(String ID){
               ArrayList<String> FileContent = null;
        int flag =0;
        FileManager fm = new FileManager();
        String FileName = "C:\\Users\\A0134543L\\Downloads\\Members.dat.txt";
        try {
           FileContent = fm.loadStringFromFile(FileName);
        }
        catch(IOException ex) {
            System.out.println(
                "Unable to open file '" + 
                FileName + "'");                
        }
        
        //display contents of the array list
        Iterator itr = FileContent.iterator();
        while(itr.hasNext()) {
         String line = itr.next().toString(); //line with commas
         String MemberID = line.split(",")[1];         
           if (MemberID.equals(ID)){             
             flag = 1;
             break;
             //System.out.println("This customer is already present");                        
         }
         else{
               flag =0;             
             
         }              
      }
      
      if (flag==1){ System.out.println("This customer is an existing customer"); }
      if (flag ==0) { // new customer , add him to the file
          String NewCustomerName = "abc";//fetch it from the input
          Integer LoyaltyPoints = -1;
          Member m = new Member(ID,LoyaltyPoints, NewCustomerName );
          try{
          addMember(m);
          }
          catch(Exception e)
          {
                System.out.println(e);
            
          } 
         
          
      }
      
    }
       
    public void addMember(Member m) throws IOException{
        
        PrintWriter writer = new PrintWriter("Members.dat.txt", "UTF-8");
        String Line = m.getCustomerName()+" "+m.getMemberID()+" "+ m.getLoyaltyPoints();
        System.out.println(Line);
        writer.println(Line);        
        writer.close();
    }  
   
        
    
}
