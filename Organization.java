package contacts;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Organization extends Record{

    String organizationName;
    String address;

    Organization() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the organization name:");
        setOrganizationName(scanner.nextLine());
        System.out.println("Enter the address:");
        setAddress(scanner.nextLine());
        System.out.println("Enter the number:");
        setPhoneNumber(scanner.nextLine());
        timeCreated = LocalDateTime.now();
        timeLastEdited = timeCreated;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getAddress() {
        return address;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void printRecordData() {
        System.out.println("Organization name: " + getOrganizationName());
        System.out.println("Address: " + getAddress());
        System.out.println("Number: " + getPhoneNumber());
        System.out.println("Time created: " + timeCreated);
        System.out.println("Time last edit: " + timeLastEdited);
    }

    @Override
    public void editRecord() {
        System.out.println("Select a field (name, address, number): ");
        Scanner editScanner = new Scanner(System.in);
        String editInput = editScanner.nextLine();
        switch (editInput) {
            case "name":
                System.out.println("Enter the organization name: ");
                setOrganizationName(editScanner.nextLine());
                timeLastEdited = LocalDateTime.now();
                break;
            case "address":
                System.out.println("Enter the address: ");
                setAddress(editScanner.nextLine());
                timeLastEdited = LocalDateTime.now();
                break;
            case "number":
                System.out.println("Enter the number: ");
                setPhoneNumber(editScanner.nextLine());
                timeLastEdited = LocalDateTime.now();
                break;
            default:
                System.out.println("Wrong input!");
                break;
        }
    }

    @Override
    public boolean containsString(String string) {
        Pattern pattern = Pattern.compile(".*" + string + ".*", Pattern.CASE_INSENSITIVE);
        Matcher matcherName = pattern.matcher(organizationName);
        Matcher matcherPhoneNumber = pattern.matcher(phoneNumber);
        if (matcherName.matches() || matcherPhoneNumber.matches()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return getOrganizationName();
    }
}
