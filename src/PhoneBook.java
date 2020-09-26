import java.util.Scanner;

public class PhoneBook {
    public static void main(String[] args) {
        String[][] book = new String[20][2];
        Scanner scanner = new Scanner(System.in);
        int record_counter = 0;
        boolean isWorking = true;

        while (isWorking) {
            System.out.println("Введите N для");
            char ans = scanner.next().charAt(0);

            String[] newRecord = getNewRecord();
            String current_name = newRecord[0];
            String current_number = newRecord[1];

            record_counter = add(book, current_name, current_number, record_counter);
            System.out.println(current_name + " " + current_number);
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

    public static int add(String[][] book, String name, String number, int counter) {
        book[counter][0] = name;
        book[counter][1] = number;
        counter++;
        return counter;
    }

    public static void list(String[][] book) {
        //print phone book
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
}
