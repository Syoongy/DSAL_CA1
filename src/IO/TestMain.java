/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import java.io.FileNotFoundException;

/**
 *
 * @author BN
 */
public class TestMain {
    public static void main(String[] args) throws FileNotFoundException {
        ProjectFile.readFile("src/DataFile/projects.txt");
    }
 
}
