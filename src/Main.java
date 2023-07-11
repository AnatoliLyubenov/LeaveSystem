import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void menu() {
        System.out.println("The system awaits your selection:");
        System.out.println("1. Request leave");
        System.out.println("2. View all leaves");
        System.out.println("3. See employee leave");
        System.out.println("4. Change leave status");
        System.out.println("5. Exit");
    }

    public static String[][] initializeLeaves() {
        String[][] leaves = new String[2][8];
        for (String[] leave : leaves) {
            Arrays.fill(leave, "n/a");
        }
        return leaves;
    }

    public static void showAllLeaves(String[][] leaves) {
        for (int i = 0; i < leaves.length; i++) {
            if (leaves[i][0].equals("n/a")) {
            } else {
                System.out.println(leaves[i][0] + "/");
                System.out.println("Name: " + leaves[i][1] + "/");
                System.out.println("Email: " + leaves[i][2] + "/");
                System.out.println("EGN: " + leaves[i][3] + "/");
                System.out.println("Start date: " + leaves[i][4] + "/");
                System.out.println("End date: " + leaves[i][5] + "/");
                System.out.println("Type of leave: " + leaves[i][6] + "/");
                System.out.println("Status:" + leaves[i][7] + "/");
                System.out.println();
            }
        }
        System.out.println();
    }

    public static void insertInfoByLeave(String[][] leaves, int leaveCounter) {
        System.out.println("Please enter the following details:");
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter name: ");
        leaves[leaveCounter][1] = scan.next();
        if (!isValidName(leaves[leaveCounter][1])) {
            System.out.println("Invalid name.");
            return;
        }
        System.out.print("Enter email address: ");
        leaves[leaveCounter][2] = scan.next();
        if (!isValidEmail(leaves[leaveCounter][2])) {
            System.out.println("Invalid email.");
            return;
        }
        System.out.print("Enter EGN: ");
        leaves[leaveCounter][3] = scan.next();
        if (!isValidEGN(leaves[leaveCounter][3])) {
            System.out.println("Invalid EGN.");
            return;
        }
        LocalDate startDate = null;
        LocalDate endDate = null;
        try {
            System.out.print("Enter start date to leave(yyyy-MM-dd): ");
            leaves[leaveCounter][4] = scan.next();
            startDate = LocalDate.parse(leaves[leaveCounter][4]);
        } catch (DateTimeParseException e) {
            System.out.println("Incorrect date format. Please use yyyy-MM-dd format.");
            leaves[leaveCounter][4] = scan.next();
            startDate = LocalDate.parse(leaves[leaveCounter][4]);
        }
        try {
            System.out.print("Enter end date to leave(yyyy-MM-dd): ");
            leaves[leaveCounter][5] = scan.next();
            endDate = LocalDate.parse(leaves[leaveCounter][5]);
        } catch (DateTimeParseException e) {
            System.out.println("Incorrect date format. Please use yyyy-MM-dd format.");
            leaves[leaveCounter][5] = scan.next();
            endDate = LocalDate.parse(leaves[leaveCounter][5]);
        }
        try {
            if (endDate.isBefore(startDate)) {
                System.out.print("Please enter valid date to leave(yyyy-MM-dd): ");
                leaves[leaveCounter][5] = scan.next();
                endDate = LocalDate.parse(leaves[leaveCounter][5]);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Incorrect date format. Please use yyyy-MM-dd format.");
            leaves[leaveCounter][5] = scan.next();
            endDate = LocalDate.parse(leaves[leaveCounter][5]);
        }

        System.out.print("Type of leave (paid or unpaid): ");
        leaves[leaveCounter][6] = scan.next();

        if (!leaves[leaveCounter][6].equalsIgnoreCase("paid") &&
                !leaves[leaveCounter][6].equalsIgnoreCase("unpaid")) {
            System.out.println("Invalid type of leave.");
            return;
        }

        leaves[leaveCounter][7] = "pending";

        System.out.println("The data entered is valid. Making a leave request...");
        System.out.println();

        leaves[leaveCounter][0] = String.valueOf(leaveCounter);
    }

    public static boolean isValidName(String name) {
        return name.matches("[A-Za-zА-Яа-я]+");
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static boolean isValidEGN(String egn) {
        if (egn.length() != 10) {
            return false;
        }

        try {
            Long.parseLong(egn);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {


        while (true) {
            menu();
            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            switch (choice) {
                case 1 -> {
                    insertInfoByLeave();

                    break;
                }
                case 2 -> {
                    showAllLeaves();
                    break;
                }
                //  case 3 -> {
                //    showEmployeeLeave(leaves);
                //  break;
            }
            // case 4 -> {
            //   editStatusOfLeave(leaves);
            // break;
        }
        //case 5 -> {
        //  writeToFile(FILE_NAME, leaves);
        System.exit(0);
    }
}