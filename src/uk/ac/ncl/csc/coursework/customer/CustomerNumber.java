package uk.ac.ncl.csc.coursework.customer;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.Calendar;

public class CustomerNumber {
    private static final Map<String, Integer> serialNumbers = new HashMap<>();
    private final String customerNumber;
    private static final Random random = new Random();

    /**
     * Private constructor for creating a unique {@link CustomerNumber} based on the first name and issue date.
     *
     * This process involves:
     * 1. Deriving an uppercase prefix from the first character of the first name.
     * 2. Forming a registration key using the generated prefix and the customer's registration issue date.
     * 3. Auto-generating a two-digit wide customer-specific additional identification serial number (if multiple customers have the same prefix in the same month and year, they receive successive numbers).
     * 4. The structure of the customer number is defined as: "<b>PrefixSerial.MonthYear</b>", for example, "J02.112023" for the second John registered in November 2023.
     *
     * @param firstName The first name of the customer, used for the customer number's leading letter.
     * @param issueDate The issue date of the document, used for partial creation of the id's series.
     */


    private CustomerNumber(String firstName, Calendar issueDate) {
        char prefix = firstName.toUpperCase().charAt(0); // pick first char of firstName
        String key = prefix + "." + (issueDate.get(Calendar.MONTH) + 1) + issueDate.get(Calendar.YEAR);
        int serial;
        if(!serialNumbers.containsKey(key)) {
            serial = random.nextInt(100);
        } else {
            serial = serialNumbers.get(key) + 1;
        }
        serialNumbers.put(key, serial);
        this.customerNumber = String.format("%c%02d.%d%04d", prefix, serial, issueDate.get(Calendar.MONTH) + 1, issueDate.get(Calendar.YEAR));

    }

    public static CustomerNumber getInstance(String firstName, Calendar issueDate) {
        return new CustomerNumber(firstName, issueDate);
    }

    public String getCustomerNumber() {
        return customerNumber;
    }
}
