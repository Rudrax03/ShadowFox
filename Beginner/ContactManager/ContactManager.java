import java.util.*;

public class ContactManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Contact> contacts = new ArrayList<>();

        while (true) {
            System.out.println("\n=== Contact Manager ===");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear input buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    contacts.add(new Contact(name, phone, email));
                    System.out.println("Contact added.");
                    break;

                case 2:
                    if (contacts.isEmpty()) {
                        System.out.println("No contacts found.");
                    } else {
                        for (int i = 0; i < contacts.size(); i++) {
                            Contact c = contacts.get(i);
                            System.out.println((i + 1) + ". " + c.name + " | " + c.phone + " | " + c.email);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter contact number to update: ");
                    int index = sc.nextInt() - 1;
                    sc.nextLine();
                    if (index >= 0 && index < contacts.size()) {
                        System.out.print("New Name: ");
                        contacts.get(index).name = sc.nextLine();
                        System.out.print("New Phone: ");
                        contacts.get(index).phone = sc.nextLine();
                        System.out.print("New Email: ");
                        contacts.get(index).email = sc.nextLine();
                        System.out.println("Contact updated.");
                    } else {
                        System.out.println("Invalid index.");
                    }
                    break;

                case 4:
                    System.out.print("Enter contact number to delete: ");
                    int delIndex = sc.nextInt() - 1;
                    if (delIndex >= 0 && delIndex < contacts.size()) {
                        contacts.remove(delIndex);
                        System.out.println("Contact deleted.");
                    } else {
                        System.out.println("Invalid index.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}