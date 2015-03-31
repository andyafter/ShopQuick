/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//functions ---add member, delete member, update

package sg.edu.nus.iss.ussa.manager;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;

import sg.edu.nus.iss.ussa.domain.Member;

/**
 *
 * @author A0134543L
 */
public class MemberManager {

    private String FileName = "C:\\Users\\A0134543L\\Downloads\\Members.dat.txt";
    String MemberID = "";

    public MemberManager() {
    }

    private ArrayList<Member> itemList = new ArrayList<Member>();

    public void SearchMember(String ID) throws ArrayIndexOutOfBoundsException {
        ArrayList<String> FileContent = null;
        int flag = 0;
        FileManager fm = new FileManager();

        try {
            FileContent = fm.loadStringFromFile(FileName);
        } catch (IOException ex) {
            System.out.println(
                    "Unable to open file '"
                    + FileName + "'");
        }

        //display contents of the array list
        Iterator itr = FileContent.iterator();
        while (itr.hasNext()) {
            String line = itr.next().toString(); //line with commas
            System.out.println("Line =" + line);
            try {
                MemberID = line.split(",")[1];
            } catch (ArrayIndexOutOfBoundsException A) {
                System.out.println(A);
            }
            System.out.println("MemberID =" + MemberID);
            if (MemberID.equals(ID)) {
                flag = 1;
                break;
                //System.out.println("This customer is already present");                        
            } else {
                flag = 0;

            }
        }

        if (flag == 1) {
            System.out.println("This customer is an existing customer");
        }
        if (flag == 0) { // new customer , add him to the file
            String NewCustomerName = "abc";//fetch it from the input
            Integer LoyaltyPoints = -1;
            Member m = new Member(ID, LoyaltyPoints, NewCustomerName);
            try {
                addMember(m);
            } catch (Exception e) {
                System.out.println(e);

            }

        }

    }

    public void addMember(Member m) throws IOException {
        try {
            FileWriter fileWritter = new FileWriter(FileName, true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            String Line = m.getCustomerName() + "," + m.getMemberID() + "," + m.getLoyaltyPoints();
            bufferWritter.append('\n');
            bufferWritter.append('\n' + Line + '\n');
            bufferWritter.close();
        } catch (IOException ex) {
            System.out.println(ex);

        }
    }

    public void deleteMember(Member m) {

        File file = new File(FileName);

        File file2 = new File(file.getParent() + "\\temp" + file.getName());
        PrintWriter pw = null;
        Scanner read = null;

        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel src = null;
        FileChannel dest = null;
        String line = m.getCustomerName() + "," + m.getMemberID() + "," + m.getLoyaltyPoints();

        try {

            pw = new PrintWriter(file2);
            read = new Scanner(file);

            while (read.hasNextLine()) {

                String currline = read.nextLine();

                if (line.equalsIgnoreCase(currline)) {
                    continue;
                } else {
                    pw.println(currline);
                }
            }

            pw.flush();

            fis = new FileInputStream(file2);
            src = fis.getChannel();
            fos = new FileOutputStream(file);
            dest = fos.getChannel();

            dest.transferFrom(src, 0, src.size());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pw.close();
            read.close();

            try {
                fis.close();
                fos.close();
                src.close();
                dest.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (file2.delete()) {
                System.out.println("File is deleted");
            } else {
                System.out.println("Error occured! File: " + file2.getName() + " is not deleted!");
            }
        }

    }
    
    public void updateLoyaltyPoints(Member m, int point) {
        
        //update in the file
        
        File file = new File(FileName);

        File file2 = new File(file.getParent() + "\\temp" + file.getName());
        PrintWriter pw = null;
        Scanner read = null;

        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel src = null;
        FileChannel dest = null;
        String line = m.getCustomerName() + "," + m.getMemberID() + "," + m.getLoyaltyPoints();

        try {

            pw = new PrintWriter(file2);
            read = new Scanner(file);

            while (read.hasNextLine()) {

                String currline = read.nextLine();

                if (line.equalsIgnoreCase(currline)) { 
                    int UpdatedPoints = m.getLoyaltyPoints()+point;
                    String UpdatedLineWithLoyaltyPoints = m.getCustomerName() + "," + m.getMemberID() + "," +UpdatedPoints;
                    pw.println(UpdatedLineWithLoyaltyPoints);
                    continue;
                } else {
                    pw.println(currline);
                }
            }

            pw.flush();

            fis = new FileInputStream(file2);
            src = fis.getChannel();
            fos = new FileOutputStream(file);
            dest = fos.getChannel();

            dest.transferFrom(src, 0, src.size());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pw.close();
            read.close();

            try {
                fis.close();
                fos.close();
                src.close();
                dest.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (file2.delete()) {
                System.out.println("File is deleted");
            } else {
                System.out.println("Error occured! File: " + file2.getName() + " is not deleted!");
            }
        }

    }

}
