package schoolcouncillogin;

/**
 * @author Peter Mitchell
 */
import java.util.Scanner;

public class InputLibrary {
    public String getString(String stringToAsk) {
        String stringToReturn;
        Scanner scan = new Scanner(System.in);
        
        System.out.printf(stringToAsk);
        stringToReturn = scan.next();

        return stringToReturn;
    }
    
    public String getStringLine(String stringToAsk) {
        String stringToReturn;
        Scanner scan = new Scanner(System.in);
        
        System.out.printf(stringToAsk);
        stringToReturn = scan.nextLine();

        return stringToReturn;
    }
    
    public int getInt(String stringToAsk) {
        int intToReturn;
        Scanner scan = new Scanner(System.in);
        
        while (true) {
            System.out.printf(stringToAsk);
            if (scan.hasNextInt()) {
                intToReturn = scan.nextInt();
                return intToReturn;
            } else {
                System.out.println("That is not an integer.");
                scan.next();
            }
        }
    }
    
    public float getFloat(String stringToAsk) {
        float floatToReturn;
        Scanner scan = new Scanner(System.in);
        
        while (true) {
            System.out.printf(stringToAsk);
            if (scan.hasNextFloat()) {
                floatToReturn = scan.nextFloat();
                return floatToReturn;
            } else {
                System.out.println("That is not an float.");
                scan.next();
            }
        }
    }
}

