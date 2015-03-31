/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
 /**
 *
 * @author Ajay
 */
package sg.edu.nus.iss.ussa.application;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.plaf.*;
import sg.edu.nus.iss.ussa.domain.ShopKeeper;
import sg.edu.nus.iss.ussa.gui.Login;
import sg.edu.nus.iss.ussa.gui.StoreMainFrame;
import sg.edu.nus.iss.ussa.manager.FileManager;
public class Store {
    
    private FileManager fmgr;
    private Login Loginbox;
    private StoreMainFrame storeframe;
    public Store()
    {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        fmgr=new FileManager();
        Loginbox=new Login(new ShopKeeper());
        } catch (Exception ex) {
            Logger.getLogger(Store.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    public FileManager getFmgrInstance()
    {
        return this.fmgr;
    }
    
    public  void startup()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Dimension dlgSize = Loginbox.getPreferredSize();
        Loginbox.setLocation((screenSize.width - dlgSize.width) / 2,
                        (screenSize.height - dlgSize.height) / 2);

        Loginbox.setModal(true);
        Loginbox.pack();
   
        Loginbox.setVisible(true);
        
         if (Loginbox.getUser().getUserName() == null) {
            System.exit(0);
        }
         storeframe=new StoreMainFrame();
         
    }
    public static void main(String[] args)
    {
        Store obj=new Store();
        obj.startup();
    }
}
