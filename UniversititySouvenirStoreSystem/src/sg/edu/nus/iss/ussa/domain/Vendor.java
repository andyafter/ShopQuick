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

    boolean equalsName(Vendor ven) {
        return this.name.equalsIgnoreCase(ven.name);
    }

    boolean equals(Vendor ven) {
        return this.name.equalsIgnoreCase(ven.name) && this.description.equalsIgnoreCase(ven.description);
    }

}
