//Transaction.java
package sg.edu.nus.iss.ussa.domain;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import sg.edu.nus.iss.ussa.util.CalcUtil;

public class Transaction {

    /**
     * Transaction Class
     *
     * @author Liu Xinzhuo
     * @author A0136010A
     * @version 1.0
     */

    private int id = 0;
    private int redeemedLoyaltyPoint;
    private Date date;
    private Customer customer;
    private Discount discount;
    private ArrayList<CartItem> itemList = new ArrayList<CartItem>();

    private double cashAmount = 0;
    private double totalPrice = 0;
    private double discountedPirce = 0;
    private int gainedPoint = 0;
    private double rest = 0;
    private double change = 0;

    private static final double DOLLAR_TO_POINT = 0.1;
    private static final double POINTS_TO_DOLLAR = 0.05;

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Transaction() {
    }

    public Transaction(int id, Customer customer, Date date) {
        this.id = id;
        this.customer = customer;
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<CartItem> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<CartItem> itemList) {
        this.itemList = itemList;
    }

    public double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(double cashAmount) {
        this.cashAmount = cashAmount;
    }

    public int getRedeemedLoyaltyPoint() {
        return redeemedLoyaltyPoint;
    }

    public void setRedeemedLoyaltyPoint(int redeemedLoyaltyPoint) {
        this.redeemedLoyaltyPoint = redeemedLoyaltyPoint;
    }

    public void addItem(Product product, int qty) {
        CartItem transactionitem = new CartItem(product, product.getPrice(), qty);
        if (!itemList.contains(transactionitem)) {
            itemList.add(transactionitem);
        }
    }

    public void addItem(Product product, double price, int qty) {
        CartItem transactionitem = new CartItem(product, price, qty);
        if (itemList.contains(transactionitem)) {

        } else {
            itemList.add(transactionitem);
        }
    }

    public void removeItem(Product product) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getProduct() == product) {
                itemList.remove(i);
            }
        }
    }

    public double calcTotalPrice() {
        double sum = 0;
        for (int i = 0; i < itemList.size(); i++) {
            CartItem it = (CartItem) itemList.get(i);
            sum = CalcUtil.add(sum, it.calculateAmount());
        }
        totalPrice = sum;
        return totalPrice;
    }

    public double calcDiscountPrice() {
        discountedPirce = calcTotalPrice() * (100 - discount.getPercent()) / 100;
        return discountedPirce;
    }

    public double calcChange() {
        change = cashAmount - calcRest();
        return change;
    }

    public int calcGainedPoint() {
        gainedPoint = (int) (calcRest() * DOLLAR_TO_POINT);
        return gainedPoint;
    }

    public double calcRest() {
        rest = calcDiscountPrice() - redeemedLoyaltyPoint * POINTS_TO_DOLLAR;
        return rest;
    }

}///~
