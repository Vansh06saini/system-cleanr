import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n====== SIMPLE SYSTEM CLEANER ======");
            System.out.println("1. Windows Temp Cleanup");
            System.out.println("2. User Temp Cleanup");
            System.out.println("3. Junk File Cleanup");
            System.out.println("4. Recycle Bin Cleanup");
            System.out.println("5. Exit Program");
            System.out.print("Choose option: ");

            int option = input.nextInt();

            switch (option) {

                case 1:
                    WindowsTempCleaner.clean();
                    break;
                    
                    case 2:
                    UserTempCleaner.clean();
                    break;

                    case 3:
                    junkcleaner.clean();
                    break;

                    case 4:
                    recyclebincleaner.clean();
                    break;
                case 5:
                    System.out.println("Program Closed.");
                    return;

                default:
                    System.out.println("Please enter a valid option.");
            }
        }
    }
}