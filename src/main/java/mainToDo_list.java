import java.util.Scanner;

public class mainToDo_list {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ToDoItemManager manager = new ToDoItemManager();

        while (true) {
            System.out.println("\nTo-Do List Application:");
            System.out.println("1. Add Item");
            System.out.println("2. Delete Item");
            System.out.println("3. Display Items");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter to-do item: ");
                    String itemName = scanner.nextLine();
                    manager.addToDoItem(itemName);
                    break;
                case 2:
                    System.out.println("Enter item ID to delete: ");
                    int itemId = scanner.nextInt();
                    scanner.nextLine();
                    manager.removeFromDoItem(itemId);
                    break;
                case 3:
                    manager.viewToDoItems();
                    break;
                case 4:
                    System.out.println("Bye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
