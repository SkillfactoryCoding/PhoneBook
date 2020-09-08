import java.util.Scanner;
import java.util.Arrays;
import java.lang.System;

public class PhoneBook {

    public static void main(String[] args) {
        //Добавить считывание ввода пользователя в цикле
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя, телефон или exit чтобы выйти");
        String input = scanner.nextLine();

        String[][] book = new String[0][2];
        while (!input.toLowerCase().equals("exit")) {
            String formatName = "";
            String formatNumber = "";

            if (!checkName(input)) {//если пользователь ввел не имя, то проверим - возможно это номер
                if (!checkPhoneNumber(input)) { //если все же не номер
                    System.out.println("Введите корректное имя, телефон или exit чтобы выйти");
                    input = scanner.nextLine();
                    continue;
                } else { // если пользователь ввел номер
                    formatNumber = formatPhoneNumber(input);
                    int indexRow = findNumberOfRowByNumber(book, formatNumber);
                    if (indexRow != -1) {// если нашли номер телефона в справочнике
                        System.out.println(book[indexRow][0]);
                        System.out.println("Введите имя, телефон или exit чтобы выйти");
                        input = scanner.nextLine();
                        continue;
                    } else {// не нашли номер в справочнике
                        System.out.println("Введите имя, пожалуйста");
                        String name = scanner.nextLine();
                        while (!checkName(name)) {
                            System.out.println("Неверный формат. " +
                                    "Введите в формате: Фамилия Имя Отчество через пробел");
                            name = scanner.nextLine();
                        }
                        formatName += formatName(name);
                    }
                }
            } else {// если пользователь ввел имя
                formatName = formatName(input);
                int indexRow = findNumberOfRowByName(book, formatName);// ищем имя в справочнике
                if (indexRow != -1) {// нашли имя в справочнике
                    System.out.println(book[indexRow][1]);
                    System.out.println("Введите имя, телефон или exit чтобы выйти");
                    input = scanner.nextLine();
                    continue;
                } else {//в справочнике имени нет
                    System.out.println("Введите номер телефона, пожалуйста");
                    String number = scanner.nextLine();
                    while (!checkPhoneNumber(number)) {
                        System.out.println("некорректный номер");
                        number = scanner.nextLine();
                    }
                    formatNumber += formatPhoneNumber(number);
                }
            }
            book = Arrays.copyOf(book, book.length+1);
            book[book.length-1] = new String[2];
            add(book, formatName, formatNumber);
            System.out.println("Введите имя, телефон или exit чтобы выйти");
            input = scanner.nextLine();
        }
        System.out.println("Показать все записи? y/n");
        if (scanner.nextLine().equals("y")) {
            list(bubbleSort(book));
        }
    }

    private static int findNumberOfRowByName(String[][] book, String formatName) {
        for (int i = 0; i < book.length; ++i) {
            if (book[i][0].equals(formatName)) return i;
        }
        return -1;
    }

    private static int findNumberOfRowByNumber(String[][] book, String formatNamber) {
        for (int i = 0; i < book.length; ++i) {
            if (book[i][1].equals(formatNamber)) return i;
        }
        return -1;
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        String cleanNumber = phoneNumber.replaceAll("[^0-9]", "");
        return cleanNumber.length() == 11; //
    }

    public static boolean checkName(String name) {
        if (name.matches(".*\\d.*")) return false;//цифр в имени быть не должно
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

    private static String[][] bubbleSort(String[][] array) {
        boolean sorted = false;
        while (!sorted) {
            for (int i = 0; i < array.length - 1; ++i) {
                if (array[i][0].compareTo(array[i + 1][0]) > 0) {
                    swap(array, i, i + 1);
                    sorted = false;
                    break;
                } else sorted = true;
            }
        }
        return array;
    }

    private static void swap(String[][] array, int i1, int i2) {
        String[] tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }
}
