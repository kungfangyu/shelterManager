package uk.ac.ncl.csc.coursework.pet;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public final class PetID {

    private static final Map<String, PetID> uniquePetID = new HashMap<String, PetID>();

    private static final Random random = new Random();
    private final char letter;
    private final String numbers, strRep ;

    public PetID(char letter, String numbers) {
        this.letter = letter;
        this.numbers = numbers;;
        this.strRep = this.letter + this.numbers;
    }

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
