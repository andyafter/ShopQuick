package sg.edu.nus.iss.ussa.domain;

/**
 * 
 * @author 
 *
 */
public class Vendor {
	private String name;
	private String description;
	
	public Vendor(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	 boolean equalsName(Vendor VENOBJ) {
        return this.name.equalsIgnoreCase(VENOBJ.name);
    }
	    
    boolean equals(Vendor VENOBJ) {
        return this.name.equalsIgnoreCase(VENOBJ.name) &&  this.description.equalsIgnoreCase(VENOBJ.description);
    }
	
}
