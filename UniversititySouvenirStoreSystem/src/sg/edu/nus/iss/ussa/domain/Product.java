
package sg.edu.nus.iss.ussa.domain;


public class Product {
    
	private String productId;
	private String productName;
	private String description;
	private int quantityAvailable;
	private double price;
	private String barCode;
	private int reorderQuantity;
	private int orderQuantity;
        private Category category;
	
	public Product(Category category, String name,
			String briefDescription, int quantityAvailable, double price,
			String barCodeNumber, int recorderQuantity, int orderQuantity) {
            
		super();
                this.category = category;
		this.productName = name;
		this.description = briefDescription;
		this.quantityAvailable = quantityAvailable;
		this.price = price;
		this.barCode = barCodeNumber;
		this.reorderQuantity = recorderQuantity;
		this.orderQuantity = orderQuantity;
	}
	
	public Product(String productId, Category category, String name,
			String briefDescription, int quantityAvailable, double price,
			String barCodeNumber, int recorderQuantity, int orderQuantity) {
		super();
		this.productId = productId;
                this.category = category;
		this.productName = name;
		this.description = briefDescription;
		this.quantityAvailable = quantityAvailable;
		this.price = price;
		this.barCode = barCodeNumber;
		this.reorderQuantity = recorderQuantity;
		this.orderQuantity = orderQuantity;
	}

	public boolean checkInventoryLevel(){
		if(this.quantityAvailable < this.reorderQuantity){
			return false;
		}
		return true;
	}
	
	public boolean compare(Product p){
		if(productName.equals(p.getName())&&description.equals(p.getBriefDescription())&super.equals(p.getCategory())
				&&price==p.getPrice()&&barCode.equals(p.getBarCodeNumber())&&reorderQuantity==p.getReorderQuantity()
				&&orderQuantity==p.getOrderQuantity()){
			return true;
		}
		return false;
	}

	public void addQuantity(int add){
		this.quantityAvailable = this.quantityAvailable + add;
	}
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return productName;
	}

	public void setName(String name) {
		this.productName = name;
	}

	public String getBriefDescription() {
		return description;
	}

	public void setBriefDescription(String briefDescription) {
		this.description = briefDescription;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBarCodeNumber() {
		return barCode;
	}

	public void setBarCodeNumber(String barCodeNumber) {
		this.barCode = barCodeNumber;
	}

	public int getReorderQuantity() {
		return reorderQuantity;
	}

	public void setRecorderQuantity(int recorderQuantity) {
		this.reorderQuantity = recorderQuantity;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	
	public void show(){
		System.out.println(productName);
	}
        
}
