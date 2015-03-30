/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.domain;

import java.util.ArrayList;

/**
 *
 * @author a0134449b
 */
public class Category {
        
    private String categorycode;
	private String categoryname;
	private ArrayList<Vendor> vendorList;
	
	public Category(String categorycode, String categoryname, ArrayList<Vendor> vendorList) 
    {
		super();
		this.categorycode = categorycode;
		this.categoryname = categoryname;
		this.vendorList = vendorList;
	}
	
	public String getCategoryCode() {
		return categorycode;
	}
	public void setCategoryCode(String code) {
		this.categorycode = categorycode;
	}
	public String getCategoryName() {
		return categoryname;
	}
	public void setCategoryName(String name) {
		this.categoryname = categoryname;
	}
	public ArrayList<Vendor> getVendorList() {
		return vendorList;
	}
	public void setVendorList(ArrayList<Vendor> vendorList) {
		this.vendorList = vendorList;
	}
	
	public Vendor getFirstVendor(){
		Vendor vendor = null;
		if (!this.vendorList.isEmpty()){
			vendor = this.vendorList.get(0);
		}	
		return vendor;
	}
    
}
