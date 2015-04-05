//TransactionItem.java
package sg.edu.nus.iss.ussa.domain;

import java.io.IOException;

import sg.edu.nus.iss.ussa.exception.DataFileException;
import sg.edu.nus.iss.ussa.exception.DataInputException;
import sg.edu.nus.iss.ussa.util.CalcUtil;

public class CartItem
{

	private Product product = null;
	private float price =0;
	private int quantity = 0;
	
	public CartItem(){}
	
	public CartItem(Product product,float price,int qty)
	{
		this.product = product;
		this.price = price;
		this.quantity = qty;
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
	public void setPrice(float price)
	{
		this.price = price;
	}
	public int getQty()
	{
		return quantity;
	}
	public void setQty(int qty)
	{
		this.quantity = qty;
	}
	public double calculateAmount()
	{
		return CalcUtil.mul(this.price, this.quantity);
	}
	
}///~
