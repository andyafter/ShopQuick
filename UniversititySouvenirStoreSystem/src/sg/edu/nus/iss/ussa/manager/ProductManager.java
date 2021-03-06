package sg.edu.nus.iss.ussa.manager;

import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.ussa.dataio.ProductIO;
import sg.edu.nus.iss.ussa.domain.Category;
import sg.edu.nus.iss.ussa.domain.Product;
import sg.edu.nus.iss.ussa.domain.Shop;
import sg.edu.nus.iss.ussa.exception.DataFileException;

/*
 * ProductManager
 * @ XIE JIABAO
 */
public class ProductManager {

    private ArrayList<Product> productList;
    private ProductIO productDao;

    public ProductManager(Shop store) throws IOException, DataFileException {
        productDao = new ProductIO(store);
        loadData();
    }

    public ProductManager(ArrayList<Product> list) {
        this.productList = list;
    }

    //load data from file
    public void loadData() throws IOException, DataFileException {
        productList = productDao.loadDataFromFile();
    }

    //save data to file
    public void saveData() throws IOException {
        productDao.saveDataToFile(productList);
    }

    //check the products below thresholds and return list
    public ArrayList<Product> checkInventory() {
        if (productList.size() == 0) {
            return null;
        }
        ArrayList<Product> orderList = new ArrayList<Product>();
        for (int i = 0; i < productList.size(); i++) {
            if (!productList.get(i).getStorageStatus()) {
                orderList.add(productList.get(i));
            }
        }
        return orderList;
    }

    public String getNewProductIdByCategory(String categoryCode) {
        String newId;
        int i = -1;
        int j = -1;
        for (Product p : this.productList) {
            if (categoryCode.equals(p.getCategory().getCode())) {
                if (i == -1) {
                    i = Integer.parseInt(p.getProductId().substring(4)) + 1;
                } else {
                    j = Integer.parseInt(p.getProductId().substring(4)) + 1;
                    if (j > i) {
                        i = j;
                    }
                }
            }
        }
        if (i == -1) {
            newId = categoryCode + "/1";
        } else {
            newId = categoryCode + "/" + Integer.toString(i);
        }
        return newId;
    }

    //add new product or implement product quantity if product exists
    public void addProduct(String id, String name, Category category, String briefDescription,
            int quantityAvailable, float price, String barCode, int threshold, int orderQuantity) {
        Product product = new Product(category, name,
                briefDescription, quantityAvailable, price, barCode, threshold, orderQuantity);
        int i = 0;
        for (; i < this.productList.size(); i++) {
            if (product.compare(productList.get(i))) {
                break;
            }
        }
        if (i >= productList.size()) {
            //add new product
            //String code = category.getCode();
            product.setProductId(id);
            productList.add(product);
        } else {
            //add quantity of existed product
            productList.get(i).addQuantity(quantityAvailable);
        }
    }

    public void modifyProduct(String id, String name, Category category, String briefDescription,
            int quantityAvailable, double price, String barCode, int threshold, int orderQuantity) {
        Product product = new Product(id, category, name, briefDescription, quantityAvailable, price, barCode, threshold, orderQuantity);
        String code = category.getCode();
        for (Product p : this.productList) {
            if (code.equals(p.getCategory().getCode())) {
                this.productList.set(productList.indexOf(p), product);
                break;
            }
        }
    }

    public void deleteProduct(String id) {
        for (int i = 0; i < productList.size(); i++) {
            if (id.equals(productList.get(i).getProductId())) {
                this.productList.remove(i);
                break;
            }
        }
    }

    public void changeQuantity(Product p, int quantity) {
        if (productList.contains(p)) {
            int i = productList.indexOf(p);
            productList.get(i).setQuantity(quantity);
        }

    }

    public Product getProduct(String id) {
        for (int x = 0; x < productList.size(); x++) {
            if (id.equals(productList.get(x).getProductId())) {
                return productList.get(x);
            }
        }
        return null;
    }

    //return the bar code of product
    public Product getProductByBarCode(String barCode) {
        for (int x = 0; x < productList.size(); x++) {
            if (barCode.equals(productList.get(x).getBarCode())) {
                return productList.get(x);
            }
        }
        return null;
    }

    public ArrayList<Product> getProductList() {
        return this.productList;
    }

    
}
