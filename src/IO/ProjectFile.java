/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import Enum.Gender;
import Model.Project;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author BN
 */
public class ProjectFile {
    
    public static void readFile(String fileName) throws FileNotFoundException{
        BufferedReader br;
        String line, token;
        int numOfProject;
        
        try{	
            br = new BufferedReader(new FileReader(fileName));		

	    String firstLine = br.readLine();
            numOfProject = Integer.parseInt(firstLine);
            Project[] projects = new Project[numOfProject];
            line = br.readLine();
            
	    for(int u=0;u<numOfProject;u++){
                StringTokenizer tokenizer = new StringTokenizer(br.readLine(),",");
                while(tokenizer.hasMoreTokens()) {//process tokens in line
                    token = tokenizer.nextToken();
                    String projectTitle = tokenizer.nextToken();
                    String school = tokenizer.nextToken();
                    String supervisorName = tokenizer.nextToken();
                    int numOfStudents = Integer.parseInt(tokenizer.nextToken());
                    Project project = new Project(projectTitle,school,supervisorName,numOfStudents);
                    System.out.println(projectTitle);
                    for(int i=0;i<numOfStudents;i++){
                        String admissionNo = tokenizer.nextToken();
                        String name = tokenizer.nextToken();
                        String course = tokenizer.nextToken();
                        String g = tokenizer.nextToken();
                        Gender gender = Gender.MALE;
                        if(g.equals("F")){
                             gender = Gender.FEMALE;
                        }
                        project.addStudent(admissionNo,name, course,gender);
                    }
                    projects[u] = project;
                }//close inner while
  
                line = br.readLine();
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
