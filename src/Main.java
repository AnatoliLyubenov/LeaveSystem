import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true) {
            menu();
            Scanner scan = new Scanner(System.in);
            int command = scan.nextInt();
            switch (command) {
                case 1 -> metod1();
                case 2 -> metod2();
                case 3 -> metod3();
                case 4 -> metod4();
                case 5 -> System.exit(0);

            }
        }
    }

    public static void menu() {
        System.out.println("Тhe system awaits your selection:");
        System.out.println("1. Request leave");
        System.out.println("2. View all leaves");
        System.out.println("3. See employee leave");
        System.out.println("4. Change leave status");
        System.out.println("5. Exit");
    }
}

