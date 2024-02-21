package uk.ac.ncl.csc.coursework.pet;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public final class PetID {

    private static final Map<String, PetID> uniquePetID = new HashMap<>();

    private static final Random random = new Random();
    private final char letter;
    private final String numbers, strRep ;


    public PetID(char letter, String numbers) {
        this.letter = letter;
        this.numbers = numbers;
        this.strRep = this.letter + this.numbers;
    }


    /**
     * Generates a unique PetID instance.
     * This method creates a new PetID composed of a random letter (A-Z) followed by two random digits (00-99)
     * to ensure uniqueness. It checks against existing PetIDs to avoid duplicates. If a generated ID already exists,
     * the method will continue to generate new IDs until a unique one is found.
     *
     * @return A unique PetID instance with a format of one uppercase letter followed by two digits.
     */
    public static PetID getInstance() {

        char letter = (char) ('A' + random.nextInt(26));

        String numbers = String.format("%02d", random.nextInt(100));
        String strRep = letter + numbers; // generate once

        //Check whether the generated id is unique
        while(uniquePetID.containsKey(strRep)){
            letter = (char) ('A' + random.nextInt(26));
            numbers = String.format("%02d", random.nextInt(100));
            strRep = letter + numbers;
        }
        PetID petID = new PetID(letter, numbers);
        uniquePetID.put(strRep, petID);
        return petID;
    }

    public char getLetter() {
        return letter;
    }

    public String getNumber() {
        return numbers;
    }

    public String getStrRap() {
        return strRep;
    }

    @Override
    public String toString() {
        return strRep;
    }
}
