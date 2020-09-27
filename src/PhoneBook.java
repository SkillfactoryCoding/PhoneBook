import java.util.Arrays;
import java.util.Scanner;

public class PhoneBook {
    public static void main(String[] args) {
        String[][] book = new String[20][2];
        Scanner scanner = new Scanner(System.in);
        int record_counter = 0;
        boolean isWorking = true;

        while (isWorking) {
            System.out.println("Введите символ: \n " +
                    "\tN - ввести имя; \n\tP - ввести номер; \n\tS - остановить работу с книгой" +
                    "\n\tB - распечатать книгу в алфавитном порядке\n");
            char ans = scanner.next().charAt(0);

            switch (ans) {
                case 'N':
                    String newName = getNewName();
                    boolean isNamePresented = checkNamePresence(book, newName);
                    if (isNamePresented) {
                        int idx = findNameIndex(book, newName);
                        System.out.println("Имя найдено в книге!\n" + book[idx][0] + ": " + book[idx][1] + "\n");
                    }
                    else {
                        System.out.println("Имя не найдено! Создение новой записи...");
                        String newNumber = getNewNumber();
                        record_counter = add(book, newName, newNumber, record_counter);
                        System.out.println("Новая запись создана!\n");
                    }
                    break;
                case 'P':
                    String newNumber = getNewNumber();
                    boolean isNumberPresented = checkNumberPresence(book, newNumber);
                    if (isNumberPresented) {
                        int idx = findNumberIndex(book, newNumber);
                        System.out.println("Номер найден в книге!\n" + book[idx][0] + ": " + book[idx][1] + "\n");
                    }
                    else {
                        System.out.println("Номер не найден! Создение новой записи...");
                        newName = getNewName();
                        record_counter = add(book, newName, newNumber, record_counter);
                        System.out.println("Новая запись создана!\n");
                    }
                    break;
                case 'S':
                    isWorking = false;
                    System.out.println("Работа с книгой закончена");
                    break;
                case 'B':
                    System.out.println("Содержимое книги:");
//                    String[][] sortedBook = getSortedBook(book);
                    list(book);
                    break;
                default:
                    System.out.println("Неизвестный символ!");
            }
        }
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        String cleanNumber = phoneNumber.replaceAll("[^0-9]", "");
        return cleanNumber.length() == 11;
    }

    public static boolean checkName(String name) {
        String[] words = name.trim().split(" ");
        return words.length == 3;
    }

    public static String formatName(String name) {
        String[] words = name.trim().split(" ");
        String result = "";
        for (String str : words) {
            str = str.toLowerCase();
            char firstChar = str.charAt(0);
            result += Character.toUpperCase(firstChar) + str.substring(1) + " ";
        }
        return result;
    }

    public static String formatPhoneNumber(String number) {
        String cleanNumber = number.replaceAll("[^0-9]", "");
        return "8 " + cleanNumber.substring(1, 4) + " " + cleanNumber.substring(4, 7)
                + " " + cleanNumber.substring(7, 9) + " " + cleanNumber.substring(9);
    }

    public static String[][] add(String[][] book, String name, String number, int counter) {
        if (counter == book.length) {
            book = Arrays.copyOf(book, book.length + 1);
        }
        book[counter][0] = name;
        book[counter][1] = number;
        return book;
    }

    public static void list(String[][] book) {
        for (String[] strings : book) {
            if (strings[0] != "") {
                System.out.println(strings[0] + ": " + strings[1]);
            }
        }
    }

    public static String[][] getSortedBook(String[][] book) {
        String[][] sortedBook = Arrays.copyOf(book, book.length);
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 1; i < sortedBook.length; i++) {
                for (int j = 0; j < book[i][0].length(); j++) {
                    if ((int) sortedBook[i][0].charAt(j) < (int) sortedBook[i - 1][0].charAt(j)) {
                        String[] temp = {sortedBook[i][0], sortedBook[i][1]};
                        sortedBook[i][0] = sortedBook[i - 1][0];
                        sortedBook[i][1] = sortedBook[i - 1][1];
                        sortedBook[i - 1][0] = temp[0];
                        sortedBook[i - 1][1] = temp[1];
                        isSorted = false;
                        break;
                    }
                    else if ((int) sortedBook[i][0].charAt(j) > (int) sortedBook[i - 1][0].charAt(j)) {
                        break;
                    }
                }
            }
        }
        return sortedBook;
    }

    public static String getNewName() {
        String current_name = "";
        Scanner scanner = new Scanner(System.in);
        boolean isCorrectName = false;
        while (!isCorrectName) {
            System.out.println("Введите ФИО:");
            String name = scanner.nextLine();
            isCorrectName = checkName(name);
            if (!isCorrectName) {
                System.out.println("Имя некорректное!");
            } else {
                current_name = formatName(name);
            }
        }
        return current_name;

    }

    public static String getNewNumber() {
        String current_number = "";
        Scanner scanner = new Scanner(System.in);
        boolean isCorrectNumber = false;
        while (!isCorrectNumber) {
            System.out.println("Введите номер телефона:");
            String phoneNumber = scanner.nextLine();
            isCorrectNumber = checkPhoneNumber(phoneNumber);
            if (!isCorrectNumber) {
                System.out.println("Номер некорректный!");
            } else {
                current_number = formatPhoneNumber(phoneNumber);
            }
        }
        return current_number;
    }

    public static boolean checkNamePresence(String[][] book, String name) {
        boolean result = false;
        for (String[] strings : book) {
            if (strings[0].equals(name)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static boolean checkNumberPresence(String[][] book, String number) {
        boolean result = false;
        for (String[] strings : book) {
            if (strings[1].equals(number)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static int findNameIndex(String[][] book, String name) {
        int idx = 0;
        for (int i = 0; i < book.length; i++) {
            if (book[i][0].equals(name)) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    public static int findNumberIndex(String[][] book, String number) {
        int idx = 0;
        for (int i = 0; i < book.length; i++) {
            if (book[i][1].equals(number)) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    public static void bookInitialization(String[][] book) {
        for (String[] strings : book) {
            Arrays.fill(strings, "");
        }
    }
}