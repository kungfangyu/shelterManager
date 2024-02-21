package uk.ac.ncl.csc.coursework.test;

import uk.ac.ncl.csc.coursework.pet.PetID;

public class PetIDTest {
        public static void main(String[] args) {
        System.out.println("PetID Test:");
        System.out.println("-".repeat(20));
        PetIDTest.testGetPetID();
    }

    private static void testGetPetID() {
        PetID petID = PetID.getInstance();

        //Test: Pet Id should not null
        Assertions.assertNotNull(petID);
        System.out.println("test get petID passed.");
    }
}
