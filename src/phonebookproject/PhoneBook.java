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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author Veronika
 */
public class PhoneBook {

    TreeMap<String, String> phoneRecords;
    Map<String, Long> outgoingCalls;

    public PhoneBook(File file) throws IOException {
        phoneRecords = new TreeMap<>();
        outgoingCalls = new HashMap<>();
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

    public TreeMap<String, String> getPhoneRecords() {
        return phoneRecords;
    }

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
    }

    public String getPhoneByName(String name) {
        return this.phoneRecords.get(name);
    }

    public void printAllRecordsSortedByName() {
        this.phoneRecords.keySet().forEach((String name) -> {
            print(name);
        });
    }

    public String makeCallTo(String name) {
        if (this.phoneRecords.containsKey(name)) {
            if (this.outgoingCalls.containsKey(name)) {
                this.outgoingCalls.put(name, outgoingCalls.get(name) + 1);
            } else {
                this.outgoingCalls.put(name, 1L);
            }
            return "Calling to" + name;
        } else {
            return "There is no record with name: " + name + "!";
        }
    }

    public void printTop5OutgoingCalls() {
        List<String> names = getNamesOfTop5OutgoingCalls();
        names.forEach((String name) -> {
            print(name);
        });
    }

    public void print(String name) {
        System.out.println(name + " - " + this.phoneRecords.get(name));
    }

    public List<String> getNamesOfTop5OutgoingCalls() {
        return this.outgoingCalls.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public boolean isValidPhoneNumber(String phone) {
        String pattern = "^(\\+359|0|00359)(8[7-9])([2-9])(\\d{6})$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(phone);
        return m.matches();
    }
}
