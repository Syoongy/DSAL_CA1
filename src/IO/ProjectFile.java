/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author BN
 */
public class ProjectFile {
    
    public static void readFile() throws FileNotFoundException{
        BufferedReader br;
        int numOfProject;
        
        try{	
            br = new BufferedReader(new FileReader("projects.txt"));		

	    String firstLine = br.readLine();
            numOfProject = Integer.parseInt(firstLine);
            
	    while (br.readLine() != null) {
	
	    }
            
        }
        catch (IOException ioe) 
        {
	   ioe.printStackTrace();
        } 

        
    }
    
    public static void writeFile(){
        
    }
}
