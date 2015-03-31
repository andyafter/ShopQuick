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

import sg.edu.nus.iss.ussa.domain.Member;

/**
 *
 * @author A0134543L
 */
public class MemberManager{    

    public MemberManager() {
    }
    
    private ArrayList<Member> itemList = new ArrayList<Member>();

       
    public boolean addMember(Member m) throws IOException{
        ArrayList<String> FileContent = null;
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
         Object line = itr.next(); //line 
         System.out.print(line + " ");
      }
      System.out.println();
        
        //Traverse through each line and check if the name exists, if yes then alert saying it is already added, else add it
       
    
        
        
        return false;
    }
    
    public boolean removeMember(){
        return false;
    }
    
    public boolean updateMember(){
        return false;
    }
    
   
        
    
}
