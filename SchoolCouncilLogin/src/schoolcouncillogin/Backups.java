/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolcouncillogin;

// @author S346902018

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Arrays;


public class Backups {
    
    Backups(String fileLocation) {
        try {
            if (SchoolCouncilLogin.backupDirectory.equals("\\\\backups.txt")) {
                Path currentRelativePath = Paths.get("");
                String currentPath = currentRelativePath.toAbsolutePath().toString();
                fileLocation = currentPath + SchoolCouncilLogin.backupDirectory;
                ProgressSaving save = new ProgressSaving();
                //System.out.println(save.fixFileLocation("1234567891011121314151617181920"));
                //System.out.println(fileLocation);
                fileLocation = save.fixFileLocation(fileLocation);
                //System.out.println(fileLocation);
                
            } else {
                return;
            }
        } catch (Exception ex) {
            System.out.println("Unknown error: Could make backup file."+ex);
        }
        
        try {
            //System.out.println(fileLocation);
            File fileToSave = new File(fileLocation); //fileLocation
            fileToSave.createNewFile(); 
            SchoolCouncilLogin.backupDirectory = fileLocation;
        } catch (Exception ex) {
            System.out.println("Failed to create backup: "+ex);
        }
    }
    
    public boolean makeBackup(String fileLocation, String[][] logins) {
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
