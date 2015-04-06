/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.gui;

/**
 *
 * @author andypan
 */
public class ErrorMSG {

    private final String NonExistingMem = "Error MemberID!";
    private final String NonExistingProd = "No product!";
    private final String WrongBarCode = "Bar Code Error!";
    private final String WrongQuantityForm = "Quantity Format Error";
    private final String QuantityNotEnough = "Quantity is not Enough!";
    private final String WrongPointForm = "Point Format Error!";
    private final String PointNotEnough = "Point is not Enough!";
    private final String WrongCashForm = "Cash Format Error!";
    private final String CashNotEnough = "Cash is not enough!";
    private final String SelectRow = "Select a Row!";

    public String getNonExistingMem() {
        return NonExistingMem;
    }

    public String getNonExistingProd() {
        return NonExistingProd;
    }

    public String getWrongBarCode() {
        return WrongBarCode;
    }

    public String getWrongQuantityForm() {
        return WrongQuantityForm;
    }

    public String getQuantityNotEnough() {
        return QuantityNotEnough;
    }

    public String getWrongPointForm() {
        return WrongPointForm;
    }

    public String getPointNotEnough() {
        return PointNotEnough;
    }

    public String getWrongCashForm() {
        return WrongCashForm;
    }

    public String getCashNotEnough() {
        return CashNotEnough;
    }

    public String getSelectRow() {
        return SelectRow;
    }

}
