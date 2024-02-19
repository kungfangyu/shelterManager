package uk.ac.ncl.csc.coursework.pet;

public class Cat extends PetFactory {
    public Cat(PetID petID, String careInstructions) {
        super(petID, "Cat", "Needs to feed two meals a day.");
    }
}
