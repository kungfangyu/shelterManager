package uk.ac.ncl.csc.coursework.customer;

import java.util.Calendar;
import java.util.Date;

public class CustomerRecord {
    private final Name name;

    private final Date dateOfBirth;

    private final Calendar issueDate;

    private final CustomerNumber customerNumber;
    private final boolean hasGarden;


    public CustomerRecord(Name name, Date dateOfBirth, Calendar issueDate, boolean hasGarden) {
        this.name = new Name(name.getFirstName(), name.getLastName());
        this.customerNumber = CustomerNumber.getInstance(name.getFirstName(), issueDate);
        this.hasGarden = hasGarden;
        this.dateOfBirth = new Date(dateOfBirth.getTime());
        this.issueDate = (Calendar) issueDate.clone();
    }

    public Name getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return new Date(dateOfBirth.getTime()); // Create a new Date object to avoid returning reference
    }

    public int getAge() {
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(dateOfBirth);
        int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
        if(today.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)){
            age--;
        }
        return age;
    }

    public Calendar getIssueDate() {
        return (Calendar) issueDate.clone();
    }

    public CustomerNumber getCustomerNumber() {
        return customerNumber;
    }

    public boolean hasGarden() {
        return hasGarden;
    }


}
