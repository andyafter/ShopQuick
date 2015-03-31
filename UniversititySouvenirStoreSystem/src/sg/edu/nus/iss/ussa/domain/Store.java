/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.domain;
import sg.edu.nus.iss.ussa.manager.MemberManager;
import java.util.ArrayList;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

/**
 *
 * @author A0134543L
 */
public class Store {
    public static void main(String args[]) throws IOException {
        MemberManager mm = new MemberManager();
        Member member = new Member("3535",33, "Suraj1");
        mm.SearchMember("3535");
       /* try {
            mm.addMember(member);
        } catch (IOException ex) {
            Logger.getLogger(MemberManager.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
 }
}
    
    




