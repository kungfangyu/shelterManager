package uk.ac.ncl.csc.coursework.test;

import uk.ac.ncl.csc.coursework.customer.CustomerNumber;
import uk.ac.ncl.csc.coursework.customer.Names;

import java.util.Calendar;

public class CustomerNumberTest {
    public static void main(String[] args) {
        System.out.println("Test customer number:");
        System.out.println("-".repeat(20));
        CustomerNumberTest.TestCustomerNumber();
    }

    private static void TestCustomerNumber() {
        Names name1 = new Names("Fane", "Kung");
        Calendar issueDate1 = Calendar.getInstance();
        CustomerNumber cn1 = CustomerNumber.getInstance(name1.getFirstName(), issueDate1);

        Names name2 = new Names("Joe", "Su");
        Calendar issueDate2 = Calendar.getInstance();
        CustomerNumber cn2 = CustomerNumber.getInstance(name2.getFirstName(), issueDate2);


        // Test 1: Assert that both CustomerNumbers are not null
        Assertions.assertNotNull(cn1);
        Assertions.assertNotNull(cn2);

        // Test 2: Assert that cn1 and cn2 are different, assuming unique IDs for different names or dates
        Assertions.assertNotEquals(cn1, cn2);

        System.out.println("Test customer number passed.");
    }
}
