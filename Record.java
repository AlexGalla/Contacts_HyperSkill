package contacts;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Record implements Serializable {

    protected String phoneNumber = "";
    protected LocalDateTime timeCreated;
    protected LocalDateTime timeLastEdited;
    //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:MM");
    //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public abstract void printRecordData();

    public abstract void editRecord();

    public abstract boolean containsString(String string);

    @Override
    public abstract String toString();

    public String getPhoneNumber() {
        if (this.phoneNumber.isEmpty()) {
            return "[no number]";
        } else {
            return this.phoneNumber;
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumberValidation(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("Wrong number format!");
            this.phoneNumber = "";
        }
    }

    boolean phoneNumberValidation(String number) {
        Pattern pattern = Pattern.compile("(\\+?\\(\\w+\\))|(\\+?\\w+)|(\\+?\\w+( |-)\\w{2,}(( |-)(\\w{2,}))*)|(\\+?\\(\\w+\\)( |-)\\w{2,}(( |-)\\w{2,})*)|(\\+?\\w+( |-)\\(\\w{2,}\\)(( |-)\\w{2,})*)");
        Matcher matcher = pattern.matcher(number);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

}
