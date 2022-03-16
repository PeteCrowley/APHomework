import java.util.ArrayList;

public class Kennel{
    private final ArrayList<Pet> petList;

    public void allSpeak(){
        for(Pet p : petList)
            System.out.println("The " + p.getClass().toString().substring(6) + " named " + p.getName() + " says " + p.speak());
    }

    public Kennel(){
        petList = new ArrayList<>();
    }

    public void addPet(Pet pet){
        petList.add(pet);
    }

    public static void main(String[] args) {
        Kennel kennel = new Kennel();
        kennel.addPet(new LoudDog("Arch"));
        kennel.addPet(new Cat("Mandy"));
        kennel.addPet(new Dog("Fido"));
        kennel.allSpeak();
    }

}
