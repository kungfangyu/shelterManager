package uk.ac.ncl.csc.coursework.pet;

public class Cat implements Pet {
    private PetID petID;

    private boolean adopted;

    public Cat(PetID petID) {
        this.petID = petID;
        this.adopted = false;
    }

    @Override
    public PetID getPetID() {
        return petID;
    }

    @Override
    public String getPetType() {
        return "Cat";
    }

    @Override
    public boolean getAdopted() {
        return adopted;
    }

    @Override
    public String getCareInstructions() {
        return "Feed twice a day.";
    }

    @Override
    public boolean isTrained() {
        return false;
    }
}
