package sg.edu.nus.iss.ussa.domain;

public class Product {

    private String productId;
    private String productName;
    private String description;
    private int quantity;
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
        this.quantity = quantityAvailable;
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
        this.quantity = quantityAvailable;
        this.price = price;
        this.barCode = barCodeNumber;
        this.reorderQuantity = recorderQuantity;
        this.orderQuantity = orderQuantity;
    }

    public boolean getStorageStatus() {
        if (this.quantity < this.reorderQuantity) {
            return false;
        }
        return true;
    }

    public boolean compare(Product p) {
        if (productName.equals(p.getProductId()) && description.equals(p.getDescription()) & super.equals(p.getCategory())
                && price == p.getPrice() && barCode.equals(p.getBarCode()) && reorderQuantity == p.getReorderQuantity()
                && orderQuantity == p.getOrderQuantity()) {
            return true;
        }
        return false;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public int getReorderQuantity() {
        return reorderQuantity;
    }

    public void setReorderQuantity(int reorderQuantity) {
        this.reorderQuantity = reorderQuantity;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    
    public void addQuantity(int add) {
        this.quantity = this.quantity + add;
    }


}
