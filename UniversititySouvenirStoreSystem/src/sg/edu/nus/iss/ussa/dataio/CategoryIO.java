package sg.edu.nus.iss.ussa.dataio;

import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.ussa.domain.Category;
import sg.edu.nus.iss.ussa.exception.DataFileException;
import sg.edu.nus.iss.ussa.util.Util;

/**
 *
 * @author Xu Minsheng
 *
 */
public class CategoryIO extends DataIO {

    private static final String fileName = "Category.dat";
    private static final int columns = 2;

    public ArrayList<Category> loadData() throws IOException, DataFileException {
        ArrayList<String> stringList = null;

        stringList = super.loadFile(super.getcDatafolderpath() + fileName);

        ArrayList<Category> dataList = new ArrayList<Category>();

        StringBuffer errMsg = new StringBuffer();

        for (int lineNo = 0; lineNo < stringList.size(); lineNo++) {

            String line = stringList.get(lineNo);

            String[] fields = line.split(Util.comma);

            // when the No. of fields of a record is less then C_Field_No, skip this record
            if (fields.length != columns) {
                errMsg.append("datafile[" + fileName + "] LineNo:" + (lineNo + 1) + System.getProperty("line.separator"));
                continue;
            }

            String code = fields[0];
            String name = fields[1];

            Category category = new Category(code, name, null);

            dataList.add(category);
        }

        if (errMsg.length() > 0) {
            String exceptionMsg = "Following data in file is not correct:" + System.getProperty("line.separator") + errMsg;
            throw new DataFileException(exceptionMsg);
        }

        return dataList;
    }


    public void saveDataToFile(ArrayList<Category> dataList) throws IOException {

        ArrayList<String> stringList = new ArrayList<String>();

        for (Category category : dataList) {
            StringBuffer line;

            line = new StringBuffer(category.getCode() + Util.comma);
            line.append(category.getName());

            stringList.add(line.toString());
        }

        super.saveString(super.getcDatafolderpath() + fileName, stringList);

    }

}
