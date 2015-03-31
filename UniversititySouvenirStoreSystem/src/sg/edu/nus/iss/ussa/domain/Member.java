/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.domain;

/**
 *
 * @author a0134449b
 */
public class Member extends Customer {
    
    private String MemberID;
    public int LoyaltyPoints;

    public String getMemberID() {
        return MemberID;
    }

    public int getLoyaltyPoints() {
        return LoyaltyPoints;
    }
   

    public Member(String MemberID, Integer LoyaltyPoints, String CustomerName) {
        super(CustomerName);
        this.MemberID = MemberID;
        this.LoyaltyPoints = LoyaltyPoints;
    }
    
    
}
