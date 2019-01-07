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

//@author Peter Mitchell
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
            //System.out.println("Could not find file. exists: "+fileToSave.exists()+", is file: "+fileToSave.isFile());
            return false;
        }
    }
    
    //create the new file, and return if it worked
    public boolean newSave(String fileLocation) { 
        //File fileToSave = new File(fileLocation); //always use \\!!!
        try {
            try {
                File fileToSave = new File(fileLocation);
                fileToSave.createNewFile(); 
                return testSave(fileLocation);
            } catch (Exception ex) {
                System.out.println("Failed to create file.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("an unknown error has occured.");
            return false;
        }
    }
    
    public boolean makeSave(String fileLocation, String[][] logins) {
        //File fileToSave = new File(fileLocation); //always use \\!!!
        try {
            //variables used
            BufferedReader fileToSave;
            try {
                fileToSave = new BufferedReader(new FileReader(fileLocation));
            } catch (FileNotFoundException  error) {
                System.out.println("File not found: "+error);
                return false;
            }
            String line;
            StringBuffer inputBuffer = new StringBuffer();
            
            //line by line getting the file so it doesn't overwrite
            try {
                while ((line = fileToSave.readLine()) != null) {
                    inputBuffer.append(line+'\n');
                }
            } catch (Exception e) {
                System.out.println("An unknown error has occured."+e);
                return false;
            }
            String inputStr = inputBuffer.toString(); //turns the inputbuffer to a string
            //splits the string by line breaks
            ArrayList<String> inputList = new ArrayList<String>(Arrays.asList(inputStr.split("\n"))); 
            try {
                fileToSave.close(); //closes the file reader.
            } catch (java.io.IOException e) {
                System.out.println("File closing failed. (minor bug, should be able to be ignored)"+e);
            }
            for (int i = 0; i < inputList.size(); i++) {
                for (String[] login : logins) {
                    if (inputList.get(i).equals(login[0] + "," + login[1] + "," + login[2] + "," + login[3])) {
                        for (int i3 = 0; i3 < login.length; i3++) {
                            login[i3] = "";
                        }
                    }
                }
            }

            try {
                for (int i = 0; i < 100; i++) {
                    //only put it in if it isn't empty.
                    //System.out.println("attempt1: "+(!logins[i][0].equals("")&&!logins[i][1].equals("")&&!logins[i][2].equals("")));
                    if (!logins[i][0].equals("")&&!logins[i][1].equals("")&&!logins[i][2].equals("")) {
                        inputList.add((logins[i][0] + "," + logins[i][1] + "," + logins[i][2] + "," + logins[i][3]));
                        //System.out.println("Test is this reached?");
                    }
                }
            } catch (Exception e) {
                //do nothing. This often gives a bugged error
            }

            FileOutputStream fileOut;
            try {
                fileOut = new FileOutputStream(fileLocation);
            } catch (FileNotFoundException  error) {
                System.out.println("File not found for writing: "+error);
                return false;
            }
            inputStr = String.join(System.lineSeparator(), inputList);
            //System.out.println(inputStr);
            //System.out.println(inputStr.getBytes());
            try {
                fileOut.write(inputStr.getBytes());
                fileOut.close();
            } catch (java.io.IOException e) {
                System.out.println("Possible file writing erorr."+e);
            }
            
            return true;
        } catch (Exception e) {
            System.out.println("an unknown error has occured."+e);
            return false;
        }
    }
}




