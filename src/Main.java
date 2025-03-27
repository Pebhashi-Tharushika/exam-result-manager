import java.util.Scanner;

public class Main {
    public static String[] studentIdArray = new String[0];
    public static String[] studentNameArray = new String[0];

    public static int[] marksPrfArray = new int[0];
    public static int[] marksDbmsArray = new int[0];

    private static final Scanner scanner = new Scanner(System.in);

    public static String[] validIds;
    public static String[] validNames;
    public static int[] validPrimaryMarks;
    public static int[] validSecondaryMarks;
    public static int[] validTotalMarks;
    public static double[] validAvgMarks;
    public static int[] validRank;

    public static void main(String[] args) {
        promptHomePage();
        scanner.close();
    }

    private static void promptRankBestInDbms() {
        clearConsole();
        printDashes();
        printTitle("BEST IN DATABASE MANAGEMENT SYSTEM");
        printDashes();

        System.out.printf("+%s+%s+%s+%s+%n", "-".repeat(10), "-".repeat(37), "-".repeat(14), "-".repeat(14));
        System.out.printf("|%-10s|%-37s|%-14s|%-14s|%n", "ID", "Name", "DBMS Marks", "PF Marks");
        System.out.printf("+%s+%s+%s+%s+%n", "-".repeat(10), "-".repeat(37), "-".repeat(14), "-".repeat(14));

        if (rankStudents("DBMS")) {
            for (int i = 0; i < validRank.length; i++) {
                System.out.printf("|%-10s|%-37s|%-14d|%-14d|%n",
                        validIds[i], validNames[i], validPrimaryMarks[i], validSecondaryMarks[i]);
            }
        }
        System.out.printf("+%s+%s+%s+%s+%n", "-".repeat(10), "-".repeat(37), "-".repeat(14), "-".repeat(14));

        answerYesOrNo("Do you want to go back to main menu? (Y/n): ", "10");
    }

    private static void promptRankBestInPrf() {
        clearConsole();
        printDashes();
        printTitle("BEST IN PROGRAMMING FUNDAMENTALS");
        printDashes();

        System.out.printf("+%s+%s+%s+%s+%n", "-".repeat(10), "-".repeat(37), "-".repeat(14), "-".repeat(14));
        System.out.printf("|%-10s|%-37s|%-14s|%-14s|%n", "ID", "Name", "PF Marks", "DBMS Marks");
        System.out.printf("+%s+%s+%s+%s+%n", "-".repeat(10), "-".repeat(37), "-".repeat(14), "-".repeat(14));

        if (rankStudents("PF")) {
            for (int i = 0; i < validRank.length; i++) {
                System.out.printf("|%-10s|%-37s|%-14d|%-14d|%n",
                        validIds[i], validNames[i], validPrimaryMarks[i], validSecondaryMarks[i]);
            }
        }
        System.out.printf("+%s+%s+%s+%s+%n", "-".repeat(10), "-".repeat(37), "-".repeat(14), "-".repeat(14));

        answerYesOrNo("Do you want to go back to main menu? (Y/n): ", "9");

    }

    private static void promptPrintStudentRanks() {
        clearConsole();
        printDashes();
        printTitle("PRINT STUDENT RANKS");
        printDashes();

        System.out.printf("+%s+%s+%s+%s+%s+%n", "-".repeat(7), "-".repeat(8), "-".repeat(35), "-".repeat(12), "-".repeat(12));
        System.out.printf("|%-7s|%-8s|%-35s|%-12s|%-12s|%n", "Rank", "ID", "Name", "Total Marks", "Avg. Marks");
        System.out.printf("+%s+%s+%s+%s+%s+%n", "-".repeat(7), "-".repeat(8), "-".repeat(35), "-".repeat(12), "-".repeat(12));

        if (rankStudents("AVG")) {
            for (int i = 0; i < validRank.length; i++) {
                System.out.printf("|%-7d|%-8s|%-35s|%12d|%-12.2f|%n",
                        validRank[i], validIds[i], validNames[i], validTotalMarks[i], validAvgMarks[i]);
            }
        }

        System.out.printf("+%s+%s+%s+%s+%s+%n", "-".repeat(7), "-".repeat(8), "-".repeat(35), "-".repeat(12), "-".repeat(12));

        answerYesOrNo("Do you want to go back to main menu? (Y/n): ", "8");

    }

