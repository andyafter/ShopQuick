package sg.edu.nus.iss.ussa.dataio;

import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.ussa.domain.Category;
import sg.edu.nus.iss.ussa.domain.Product;
import sg.edu.nus.iss.ussa.domain.Shop;
import sg.edu.nus.iss.ussa.exception.DataFileException;
import sg.edu.nus.iss.ussa.exception.DataInputException;
import sg.edu.nus.iss.ussa.util.Util;

/**
 * provide Data Access to file for Product entity
 *
 * @author Xu Minsheng
 *
 */
public class ProductIO extends DataIO {

    private static final String fileName = "Products.dat";
    private static final int columns = 8;

    private Shop shop;

    /**
     *
     */
    public ProductIO(Shop shop) {
        this.shop = shop;
    }

    
    public ArrayList<Product> loadDataFromFile() throws IOException, DataFileException {
        ArrayList<String> stringList = null;

        stringList = super.loadFile(super.getcDatafolderpath() + fileName);

        ArrayList<Product> dataList = new ArrayList<Product>();

        StringBuffer errMsg = new StringBuffer();

        for (int lineNo = 0; lineNo < stringList.size(); lineNo++) {

            String line = stringList.get(lineNo);

            String[] fields = line.split(Util.comma);

            // when the No. of fields of a record is less then C_Field_No, skip this record
            if (fields.length != columns) {
                errMsg.append("datafile[" + fileName + "] LineNo:" + (lineNo + 1) + System.getProperty("line.separator"));
                continue;
            }

            try {
                String productId = fields[0];
                String categoryCode = productId.substring(0, 3);
                Category category = shop.getCategoryByCode(categoryCode);

                String name = fields[1];
                String briefDescription = fields[2];
                int quantityAvaible = Util.strToInt(fields[3]);
                double price = Util.strToDouble(fields[4]);
                String barCodeNumber = fields[5];
                int reorderQuantity = Util.strToInt(fields[6]);
                int orderQuantity = Util.strToInt(fields[7]);

                Product product = new Product(productId, category, name, briefDescription,
                        quantityAvaible, price, barCodeNumber, reorderQuantity, orderQuantity);

                dataList.add(product);
            } catch (DataInputException e) {
                errMsg.append("datafile[" + fileName + "] LineNo:" + (lineNo + 1) + System.getProperty("line.separator"));
            }
        }

        if (errMsg.length() > 0) {
            String exceptionMsg = "Following data in file is not correct:" + System.getProperty("line.separator") + errMsg;
            throw new DataFileException(exceptionMsg);
        }

        return dataList;
    }

    /**
     *
     * @param dataList
     * @throws IOException
     */
    public void saveDataToFile(ArrayList<Product> dataList) throws IOException {

        ArrayList<String> stringList = new ArrayList<String>();

        for (Product product : dataList) {
            StringBuffer line;

            line = new StringBuffer(product.getProductId() + Util.comma);
            line.append(product.getProductName() + Util.comma);
            line.append(product.getDescription() + Util.comma);
            line.append(product.getQuantity() + Util.comma);
            line.append(product.getPrice() + Util.comma);
            line.append(product.getBarCode() + Util.comma);
            line.append(product.getReorderQuantity() + Util.comma);
            line.append(product.getOrderQuantity());

            stringList.add(line.toString());
        }

        super.saveString(super.getcDatafolderpath() + fileName, stringList);

    }

}
