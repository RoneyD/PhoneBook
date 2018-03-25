/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebookproject;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Veronika
 */
public class PhoneBookProject {

    public static void main(String[] args) {
        File myFile = new File("PhoneBook.txt");
        try {
            PhoneBook book = new PhoneBook(myFile);
            book.printAllRecordsSortedByName();
        } catch (IOException ex) {
            Logger.getLogger(PhoneBookProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
