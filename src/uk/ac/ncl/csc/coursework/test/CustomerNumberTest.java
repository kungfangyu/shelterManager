package uk.ac.ncl.csc.coursework.test;

import uk.ac.ncl.csc.coursework.customer.CustomerNumber;
import uk.ac.ncl.csc.coursework.customer.Name;

import java.util.Calendar;

public class CustomerNumberTest {
    public static void main(String[] args) {
        System.out.println("Test customer number:");
        Calendar issueDate = Calendar.getInstance();
        issueDate.set(2024, Calendar.JANUARY + 1, 1);
        Name name = new Name("Fane", "Kung");
        CustomerNumber cn1 = CustomerNumber.getInstance(name.getFirstName(), issueDate);
        CustomerNumber cn2 = CustomerNumber.getInstance("Jane", issueDate);


        System.out.println(cn1.getCustomerNumber());
        System.out.println(cn2.getCustomerNumber());
    }
}
