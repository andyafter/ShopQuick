package sg.edu.nus.iss.ussa.domain;

/**
 * @author Achyut Suresh Rao
 */

public class Public extends Customer {

	private static final long serialVersionUID = 1L;
	
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
