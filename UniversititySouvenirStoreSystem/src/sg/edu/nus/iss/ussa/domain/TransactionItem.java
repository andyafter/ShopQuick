//TransactionItem.java
package sg.edu.nus.iss.ussa.domain;

import java.io.IOException;

import sg.edu.nus.iss.ussa.exception.DataFileException;
import sg.edu.nus.iss.ussa.exception.DataInputException;
import sg.edu.nus.iss.ussa.util.CalcUtil;

public class TransactionItem
{
	/**
	 * TransactionItem Class
	 * 
	 * @author Liu Xinzhuo
	 * @author A0136010A
	 * @version 1.0
	 */
	private Product product = null;
	private double price =0;
	private int qty = 0;
	
	public TransactionItem()
	{
		
	}
	
	public TransactionItem(Product product,double price,int qty)
	{
		this.product = product;
		this.price = price;
		this.qty = qty;
	}
	
	public Product getProduct()
	{
		return product;
	}
	public void setProduct(Product product)
	{
		this.product = product;
	}
	public double getPrice()
	{
		return price;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}
	public int getQty()
	{
		return qty;
	}
	public void setQty(int qty)
	{
		this.qty = qty;
	}
	public double calculateAmount()
	{
		return CalcUtil.mul(this.price, this.qty);
	}
	
}///~
