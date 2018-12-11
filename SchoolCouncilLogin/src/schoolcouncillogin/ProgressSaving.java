package schoolcouncillogin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Peter Mitchell
 */
public class ProgressSaving {

    private static boolean isFloat(String str1) {
        try {
            Float.parseFloat(str1);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public String fixFileLocation(String fileLocation) {
        int stringLength = fileLocation.length();
        for (int i = 0; i < stringLength; i++) {
            if (fileLocation.charAt(i) == '\\' && i+1 < stringLength && fileLocation.charAt(i+1) != '\\') {
                fileLocation = fileLocation.substring(0, i)+"\\"+fileLocation.substring(i, stringLength);
                i++;
            } else if (fileLocation.charAt(i) == '\\' && fileLocation.charAt(i) != '\\') {
                fileLocation = fileLocation+"\\";
            }
        }
        
        return fileLocation;
    }
    
    public boolean testSave(String fileLocation) {
        File fileToSave = new File(fileLocation); //always use \\!!!
        if (fileToSave.exists() && fileToSave.isFile()) {
            try {
                Scanner scan = new Scanner(fileToSave);
                scan.useDelimiter("\n");
                
                String foundString;
                while(scan.hasNext()) {
                  foundString = scan.next();
                  //System.out.println(foundString);
                }
                return true;
            } catch(Exception e) {
                System.out.println("Unknown error");
                return false;
            }
        } else {
            System.out.println("Could not find file. exists: "+fileToSave.exists()+", is file: "+fileToSave.isFile());
            return false;
        }
    }
    
    public boolean makeSave(String fileLocation, float[] betsToSave) {
        //File fileToSave = new File(fileLocation); //always use \\!!!
        try {
            BufferedReader fileToSave = new BufferedReader(new FileReader(fileLocation));
            String line;
            StringBuffer inputBuffer = new StringBuffer();

            while ((line = fileToSave.readLine()) != null) {
                inputBuffer.append(line+'\n');
            }
            String inputStr = inputBuffer.toString();
            ArrayList<String> inputList = new ArrayList<String>(Arrays.asList(inputStr.split("\n")));
            fileToSave.close();
            

            InputLibrary getInput = new InputLibrary();
            System.out.println("Please make a save id. This is needed to retrieve the save in the future.");
            String saveIdSearch;
            
            saveIdSearch = getInput.getString("please input the save id: ");
            
            boolean foundSave = false;
            int startedPoint;
            for (int i = 0; i < inputList.size(); i++) {
                if (foundSave == false && inputList.get(i).equals("p"+saveIdSearch)) {
                    foundSave = true;
                } else if (foundSave && isFloat(inputList.get(i))) {
                    //System.out.println("This is a test3."+i);
                    startedPoint = i;
                    inputList.set(i, Float.toString(betsToSave[i-startedPoint]));
                    //startedPoint++;
                } else if (foundSave) {
                    break;
                }
            }
            
            if (foundSave == false) {
                inputList.add("p"+saveIdSearch);
                try {
                    for (int i = 0; i < 11; i++) { 
                        if (betsToSave[i] > 0) {
                            inputList.add(Float.toString(betsToSave[i]));
                        }
                    }
                } catch (Exception ex) {
                    //do nothing.
                }
                    
            }
            
            //inputStr = String.join("\n", inputList);
            
            FileOutputStream fileOut = new FileOutputStream(fileLocation);
            //fileOut.write(inputStr.getBytes());
            fileOut.close();
            return true;
        } catch (Exception e) {
            System.out.println("an unknown error has occured."+e);
            return false;
        }
    }
    
    public boolean newSave(String fileLocation, float[] betsToSave) {
        //File fileToSave = new File(fileLocation); //always use \\!!!
        try {
            try {
                File fileToSave = new File(fileLocation);
                fileToSave.createNewFile(); 
            } catch (Exception ex) {
                System.out.println("Failed to create file.");
                return false;
            }
            
            ArrayList<String> inputList = new ArrayList<String>();

            InputLibrary getInput = new InputLibrary();
            System.out.println("Please make a save id. This is needed to retrieve the save in the future.");
            String saveIdSearch;
            saveIdSearch = getInput.getString("please input the save id: ");
            
            inputList.add("p"+saveIdSearch);
            try {
                for (int i = 0; i < 11; i++) { 
                    if (betsToSave[i] > 0) {
                        inputList.add(Float.toString(betsToSave[i]));
                    }
                }
            } catch (Exception ex) {
                //do nothing.
            }
            
            //String saveString = String.join("\n", inputList); 
            
            FileOutputStream fileOut = new FileOutputStream(fileLocation);
            //fileOut.write(saveString.getBytes());
            fileOut.close();
            return true;
        } catch (Exception e) {
            System.out.println("an unknown error has occured.");
            return false;
        }
    }
}




