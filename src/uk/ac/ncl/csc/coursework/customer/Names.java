package uk.ac.ncl.csc.coursework.customer;

public class Names {
    private String firstName;
    private String lastName;

    public Names(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }
    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public void setFirstName(String firstName) {
        if (firstName.length() == 0)
            throw new IllegalArgumentException("Empty first name");

        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName.length() == 0)
            throw new IllegalArgumentException("Empty last name");

        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof Names))
            return false;

        final Names name = (Names) obj;

        return firstName.equals(name.firstName)
                && lastName.equals(lastName);
    }


    @Override
    public int hashCode() {
        int hc = 17;

        hc = 37 * hc + firstName.hashCode();

        return 37 * hc + lastName.hashCode();
    }



    @Override
    public String toString() {
        return firstName + " " + lastName;
    }


    public static Names valueOf(String name) {
        final String[] parts = name.split(" ");

        return new Names(parts[0], parts[1]);
    }

}
