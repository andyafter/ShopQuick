package sg.edu.nus.iss.ussa.domain;

/**
 * @author Andy Pan
 */
public class Public extends Customer {

    public Public() {
        super();
    }

    public Public(String name) {
        super(name);
    }

    @Override
    public String getID() {
        return "PUBLIC";
    }

}
