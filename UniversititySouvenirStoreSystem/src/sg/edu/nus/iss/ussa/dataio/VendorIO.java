package sg.edu.nus.iss.ussa.dataio;

import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.ussa.domain.Category;
import sg.edu.nus.iss.ussa.domain.Vendor;
import sg.edu.nus.iss.ussa.exception.DataFileException;
import sg.edu.nus.iss.ussa.util.Util;

public class VendorIO extends DataIO {

    // datafile name

    private static final String filePrefix = "Vendors";
    private static final String fileSuffix = ".dat";

    private static final int columns = 2;


    public ArrayList<Vendor> loadData(ArrayList<Category> cateList) throws IOException, DataFileException {

        for (Category category : cateList) {

            String filename = filePrefix + category.getCode() + fileSuffix;
            ArrayList<String> stringList = null;
            stringList = super.loadFile(super.getcDatafolderpath() + filename);
            ArrayList<Vendor> vendorOfCategoryList = new ArrayList<Vendor>();
            StringBuffer errMsg = new StringBuffer();
            for (int lineNo = 0; lineNo < stringList.size(); lineNo++) {
                String line = stringList.get(lineNo);
                String[] fields = line.split(Util.comma);
                if (fields.length != columns) {
                    errMsg.append("datafile[" + filename + "] LineNo:" + (lineNo + 1) + System.getProperty("line.separator"));
                    continue;
                }

                String name = fields[0];
                String description = fields[1];

                Vendor vendor = new Vendor(name, description);

                //add to categories' VendorList
                vendorOfCategoryList.add(vendor);

				//add to store's VendorList(non-duplicate)
                //boolean isExist = false;
                //check whether vendor with same name already exist
                //for(int i=0; i<allVendorList.size() ;i++){
                //	if(name.equals(allVendorList.get(i).getName()))isExist = true;
                //}
                //add to store's VendorList(non-duplicate)
                //if( !isExist )allVendorList.add(vendor);
            }

            // set VendorList to related category
            category.setVendorList(vendorOfCategoryList);

            if (errMsg.length() > 0) {
                String exceptionMsg = "Following data in file is not correct:" + System.getProperty("line.separator") + errMsg;
                throw new DataFileException(exceptionMsg);
            }

        }
        return null;
        //return allVendorList;
    }

    /**
     * get Vendors from category and save them to file
     *
     * @param dataList
     * @throws IOException
     */
    public void saveDataToFile(ArrayList<Category> categoryList) throws IOException {

        for (Category category : categoryList) {

            String filename = filePrefix + category.getCode() + fileSuffix;

            ArrayList<String> stringList = new ArrayList<String>();

            for (Vendor vendor : category.getVendorList()) {
                StringBuffer line;

                line = new StringBuffer(vendor.getName() + Util.comma);
                line.append(vendor.getDescription());

                stringList.add(line.toString());
            }

            super.saveString(super.getcDatafolderpath() + filename, stringList);
        }
    }

}
