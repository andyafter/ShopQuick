package sg.edu.nus.iss.ussa.dataio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import sg.edu.nus.iss.ussa.domain.Discount;
import sg.edu.nus.iss.ussa.manager.DiscountManager;
import sg.edu.nus.iss.ussa.domain.MemberDiscount;
import sg.edu.nus.iss.ussa.domain.UsualDiscount;
import sg.edu.nus.iss.ussa.exception.DataFileException;
import sg.edu.nus.iss.ussa.exception.DataInputException;
import sg.edu.nus.iss.ussa.util.Util;

public class DiscountIO extends DataIO {

    private static final String C_File_Name = "Discounts.dat";
    private static final int C_Field_No = 6;
	//private Discount discount;

    public ArrayList<Discount> loadDataFromFile() throws IOException, DataFileException {
        ArrayList<String> stringList = null;

        stringList = super.loadStringFromFile(super.getcDatafolderpath() + C_File_Name);

        ArrayList<Discount> discountList = new ArrayList<Discount>();

        StringBuffer errMsg = new StringBuffer();

        for (int lineNo = 0; lineNo < stringList.size(); lineNo++) {

            String line = stringList.get(lineNo);

            String[] fields = line.split(Util.C_Separator);

            // when the No. of fields of a record is less then C_Field_No, skip this record
            if (fields.length != C_Field_No) {
                errMsg.append("datafile[" + C_File_Name + "] LineNo:" + (lineNo + 1) + System.getProperty("line.separator"));
                continue;
            }

            try {
                String discountCode = fields[0];
                String discountDescription = fields[1];
                Date startDate;
                if (!fields[2].equalsIgnoreCase("ALWAYS")) {
                    startDate = Util.castDate(fields[2]);
                } else {
                    startDate = new Date();
                }

                int period = 0;
                if (!fields[3].equalsIgnoreCase("ALWAYS")) {
                    period = Util.castInt(fields[3]);
                }

                double percent = Util.castDouble(fields[4]);
                String Applicable = (fields[5]);

                Discount discount;
                if (Applicable.contains("M")) {

                    discount = new MemberDiscount(discountCode, discountDescription, startDate, period, percent, Applicable);
                } else {
                    discount = new UsualDiscount(discountCode, discountDescription, startDate, period, percent, Applicable);
                }

                discountList.add(discount);

            } catch (DataInputException e) {
                errMsg.append("datafile[" + C_File_Name + "] LineNo:" + (lineNo + 1) + System.getProperty("line.separator"));
            }
        }

        if (errMsg.length() > 0) {
            String exceptionMsg = "Following data in file is not correct:" + System.getProperty("line.separator") + errMsg;
            throw new DataFileException(exceptionMsg);
        }

        return discountList;

    }

    public void saveDataToFile(ArrayList<Discount> discountList) throws IOException {

        ArrayList<String> stringList = new ArrayList<String>();

        for (Discount discount : discountList) {
            //setDiscount(iterator.next());
            StringBuffer line;

            line = new StringBuffer(discount.getDiscountcode() + Util.C_Separator);
            line.append(discount.getDiscountDescription() + Util.C_Separator);
            //line.append(Util.dateToString(discount.getStartDate()) + Util.C_Separator);
            line.append(Util.dateToString(discount.getStartDate()) + Util.C_Separator);
            line.append(discount.getPeriod() + Util.C_Separator);
            line.append(discount.getPercent() + Util.C_Separator);
            line.append(discount.getApplicable());

            stringList.add(line.toString());
        }

        super.saveStringToFile(super.getcDatafolderpath() + C_File_Name, stringList);

    }

    public static void main(String[] arg) {
		//DiscountDao testDao = new DiscountDao();
        //	ArrayList<Discount> discList = new ArrayList<Discount>();
        double mDisc;

        try {
            //discList=testDao.loadDataFromFile();
            DiscountManager discountMgrObject = new DiscountManager();

            mDisc = discountMgrObject.getMaxDiscount("Public", 1);
            /*	discList=discountMgrObject.getDiscountlist();
             for(Discount d:discList)
             {
             System.out.println(d.getDiscountcode()+","+d.getDiscountDescription()+","+
             d.getStartDate()+","+d.getPeriod()+","+d.getApplicable());
		
             }*/

            System.out.println(mDisc);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DataFileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
