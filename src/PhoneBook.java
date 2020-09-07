import java.util.Scanner;
import java.util.Arrays;
import java.lang.System;

public class PhoneBook {

    public static void main(String[] args) {
        //Добавить считывание ввода пользователя в цикле
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        String[][] book = new String[0][2];
        while (!name.toLowerCase().equals("exit")) {
            if (!checkName(name)) System.out.println("Введите корректное имя или exit чтобы выйти");
            else {
                String formatName = formatName(name);
                int indexRow = findNumberOfRowByName(book, formatName);// ищем имя в справочнике
                if (indexRow != -1) {
                    System.out.println(book[indexRow][1]);
                } else {
                    System.out.println("Введите номер телефона, пожалуйста");
                    String number = scanner.nextLine();
                    while (!checkPhoneNumber(number)) {
                        System.out.println("некорректный номер");
                        number = scanner.nextLine();
                    }
                    String formatNumber = formatPhoneNumber(number);
                    String[][] tmpArray = Arrays.copyOf(book, book.length);
                    book = new String[book.length + 1][2];
                    System.arraycopy(tmpArray, 0, book, 0, tmpArray.length);
                    add(book, formatName, formatNumber);

                }
                System.out.println("Введите имя или exit чтобы выйти");
            }
            name = scanner.nextLine();
        }
        System.out.println("Показать все записи? y/n");
        if (scanner.nextLine().equals("y")) {
            list(book);
        }
    }

    private static int findNumberOfRowByName(String[][] book, String formatName) {
        for (int i = 0; i < book.length; ++i) {
            if (book[i][0].equals(formatName)) return i;
        }
        return -1;
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        String cleanNumber = phoneNumber.replaceAll("[^0-9]", "");
        return cleanNumber.length() == 11; //
    }

    public static boolean checkName(String name) {
        String[] checked = name.trim().split(" ");
        if (checked.length == 3) {
            if (!(checked[0] + checked[1] + checked[2]).contains(" ")) return true;
            else return false;
        }
        return false;
    }

    public static String formatName(String name) {
        String[] words = name.trim().split(" ");
        String res = "";
        for (int i = 0; i < words.length; ++i) {
            words[i] = words[i].replaceFirst(("" + words[i].charAt(0)), ("" + words[i].charAt(0)).toUpperCase());
            res = res.concat(words[i].concat(" "));
        }
        return res.trim();
    }

    public static String formatPhoneNumber(String number) {
        String cleanNumber = number.replaceAll("[^0-9]", "");
        return cleanNumber.substring(0, 1) + " " + cleanNumber.substring(1, 4) + " " + cleanNumber.substring(4, 7) + " "
                + cleanNumber.substring(7, 9) + " " + cleanNumber.substring(9);
    }

    public static void add(String[][] book, String name, String number) {
        book[book.length - 1][0] = name;
        book[book.length - 1][1] = number;
        //add logic
    }

    public static void list(String[][] book) {
        for (int i = 0; i < book.length; ++i) {
            System.out.println(book[i][0] + ": " + book[i][1]);
        }
        //print phone book
    }
}
