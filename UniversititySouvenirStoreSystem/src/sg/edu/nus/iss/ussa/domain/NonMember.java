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
public class NonMember extends Customer {
    
    private Integer NonMemberName;

    public Integer getNonMemberName() {
        return NonMemberName;
    }

    public NonMember(Integer NonMemberName, String CustomerName) {
        super(CustomerName);
        this.NonMemberName = NonMemberName;
    }
    

    
}
