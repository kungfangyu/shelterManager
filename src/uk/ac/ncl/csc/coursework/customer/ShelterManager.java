package uk.ac.ncl.csc.coursework.customer;

import uk.ac.ncl.csc.coursework.pet.Dog;
import uk.ac.ncl.csc.coursework.pet.Pet;
import uk.ac.ncl.csc.coursework.pet.PetFactory;
import uk.ac.ncl.csc.coursework.pet.PetID;

import java.util.ArrayList;
import java.util.Date;
import java.util.Collection;
import java.util.List;


public class ShelterManager {

    //you can add attributes and additional methods if needed.
    //you can throw an exception if needed

    //Task 1.3 methods

    private List<Pet> pets;
    private int dogCount;
    private int catCount;

    public ShelterManager(){
        pets = new ArrayList<>();
        dogCount = 0;
        catCount = 0;
    }


    public Pet addPet(String petType){
        Pet pet = PetFactory.createPet(petType);
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
        //add your code here. Do NOT change the method signature
        return null;
    }


    public Boolean adoptPet(CustomerRecord customerRecord, String petType) {
        //add your code here. Do NOT change the method signature
        return false;
    }

    public Collection<Pet> adoptedPetsByCustomer (CustomerNumber customerNumber){
        //add your code here. Do NOT change the method signature
        return null;
    }


}
