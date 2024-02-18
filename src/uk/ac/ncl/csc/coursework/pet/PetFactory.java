package uk.ac.ncl.csc.coursework.pet;

public abstract class PetFactory {
    public static final String DOG = "Dog";
    public static final String CAT = "Cat";

    public static Pet createPet(String petType){
        PetID petID = PetID.getInstance();
        switch (petType) {
            case DOG:
                return new Dog(petID);
            case CAT:
                return new Cat(petID);
            default:
                throw new IllegalArgumentException("Invalid pet type: " + petType);
        }
    }

}
