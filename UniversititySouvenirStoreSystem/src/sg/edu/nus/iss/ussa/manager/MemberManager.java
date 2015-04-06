package sg.edu.nus.iss.ussa.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import sg.edu.nus.iss.ussa.dataio.MemberIO;
import sg.edu.nus.iss.ussa.domain.Member;
import sg.edu.nus.iss.ussa.exception.DataFileException;

/**
 * @author Achyut Suresh Rao
 */
public class MemberManager {

    private ArrayList<Member> memberList;
    private MemberIO memberDao;

    public MemberManager() throws IOException, DataFileException {

        memberList = new ArrayList<Member>();
        memberDao = new MemberIO();
        readFile();
    }

    public ArrayList<Member> registerMember(String name, String memberID,
            int loyaltyPoint) {

        this.memberList.add(new Member(name, memberID, loyaltyPoint));
        return memberList;
    }

    public void registerMember(Member mem) {
        this.memberList.add(mem);
    }

    public ArrayList<Member> getMemberList() {

        return this.memberList;

    }

    public Member getMember(String memID) {
        Iterator<Member> i = this.memberList.iterator();
        while (i.hasNext()) {
            Member mem = i.next();
            if (mem.getMemberID().equals(memID)) {
                return mem;
            }
        }
        return null;
    }

    public void removeMember(String memID) {
        Member mem = getMember(memID);
        if (mem != null) {
            this.memberList.remove(mem);
        }
    }

    public void modifyMember(String name, String memID, int loyaltyPoint, int index) {
        Member mem = new Member(name, memID, loyaltyPoint);
        this.memberList.set(index, mem);
    }

    public void writeFile() throws IOException {
        memberDao.saveDataToFile(memberList);
    }

    public void readFile() throws IOException, DataFileException {
        memberList = memberDao.loadData();
    }

}
