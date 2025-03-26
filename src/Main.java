import java.util.Scanner;

public class Main {
    public static String[] studentIdArray = new String[0];
    public static String[] studentNameArray = new String[0];

    public static int[] marksPrfArray = new int[0];
    public static int[] marksDbmsArray = new int[0];

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        promptHomePage();
        scanner.close();
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

    private static void promptUpdateStudentDetails() {

    }

    private static void promptAddMarks() {
        clearConsole();
        printDashes();
        printTitle("ADD MARKS");
        printDashes();

        String studentId;
        boolean isExistId, isAgain;

        do {
            System.out.print("\nEnter Student ID   : ");
            studentId = scanner.next();
            isExistId = findStudentIndexById(studentId) != -1;

            if (!isExistId) {
                isAgain = searchAgainOrNot("Invalid Student ID. Do you want to search again? (Y/n)");
                if (!isAgain) {
                    promptHomePage();
                    break;
                }
            }

        } while (!isExistId);


        int index = findStudentIndexById(studentId);
        System.out.println("Student Name       : " + studentNameArray[index]);

        int[] marks = findMarksById(index);
        if (marks[0] != -1 && marks[1] != -1) {
            System.out.println("This student marks have been already added.");
            System.out.println("If you want to update marks, please use [4] Update Marks option.\n");
            answerYesOrNo("Do you want to add marks for another student (Y/n): ", 3);
            return;
        }

        addStudentMarks(index);

        System.out.println();
        answerYesOrNo("Marks have been added. Do you want to add marks for another student (Y/n): ", 3);
    }

    private static void promptAddNewStudentWithMarks() {
        clearConsole();
        printDashes();
        printTitle("ADD NEW STUDENT WITH MARKS");
        printDashes();

        addNewStudentIdAndName();
        addStudentMarks(-1);

        System.out.println();
        answerYesOrNo("Student has been added successfully. Do you want to add new student (Y/n): ", 2);
    }

    private static void promptAddNewStudent() {
        clearConsole();
        printDashes();
        printTitle("ADD NEW STUDENT");
        printDashes();

        addNewStudentIdAndName();
        marksPrfArray = addNewElementToArray(marksPrfArray, -1); // add new student PRF marks
        marksDbmsArray = addNewElementToArray(marksDbmsArray, -1); // add new student DBMS marks

        System.out.println();
        answerYesOrNo("Student has been added successfully. Do you want to add new student (Y/n): ", 1);
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

        int option = scanner.nextInt();
        chooseOption(option);
    }

    private static void addStudentMarks(int index) {

        int marksPrf, marksDbms;
        boolean isInvalidMarksPrf, isInvalidMarksDbms;

        do {
            System.out.print("\nProgramming Fundamental Marks     : ");
            marksPrf = scanner.nextInt();
            isInvalidMarksPrf = (marksPrf < 0 || 100 < marksPrf);
            if (isInvalidMarksPrf) System.out.println("Invalid marks. Please enter correct marks");
        } while (isInvalidMarksPrf);

        if (index == -1) {
            marksPrfArray = addNewElementToArray(marksPrfArray, marksPrf); // add new student PRF marks
        } else {
            marksPrfArray[index] = marksPrf;
        }
        do {
            System.out.print("Database Management System Marks  : ");
            marksDbms = scanner.nextInt();
            isInvalidMarksDbms = (marksDbms < 0 || 100 < marksDbms);
            if (isInvalidMarksDbms) System.out.println("Invalid marks. Please enter correct marks\n");
        } while (isInvalidMarksDbms);

        if (index == -1) {
            marksDbmsArray = addNewElementToArray(marksDbmsArray, marksDbms); // add new student DBMS marks
        } else {
            marksDbmsArray[index] = marksDbms;
        }
    }

    private static void addNewStudentIdAndName() {

        String studentId;
        boolean isExistId;

        do {
            System.out.print("Enter Student ID   : ");
            studentId = scanner.next();
            isExistId = findStudentIndexById(studentId) != -1;

            if (isExistId) {
                System.out.println("The student ID already exist\n");
            }

        } while (isExistId);

        studentIdArray = addNewElementToArray(studentIdArray, studentId); // add new student id

        System.out.print("Enter Student Name : ");
        String studentName = scanner.next();
        studentNameArray = addNewElementToArray(studentNameArray, studentName); // add new student name
    }

    private static String[] addNewElementToArray(String[] array, String value) {
        String[] tempArray = new String[array.length + 1];
        System.arraycopy(array, 0, tempArray, 0, array.length);
        tempArray[array.length] = value; // Add new element at the last index
        return tempArray;
    }

    private static int[] addNewElementToArray(int[] array, int value) {
        int[] tempArray = new int[array.length + 1];
        System.arraycopy(array, 0, tempArray, 0, array.length);
        tempArray[array.length] = value; // Add new element at the last index
        return tempArray;
    }

    private static int findStudentIndexById(String studentId) {
        for (int i = 0; i < studentIdArray.length; i++) {
            if (studentIdArray[i].equalsIgnoreCase(studentId))
                return i;
        }
        return -1;
    }

    private static int[] findMarksById(int index) {
        int[] marks = new int[2];
        marks[0] = marksPrfArray[index];
        marks[1] = marksDbmsArray[index];
        return marks;
    }

    private static void answerYesOrNo(String text, int num) {

        while (true) {
            System.out.print(text);
            String answer = scanner.next().trim().toUpperCase();

            if ("Y".equals(answer)) {
                chooseOption(num);
            } else if ("N".equals(answer)) {
                promptHomePage();
                break;
            } else {
                moveCursorUpAndClear();  // Move cursor to top and clear the line
            }
        }
    }

    private static boolean searchAgainOrNot(String text) {

        while (true) {
            System.out.print(text);
            String answer = scanner.next().trim().toUpperCase();

            if ("Y".equals(answer)) {
                return true;
            } else if ("N".equals(answer)) {
                return false;
            } else {
                moveCursorUpAndClear();  // Move cursor to top and clear the line
            }
        }
    }

    private static void moveCursorUpAndClear() {
        System.out.print("\033[F\033[K"); // Move cursor up one line and clear it
    }

    private static void chooseOption(int option) {
        switch (option) {
            case 1:
                promptAddNewStudent();
                break;
            case 2:
                promptAddNewStudentWithMarks();
            case 3:
                promptAddMarks();
            case 4:
                promptUpdateStudentDetails();
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