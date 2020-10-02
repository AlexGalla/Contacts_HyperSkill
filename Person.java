package contacts;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person extends Record{

    String name;
    String surname;
    LocalDate birthDate;
    Gender gender;

    enum Gender {
        M, F
    }

    Person() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name: ");
        setName(scanner.nextLine());
        System.out.println("Enter the surname: ");
        setSurname(scanner.nextLine());
        System.out.println("Enter the birth date: ");
        setBirthDate(scanner.nextLine());
        System.out.println("Enter the gender (M, F): ");
        setGender(scanner.nextLine());
        System.out.println("Enter the number: ");
        setPhoneNumber(scanner.nextLine());
        timeCreated = LocalDateTime.now();
        timeLastEdited = timeCreated;
    }

    public String getName() {
        return this.name;
    }
    public String getSurname() {
        return this.surname;
    }

    public String getBirthDate() {
        if (this.birthDate == null) {
            return "[no data]";
        } else {
            return this.birthDate.toString();
        }
    }

    public String getGender() {
        if (this.gender == null) {
            return "[no data]";
        } else if (this.gender == Gender.F) {
            return "F";
        } else {
            return "M";
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDate(String birthDate) {
        try {
            this.birthDate = LocalDate.parse(birthDate);
        } catch (DateTimeParseException e) {
            System.out.println("Bad birth date!");
            this.birthDate = null;
        }
    }

    public void setGender(String gender) {
        switch (gender) {
            case "M":
            case "m":
                this.gender = Gender.M;
                break;
            case "F":
            case "f":
                this.gender = Gender.F;
                break;
            default:
                System.out.println("Bad gender!");
                this.gender = null;
                break;
        }
    }


    @Override
    public void printRecordData() {
        System.out.println("Name: " + getName());
        System.out.println("Surname: " + getSurname());
        System.out.println("Birth date: " + getBirthDate());
        System.out.println("Gender: " + getGender());
        System.out.println("Number: " + getPhoneNumber());
        System.out.println("Time created: " + timeCreated);
        System.out.println("Time last edit: " + timeLastEdited);
    }

    @Override
    public void editRecord() {
        System.out.println("Select a field (name, surname, birth, gender, number): ");
        Scanner editScanner = new Scanner(System.in);
        String editInput = editScanner.nextLine();
        switch (editInput) {
            case "name":
                System.out.println("Enter the name: ");
                setName(editScanner.nextLine());
                timeLastEdited = LocalDateTime.now();
                break;
            case "surname":
                System.out.println("Enter the surname: ");
                setSurname(editScanner.nextLine());
                timeLastEdited = LocalDateTime.now();
                break;
            case "birth":
                System.out.println("Enter the birth date: ");
                setBirthDate(editScanner.nextLine());
                timeLastEdited = LocalDateTime.now();
                break;
            case "gender":
                System.out.println("Enter the gender (M, F):");
                setGender(editScanner.nextLine());
                timeLastEdited = LocalDateTime.now();
                break;
            case "number":
                System.out.println("Enter the number: ");
                setPhoneNumber(editScanner.nextLine());
                timeLastEdited = LocalDateTime.now();
                break;
        }
    }

    @Override
    public boolean containsString(String string) {
        Pattern pattern = Pattern.compile(".*" + string + ".*", Pattern.CASE_INSENSITIVE);
        Matcher matcherName = pattern.matcher(name);
        Matcher matcherSurname = pattern.matcher(surname);
        Matcher matcherPhoneNumber = pattern.matcher(phoneNumber);
        if (matcherName.matches() || matcherSurname.matches() || matcherPhoneNumber.matches()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return getName() + " " + getSurname();
    }
}
