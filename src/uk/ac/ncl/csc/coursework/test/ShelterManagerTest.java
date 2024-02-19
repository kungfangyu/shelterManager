package uk.ac.ncl.csc.coursework.test;

import uk.ac.ncl.csc.coursework.customer.CustomerRecord;
import uk.ac.ncl.csc.coursework.customer.Name;
import uk.ac.ncl.csc.coursework.customer.ShelterManager;
import uk.ac.ncl.csc.coursework.pet.*;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class ShelterManagerTest {
    public static void main(String[] args) {
        System.out.println("Test shelter manager:");

        ShelterManagerTest.testPet();
        ShelterManagerTest.testAddCustomerRecord();
        ShelterManagerTest.testAdoptPet();

    }



    public static void testPet() {
        ShelterManager shelterManager = new ShelterManager();

        PetID petID = PetID.getInstance();

        // Testing dog should be added or not
        Pet dog = shelterManager.addPet(PetFactory.DOG, petID, "Needs daily walks and three meals a day.");
        Pet cat = shelterManager.addPet(PetFactory.CAT, petID, "Needs to feed two meals a day.");
        if (dog != null && dog instanceof Dog && !dog.isTrained() && dog.getPetType().equals("Dog")) {
            System.out.println("Dog added successfully." + "pet id: " + petID + ", pet type: " + dog.getPetType() + ", isTrained: " + ((Dog) dog).isTrained);

        }
        if (cat != null && cat instanceof Cat && !cat.isTrained() && cat.getPetType().equals("Cat")) {
            if(cat.getPetID().equals(dog.getPetID())) {
                PetID newPetID = PetID.getInstance();
                System.out.println("Cat added successfully.");
            }
        }
    }

    //Test addCustomerRecord

    public static void testAddCustomerRecord() {
        ShelterManager shelterManager = new ShelterManager();
        String firstName = "Fane";
        String lastName = "Kung";
        Calendar dobCal = Calendar.getInstance();
        dobCal.set(1992, Calendar.OCTOBER, 26);
        Date dob = dobCal.getTime();
        boolean hasGarden = true;

        try {
            // Create new customer record
            CustomerRecord record = shelterManager.addCustomerRecord(firstName, lastName, dob, hasGarden);
            if (record != null &&
                    record.getName().getFirstName().equals(firstName) &&
                    record.getName().getLastName().equals(lastName)) {
                System.out.println("Customer record added successfully.");
                System.out.println("Customer name: " + firstName+ " " + lastName + ", Date of birth: " + dob);
            } else {
                System.out.println("Failed to add customer record.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Correctly caught an attempt to add a duplicate customer record.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    //Test adopt pet
    public static void testAdoptPet() {
        ShelterManager shelterManager = new ShelterManager();
        Calendar dob = Calendar.getInstance();
        dob.set(1990, Calendar.JANUARY, 1); // initial date of birth
        CustomerRecord customerRecord = new CustomerRecord(new Name("Fane", "Kung"), dob.getTime(), Calendar.getInstance(), true);

        shelterManager.addPet(PetFactory.DOG, PetID.getInstance(), "Care instructions");

        boolean result = shelterManager.adoptPet(customerRecord, "Dog");
        Assertions.assertTrue(result);

        Collection<Pet> adoptedPets = shelterManager.adoptedPetsByCustomer(customerRecord.getCustomerNumber());
        Assertions.assertEquals(1, adoptedPets.size());

    }
}
