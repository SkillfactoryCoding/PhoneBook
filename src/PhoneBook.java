import java.util.Scanner;

public class PhoneBook {

    public static void main(String[] args) {
        //Добавить считывание ввода пользователя в цикле
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        String[][] book = new String[1][2];
        while (!name.toLowerCase().equals("exit")){
            if (!isCorrectName(name)) System.out.println("Введите корректное имя!");
            else {
                String formatName = formatName(name);
                System.out.println(formatName);
//                if(checkName(formatName)){
//
//                }
            }
            name = scanner.nextLine();
        }
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        return true;
    }

    public static boolean checkName(String name) {
        return true;
    }
    public static boolean isCorrectName(String name) {
        String[] checked = name.trim().split(" ");
        if (checked.length == 3) {
            if (!(checked[0]+checked[1]+checked[2]).contains(" ")) return true;
            else return false;
        }
        return false;
    }

    public static String formatName(String name) {
        String[] words = name.trim().split(" ");
        String res = "";
        for (int i = 0; i < words.length; ++i) {
            words[i] = words[i].replaceFirst(("" + words[i].charAt(0)), ("" + words[i].charAt(0)).toUpperCase());
            res= res.concat(words[i].concat(" "));
        }
        return res.trim();
    }

    public static String formatPhoneNumber(String number) {
        return "";
    }

    public static void add(String[][] book, String name, String number) {
        //add logic
    }

    public static void list(String[][] book) {
        //print phone book
    }
}
