/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebookproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Veronika
 */
public class PhoneBook {

    TreeMap<String, String> phoneRecords = new TreeMap<>();
    TreeMap<String, Long> outgoingCallsPerRecord = new TreeMap<>();

    public PhoneBook(File file) throws IOException {
        BufferedReader inputStream = new BufferedReader(new FileReader(file));
        String line;
        String[] recordValues;
        String name;
        String phone;
        while ((line = inputStream.readLine()) != null) {
            recordValues = line.trim().split(",");
            name = recordValues[0];
            phone = recordValues[1];
            if (isValidPhoneNumber(phone)) {
                phoneRecords.put(name, phone);
            }
        }
    }

//    public TreeMap<String, String> getPhoneRecords() {
//        return phoneRecords;
//    }
//    public void setPhoneRecords(TreeMap<String, String> phoneRecords) {
//        this.phoneRecords = phoneRecords;
//    }
    public String addRecord(String name, String phone) {
        if (isValidPhoneNumber(phone)) {
            this.phoneRecords.put(name, phone);
            return "New record added.";
        } else {
            return phone + " is not valid phone number!";
        }
    }

    public void removeRecord(String name) {
        this.phoneRecords.remove(name);
        this.outgoingCallsPerRecord.remove(name);
    }

    public String getPhoneByName(String name) {
        return this.phoneRecords.get(name);
    }

    public void printAllRecordsSortedByName() {
        this.phoneRecords.keySet().forEach((String name) -> {
            System.out.println(name + " - " + this.phoneRecords.get(name));
        });
    }

    public void printMostDialedRecords() {

    }

    public boolean isValidPhoneNumber(String phone) {
        String pattern = "^(\\+359|0|00359)(8[7-9])([2-9])(\\d{6})$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(phone);
        return m.matches();
    }
}
