package uk.ac.ncl.csc.coursework.test;

import uk.ac.ncl.csc.coursework.pet.PetID;

public class PetTest {
    public static void main(String[] args) {
        PetTest petTest = new PetTest();
        System.out.println("Test create pet");
        PetTest.getPetIDInfo();
    }
    private static void getPetIDInfo() {
        for(int i = 0; i < 5; i++) {
            PetID petId = PetID.getInstance();
            System.out.println("Generate PetID:" + petId);
        }
    }
}
