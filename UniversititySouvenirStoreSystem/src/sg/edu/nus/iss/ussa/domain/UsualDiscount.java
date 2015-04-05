package sg.edu.nus.iss.ussa.domain;

import java.util.Date;

public class UsualDiscount extends Discount {
    
	public UsualDiscount(String discountCode, String discountDescription,
			Date startDate, int period, double percent, String Applicable) {
		super(discountCode, discountDescription, startDate, period, percent, Applicable);
		// TODO Auto-generated constructor stub
	}

}
