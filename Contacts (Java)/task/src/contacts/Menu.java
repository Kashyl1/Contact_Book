package contacts;

import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);


    /**
     * Constructor, main menu
     */
    public Menu(String fileName) {
        App app = App.load(fileName);
        String info = "\n[menu] Enter action (add, list, search, count, exit):";
        System.out.println(info);
        String input = scanner.nextLine();
        while (!input.equals("exit")) {
            switch (input) {
                case "add" -> {
                    app.addContact();
                    app.save();
                }
                case "search" -> {
                    app.searchEngineMenu();
                    app.save();
                }
                case "count" -> {
                    System.out.println(app.countContacts());
                    app.save();
                }
                case "list" -> {
                    {
                        if (app.showContacts()) {
                            app.showInfo();
                            app.save();
                        }
                    }
                }
                default -> System.out.println("Wrong action!");
            }
            System.out.println(info);
            input = scanner.nextLine();
        }
        app.save();
    }
}
