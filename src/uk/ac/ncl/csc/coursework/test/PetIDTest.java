package uk.ac.ncl.csc.coursework.test;

import uk.ac.ncl.csc.coursework.pet.PetID;

public class PetIDTest {
    public static void main(String[] args) {
        PetIDTest petIDTest = new PetIDTest();
        System.out.println("Test create pet");
        PetIDTest.getPetIDInfo();
    }

    //Generate 5 pet id
    private static void getPetIDInfo() {
        for(int i = 0; i < 5; i++) {
            PetID petId = PetID.getInstance();
            System.out.println("Generate PetID:" + petId);
        }
    }
}
