/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import Model.Project;
import java.io.FileNotFoundException;

/**
 *
 * @author BN
 */
public class TestMain {
    public static void main(String[] args) throws FileNotFoundException {
        Project[] projects = ProjectFile.readFile("src/DataFile/projects.txt");
        System.out.println(projects);
        ProjectFile.writeFile(projects,"src/DataFile/output.txt");
    }
 
}
