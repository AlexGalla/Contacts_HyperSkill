package contacts;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        PhoneBook phoneBook;
        File file = new File("G:\\phonebook.db");

        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            phoneBook = (PhoneBook) ois.readObject();
        } catch (EOFException e) {
            phoneBook = new PhoneBook();
        }

        phoneBook.menu();

        /*FileOutputStream outputStream = new FileOutputStream("G:\\phonebook.db");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(phoneBook);
        objectOutputStream.close();*/
    }
}
