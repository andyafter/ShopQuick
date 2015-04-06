package sg.edu.nus.iss.ussa.domain;

import sg.edu.nus.iss.ussa.manager.ProductManager;
import sg.edu.nus.iss.ussa.manager.TransactionManager;
import sg.edu.nus.iss.ussa.manager.StoreKeeperManager;
import sg.edu.nus.iss.ussa.manager.DiscountManager;
import sg.edu.nus.iss.ussa.manager.MemberManager;
import sg.edu.nus.iss.ussa.manager.CategoryManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import sg.edu.nus.iss.ussa.exception.DataFileException;
import sg.edu.nus.iss.ussa.exception.DataNotFoundException;

/**
 *
 * @author Xu Minsheng
 *
 */
public class Shop {



    public Shop() throws IOException, DataFileException {
        storekeeperMgr = new StoreKeeperManager();
        categoryMgr = new CategoryManager();
        memberMgr = new MemberManager();
        discountMgr = new DiscountManager();
        productMgr = new ProductManager(this);
        transactionMgr = new TransactionManager(this);
    }

    /**
     * @throws IOException
     *
     */
    public void saveData() throws IOException {
        memberMgr.writeFile();
        transactionMgr.save();
        productMgr.saveData();
        categoryMgr.saveData();
        discountMgr.saveData();
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username, String password) {
        return storekeeperMgr.checkAuthority(username, password);
    }

//  -------------------- transaction related methods	-------------------
    /**
     *
     */
    public Transaction checkout() {
        Transaction transaction = new Transaction();
        transaction.setCustomer(new Public(""));
        Discount discount = discountMgr.getMaxDiscount(transaction.getCustomer());
        transaction.setDiscount(discount);
        return transaction;
    }

    public ArrayList<Transaction> getTransactionList() {
        return transactionMgr.getTransactionList();
    }

    /**
     * set Customer info. and according to it, get Highest Discount
     *
     * @param transaction
     * @param memberId
     * @return
     * @throws DataNotFoundException
     */
    public Transaction setBillCustomer(Transaction transaction, String memberId) throws DataNotFoundException {

        Customer customer;
        Discount discount;

        // get customer info
        if (memberId == null) {
            customer = new Public("");
        } else {
            customer = memberMgr.getMember(memberId);
            if (customer == null) {
                throw new DataNotFoundException("Member", memberId);
            }
        }

        // get max discount
        discount = discountMgr.getMaxDiscount(customer);

        //set customer and discount to transaction
        transaction.setCustomer(customer);
        transaction.setDiscount(discount);

        return transaction;
    }

    /**
     *
     * @param transaction
     * @param productId
     * @param quantity
     * @return
     * @throws DataNotFoundException
     */
    public Transaction addBillItem(Transaction transaction, String productId, int quantity) throws DataNotFoundException {

        Product product = productMgr.getProduct(productId);

        if (product == null) {
            throw new DataNotFoundException("Product", productId);
        }

        transaction.addItem(product, quantity);

        return transaction;
    }

    /**
     *
     * @param transaction
     * @param productId
     * @return
     */
    public Transaction removeBillItem(Transaction transaction, String productId) {

        Product product = productMgr.getProduct(productId);

        transaction.removeItem(product);

        return transaction;
    }

    /**
     *
     * @param transaction
     * @param cash
     * @param usedPoints
     * @return
     */
    public Transaction setPayment(Transaction transaction, double cash, int usedPoints) {
        transaction.setUsedPoints(usedPoints);
        transaction.setCashAmount(cash);
        return transaction;
    }

    /**
     *
     */
    public Transaction confirmPayment(Transaction transaction) {

		// verification product
        // add to transaction list
        transaction.setId(transactionMgr.getMaxId() + 1);
        transaction.setDate(new Date());
        transactionMgr.addTransaction(transaction);

        // update product's quantity
        ArrayList<CartItem> itemList = transaction.getItemList();
        for (CartItem item : itemList) {
            productMgr.changeQuantity(item.getProduct(), item.getProduct().getQuantity() - item.getQty());
        }

        // update Member's loyalty point
        Customer customer = transaction.getCustomer();
        if (customer instanceof Member) {
            Member member = (Member) customer;
            int originalPoint = member.getPoint();
            int currentPoint = originalPoint
                    - transaction.getUsedPoints() + transaction.calcGainedPoint();
            member.setPoint(currentPoint);
        }

        return transaction;
    }