    private static void promptPrintStudentDetails() {
        clearConsole();
        printDashes();
        printTitle("PRINT STUDENT DETAILS");
        printDashes();

        int index = fetchAlreadyExistStudentIndex();
        if (index == -1) return;
        System.out.println("Student Name       : " + studentNameArray[index]);

        if (marksPrfArray[index] == -1 || marksDbmsArray[index] == -1) {
            System.out.println("\nMarks yet to be added.\n");
            answerYesOrNo("Do you want to search another student details (Y/n): ", "7");
            return;
        }

        int totalMarks = marksPrfArray[index] + marksDbmsArray[index];
        double avgMarks = totalMarks / 2.0;
        int rank = findRankByAvgMarks(studentIdArray[index]);

        int lastRank = getLastRank();
        if (lastRank == 0) return;

        boolean isAvailableTextRank = rank == 1 || rank == 2 || rank == 3 || rank == lastRank;

        System.out.printf("+%s+%s+%n", "-".repeat(35), "-".repeat(15));
        System.out.printf("|%-35s|%15s|%n", "Programming Fundamental Marks", marksPrfArray[index]);
        System.out.printf("|%-35s|%15s|%n", "Database Management System Marks", marksDbmsArray[index]);
        System.out.printf("|%-35s|%15s|%n", "Total Marks", totalMarks);
        System.out.printf("|%-35s|%15s|%n", "Avg. Marks", String.format("%.2f", avgMarks));
        System.out.printf("|%-35s|%15s|%n", "Rank", rank + (isAvailableTextRank ? getTextRank(rank) : ""));
        System.out.printf("+%s+%s+%n", "-".repeat(35), "-".repeat(15));

        answerYesOrNo("Do you want to search another student details (Y/n): ", "7");
    }

    private static void promptDeleteStudent() {
        clearConsole();
        printDashes();
        printTitle("DELETE STUDENT");
        printDashes();

        int index = fetchAlreadyExistStudentIndex();
        if (index == -1) return;

        deleteStudent(index);

        System.out.println("\nStudent has been deleted successfully");
        answerYesOrNo("Do you want to delete another student? (Y/n): ", "6");

    }

    private static void promptUpdateMarks() {
        clearConsole();
        printDashes();
        printTitle("UPDATE MARKS");
        printDashes();

        int index = fetchAlreadyExistStudentIndex();
        if (index == -1) return;
        System.out.println("Student Name       : " + studentNameArray[index]);

        if (marksPrfArray[index] == -1 || marksDbmsArray[index] == -1) {
            System.out.println("\nThis student's marks yet to be added.");
            answerYesOrNo("Do you want to update marks of another student (Y/n): ", "5");
            return;
        }
        System.out.println("\nProgramming Fundamentals Marks   : " + marksPrfArray[index]);
        System.out.println("Database Management System Marks : " + marksDbmsArray[index]);

        addStudentMarks(index, false);

        System.out.println("\nMarks have been updated successfully");
        answerYesOrNo("Do you want to update marks for another student (Y/n): ", "5");
    }

    private static void promptUpdateStudentDetails() {
        clearConsole();
        printDashes();
        printTitle("UPDATE STUDENT DETAILS");
        printDashes();

        int index = fetchAlreadyExistStudentIndex();
        if (index == -1) return;
        System.out.println("Student Name       : " + studentNameArray[index]);

        System.out.print("\nEnter the new student name  : ");
        studentNameArray[index] = scanner.next();

        System.out.println("\nStudent details has been updated successfully");
        answerYesOrNo("Do you want to update another student details? (Y/n): ", "4");
    }

