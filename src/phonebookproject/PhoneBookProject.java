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
        PhoneBook book = null;
        try {
            book = new PhoneBook(myFile);

        } catch (IOException ex) {
            Logger.getLogger(PhoneBookProject.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        book.addRecord("Aa", "+359878224477");
        book.addRecord("Casd", "+359878224477");
        book.addRecord("Bbb", "+359878224477");
        System.out.println("------------------all records sorted by name-----------------");
        book.printAllRecordsSortedByName();

        book.makeCallTo("Aa");
        book.makeCallTo("Aa");
        book.makeCallTo("Casd");
        book.makeCallTo("Casd");
        book.makeCallTo("Casd");
        book.makeCallTo("Bbb");
        book.makeCallTo("Zazo");
        book.makeCallTo("Zazo");
        book.makeCallTo("Zazo");
        book.makeCallTo("Zazo");
        book.makeCallTo("Zazo");
        book.makeCallTo("Anna");
        book.makeCallTo("Mariq");
        System.out.println("--------------------top 5 outgoint calls------------------");
        book.printTop5OutgoingCalls();
    }

}