    public ArrayList<Transaction> getTransactionByDate(Date date) {
        return transactionMgr.getTransactionListByDate(date);
    }


    public void registerMember(String name, String memberId, int loyalty) {
        memberMgr.registerMember(name, memberId, loyalty);
    }

    public void registerMember(Member mem) {
        memberMgr.registerMember(mem);
    }


    public Member getMemberById(String memberId) {
        return memberMgr.getMember(memberId);
    }


    public ArrayList<Member> getMemberList() {
        return memberMgr.getMemberList();
    }

    public void removeMember(String memberID) {
        memberMgr.removeMember(memberID);
    }

    public void modifyMember(String name, String memID, int point, int index) {
        memberMgr.modifyMember(name, memID, point, index);
    }

    public Member getMemberByID(String memID) {
        return memberMgr.getMember(memID);
    }

//  -------------------- product related methods	-------------------
    /**
     *
     * @param name
     * @param categoryCode
     * @param briefDescription
     * @param quantityAvailable
     * @param price
     * @param barCode
     * @param threshold
     * @param orderQuantity
     */
    public String getNewProductIdByCategory(String code) {
        return productMgr.getNewProductIdByCategory(code);
    }

    public void addProduct(String id, String name, String categoryCode, String briefDescription,
            int quantityAvailable, float price, String barCode, int threshold, int orderQuantity) {

        productMgr.addProduct(id, name, categoryMgr.getCategoryByCode(categoryCode),
                briefDescription, quantityAvailable, price, barCode, threshold, orderQuantity);
    }

    /**
     *
     * @param product
     * @param indenx
     */
    public void modifyProduct(String id, String name, String categoryCode, String briefDescription,
            int quantityAvailable, float price, String barCode, int threshold, int orderQuantity) {
        productMgr.modifyProduct(id, name, categoryMgr.getCategoryByCode(categoryCode), briefDescription, quantityAvailable, price, barCode, threshold, orderQuantity);

    }

    /**
     *
     * @param index
     */
    public void deleteProduct(String id) {
        productMgr.deleteProduct(id);
    }

    /**
     *
     * @return
     */
    public ArrayList<Product> getProductList() {
        return productMgr.getProductList();
    }

    /**
     *
     * @return
     */
    public Product getProductById(String productId) {
        return productMgr.getProduct(productId);
    }

    /**
     *
     * @param barCode
     * @return
     */
    public Product getProductByBarCode(String barCode) {
        return productMgr.getProductByBarCode(barCode);
    }

    


    public void addCategory(String code, String name, ArrayList<Vendor> vendorList) {
        categoryMgr.addCategory(code, name, vendorList);
    }


    public void updCategory(String code, String name) {
        categoryMgr.updCategory(code, name);
    }


    public void delCategoryByCode(String code) {
        categoryMgr.delCategoryByCode(code);
    }

    public void setCategoryList(ArrayList<Category> categoryList) {
        categoryMgr.setCategoryList(categoryList);
    }


    public ArrayList<Category> getCategoryList() {
        return categoryMgr.getCategoryList();
    }


    public Category getCategoryByCode(String code) {
        if (getCategoryList().size() == 0) {
            return null;
        }
        return categoryMgr.getCategoryByCode(code);
    }

    public void addDiscount(String discountCode, String discountDescription,
            Date startDate, int period, double percent, String Applicable) {

        discountMgr.registerDiscount(discountCode, discountDescription, startDate, period, percent, Applicable);
    }


    public void addDiscount(Discount discount) {
        discountMgr.getdiscountList();
    }

    public void modifyDiscount(String discountCode, String discountDescription,
            Date startDate, int period, double percent, String Applicable) {
        discountMgr.modifyDiscount(discountCode, discountDescription,
                startDate, period, percent, Applicable);

    }

    public void deleteDiscount(int index) {
        discountMgr.deleteDiscount(index);
    }

    public ArrayList<Discount> getDiscountList() {
        return discountMgr.getDiscountlist();
    }

    private StoreKeeperManager storekeeperMgr;
    private MemberManager memberMgr;
    private TransactionManager transactionMgr;
    private ProductManager productMgr;
    private CategoryManager categoryMgr;
    private DiscountManager discountMgr;
}
