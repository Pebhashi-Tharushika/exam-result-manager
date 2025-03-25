import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static String[] studentIdArray = new String[0];
    public static String[] studentNameArray = new String[0];


    public static void main(String[] args) {
        promptHomePage();
    }

    private static void chooseOption() {
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                promptAddNewStudent();
                break;
            case 2:
                addNewStudentWithMarks();
            case 3:
                addMarks();
            case 4:
                updateStudentDetails();
            case 5:
                updateMarks();
            case 6:
                deleteStudent();
            case 7:
                printStudentDetails();
            case 8:
                printStudentRanks();
            case 9:
                rankBestInPrf();
            case 10:
                rankBestInDbms();
            default:
                invalidOption();

        }
    }

    private static void invalidOption() {
    }

    private static void rankBestInDbms() {

    }

    private static void rankBestInPrf() {

    }

    private static void printStudentRanks() {

    }

    private static void printStudentDetails() {
    }


    private static void deleteStudent() {

    }

    private static void updateMarks() {

    }

    private static void updateStudentDetails() {

    }

    private static void addMarks() {

    }

    private static void addNewStudentWithMarks() {

    }

    private static void promptAddNewStudent() {
        clearConsole();
        printDashes();
        printTitle("ADD NEW STUDENT");
        printDashes();

        Scanner scanner = new Scanner(System.in);

        String studentId;
        boolean isExistId;

        do {
            System.out.print("Enter Student ID   : ");
            studentId = scanner.next();
            isExistId = isExistStudentId(studentId);

            if (isExistId) {
                System.out.println("The student ID already exist");
            }

        } while (isExistId);

        addNewStudentId(studentId);

        System.out.print("Enter Student Name : ");
        String studentName = scanner.next();
        addNewStudentName(studentName);

        System.out.println();
        System.out.print("Student has been added successfully. Do you want to add new student (Y/N): ");
        String answer = scanner.next();

        switch (answer.toUpperCase()) {
            case "Y":
                promptAddNewStudent();
                break;
            case "N":
                promptHomePage();
                break;
//                default: InvalidAnswer();
        }
    }

    private static void addNewStudentId(String studentId) {
        studentIdArray = addNewElementToArray(studentIdArray, studentId);
    }

    private static void addNewStudentName(String studentName) {
        studentNameArray = addNewElementToArray(studentNameArray, studentName);
    }


    private static String[] addNewElementToArray(String[] array, String value) {
        String[] tempArray = new String[array.length + 1];
        System.arraycopy(array, 0, tempArray, 0, array.length);
        tempArray[array.length] = value; // Add new element at the last index
        return tempArray;
    }


    private static boolean isExistStudentId(String studentId) {
        for (String id : studentIdArray) {
            if (id.equalsIgnoreCase(studentId)) return true;
        }
        return false;
    }

    public static void promptHomePage() {
        clearConsole();

        printDashes();
        printTitle("WELCOME TO STUDENT MARKS MANAGEMENT SYSTEM");
        printDashes();
        System.out.printf("[1] %-35s [2] %-35s\n", "Add New Student", "Add New Student With Marks");
        System.out.printf("[3] %-35s [4] %-35s\n", "Add Marks", "Update Student Details");
        System.out.printf("[5] %-35s [6] %-35s\n", "Update Marks", "Delete Student");
        System.out.printf("[7] %-35s [8] %-35s\n", "Print Student Details", "Print Student Ranks");
        System.out.printf("[9] %-35s [10] %-35s\n", "Best in Programming Fundamentals", "Best in Database Management System");

        System.out.print("Enter an option to continue > ");
        chooseOption();
    }

    public static void printTitle(String title) {
        int totalWidth = 80;
        int textLength = title.length();
        int padding = (totalWidth - textLength - 2) / 2; // 2 for the border "|"

        System.out.printf("|%" + padding + "s%s%" + padding + "s|%n", "", title, "");
    }

    public static void printDashes() {
        System.out.println("-".repeat(80));
    }

    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
// Handle any exceptions.
        }
    }
}