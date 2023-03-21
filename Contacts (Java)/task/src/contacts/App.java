package contacts;

import java.io.*;
import java.util.*;

public class App implements Serializable {
    @Serial
    private static final long   serialVersionUID = 1L;

    private transient Scanner scanner = new Scanner(System.in);

    TreeMap<Integer, Contact> contact = new TreeMap<>();
    ContactFactory factory = new ContactFactory();
    int contactNumber = 1;
    private final String fileName;

    private final static String ADD = "The record added.";
    private final static String EDIT = "The record updated!";


    public App(String fileName) {
        this.fileName = fileName;
    }
    /**
     * The user can add objects from the Person and Organization classes and then add these objects to the TreeMap
     */
    public void addContact() {
        System.out.println("Enter the type (person, organization):");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("person")) {
            System.out.println("Enter the name:");
            String name = scanner.nextLine();
            System.out.println("Enter the surname:");
            String surname = scanner.nextLine();
            System.out.println("Enter the birth date:");
            String birthDate = scanner.nextLine();
            if (Validator.isInvalidBirthDate(birthDate)) {
                birthDate = "[no data]";
            }
            System.out.println("Enter the gender (M, F):");
            String gender = scanner.nextLine();
            if (Validator.isInvalidGender(gender)) {
                gender = "[no data]";
            }
            System.out.println("Enter the number:");
            String phoneNumber = scanner.nextLine();
            if (Validator.isInvalidPhoneNumber(phoneNumber, ADD)) {
                phoneNumber = "[no number]";
            }

            Contact person = factory.createContact(name, surname, phoneNumber, birthDate, gender);
            contact.put(contactNumber, person);
            contactNumber++;
        } else if (input.equalsIgnoreCase("organization")) {
            System.out.println("Enter the organization name:");
            String organizationName = scanner.nextLine();
            System.out.println("Enter the address:");
            String address = scanner.nextLine();
            System.out.println("Enter the number:");
            String phoneNumber = scanner.nextLine();
            if (Validator.isInvalidPhoneNumber(phoneNumber, ADD)) {
                phoneNumber = "[no number]";
            }
            Contact organization = factory.createOrganization(organizationName, address, phoneNumber);
            contact.put(contactNumber, organization);
            contactNumber++;
        }
        save();
    }

    /**
     * Show name of added contacts
     * @return false if list is empty otherwise true
     */
    public boolean showContacts() {
        if (contact.isEmpty()) {
            System.out.println("List is empty!");
            return false;
        } else {
            for (Map.Entry<Integer, Contact> entry : contact.entrySet()) {
                Integer key = entry.getKey();
                Contact value = entry.getValue();

                if (value instanceof Person person) {
                    System.out.println(key + ". " + person.getName() + " " + person.getSurname());
                } else if (value instanceof Organization organization) {
                    System.out.println(key + ". " + organization.getName());
                }
            } return true;
        }
    }

    /**
     * Show info about Contact in TreeMap
     */
    public void showInfo() {
        System.out.println("[list] Enter action ([number], back):");
        String input = scanner.nextLine();
        if (input.equals("back")) {
        } else {
            searchInfo(Integer.parseInt(input));
        }
    }


    /**
     * Remove contact
     * @param index The contact key to be deleted
     */
    public void removeContact(int index) {
        if (contact.containsKey(index)) {
            contact.remove(index);
            System.out.println("The record removed!");

            TreeMap<Integer, Contact> updatedContacts = new TreeMap<>();
            for (Map.Entry<Integer, Contact> entry : contact.tailMap(index, false).entrySet()) {
                updatedContacts.put(entry.getKey() - 1, entry.getValue());
            }

            // Remove the original records.
            for (Integer key : updatedContacts.keySet()) {
                contact.remove(key + 1);
            }

            // Add the updated records with decremented keys.
            contact.putAll(updatedContacts);
            contactNumber--;
        }
    }


    /**
     * Edit fields of contact
     * @param index The contact key to be edited
     */
    public void editContact(int index) {
            if (contact.containsKey(index)) {
                Contact selectedContact = contact.get(index);
                if (selectedContact instanceof Person) {
                    System.out.println("Select a field (name, surname, birth, gender, number):");
                    String field = scanner.nextLine();
                    switch (field) {
                        case "name" -> {
                            System.out.println("Enter name:");
                            String name = scanner.nextLine();
                            selectedContact.setName(name);
                            System.out.println("Saved");
                        }
                        case "surname" -> {
                            System.out.println("Enter surname:");
                            String surname = scanner.nextLine();
                            ((Person) selectedContact).setSurname(surname);
                            System.out.println("Saved");
                        }
                        case "number" -> {
                            System.out.println("Enter number:");
                            String phoneNumber = scanner.nextLine();
                            if (Validator.isInvalidPhoneNumber(phoneNumber, EDIT)) {
                                phoneNumber = "[no number]";
                            }
                            selectedContact.setPhoneNumber(phoneNumber);
                        }
                        case "birth" -> {
                            System.out.println("Enter birthdate:");
                            String birthDate = scanner.nextLine();
                            if (Validator.isInvalidBirthDate(birthDate)) {
                                birthDate = "[no info]";
                            }
                            ((Person) selectedContact).setBirthDate(birthDate);
                            System.out.println("Saved");
                        }
                        case "gender" -> {
                            System.out.println("Enter gender:");
                            String gender = scanner.nextLine();
                            if (Validator.isInvalidGender(gender)) {
                                gender = "[no info]";
                            }
                            ((Person) selectedContact).setGender(gender);
                            System.out.println("Saved");
                        }
                        default -> System.out.println("Wrong field to edit!");
                    }
                } else if (selectedContact instanceof Organization) {
                    System.out.println("Select a field (name, address, number):");
                    String field = scanner.nextLine();
                    switch (field) {
                        case "name" -> {
                            System.out.println("Enter name:");
                            String name = scanner.nextLine();
                            selectedContact.setName(name);
                        }
                        case "address" -> {
                            System.out.println("Enter address:");
                            String address = scanner.nextLine();
                            ((Organization) selectedContact).setAddress(address);
                            System.out.println("Saved");
                        }
                        case "number" -> {
                            System.out.println("Enter number:");
                            String phoneNumber = scanner.nextLine();
                            if (Validator.isInvalidPhoneNumber(phoneNumber, EDIT)) {
                                phoneNumber = "[no number]";
                            }
                            selectedContact.setPhoneNumber(phoneNumber);
                            System.out.println("Saved");
                        }
                        default -> System.out.println("Wrong field to edit!");
                    }
                }
                selectedContact.setLastEditTime();
            }
    }

    /**
     * Count added Contacts
     *
     * @return String with number of Contacts
     */
    public String countContacts() {
        return "The Phone Book has " + contact.size() + " records.";
    }

    /**
     * Save objects to database
     */
    public void save() {
        if (fileName != null) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Load contacts from database
     * @param fileName Name of the databates from command-line argument
     * @return returns this deserialized App object.
     */
    public static App load(String fileName) {
        if (fileName != null) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                return (App) ois.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("File not found, creating a new instance.");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new App(fileName);
    }


    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        this.scanner = new Scanner(System.in);
    }

    /**
     * The search engine looks in the database of names
     */
    public void searchEngineMenu() {
        if (contact.isEmpty()) {
            System.out.println("List is empty!");
        } else {
            while (true) {
                int match = 0;
                System.out.print("Enter search query: ");
                String query = scanner.nextLine();
                String message = "No matches found.";
                boolean found = false;
                List<Integer> keys = new ArrayList<>();

                for (Map.Entry<Integer, Contact> entry : contact.entrySet()) {
                    Integer key = entry.getKey();
                    Contact value = entry.getValue();
                    String name = "";

                    if (value instanceof Person person) {
                        name = person.getName() + " " + person.getSurname();
                    } else if (value instanceof Organization organization) {
                        name = organization.getName();
                    }

                    if (name.toLowerCase().contains(query.toLowerCase())) {
                        keys.add(key);
                        match++;
                        if (!found) {
                            System.out.println("Found results:");
                            found = true;
                        }
                        System.out.println(match + ". " + name);
                    }
                }

                if (!found) {
                    System.out.println(message);
                    continue;
                }

                while (true) {
                    System.out.print("[search] Enter action ([number], back, again): ");
                    String action = scanner.nextLine();
                    if (action.equalsIgnoreCase("back")) {
                        return;
                    } else if (action.equalsIgnoreCase("again")) {
                        break;
                    } else {
                        int index;
                        try {
                            index = Integer.parseInt(action) - 1;
                            if (index >= 0 && index < keys.size()) {
                                searchInfo(keys.get(index));
                            } else {
                                System.out.println("Invalid index.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input.");
                        }
                    }
                }
            }
        }
    }

    /**
     * In this method user can edit/delete contact
     * @param index The contact key to be edited/deleted
     */
    public void searchInfo(int index) {
        Contact contact = this.contact.get(index);
        if (contact == null) {
            System.out.println("Wrong number!");
        } else {
            System.out.println(contact);

            while (true) {
                System.out.print("[record] Enter action (edit, delete, back): ");
                String action = scanner.nextLine();
                if (action.equalsIgnoreCase("edit")) {
                    editContact(index);
                } else if (action.equalsIgnoreCase("delete")) {
                    removeContact(index);
                    System.out.println("The record has been removed!");
                    return;
                } else if (action.equalsIgnoreCase("back")) {
                    return;
                } else {
                    System.out.println("Invalid input.");
                }
            }
        }
    }
}

