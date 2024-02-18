package uk.ac.ncl.csc.coursework.pet;

public class Dog implements Pet{
    private PetID petID;
    private boolean adopted;
    private boolean trained;

    Dog(PetID petID) {
        this.petID = petID;
        this.adopted = false;
        this.trained = false;
    }


    @Override
    public PetID getPetID() {
        return petID;
    }

    @Override
    public String getPetType() {
        return "Dog";
    }

    @Override
    public boolean getAdopted() {
        return adopted;
    }

    @Override
    public String getCareInstructions() {
        return "Needs daily walks and three meals a day.";
    }

    @Override
    public boolean isTrained() {
        return trained;
    }

    public void setAdopted(boolean adopted){
        this.adopted = adopted;
    }

    public void setTrained(boolean trained){
        this.trained = trained;
    }
}
