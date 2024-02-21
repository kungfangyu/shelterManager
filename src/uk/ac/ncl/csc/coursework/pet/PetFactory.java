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

    /**
     * Creates and returns a new instance of a Pet based on the specified pet type.
     *
     * This method generates a new pet either as a Dog or a Cat, based on the input parameter. Each pet
     * is assigned a unique PetID and specific care instructions. For dogs, they are marked as untrained
     * initially. If an invalid pet type is specified, an IllegalArgumentException is thrown.
     *
     * @param petType The type of pet to create. Valid types are defined in {@link PetFactory} as DOG or CAT.
     * @return A new instance of a Pet, either a Dog or Cat, with a unique PetID and care instructions.
     * @throws IllegalArgumentException If the specified pet type is not recognized.
     */

    public static Pet getInstance(String petType){
        Pet pet;
        if(PetFactory.DOG.equals(petType)){
            String care = "Dogs must feed three meals and walk a day";
            pet = new Dog(PetID.getInstance(), petType, care);
            pet.setTrained(false); // initially dog should not be trained.
        } else if (PetFactory.CAT.equals(petType)) {
            String care = "Cat must feed two meals a day";
            pet = new Cat(PetID.getInstance(), petType, care);
        } else throw new IllegalArgumentException("Invalid pet type: " + petType);
        pets.put(pet.getPetID(), pet);
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
