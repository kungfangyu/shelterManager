package uk.ac.ncl.csc.coursework.pet;

public class Dog extends PetFactory{
    public Dog(PetID petID, String careInstructions) {
        super(petID, "Dog", "Needs daily walks and three meals a day.");
    }

}
