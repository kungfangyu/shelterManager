package uk.ac.ncl.csc.coursework.pet;

public interface Pet {
    //You can add additional methods (e.g. setter methods) if your solution requires that


    /**
     * Returns the pet ID.
     * All pets must have an ID
     * @return the PetID object
     */
    PetID getPetID();


    /**
     * Returns the pet type.
     * a pet can be either a cat or a dog
     * @return a string (cat or dog)
     */
    String getPetType();


    /**
     * Returns a boolean indicating whether or not the pet is adopted.     *
     * @return true if pet is adopted and false otherwise
     */
    boolean getAdopted();


    /**
     * Returns a String indicating the care instructions.     *
     * @return a string
     */
    String getCareInstructions();

    /**
     * Returns a boolean if pet can be trained.     *
     * @return a boolean
     */
    boolean isTrained();

    void setAdopted(boolean adopted);
    void setTrained(boolean trained);



}



