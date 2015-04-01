package sg.edu.nus.iss.ussa.manager;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import sg.edu.nus.iss.ussa.dataio.DiscountIO;
import sg.edu.nus.iss.ussa.domain.Customer;
import sg.edu.nus.iss.ussa.domain.Discount;
import sg.edu.nus.iss.ussa.domain.Member;
import sg.edu.nus.iss.ussa.domain.MemberDiscount;
import sg.edu.nus.iss.ussa.exception.DataFileException;
import sg.edu.nus.iss.ussa.util.Util;

/*
 * 
 * 
 * @ tanuj
 */
public class DiscountManager {
	private ArrayList<Discount> discountList;
	private DiscountIO discountDao;
	
	public DiscountManager() throws IOException, DataFileException{
		discountDao = new DiscountIO();
		loadData();
	}

	public DiscountManager(ArrayList<Discount> list){
		this.discountList = list;
	}

	public void loadData() throws IOException, DataFileException{
		discountList = discountDao.loadDataFromFile();
	}
	
	
	public void saveData() throws IOException{
		discountDao.saveDataToFile(discountList);
		
	}

	public ArrayList<Discount> getDiscountlist(){
		return this.discountList;
	}


	
	
	/**
	 * 
	 * @param discountCode
	 * @param discountDescription
	 * @param startDate
	 * @param period
	 * @param percent
	 * @param Applicable
	 * @return
	 */
	public ArrayList<Discount> registerDiscount(String discountCode, String discountDescription,
			Date startDate, int period, double percent, String Applicable) {

		discountList.add(new MemberDiscount(discountCode, discountDescription, startDate, period, percent, Applicable));
		return discountList;
	}

	public ArrayList<Discount> getdiscountList(){

		return discountList;

	}

	public Discount getdiscountCode(String discountCode) {
		Iterator<Discount> i = discountList.iterator();
		while (i.hasNext()) {
			Discount disc = i.next();
			if (disc.getDiscountcode().equals(discountCode))
				return disc;
		}
		return null;
	}

	public void writeFile() throws IOException {
		discountDao.saveDataToFile(discountList);
	}

	public void readFile() throws IOException, DataFileException {
		discountList = discountDao.loadDataFromFile();
	}

	
	
		
	/* maximum disc*/
	public double getMaxDiscount(String customerId,int loyaltyPoint){
		boolean isMember = false;
		boolean hasTransaction=false;
		double maxDiscount=0;
	    Date currentDate= new Date();
		
		ArrayList<Discount> discList = new ArrayList<Discount>();
		
		if(! customerId.equalsIgnoreCase("Public"))
		{
			isMember=true	;	
		}	
		if(loyaltyPoint!=-1)
		{
		
		hasTransaction=true;
		}
		
		discList= this.getDiscountlist();
		
		for(Discount d:discList)
		{
			if (d.getApplicable().equalsIgnoreCase("m") && isMember){
			    
				if(!hasTransaction && d.getDiscountcode().equalsIgnoreCase("MEMBER_FIRST")){
					 if(maxDiscount < d.getPercent()){
							maxDiscount=d.getPercent();							  
						  }		
				}
				else if(!d.getDiscountcode().equalsIgnoreCase("MEMBER_FIRST")){
				
					 if(maxDiscount < d.getPercent()){
							maxDiscount=d.getPercent();							  
						  }		
			 
				}
				
				}
				 
	else if(d.getStartDate().compareTo(currentDate) <= 0 && Util.addDays(d.getStartDate(), d.getPeriod()).compareTo(currentDate) >=0 ) {
				  if(maxDiscount < d.getPercent())
				  {
						maxDiscount=d.getPercent();
									  
					  }
				
			}
			
			
			
			}
			
			
		//	System.out.println(d.getDiscountcode()+","+d.getDiscountDescription()+","+
	//	d.getStartDate()+","+d.getPeriod()+","+d.getApplicable());
			
		
					
		return maxDiscount;
		
	}

	/**
	 * according to customer's type, return the applicable and highest discount
	 * 
	 * @param customer
	 * @return highest discount (may be null)
	 */
	public Discount getMaxDiscount(Customer customer){
		boolean isMember = false;
		boolean hasTransaction=false;
		Discount maxDiscount = null;
	    Date currentDate= new Date();
		
		if(customer instanceof Member){
			isMember=true;
			if(((Member) customer).getLoyaltyPoint() != -1){
				hasTransaction=true;
			}
		}
		
		for(Discount discount:this.discountList){
			if (discount.getApplicable().equalsIgnoreCase("m") && isMember){
			    // discount for member only && customer is a member 
				
				if(!hasTransaction && discount.getDiscountcode().equalsIgnoreCase("MEMBER_FIRST")){
					// discount for member's first purchase only
					maxDiscount = discount.getHigherDiscount(maxDiscount);
				}
				else if(!discount.getDiscountcode().equalsIgnoreCase("MEMBER_FIRST")){
					// discount for member's subseq purchase
					maxDiscount = discount.getHigherDiscount(maxDiscount);
				}
			}
			else if(discount.getStartDate().compareTo(currentDate) <= 0 && 
					Util.addDays(discount.getStartDate(), discount.getPeriod()).compareTo(currentDate) >=0 ) {
				// occasional discount is valid for current date 
				maxDiscount = discount.getHigherDiscount(maxDiscount);
			}
		}
		
		return maxDiscount;
	}

	public void modifyDiscount(String discountCode, String discountDescription,
			Date startDate, int period, double percent, String applicable) {
		// TODO Auto-generated method stub
		
	}

	public void deleteDiscount(int index) {
		// TODO Auto-generated method stub
		
	} 
}


