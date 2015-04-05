//TransactionDao.java
package sg.edu.nus.iss.ussa.dataio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import sg.edu.nus.iss.ussa.domain.Customer;
import sg.edu.nus.iss.ussa.domain.Product;
import sg.edu.nus.iss.ussa.domain.Public;
import sg.edu.nus.iss.ussa.domain.Shop;
import sg.edu.nus.iss.ussa.domain.Transaction;
import sg.edu.nus.iss.ussa.exception.DataFileException;
import sg.edu.nus.iss.ussa.exception.DataInputException;
import sg.edu.nus.iss.ussa.util.Util;

/**
 * provide Data Access to file for Product entity
 *
 * @author Andy Pan
 */
public class TransactionIO extends DataIO {

    private static final String fileName = "Transactions.dat";
    private static final int columns = 6;
    private Shop shop;

    public TransactionIO(Shop shop) {
        this.shop = shop;
    }

    public ArrayList<Transaction> loadData() throws IOException,
            DataFileException {
        ArrayList<String> stringList = null;
        int flag = 0;
        Transaction tflag = new Transaction();
        stringList = super.loadFile(super.getcDatafolderpath()
                + fileName);
        ArrayList<Transaction> dataList = new ArrayList<Transaction>();
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
                int id = Util.strToInt(fields[0]);
                String productID = fields[1];
                String customerID = fields[2];
                int qty = Util.strToInt(fields[3]);
                Date date = Util.strToDate(fields[4]);
                double price = Util.strToDouble(fields[5]);
				//System.out.println("Dao" + date);
                //ProductMgr pm = new ProductMgr();
                Product product = shop.getProductById(productID);
                if (flag == id) {
                    //TransactionItem ti = new TransactionItem(productID, qty);
                    tflag.addItem(product, price, qty);
                    // System.out.println("1");
                } else {
                    Customer customer;
                    if (customerID.equalsIgnoreCase("PUBLIC")) {
                        customer = new Public();
                    } else {
                        customer = shop.getMemberById(customerID);
                    }

                    Transaction t = new Transaction(id, customer, date);
                    t.addItem(product, price, qty);
                    dataList.add(t);
                    tflag = t;
                    // System.out.println("2");
                }
                flag = id;
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
        // System.out.println(dataList.size());
        return dataList;
    }

    public void saveData(ArrayList<Transaction> dataList)
            throws IOException {
        ArrayList<String> stringList = new ArrayList<String>();
        for (int i = 0; i < dataList.size(); i++) {
            for (int j = 0; j < dataList.get(i).getItemList().size(); j++) {
                StringBuffer line;
                line = new StringBuffer(dataList.get(i).getId() + Util.comma);
                line.append(dataList.get(i).getItemList().get(j).getProduct().getProductId() + Util.comma);
                line.append(dataList.get(i).getCustomer().getID() + Util.comma);
                line.append(dataList.get(i).getItemList().get(j).getQty() + Util.comma);
                line.append(Util.dateToString(dataList.get(i).getDate()) + Util.comma);
                line.append(dataList.get(i).getItemList().get(j).getPrice());
                stringList.add(line.toString());
            }
        }
        super.saveString(super.getcDatafolderpath() + fileName,
                stringList);
    }
}///~