    private static void promptAddMarks() {
        clearConsole();
        printDashes();
        printTitle("ADD MARKS");
        printDashes();

        int index = fetchAlreadyExistStudentIndex();
        if (index == -1) return;
        System.out.println("Student Name       : " + studentNameArray[index]);

        int[] marks = findMarksById(index);
        if (marks[0] != -1 && marks[1] != -1) {
            System.out.println("This student marks have been already added.");
            System.out.println("If you want to update marks, please use [5] Update Marks option.\n");
            answerYesOrNo("Do you want to add marks for another student (Y/n): ", "3");
            return;
        }

        addStudentMarks(index, true);

        System.out.println();
        answerYesOrNo("Marks have been added. Do you want to add marks for another student (Y/n): ", "3");
    }

    private static void promptAddNewStudentWithMarks() {
        clearConsole();
        printDashes();
        printTitle("ADD NEW STUDENT WITH MARKS");
        printDashes();

        addNewStudentIdAndName();
        addStudentMarks(-1, true);

        System.out.println();
        answerYesOrNo("Student has been added successfully. Do you want to add new student (Y/n): ", "2");
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
        answerYesOrNo("Student has been added successfully. Do you want to add new student (Y/n): ", "1");
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

        System.out.print("Enter an option number to continue or type 'q' to exit > ");

        String option = scanner.next().trim();
        chooseOption(option);

    }

