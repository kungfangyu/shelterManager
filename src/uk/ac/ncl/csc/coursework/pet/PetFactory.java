package uk.ac.ncl.csc.coursework.pet;

import java.util.HashMap;
import java.util.Map;

public abstract class PetFactory implements Pet {
    public static final String DOG = "Dog";
    public static final String CAT = "Cat";

    private static final Map<PetID, Pet> pets = new HashMap<PetID, Pet>();

    public PetID petID;

    public String petType;

    public boolean isAdopted;

    public String careInstructions;

    public boolean isTrained;

    PetFactory(PetID petID, String petType, String careInstructions) {
        this.petID = petID;
        this.petType = petType;
        this.careInstructions = careInstructions;
        this.isAdopted = false;
        this.isTrained = false;
    }

    public static Pet getInstance(String petType, PetID petID, String careInstructions){
        Pet pet = pets.get(petID);

        if(pet == null) {
            if(petType.equals(DOG)) {
               pet = new Dog(petID, careInstructions);
            } else if(petType.equals(CAT)) {
                pet = new Cat(petID, careInstructions);
            } else throw new IllegalArgumentException("Invalid pet type: " + petType);

            pets.put(petID, pet);
        }
        return pet;
    }

    public PetID getPetID() {
        return petID;
    }


    public String getPetType() {
        return petType;
    }


    public boolean getAdopted() {
        return isAdopted;
    }


    public String getCareInstructions() {
        return careInstructions;
    }

    public boolean isTrained() {
        return isTrained;
    }

    public void setAdopted(boolean adopted){
        this.isAdopted = adopted;
    }

    public void setTrained(boolean trained){
        this.isTrained = trained;
    }

}
