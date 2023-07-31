import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int boxesNum = 100;
        int generation = 10000;
        int sum = 0;
        int released = 0;

        for(int i = 0; i < generation; i++) {
            sum += prisonRandom(createRoom(boxesNum));
            if(prisonRandom(createRoom(boxesNum)) == boxesNum) {
                released++;
            }
        }
        System.out.println("---RANDOM--- \nOne person's chance to find their number: " + sum*1.0/generation/boxesNum);
        System.out.println("Chance that everyone will find their number: " + released*1.0/generation);
        sum = 0;
        released = 0;


        for(int i = 0; i < generation; i++) {
            sum += prisonStrategy(createRoom(boxesNum));
            if(prisonStrategy(createRoom(boxesNum)) == boxesNum) {
                released++;
            }
        }
        System.out.println("\n---STRATEGY--- \nOne person's chance to find their number:" + sum*1.0/generation/boxesNum);
        System.out.println("Chance that everyone will find their number: " + released*1.0/generation);
    }

    public static int prisonStrategy(List<Integer> room) {
        int coincidenceCounter = 0;
        int nextBox = 0;

        for(int i = 0; i < room.size(); i++) {
            for(int j = 0; j < room.size()/2; j++) {
                int boxToOpen;
                if(j == 0)
                    boxToOpen = i;
                else
                    boxToOpen = nextBox;

                int numberInOpenedBox = room.get(boxToOpen);
                if (i == numberInOpenedBox) {
                    coincidenceCounter++;
                    break;
                }
                nextBox = numberInOpenedBox;
            }
        }
        return coincidenceCounter;
    }

    public static int prisonRandom(List<Integer> room) {
        List<Integer> alreadyOpen = new ArrayList<>();
        int coincidenceCounter = 0;
        Random generator = new Random();

        for(int i = 0; i < room.size(); i++) {
            while(true){
                int boxNumber = generator.nextInt(room.size());
                if(!alreadyOpen.contains(boxNumber)) {
                    if(i == room.get(boxNumber)){
                        coincidenceCounter++;
                    }
                    alreadyOpen.add(boxNumber);
                }
                if(alreadyOpen.size() == 50) {
                    alreadyOpen.clear();
                    break;
                }
            }
        }
        return coincidenceCounter;
    }

    public static List<Integer> createRoom(int boxesNum) {
        List<Integer> boxes = new ArrayList<>();
        Random generator = new Random();

        do {
            int randomNumber = generator.nextInt(boxesNum);

            if (!boxes.contains(randomNumber))
                boxes.add(randomNumber);

        } while (boxes.size() != boxesNum);
        return boxes;
    }
}