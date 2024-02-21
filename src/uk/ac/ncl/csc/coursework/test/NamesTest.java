package uk.ac.ncl.csc.coursework.test;

import uk.ac.ncl.csc.coursework.customer.Names;

public class NamesTest {
    public static void main(String[] args) {
        NamesTest nameTest = new NamesTest();
        System.out.println("Test Names:");
        System.out.println("-".repeat(20));
        nameTest.createNames();
        nameTest.testEquals();
        nameTest.testHashCode();
    }

    private void createNames() {
        //Test 1: Normal case
        Names names = new Names("Chris", "Smith");
        Assertions.assertNotNull(names);

        //Test 2: Exception case"
        try{
            final Names names1 = new Names(null, null);
        } catch (Throwable t) {
            Assertions.assertExpectedThrowable(
                    NullPointerException.class, t);
        }

        //Test 3: Exception case"
        try{
            final Names names1 = new Names("Chris", null);
        } catch (Throwable t) {
            Assertions.assertExpectedThrowable(
                    NullPointerException.class, t);
        }

        //Test 4: exception case"
        try{
            final Names names1 = new Names("Chris", "");
        } catch (Throwable t) {
            Assertions.assertExpectedThrowable(
                    IllegalArgumentException.class, t);
        }

        System.out.println("Test createNames passed.");

    }

    private void testHashCode() {
        Names names1 = new Names("Chris", "Smith");
        Names names2 = new Names("Jane", "Jones");
        Names namesCopy = new Names("Chris", "Smith");

        //Test: If name is equal the hashCode should be same, if no hashCode should be different
        Assertions.assertEquals(names1.hashCode(), namesCopy.hashCode());
        Assertions.assertNotEquals(names1.hashCode(), names2.hashCode());
        System.out.println("HashCode passed.");
    }

    private void testEquals() {
        Names names = new Names("Jane", "Jones");

        //Test: Name is the same or not
        Assertions.assertTrue(names.equals(names));
        Assertions.assertFalse(names.equals(null));
        Assertions.assertFalse(names.equals("Jennifer"));
        Assertions.assertTrue(names.equals(new Names("Jane", "Jones")));
        Assertions.assertFalse(names.equals(new Names("Jennifer", "Jones")));
        Assertions.assertFalse(names.equals(new Names("Helen", "Lara")));

        System.out.println("Test equals passed.");

    }
}
