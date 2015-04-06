package sg.edu.nus.iss.ussa.dataio;

import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.ussa.domain.Member;
import sg.edu.nus.iss.ussa.exception.DataFileException;
import sg.edu.nus.iss.ussa.exception.DataInputException;
import sg.edu.nus.iss.ussa.util.Util;

/**
 * provide Data Access to file for Product entity
 *
 * @author Achyut Suresh Rao
 *
 */
public class MemberIO extends DataIO {

    private static final String fileName = "Members.dat";
    private static final int columns = 3;


    public ArrayList<Member> loadData() throws IOException,
            DataFileException {
        ArrayList<String> stringList = null;

        stringList = super.loadFile(super.getcDatafolderpath()
                + fileName);

        ArrayList<Member> dataList = new ArrayList<Member>();

        StringBuffer errMsg = new StringBuffer();

        for (int lineNo = 0; lineNo < stringList.size(); lineNo++) {

            String line = stringList.get(lineNo);

            String[] fields = line.split(Util.comma);

			// when the No. of fields of a record is less then C_Field_No, skip
            // this record
            if (fields.length != columns) {
                errMsg.append("datafile[" + fileName + "] LineNo:"
                        + (lineNo + 1) + System.getProperty("line.separator"));
                continue;
            }

            try {
                String name = fields[0];
                String memberID = fields[1];
                int loyaltyPoint = Util.strToInt(fields[2]);

                Member member = new Member(name, memberID, loyaltyPoint);

                dataList.add(member);
            } catch (DataInputException e) {
                errMsg.append("datafile[" + fileName + "] LineNo:"
                        + (lineNo + 1) + System.getProperty("line.separator"));
            }
        }

        if (errMsg.length() > 0) {
            String exceptionMsg = "Following data in file is not correct:"
                    + System.getProperty("line.separator") + errMsg;
            throw new DataFileException(exceptionMsg);
        }

        return dataList;
    }

    /**
     *
     * @param dataList
     * @throws IOException
     */
    public void saveDataToFile(ArrayList<Member> dataList) throws IOException {

        ArrayList<String> stringList = new ArrayList<String>();

        for (Member member : dataList) {
            StringBuffer line;

            line = new StringBuffer(member.getName() + Util.comma);
            line.append(member.getMemberID() + Util.comma);
            line.append(member.getPoint());

            stringList.add(line.toString());
        }

        super.saveString(super.getcDatafolderpath() + fileName,
                stringList);

    }

}
