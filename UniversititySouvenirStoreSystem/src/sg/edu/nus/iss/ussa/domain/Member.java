package sg.edu.nus.iss.ussa.domain;

/**
 * @author Andy Pan
 */
public class Member extends Customer {

    private String memberID;
    private int point;

    public Member(String name, String memberID, int point) {
        super(name);
        this.memberID = memberID;
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String toString() {
        return name + "," + memberID + "," + point;
    }

    @Override
    public String getID() {
        return memberID;
    }
}
