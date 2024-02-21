package uk.ac.ncl.csc.coursework.customer;

import uk.ac.ncl.csc.coursework.pet.*;

import java.util.*;


public class ShelterManager {
    private List<Pet> pets = new ArrayList<>();
    private Map<CustomerNumber, CustomerRecord> customerRecords = new HashMap<>();
    private Map<CustomerNumber, List<Pet>> adoptedPets= new HashMap<>(); // Every customer's pet list

    public ShelterManager(){
        this.customerRecords = new HashMap<>();
        this.adoptedPets = new HashMap<>();
        pets = new ArrayList<>();
    }

    /**
     * Adds a new pet to the shelter and creates a pet instance based on the provided parameters.
     * <p>
     * This method uses {@link PetFactory} to create a new pet instance according to the specified pet type (e.g., dog or cat).
     * For dogs, they are defaulted to being untrained ({@code setTrained(false)}).
     * Once a pet is successfully created, it is added to the shelter's list of pets.
     * </p>
     *
     * @param petType The type of the pet. Valid types are defined by {@link PetFactory}, such as {@link PetFactory#DOG} or {@link PetFactory#CAT}.
     *
     * @return The created pet instance. If the pet type is not supported, it may return {@code null}.
     *
     */


    public Pet addPet(String petType){
        Pet pet = PetFactory.getInstance(petType);
        if(pet != null) {
            pets.add(pet);
        }
        return pet;
    }

    /**
     * Updates the training record of a pet identified by its PetID.
     *
     * This method iterates over the list of pets in the shelter and updates the training
     * status of a dog that matches the provided PetID. If a matching dog is found,
     * its training status is updated according to the provided {@code trained} flag.
     *
     * @param petID The unique identifier for the pet whose training status needs to be updated.
     * @param trained The new training status of the pet; {@code true} indicates the pet is trained,
     *                while {@code false} indicates it is not.
     *
     * @return {@code true} if the training status of the pet is successfully updated,
     *         {@code false} otherwise. This method returns {@code false} if no matching
     *         pet is found or the pet found is not a dog.
     */


    public Boolean updatePetRecord (PetID petID, Boolean trained) {

        for(Pet pet: pets) {
            if(pet.getPetID().equals(petID) && pet instanceof Dog) {

                pet.setTrained(trained);
                return true;
            }
            break;
        }
        return false;
    }

    /**
     * Calculates the number of available pets of a specified type that have not been adopted.
     *
     * This method iterates through all pets in the shelter and counts those that match
     * the specified pet type and are currently not adopted. The pet type is case-sensitive
     * and must exactly match the types defined in the system (e.g., "Dog", "Cat").
     *
     * @param petType The type of pet to count. This should correspond to the pet types
     *                recognized by the system, such as "Dog" or "Cat".
     * @return The number of pets of the specified type that are available for adoption.
     */

    public int noOfAvailablePets(String petType) {
        int count = 0;
        for(Pet pet : pets) {
            if(pet.getPetType().equals(petType) && !pet.getAdopted()){
                count++;
            }
        }
        return count;
    }


    /**
     * Adds a new customer record to the system.
     *
     * This method creates a new {@link CustomerRecord} based on the provided first name, last name,
     * date of birth (dob), and a boolean indicating whether the customer has a garden. It generates a
     * unique {@link CustomerNumber} based on the first name and the current date. If a customer with
     * the same customer number already exists in the system, an {@link IllegalArgumentException} is thrown
     * to prevent duplicate records. Otherwise, the new customer record is added to the system.
     *
     * @param firstName The first name of the customer.
     * @param lastName The last name of the customer.
     * @param dob The date of birth of the customer.
     * @param hasGarden A boolean indicating whether the customer has a garden or not.
     * @return A new {@link CustomerRecord} instance representing the added customer.
     * @throws IllegalArgumentException if a customer with the same customer number already exists.
     */

