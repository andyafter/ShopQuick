package sg.edu.nus.iss.ussa.domain;

import java.util.Date;

public abstract class Discount {
	private String discountCode;
	private String discountDescription;
	//private String startDate;
	private Date startDate;
	private int period;
	private double percent;
	private String Applicable;

	public Discount(String discountCode, String discountDescription,
			Date startDate, int period, double percent, String Applicable) {
		this.discountCode = discountCode;
		this.discountDescription = discountDescription;
        this.startDate = startDate;
        this.period=period;
        this.percent=percent;
        this.Applicable=Applicable;
        
	}

	public String getDiscountcode() {
		// TODO Auto-generated method stub
		return discountCode;
	}

	public String getDiscountDescription() {
		// TODO Auto-generated method stub
		return discountDescription;
	}
//	public String getStartDate() {
	public Date getStartDate() {
		// TODO Auto-generated method stub
		return startDate;
	}

	public int getPeriod() {
		return period;
	}

	public double getPercent() {
		return percent;
	}

	public String getApplicable() {
		return Applicable;
	}
	
	/**
	 * compare percent with another discount, return the higher one
	 * 
	 * @param discount
	 * @return higher discount
	 */
	public Discount getHigherDiscount(Discount discount){
		if ((discount == null) || (this.getPercent() > discount.getPercent())) 
			return this;
	else 
			return discount;
	}

}
