package contacts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook implements Serializable {
    private ArrayList<Record> records = new ArrayList<>();

    PhoneBook() {
    }

    public void menu() {
        boolean flag = true;
        while (flag) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("[menu] Enter action (add, list, search, count, exit):");
            String input = scanner.nextLine();
            switch (input) {
                case "add":
                    System.out.println("[add] Enter the type (person, organization): ");
                    String recordType = scanner.nextLine();
                    if (recordType.equals("person")) {
                        addRecord(new Person());
                    } else {
                        addRecord(new Organization());
                    }
                    break;
                case "list":
                    listMenu(scanner);
                    System.out.println();
                    break;
                case "search":
                    searchMenu(scanner);
                    break;
                case "count":
                    System.out.println("The Phone Book has " + records.size() + " records.");
                    System.out.println();
                    break;
                case "exit":
                    flag = false;
                    break;
                default:
                    System.out.println("Wrong input!");
                    break;
            }
        }
    }

    private void addRecord(Record record) {
        records.add(record);
        System.out.println("The record added.");
        System.out.println();
    }

    private void listMenu(Scanner scanner) {
        if (records.size() == 0) {
            System.out.println("No records to show info!");
        } else {
            printRecords();
            while (true) {
                System.out.println();
                System.out.println("[list] Enter action ([number], back): ");
                String listInput = scanner.nextLine();
                if (isNumber(listInput)) {
                    if (Integer.parseInt(listInput) >= 1 && Integer.parseInt(listInput) <= records.size()) {
                        records.get(Integer.parseInt(listInput) - 1).printRecordData();
                        System.out.println();
                        recordMenu(scanner, Integer.parseInt(listInput) - 1);
                        break;
                    } else {
                        System.out.println("Wrong number!");
                    }
                } else if (listInput.equals("back")) {
                    break;
                } else {
                    System.out.println("Wrong input!");
                }
            }
        }
    }

    private void printRecords() {
        for (int i = 0; i < records.size(); i++) {
            System.out.println((i+1) + ". " + records.get(i).toString());
        }
    }

    private void searchMenu(Scanner scanner) {
        boolean searchMenuFlag = true;
        while (searchMenuFlag) {
            System.out.println("Enter search query: ");
            String searchString = scanner.nextLine();
            ArrayList<Record> searchResultArray = new ArrayList<>();
            for (int i = 0; i < records.size(); i++) {
                if (records.get(i).containsString(searchString)) {
                    searchResultArray.add(records.get(i));
                }
            }
            if (searchResultArray.size() == 0) {
                System.out.println("No results!");
                searchMenuFlag = false;
            } else {
                if (searchResultArray.size() == 1) {
                    System.out.println("Found 1 result:");
                    System.out.println("1. " + searchResultArray.get(0).toString());
                } else {
                    System.out.println("Found " + searchResultArray.size() + " results: ");
                    for (int i = 0; i < searchResultArray.size(); i++) {
                        System.out.println((i + 1) + ". " + searchResultArray.get(i).toString());
                    }
                }
                System.out.println("[search] Enter action ([number], back, again): ");
                String searchMenuInput = scanner.nextLine();
                if (isNumber(searchMenuInput)) {
                    if (Integer.parseInt(searchMenuInput) <= searchResultArray.size() && Integer.parseInt(searchMenuInput) >= 1) {
                        searchResultArray.get(Integer.parseInt(searchMenuInput) - 1).printRecordData();
                        System.out.println();
                        recordMenu(scanner, records.indexOf(searchResultArray.get(Integer.parseInt(searchMenuInput) - 1)));
                        searchMenuFlag = false;
                    } else {
                        System.out.println("Wrong number!");
                    }
                } else if (searchMenuInput.equals("again")) {
                        continue;
                } else if (searchMenuInput.equals("back")) {
                        searchMenuFlag = false;
                } else {
                        System.out.println("Wrong input!");
                }
            }
        }
    }

    public boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    private void recordMenu(Scanner scanner, int i) {
        String recordMenuInput;
        boolean recordMenuFlag = true;
        while (recordMenuFlag) {
            System.out.println("[record] Enter action (edit, delete, menu):");
            recordMenuInput = scanner.nextLine();
            switch (recordMenuInput) {
                case "edit":
                    records.get(i).editRecord();
                    System.out.println("Saved");
                    records.get(i).printRecordData();
                    System.out.println();
                    //recordFlag = false;
                    break;
                case "delete":
                    records.remove(i);
                    System.out.println("Deleted!");
                    recordMenuFlag = false;
                    break;
                case "menu":
                    recordMenuFlag = false;
                    break;
                default:
                    System.out.println("Wrong input!");
                    break;
            }
        }
    }
}
