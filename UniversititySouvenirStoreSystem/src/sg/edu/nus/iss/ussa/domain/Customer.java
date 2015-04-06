package sg.edu.nus.iss.ussa.domain;

import java.io.Serializable;


public abstract class Customer  {

    public String name;

    public Customer() {

    }

    public Customer(String name) {
        this.name = name;
    }

    public abstract String getID();

}
