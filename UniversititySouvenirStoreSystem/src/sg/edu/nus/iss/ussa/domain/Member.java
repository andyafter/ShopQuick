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
    
    private Integer MemberID;
    private Integer LoyaltyPoints;

    public Integer getMemberID() {
        return MemberID;
    }

    public Integer getLoyaltyPoints() {
        return LoyaltyPoints;
    }

    public Member(Integer MemberID, Integer LoyaltyPoints, String CustomerName) {
        super(CustomerName);
        this.MemberID = MemberID;
        this.LoyaltyPoints = LoyaltyPoints;
    }
    
    
}
