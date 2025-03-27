import java.util.Scanner;

public class Main {
    public static String[] studentIdArray = new String[0];
    public static String[] studentNameArray = new String[0];

    public static int[] marksPrfArray = new int[0];
    public static int[] marksDbmsArray = new int[0];

    private static final Scanner scanner = new Scanner(System.in);

    public static String[] validIds;
    public static String[] validNames;
    public static int[] validTotalMarks;
    public static double[] validAvgMarks;
    public static int[] validRank;

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

    private static void promptPrintStudentDetails() {
        clearConsole();
        printDashes();
        printTitle("PRINT STUDENT DETAILS");
        printDashes();

        int index = fetchAlreadyExistStudentIndex();
        System.out.println("Student Name       : " + studentNameArray[index]);

        if (marksPrfArray[index] == -1 || marksDbmsArray[index] == -1) {
            System.out.println("\nMarks yet to be added.\n");
            answerYesOrNo("Do you want to search another student details (Y/n): ", 7);
            return;
        }

        int totalMarks = marksPrfArray[index] + marksDbmsArray[index];
        double avgMarks = totalMarks / 2.0;
        int rank = findRank(studentIdArray[index]);

        int lastRank = getLAstRank();
        if (lastRank == 0) return;

        boolean isAvailableTextRank = rank == 1 || rank == 2 || rank == 3 || rank == lastRank;

        System.out.printf("+%s+%s+%n", "-".repeat(35), "-".repeat(15));
        System.out.printf("|%-35s|%15s|%n", "Programming Fundamental Marks", marksPrfArray[index]);
        System.out.printf("|%-35s|%15s|%n", "Database Management System Marks", marksDbmsArray[index]);
        System.out.printf("|%-35s|%15s|%n", "Total Marks", totalMarks);
        System.out.printf("|%-35s|%15s|%n", "Avg. Marks", String.format("%.2f", avgMarks));
        System.out.printf("|%-35s|%15s|%n", "Rank", rank + (isAvailableTextRank ? getTextRank(rank) : ""));
        System.out.printf("+%s+%s+%n", "-".repeat(35), "-".repeat(15));

        answerYesOrNo("Do you want to search another student details (Y/n): ", 7);
    }

    private static int getLAstRank() {
        int max = 0;
        for (int i = 0; i < validRank.length; i++) {
            if (max < validRank[i]) {
                max = validRank[i];
            }
        }
        return max;
    }

    private static String getTextRank(int rank) {
        if(rank == 1)
                return " (First)";
        if(rank==2)
                return " (Second)";
        if(rank==3)
                return " (Third)";
        if(rank==getLAstRank())
                return " (Last)";

        return null;
    }

