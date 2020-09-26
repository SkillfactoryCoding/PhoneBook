import java.util.Scanner;

public class PhoneBook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean isCorrectName = false;
        while (!isCorrectName) {
            System.out.println("Введите ФИО:");
            String name = scanner.nextLine();
            isCorrectName = checkName(name);
            if (!isCorrectName) {
                System.out.println("Имя некорректное!");
            } else {
                System.out.println(formatName(name));
            }
        }

        boolean isCorrectNumber = false;
        while (!isCorrectNumber) {
            System.out.println("Введите номер телефона:");
            String phoneNumber = scanner.nextLine();
            isCorrectNumber = checkPhoneNumber(phoneNumber);
            if (!isCorrectNumber) {
                System.out.println("Номер некорректный!");
            } else {
                System.out.println(formatPhoneNumber(phoneNumber));
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

    public static void add(String[][] book, String name, String number) {
        //add logic
    }

    public static void list(String[][] book) {
        //print phone book
    }
}
