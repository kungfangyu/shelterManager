package uk.ac.ncl.csc.coursework.customer;

import java.util.Calendar;

public class CustomerRecord {
    final private String firstName;

    final private  String lastName;

    final Calendar birthDate;

    final Calendar publishDate;

    private final String customerNumber;
    private final boolean hasGarden;


    public CustomerRecord(Name name, Calendar birthDate, Calendar publishDate, CustomerNumber customerNumber, boolean hasGarden) {
        this.firstName = name.getFirstName();
        this.lastName = name.getLastName();
        this.customerNumber = customerNumber.getCustomerNumber();
        this.hasGarden = hasGarden;
        this.birthDate = Calendar.getInstance();
        this.publishDate = Calendar.getInstance();
    }

}
