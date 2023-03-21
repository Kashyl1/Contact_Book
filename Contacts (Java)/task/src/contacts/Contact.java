package contacts;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Interface for Method Factory
 */
public interface Contact extends Serializable {
    String getName();
    void setName(String name);
    void setPhoneNumber(String phoneNumber);
    void setLastEditTime();
}

/**
 * Class Person
 */
class Person implements Contact {
    private String name;
    private String surname;
    private String phoneNumber;
    private String birthDate;
    private String gender;
    private final LocalDateTime createdTime;
    private LocalDateTime lastEditTime;


    public Person(String name, String surname, String phoneNumber, String birthDate, String gender) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.gender = gender;
        this.createdTime = LocalDateTime.now();
        this.lastEditTime = LocalDateTime.now();
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    @Override
    public void setLastEditTime() {
        this.lastEditTime = LocalDateTime.now();
    }
    @Override
    public String toString() {
        return  "Name: " + name + "\nSurname: " + surname + "\nBirth date: " + birthDate + "\nGender: " + gender + "\nNumber: " + phoneNumber + "\nTime created: " + getFormattedCreatedTime() + "\nTime last edit: " + getFormattedLastEditTime();
    }
    public String getFormattedCreatedTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return createdTime.format(formatter);
    }

    public String getFormattedLastEditTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return lastEditTime.format(formatter);
    }
}

/**
 * Class Organization
 */
class Organization implements Contact {
    String name;
    String address;
    String phoneNumber;
    private final LocalDateTime createdTime;
    private LocalDateTime lastEditTime;

    public Organization(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.createdTime = LocalDateTime.now();
        this.lastEditTime = LocalDateTime.now();
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setLastEditTime() {
        this.lastEditTime = LocalDateTime.now();
    }
    @Override
    public String toString() {
        return  "Organization name: " + name + "\nAddress: " + address + "\nNumber: " + phoneNumber + "\nTime created: " + getFormattedCreatedTime() + "\nTime last edit: " + getFormattedLastEditTime();
    }
    public String getFormattedCreatedTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return createdTime.format(formatter);
    }

    public String getFormattedLastEditTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return lastEditTime.format(formatter);
    }
}

/**
 * Create a Person/Organization object
 */
class ContactFactory implements Serializable{
    public Contact createContact(String name, String secondaryInfo, String phoneNumber, String birthDate, String gender) {
            return new Person(name, secondaryInfo, phoneNumber, birthDate, gender);
    }
    public Contact createOrganization(String name, String address, String phoneNumber) {
        return new Organization(name, address, phoneNumber);

    }
}