    private static int findRank(String studentId) {
        boolean isRanked = rankStudents();
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

    private static boolean rankStudents() {
        int n = studentIdArray.length;

        // Step 1: Filter out students with -1 marks
        int validCount = 0;
        for (int i = 0; i < n; i++) {
            if (marksPrfArray[i] != -1 && marksDbmsArray[i] != -1) {
                validCount++;
            }
        }

        if (validCount == 0) {
            return false;
        }

        // Step 2: Create arrays for valid students
        validIds = new String[validCount];
        validNames = new String[validCount];
        validTotalMarks = new int[validCount];
        validAvgMarks = new double[validCount];

        int index = 0;
        for (int i = 0; i < n; i++) {
            if (marksPrfArray[i] != -1 && marksDbmsArray[i] != -1) {
                validIds[index] = studentIdArray[i];
                validNames[index] = studentNameArray[i];
                validTotalMarks[index] = marksPrfArray[i] + marksDbmsArray[i];
                validAvgMarks[index] = validTotalMarks[index] / 2.0;
                index++;
            }
        }

        // Step 3: Sort students using Bubble Sort (Descending Order based on Avg Marks)
        for (int i = 0; i < validCount - 1; i++) {
            for (int j = 0; j < validCount - i - 1; j++) {
                if (validAvgMarks[j] < validAvgMarks[j + 1]) {
                    // Swap avg marks
                    double tempAvg = validAvgMarks[j];
                    validAvgMarks[j] = validAvgMarks[j + 1];
                    validAvgMarks[j + 1] = tempAvg;

                    // Swap total marks
                    int tempTotal = validTotalMarks[j];
                    validTotalMarks[j] = validTotalMarks[j + 1];
                    validTotalMarks[j + 1] = tempTotal;

                    // Swap student IDs
                    String tempId = validIds[j];
                    validIds[j] = validIds[j + 1];
                    validIds[j + 1] = tempId;

                    // Swap names
                    String tempName = validNames[j];
                    validNames[j] = validNames[j + 1];
                    validNames[j + 1] = tempName;
                }
            }
        }

        // Step 4: Rank students
        validRank = new int[validCount];
        int rank = 1;

        for (int i = 0; i < validCount; i++) {

            if (i > 0 && validAvgMarks[i - 1] > validAvgMarks[i]) {
                rank = i + 1;
            }

            validRank[i] = rank;

            // Handle skipping ranks for same average marks
            if (i > 0 && validAvgMarks[i - 1] == validAvgMarks[i]) {
                rank++;
            }

        }
        return true;
    }

    private static void promptDeleteStudent() {
        clearConsole();
        printDashes();
        printTitle("DELETE STUDENT");
        printDashes();

        int index = fetchAlreadyExistStudentIndex();

        deleteStudent(index);

        System.out.println("\nStudent has been deleted successfully");
        answerYesOrNo("Do you want to delete another student? (Y/n)", 6);

    }

    private static void promptUpdateMarks() {
        clearConsole();
        printDashes();
        printTitle("UPDATE MARKS");
        printDashes();

        int index = fetchAlreadyExistStudentIndex();
        System.out.println("Student Name       : " + studentNameArray[index]);

        if (marksPrfArray[index] == -1 || marksDbmsArray[index] == -1) {
            System.out.println("\nThis student's marks yet to be added.");
            answerYesOrNo("Do you want to update marks of another student (Y/n): ", 5);
            return;
        }
        System.out.println("\nProgramming Fundamentals Marks   : " + marksPrfArray[index]);
        System.out.println("Database Management System Marks : " + marksDbmsArray[index]);

        addStudentMarks(index, false);

        System.out.println("\nMarks have been updated successfully");
        answerYesOrNo("Do you want to update marks for another student (Y/n): ", 5);
    }

    private static void promptUpdateStudentDetails() {
        clearConsole();
        printDashes();
        printTitle("UPDATE STUDENT DETAILS");
        printDashes();

        int index = fetchAlreadyExistStudentIndex();
        System.out.println("Student Name       : " + studentNameArray[index]);

        System.out.print("\nEnter the new student name  : ");
        studentNameArray[index] = scanner.next();

        System.out.println("\nStudent details has been updated successfully");
        answerYesOrNo("Do you want to update another student details? (Y/n)", 4);
    }

    private static void promptAddMarks() {
        clearConsole();
        printDashes();
        printTitle("ADD MARKS");
        printDashes();

        int index = fetchAlreadyExistStudentIndex();
        System.out.println("Student Name       : " + studentNameArray[index]);

        int[] marks = findMarksById(index);
        if (marks[0] != -1 && marks[1] != -1) {
            System.out.println("This student marks have been already added.");
            System.out.println("If you want to update marks, please use [4] Update Marks option.\n");
            answerYesOrNo("Do you want to add marks for another student (Y/n): ", 3);
            return;
        }

        addStudentMarks(index, true);

        System.out.println();
        answerYesOrNo("Marks have been added. Do you want to add marks for another student (Y/n): ", 3);
    }

    private static void promptAddNewStudentWithMarks() {
        clearConsole();
        printDashes();
        printTitle("ADD NEW STUDENT WITH MARKS");
        printDashes();

        addNewStudentIdAndName();
        addStudentMarks(-1, true);

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
                isAgain = searchAgainOrNot("Invalid Student ID. Do you want to search again? (Y/n)");
                if (!isAgain) {
                    promptHomePage();
                    break;
                }
            }

        } while (!isExistId);
        return findStudentIndexById(studentId);
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
                promptUpdateMarks();
            case 6:
                promptDeleteStudent();
            case 7:
                promptPrintStudentDetails();
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