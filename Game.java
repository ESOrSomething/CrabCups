import java.util.ArrayList;

public class Game {
    public static void main(String[] args) {
        String orig = "157623984";
        String held;
        ArrayList<Integer> cupCircle = readInString2(orig);
        ArrayList<Integer> heldCups;

        int currentCup = cupCircle.getFirst();
        int currentCupIndex = 0;

        // System.out.println(findDestination(2, cupCircle));
        // cupCircle = insertCups(cupCircle.indexOf(findDestination(8, cupCircle)), cupCircle, heldCups);
        // System.out.println(cupCircle);

        // heldCups = strip(4, cupCircle);
        // System.out.println(heldCups);
        // System.out.println(cupCircle);

        for (int i = 0; i < 10000000; i++) {
            if (i % 1000 == 0) {
                System.out.println("--Move " + i +"--");
            }
            // System.out.println("cupCircle (before shifting): " + cupCircle);

            heldCups = strip(currentCup, cupCircle, 3);
            // System.out.println("pick up: " + heldCups);
            findDestination(currentCup, cupCircle);
            int destination = cupCircle.indexOf(findDestination(currentCup, cupCircle));
            insertCups((destination % 9), cupCircle, heldCups);
            // System.out.println(cupCircle);
            // System.out.println();

            currentCupIndex = (cupCircle.indexOf(currentCup) + 1) % 9;
            currentCup = cupCircle.get(currentCupIndex);
        }

        heldCups = strip(1, cupCircle, 8);
        System.out.println(arrayListToString(heldCups));
    }
    public static ArrayList<Integer> strip(int currentCup, ArrayList<Integer> cupCircle, int cups) {
        int currentCupIndex = cupCircle.indexOf(currentCup);

        ArrayList<Integer> heldCups = new ArrayList<Integer>();

        currentCupIndex += 1;

        for (int i = 0; i < cups; i++) {
            if (currentCupIndex == cupCircle.size()) {
                currentCupIndex = 0;
            }
            heldCups.add(cupCircle.get(currentCupIndex));
            cupCircle.remove(currentCupIndex);
        }
        return heldCups; 
    }

    public static int findDestination(int currentCup, ArrayList<Integer> cupCircle) {
        int target = currentCup - 1;
        while (!cupCircle.contains(target)) {
            target--;
            if (target < 1) {
                target = 9;
            }
        }
        return target;
    }
    
    public static ArrayList<Integer> readInString(String orig) {
        ArrayList<Integer> myArrayList = new ArrayList<Integer>();
        for (int i = 0; i < orig.length(); i++) {
            myArrayList.add(Integer.parseInt(orig.substring(i,i+1)));
        }
        return myArrayList;
    }

    public static ArrayList<Integer> readInString2(String orig) {
        ArrayList<Integer> myArrayList = new ArrayList<Integer>();
        for (int i = 0; i < 1000000; i++) {
            if (i < orig.length()) {
                myArrayList.add(Integer.parseInt(orig.substring(i,i+1)));
            } else {
                myArrayList.add(i+1);
            }
        }
        return myArrayList;
    }

    public static ArrayList<Integer> insertCups(int dest, ArrayList<Integer> cupCircle, ArrayList<Integer> heldCups){
        for (int i = 0; i < heldCups.size(); i++) {
            cupCircle.add(dest + i + 1, heldCups.get(i));
        }
        
        return cupCircle;
    }

    public static String arrayListToString(ArrayList<Integer> myArrayList) {
        String myString = "";
        for (int i = 0; i < myArrayList.size(); i++) {
            myString += String.valueOf(myArrayList.get(i));
        }
        return myString;
    }


}