    public CustomerRecord addCustomerRecord(String firstName, String lastName, Date dob, Boolean hasGarden) {
        Names name = new Names(firstName, lastName);
        Calendar issueDate = Calendar.getInstance();

        CustomerNumber tempCustomerNumber = CustomerNumber.getInstance(firstName, issueDate);
        if(customerRecords.containsKey(tempCustomerNumber)){
            throw new IllegalArgumentException("A customer with this customer number already exists.");
        }
        CustomerRecord newRecord = new CustomerRecord(name, dob, issueDate, hasGarden);
        customerRecords.put(newRecord.getCustomerNumber(), newRecord);
        return newRecord;

    }


    /**
     * Tries to adopt a pet of a specified type for a particular customer.
     * <p>
     * The method enforces several rules to determine if the adoption process can proceed:
     * <ul>
     * <li>A customer cannot adopt more than three pets in total.</li>
     * <li>Customers without a garden cannot adopt any pets.</li>
     * <li>To adopt a dog, a customer must be at least 18 years old for a trained dog or at least 21 for an untrained dog.</li>
     * <li>To adopt a cat, a customer must be at least 18 years old.</li>
     * </ul>
     * If a suitable pet is found and the customer meets all requirements, the pet's status is updated to adopted, and it is added to the customer's list of adopted pets.
     * </p>
     *
     * @param customerRecord The record of the customer attempting to adopt a pet.
     * @param petType The type of pet the customer wishes to adopt (e.g., "Dog" or "Cat").
     * @return {@code true} if a pet was successfully adopted, {@code false} if the adoption could not proceed due to any of the rules not being satisfied or if no suitable pet was found.
     */


    public Boolean adoptPet(CustomerRecord customerRecord, String petType) {
        List<Pet> customerPets = adoptedPets.computeIfAbsent(customerRecord.getCustomerNumber(), key -> new ArrayList<>());

        // if customer adopt over 3 pets then they can not adopt.
        if(customerPets.size() >= 3) {
            System.out.println("Failure: Customer cannot adopt more than three pets.");
            return false;
        }

        if(petType.equals(PetFactory.DOG) && !customerRecord.hasGarden()) {
            System.out.println("Failure: Customer must have a garden to adopt a dog.");
            return false;
        }

        for(Pet pet: pets) {
            if(pet.getPetType().equals(petType) && !pet.getAdopted()) {
                boolean canAdopt = false;
                int customerAge = customerRecord.getAge();
                if (petType.equals(PetFactory.DOG)) {
                    if (pet.isTrained() && customerAge >= 18) {
                        canAdopt = true;
                    } else if (!pet.isTrained() && customerAge >= 21) {
                        canAdopt = true;
                    } else {
                        System.out.println("Failure: Customer does not meet the age requirement to adopt the specified dog.");
                        continue;
                    }
                } else if (petType.equals(PetFactory.CAT) && customerAge >= 18) {
                    canAdopt = true;
                } else {
                    System.out.println("Failure: Customer does not meet the age requirement to adopt a cat.");
                    continue;
                }

                if(canAdopt) {
                    pet.setAdopted(true);
                    customerPets.add(pet);
                    System.out.println("Success: Customer has successfully adopted a " + petType + ".");
                    return true;
                }
            }
        }
        System.out.println("Failure: No suitable pets available for adoption.");
        return false;
    }

    /**
     * Retrieves a collection of pets adopted by a specific customer.
     *
     * This method returns an unmodifiable collection of pets that have been adopted by the customer
     * identified by the provided customer number. If the customer has not adopted any pets, or the customer
     * number does not match any records, an empty collection is returned. This ensures that the caller
     * cannot modify the collection directly, preserving the integrity of the adoption records.
     *
     * @param customerNumber The unique identifier for the customer whose adopted pets are being requested.
     * @return An unmodifiable collection of {@link Pet} instances adopted by the specified customer. If no pets
     *         have been adopted by the customer, or if the customer number is not found, returns an empty collection.
     */
    public Collection<Pet> adoptedPetsByCustomer (CustomerNumber customerNumber){
        List<Pet> customerPets = adoptedPets.get(customerNumber);
        if(customerPets != null) {
            return Collections.unmodifiableCollection(customerPets);
        } else {
            return Collections.emptyList();
        }
    }


}
