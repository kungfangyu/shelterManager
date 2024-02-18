package uk.ac.ncl.csc.coursework.customer;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.Calendar;

public class CustomerNumber {
    private static final Map<String, Integer> serialNumbers = new HashMap<>();
    private final String customerNumber;
    private static final Random random = new Random();

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
