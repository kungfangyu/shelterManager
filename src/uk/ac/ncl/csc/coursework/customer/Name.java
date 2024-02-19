package uk.ac.ncl.csc.coursework.customer;

import java.util.HashMap;
import java.util.Map;

public class Name {
    private static final Map<String, Name> NAMES = new HashMap<String, Name>();

    private final String firstName, lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public static Name getInstance(String firstName, String lastName) {
        String strRep = firstName + " " + lastName;
        Name name = NAMES.get(strRep);
        if(name == null) {
            name = new Name(firstName, lastName);
            NAMES.put(strRep, name);
        }
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
