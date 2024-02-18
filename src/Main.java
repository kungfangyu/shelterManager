import uk.ac.ncl.csc.coursework.customer.Name;
import uk.ac.ncl.csc.coursework.pet.PetID;

public class Main {

    public static void main(String[] args) {
        PetID catId = PetID.getInstance();
        System.out.println(catId);
        PetID dogId = PetID.getInstance();
        System.out.println(dogId);
    }
}