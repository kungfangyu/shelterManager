package uk.ac.ncl.csc.coursework.customer;

import uk.ac.ncl.csc.coursework.pet.*;

import java.util.*;


public class ShelterManager {
    //Task 1.3 methods



    private List<Pet> pets;
    private int dogCount;
    private int catCount;

    private Map<String, CustomerRecord> customerRecords = new HashMap<>();
    private Map<CustomerNumber, List<Pet>> adoptedPets= new HashMap<>(); // Every customer's pet list

    public ShelterManager(){
        this.customerRecords = new HashMap<>();
        this.adoptedPets = new HashMap<>();
        pets = new ArrayList<>();
        dogCount = 0;
        catCount = 0;
    }


    public Pet addPet(String petType, PetID petID, String careInstructions){
        Pet pet = PetFactory.getInstance(petType, petID, careInstructions);
        if(petType.equals(PetFactory.DOG)){
            ((Dog)pet).setTrained(false); // initially dog should not be trained.
        }
        pets.add(pet);
        return pet;
    }


    public Boolean updatePetRecord (PetID petID, Boolean trained) {

        for(Pet pet: pets) {
            if(pet.getPetID().equals(petID)) {
                if(pet instanceof Dog) {
                    ((Dog)pet).setTrained(trained);
                    return true;
                }
            }
            break;
        }
        return false;
    }


    public int noOfAvailablePets(String petType) {
        int count = 0;
        for(Pet pet : pets) {
            if(pet.getPetType().equals(petType) && !pet.getAdopted()){
                count++;
            }
        }
        return count;
    }




    //Task 2.2 methods

    public CustomerRecord addCustomerRecord(String firstName, String lastName, Date dob, Boolean hasGarden) {
        Name name = new Name(firstName, lastName);
        Calendar issueDate = Calendar.getInstance();

        CustomerNumber tempCustomerNumber = CustomerNumber.getInstance(name.getFirstName(), issueDate);
        if(customerRecords.containsKey(tempCustomerNumber.toString())){
            throw new IllegalArgumentException("A customer with this customer number already exists.");
        }
        CustomerRecord newRecord = new CustomerRecord(name, dob, issueDate, hasGarden);
        customerRecords.put(newRecord.getCustomerNumber().toString(), newRecord);
        return newRecord;

    }


    public Boolean adoptPet(CustomerRecord customerRecord, String petType) {
        List<Pet> customerPets = adoptedPets.computeIfAbsent(customerRecord.getCustomerNumber(), key -> new ArrayList<>());

        // if customer adopt over 3 pets then they can not adopt.
        if(customerPets.size() >= 3) {
            System.out.println("Customer should not adopt one or more pets.");
            return false;
        }

        for(Pet pet: pets) {
            if(pet.getPetType().equals(petType) && !pet.getAdopted()) {
                boolean canAdopt = false;
                if(petType.equals(PetFactory.DOG)) {
                    canAdopt = (pet.isTrained() && customerRecord.getAge() >= 18 && customerRecord.hasGarden()) ||
                            (!pet.isTrained() && customerRecord.getAge() >= 21 && customerRecord.hasGarden());
                } else if (petType.equals(PetFactory.CAT)){
                    canAdopt = customerRecord.getAge() >= 18;
                }
                if(canAdopt) {
                    pet.setAdopted(true);
                    customerPets.add(pet);
                    System.out.println("Adopting a " + petType + " successfully.");
                    return true;
                }
                break; // Break after finding the first match
            }
        }
        System.out.println("No suitable " + petType + "available for adoption or customer cannot adopt.");
        return false;
    }

    public Collection<Pet> adoptedPetsByCustomer (CustomerNumber customerNumber){
        List<Pet> customerPets = adoptedPets.get(customerNumber);
        if(customerPets != null) {
            return Collections.unmodifiableCollection(customerPets);
        } else {
            return Collections.emptyList();
        }
    }


}
