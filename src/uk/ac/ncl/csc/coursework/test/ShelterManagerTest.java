package uk.ac.ncl.csc.coursework.test;

import uk.ac.ncl.csc.coursework.customer.*;
import uk.ac.ncl.csc.coursework.pet.*;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class ShelterManagerTest {
    public static void main(String[] args) {
        System.out.println("Test shelter manager:");
        System.out.println("-".repeat(20));

        ShelterManagerTest.testAddPet();
        ShelterManagerTest.testUpdatePetRecord();
        ShelterManagerTest.testNoOfAvailablePets();
        ShelterManagerTest.testAddCustomerRecord();
        ShelterManagerTest.testAdoptPet();
        ShelterManagerTest.testAdoptPetByCustomer();
    }


    private static void testAddPet() {
        System.out.println("TestAddPet " + "-".repeat(20));

        ShelterManager shelterManager = new ShelterManager();

        // Testing dog should be added or not
        Pet dog = shelterManager.addPet(PetFactory.DOG);
        Pet cat = shelterManager.addPet(PetFactory.CAT);
        Assertions.assertTrue(dog != null && dog instanceof Dog && !dog.isTrained() && dog.getPetType().equals("Dog"));

        // Testing cat should be added or not
        Assertions.assertTrue(cat != null && cat instanceof Cat && cat.getPetType().equals("Cat"));

        //Testing if cat type is wrong
        Assertions.assertFalse(cat != null && cat instanceof Cat && cat.getPetType().equals("Dog"));
        System.out.println("Add pet passed.");
    }

    //Verify update pet record
    private static void testUpdatePetRecord() {
        System.out.println("TestUpdatePetRecord " + "-".repeat(20));
        ShelterManager shelterManager = new ShelterManager();
        Pet updateDog = shelterManager.addPet("Dog");
        updateDog.setTrained(true);

        Pet updateDogToNotTrained = shelterManager.addPet("Dog");
        updateDog.setTrained(false);

        //Test update dog is trained or not
        boolean updateResult = shelterManager.updatePetRecord(updateDog.getPetID(), true);
        boolean updateNotTrainedResult = shelterManager.updatePetRecord(updateDog.getPetID(), false);
        Assertions.assertTrue(updateResult);
        Assertions.assertTrue(updateNotTrainedResult);
        System.out.println("Update record passed.");

    }

    //Verify no of available pets
    private static void testNoOfAvailablePets() {
        System.out.println("TestNoOfAvailablePets " + "-".repeat(20));
        ShelterManager shelterManager = new ShelterManager();
        shelterManager.addPet("Dog");
        shelterManager.addPet("Dog");
        shelterManager.addPet("Cat");


        int availableDogs = shelterManager.noOfAvailablePets("Dog");
        int availableCats = shelterManager.noOfAvailablePets("Cat");

        // available numbers of dog is 2 and cat is 1
        Assertions.assertEquals(2, availableDogs);
        Assertions.assertEquals(1, availableCats);
        System.out.println("No of available pets passed.");
    }

    //Verify addCustomerRecord
    private static void testAddCustomerRecord() {
        System.out.println("TestAddCustomerRecord " + "-".repeat(20));
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
            Assertions.assertTrue(record != null &&
                    record.getName().getFirstName().equals(firstName) &&
                    record.getName().getLastName().equals(lastName));
            System.out.println("Add customer record test passed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Correctly caught an attempt to add a duplicate customer record.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    //Test adopt pet
    private static void testAdoptPet() {
        System.out.println("TestAdoptPet " + "-".repeat(20));
        ShelterManager shelterManager = new ShelterManager();
        Calendar dob = Calendar.getInstance();
        dob.set(1990, Calendar.JANUARY, 1); // initial date of birth

        Calendar dob2 = Calendar.getInstance();
        dob2.set(2020, Calendar.JANUARY, 1);
        CustomerRecord customerRecord = new CustomerRecord(new Names("Fane", "Kung"), dob.getTime(), Calendar.getInstance(), true);
        CustomerRecord customerRecord2 = new CustomerRecord(new Names("Amber", "Ho"), dob2.getTime(), Calendar.getInstance(), true);
        CustomerRecord customerRecord3 = new CustomerRecord(new Names("Joe", "Su"), dob.getTime(), Calendar.getInstance(), false);
        CustomerRecord customerRecord4 = new CustomerRecord(new Names("Claire", "Lee"), dob.getTime(), Calendar.getInstance(), true);

        shelterManager.addPet(PetFactory.DOG);
        shelterManager.addPet(PetFactory.CAT);
        shelterManager.addPet(PetFactory.DOG);
        shelterManager.addPet(PetFactory.CAT);
        shelterManager.addPet(PetFactory.DOG);

        //Test 1: Verify customer adopt 3 pets.
        Assertions.assertEquals(true ,shelterManager.adoptPet(customerRecord,PetFactory.DOG));
        Assertions.assertEquals(true ,shelterManager.adoptPet(customerRecord,PetFactory.CAT));
        Assertions.assertEquals(true ,shelterManager.adoptPet(customerRecord,PetFactory.DOG));

        //Test 2: Verify customer can not adopt more than 3 pets
        boolean result = shelterManager.adoptPet(customerRecord, PetFactory.CAT);
        Assertions.assertEquals(false, result);

        //Test 3: Verify customer's age is less than 18
        Assertions.assertEquals(false ,shelterManager.adoptPet(customerRecord2,PetFactory.DOG));

        //Test 4: Verify customer doesn't have garden
        Assertions.assertEquals(false ,shelterManager.adoptPet(customerRecord3,PetFactory.DOG));

        //Test 5: Verify customer is over 18 who can adopt cat
        Assertions.assertEquals(true ,shelterManager.adoptPet(customerRecord4,PetFactory.CAT));
        //Test 6: Verify customer's age is over 21 and has garden
        Assertions.assertEquals(true ,shelterManager.adoptPet(customerRecord4,PetFactory.DOG));


        Collection<Pet> adoptedPets = shelterManager.adoptedPetsByCustomer(customerRecord.getCustomerNumber());
        //Test 6: Verify customer can adopt 3 pets
        Assertions.assertEquals(3, adoptedPets.size());
        System.out.println("Adopt pet passed.");
    }


    //Test adopt pet by customer
    private static void testAdoptPetByCustomer() {
        System.out.println("TestAdoptPetByCustomer " + "-".repeat(20));
        ShelterManager shelterManager = new ShelterManager();

        // Setup test data
        Calendar dob = Calendar.getInstance();
        dob.set(1990, Calendar.JANUARY, 1);

        //Add a customer
        CustomerRecord customerRecord = new CustomerRecord(new Names("Fane", "Kung"), dob.getTime(), Calendar.getInstance(), true);
        CustomerNumber customerNumber = customerRecord.getCustomerNumber();

        //Add pets to shelter and adopt them
        Pet dog = shelterManager.addPet(PetFactory.DOG);
        Pet cat = shelterManager.addPet(PetFactory.CAT);

        // Test 1: Verify pets were adopted
        Assertions.assertTrue(shelterManager.adoptPet(customerRecord, PetFactory.DOG));
        Assertions.assertTrue(shelterManager.adoptPet(customerRecord, PetFactory.CAT));

        // Test 2: Verify correct pets are returned for the customer
        Collection<Pet> adoptedPets = shelterManager.adoptedPetsByCustomer(customerNumber);
        Assertions.assertNotNull(adoptedPets);
        Assertions.assertEquals(2, adoptedPets.size()); // Assumes customer adopted exactly 2 pets

        // Test 3: Verify empty collection is returned for a customer with no adopted pets
        CustomerRecord newCustomerRecord = shelterManager.addCustomerRecord("Amber", "Ho", dob.getTime(), true);
        CustomerNumber newCustomerNumber = newCustomerRecord.getCustomerNumber();
        Collection<Pet> noPets = shelterManager.adoptedPetsByCustomer(newCustomerNumber);
        Assertions.assertNotNull(noPets);
        Assertions.assertTrue(noPets.isEmpty());

        System.out.println("Test Adopt Pet By Customer passed.");
    }

}
