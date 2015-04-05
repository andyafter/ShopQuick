package sg.edu.nus.iss.ussa.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import sg.edu.nus.iss.ussa.domain.Category;
import sg.edu.nus.iss.ussa.domain.Discount;
import sg.edu.nus.iss.ussa.domain.Member;
import sg.edu.nus.iss.ussa.domain.Product;
import sg.edu.nus.iss.ussa.domain.Shop;
import sg.edu.nus.iss.ussa.domain.Transaction;
import sg.edu.nus.iss.ussa.domain.Vendor;
import sg.edu.nus.iss.ussa.exception.DataFileException;
import sg.edu.nus.iss.ussa.exception.DataNotFoundException;
import sg.edu.nus.iss.ussa.gui.LoginForm;
import sg.edu.nus.iss.ussa.gui.ShopBase;

/**
 * Main method here
 *
 * @author Andy Pan
 *
 */
public class Shopping {

    private Shop shop;
    private LoginForm loginScreen;
    private ShopBase shopWin;

    public static void main(String[] args) {
        Shopping shopping = new Shopping();
        shopping.startup();

    }

    public Shopping() {
        // instantiate attributes	
        try {
            // instantiate store & load date
            shop = new Shop();
        } catch (IOException | DataFileException e) {

            e.printStackTrace();
            System.exit(0);
        }
    }

    public void startup() {
        // show login screen
        loginScreen = new LoginForm(this);
        loginScreen.setLocationRelativeTo(null);
        loginScreen.setVisible(true);
    }

    public void shutdown() {
        try {
            shop.saveData();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }

    public boolean login(String username, String password) {

        if (shop.login(username, password) == true) {

            loginScreen.dispose();

            shopWin = new ShopBase(this);
            shopWin.setVisible(true);
            return true;
        } else {
            return false;
        }
    }

    public Transaction checkOut() {
        return shop.checkout();
    }

    public Transaction setMemTransaction(Transaction transaction, String memberId) {
        try {
            transaction = shop.setBillCustomer(transaction, memberId);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return transaction;
    }

    public Transaction addTransactionItem(Transaction transaction, String productId, int quantity) {
        try {
            transaction = shop.addBillItem(transaction, productId, quantity);
        } catch (DataNotFoundException e) {
			// UI action

            e.printStackTrace();
        }
        return transaction;
    }

    public Transaction removeTransactionItem(Transaction transaction, String productId) {
        transaction = shop.removeBillItem(transaction, productId);
        return transaction;
    }

    public Transaction makePayment(Transaction transaction, double cash, int redeemLoyaltyPoint) {
        return this.shop.setPayment(transaction, cash, redeemLoyaltyPoint);
    }

    public Transaction confirmPayment(Transaction transaction) {
        return shop.confirmPayment(transaction);
    }

    public String Cate2ProdID(String code) {
        return shop.getNewProductIdByCategory(code);
    }

    public void addProduct(String id, String name, String categoryCode, String briefDescription,
            int quantityAvailable, float price, String barCode, int threshold, int orderQuantity) {
        shop.addProduct(id, name, categoryCode, briefDescription, quantityAvailable, price, barCode, threshold, orderQuantity);
    }


    public void modifyProduct(String id, String name, String categoryCode, String briefDescription,
            int quantityAvailable, float price, String barCode, int threshold, int orderQuantity) {
        shop.modifyProduct(id, name, categoryCode, briefDescription, quantityAvailable, price, barCode, threshold, orderQuantity);
    }

    /**
     *
     * @param index
     */
    public void deleteProduct(String id) {
        shop.deleteProduct(id);
    }


    public ArrayList<Product> getProductList() {
        return shop.getProductList();
    }


    public Product getProductById(String productId) {
        return shop.getProductById(productId);
    }

    /**
     *
     * @param barCode
     * @return
     */
    public Product barCodeToProd(String barCode) {
        return shop.getProductByBarCode(barCode);
    }

    /**
     *
     * @param memberId
     * @return
     */
    public Member IdToMember(String memberId) {
        return shop.getMemberById(memberId);
    }

    /**
     *
     * @return
     */
    public ArrayList<Member> getMemberList() {
        return shop.getMemberList();
    }

    /**
     *
     * @return
     */
    public void deleteMember(String memberID) {
        shop.removeMember(memberID);
    }

    public void newMember(Member mem) {
        shop.registerMember(mem);
    }

    public void newMember(String name, String id, int loyalty) {
        shop.registerMember(name, id, loyalty);
    }

    public void changeMember(String name, String memID, int loyaltyPoint, int index) {
        shop.modifyMember(name, memID, loyaltyPoint, index);
    }

    public ArrayList<Category> getCategoryList() {
        return shop.getCategoryList();
    }

    public ArrayList<Transaction> getTransactionList() {
        return shop.getTransactionList();
    }


    public Category codeToCate(String code) {
        return shop.getCategoryByCode(code);
    }


    public void addCategory(String code, String name, ArrayList<Vendor> vendorList) {
        shop.addCategory(code, name, vendorList);
    }


    public void changeCate(String code, String name) {
        shop.updCategory(code, name);
    }

    /**
     *
     * @param code
     */
    public void deleteCate(String code) {
        shop.delCategoryByCode(code);
    }

    public ArrayList<Discount> getDiscountList() {
        return shop.getDiscountList();

    }

    public ShopBase getShopWindow() {
        return shopWin;
    }

    public Discount get(int index) {

        return null;
    }

    public void addDiscount(String discountCode, String discountDescription,
            Date startDate, int period, double percent, String Applicable) {
        shop.addDiscount(discountCode, discountDescription, startDate, period, percent, Applicable);
    }

}