    private static void addStudentMarks(int index, boolean isAdd) {

        int marksPrf, marksDbms;
        boolean isInvalidMarksPrf, isInvalidMarksDbms;

        do {
            System.out.print("\n" + (isAdd ? "" : "Enter new ") + "Programming Fundamental Marks     : ");
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
            System.out.print((isAdd ? "" : "Enter new ") + "Database Management System Marks  : ");
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

    private static void deleteStudent(int index) {
        studentIdArray = removeElementFromArray(studentIdArray, index);
        studentNameArray = removeElementFromArray(studentNameArray, index);
        marksPrfArray = removeElementFromArray(marksPrfArray, index);
        marksDbmsArray = removeElementFromArray(marksDbmsArray, index);
    }

    private static int[] removeElementFromArray(int[] array, int index) {
        int[] newArray = new int[array.length - 1];
        for (int i = 0; i < newArray.length; i++) {
            if (i < index) {
                newArray[i] = array[i];
            } else {
                newArray[i] = array[i + 1];
            }
        }
        return newArray;
    }

    private static String[] removeElementFromArray(String[] array, int index) {
        String[] newArray = new String[array.length - 1];
        for (int i = 0; i < newArray.length; i++) {
            if (i < index) {
                newArray[i] = array[i];
            } else {
                newArray[i] = array[i + 1];
            }
        }
        return newArray;
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

    private static int fetchAlreadyExistStudentIndex() {
        String studentId;
        boolean isExistId, isAgain;

        do {
            System.out.print("\nEnter Student ID   : ");
            studentId = scanner.next();
            isExistId = findStudentIndexById(studentId) != -1;

            if (!isExistId) {
                isAgain = searchAgainOrNot("Invalid Student ID. Do you want to search again? (Y/n): ");
                if (!isAgain) {
                    promptHomePage();
                    return -1;
                }
            }

        } while (!isExistId);
        return findStudentIndexById(studentId);
    }

    private static int getLastRank() {
        int max = 0;
        for (int i = 0; i < validRank.length; i++) {
            if (max < validRank[i]) {
                max = validRank[i];
            }
        }
        return max;
    }

    private static String getTextRank(int rank) {
        if (rank == 1)
            return " (First)";
        if (rank == 2)
            return " (Second)";
        if (rank == 3)
            return " (Third)";
        if (rank == getLastRank())
            return " (Last)";

        return null;
    }

    private static int findRankByAvgMarks(String studentId) {
        boolean isRanked = rankStudents("AVG");
        if (isRanked) {
            for (int i = 0; i < validIds.length; i++) {
                if (validIds[i].equalsIgnoreCase(studentId)) {
                    return validRank[i];
                }
            }
        }
//        else{
//            System.out.println("No students with valid marks available.");
//        }
        return -1;
    }

    private static boolean rankStudents(String criteria) {
        int n = studentIdArray.length;

        // Step 1: Filter out students with -1 marks
        int validCount = 0;
        for (int i = 0; i < n; i++) {
            if (marksPrfArray[i] != -1 && marksDbmsArray[i] != -1) {
                validCount++;
            }
        }

        if (validCount == 0) return false;

        // Step 2: Create arrays for valid students
        validIds = new String[validCount];
        validNames = new String[validCount];
        validPrimaryMarks = new int[validCount];
        validSecondaryMarks = new int[validCount];
        validTotalMarks = new int[validCount];
        validAvgMarks = new double[validCount];

        int index = 0;
        for (int i = 0; i < n; i++) {
            if (marksPrfArray[i] != -1 && marksDbmsArray[i] != -1) {
                validIds[index] = studentIdArray[i];
                validNames[index] = studentNameArray[i];
                validPrimaryMarks[index] = (criteria.equals("PF")) ? marksPrfArray[i] : marksDbmsArray[i];
                validSecondaryMarks[index] = (criteria.equals("PF")) ? marksDbmsArray[i] : marksPrfArray[i];
                validTotalMarks[index] = marksPrfArray[i] + marksDbmsArray[i];
                validAvgMarks[index] = validTotalMarks[index] / 2.0;
                index++;
            }
        }

        // Step 3: Sort students using Bubble Sort (Descending Order based on Avg Marks)
        for (int i = 0; i < validCount - 1; i++) {
            for (int j = 0; j < validCount - i - 1; j++) {
                boolean swap = false;
                if (criteria.equals("AVG")) {
                    swap = validAvgMarks[j] < validAvgMarks[j + 1];
                } else {
                    swap = validPrimaryMarks[j] < validPrimaryMarks[j + 1];
                }

                if (swap) {
                    swap(validAvgMarks, j, j + 1);
                    swap(validPrimaryMarks, j, j + 1);
                    swap(validSecondaryMarks, j, j + 1);
                    swap(validTotalMarks, j, j + 1);
                    swap(validIds, j, j + 1);
                    swap(validNames, j, j + 1);
                }

            }
        }

        // Step 4: Rank students
        validRank = new int[validCount];
        int rank = 1;

        for (int i = 0; i < validCount; i++) {
            if (criteria.equals("AVG") && i > 0 && validAvgMarks[i - 1] > validAvgMarks[i]) {
                rank = i + 1;
            }
            validRank[i] = rank;
        }
        return true;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void swap(double[] array, int i, int j) {
        double temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void swap(String[] array, int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void answerYesOrNo(String text, String num) {

        while (true) {
            System.out.print(text);
            String answer = scanner.next().trim().toUpperCase();

            boolean isGoMainMenu = num.equals("8") || num.equals("9") || num.equals("10");
            if ("Y".equals(answer)) {
                if (isGoMainMenu) promptHomePage();
                else chooseOption(num);
                break;
            } else if ("N".equals(answer)) {
                if (isGoMainMenu) chooseOption(num);
                else promptHomePage();
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

    private static void chooseOption(String option) {
        switch (option) {
            case "1":
                promptAddNewStudent();
                break;
            case "2":
                promptAddNewStudentWithMarks();
                break;
            case "3":
                promptAddMarks();
                break;
            case "4":
                promptUpdateStudentDetails();
                break;
            case "5":
                promptUpdateMarks();
                break;
            case "6":
                promptDeleteStudent();
                break;
            case "7":
                promptPrintStudentDetails();
                break;
            case "8":
                promptPrintStudentRanks();
                break;
            case "9":
                promptRankBestInPrf();
                break;
            case "10":
                promptRankBestInDbms();
                break;
            case "q":
                System.exit(0);
            default: {
                System.out.println("\nInvalid option. Please enter correct option.");
                promptHomePage();
            }

        }
    }

    public static void printTitle(String title) {
        int totalWidth = 80;
        int textLength = title.length();
        int padding = (totalWidth - textLength - 2) / 2; // 2 for the border "|"

        if (title.length() % 2 == 1) title = title + " ";
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