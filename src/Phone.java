import java.util.Scanner;
import java.util.Arrays;


public class Phone {
    private int indexOfLastElement;
    private Scanner scanner;
    private String[][] book;

    public Phone(String[][] book) {
        indexOfLastElement = book.length - 1;
        //телефонный справочник будет дополняться, поэтому
        //передаваемый массив не должен содержать пустых строк
        //столюцов в передаваемой таблице должно быть два
        //за тем, чтобы передаваемый массив был верным несет ответственность пользователь
        this.book = Arrays.copyOf(book, book.length);
        scanner = new Scanner(System.in);
    }

    public Phone() {
        indexOfLastElement = -1;
        book = new String[0][2];
        scanner = new Scanner(System.in);
    }

    public void useBook() {
        //Добавить считывание ввода пользователя в цикле
        System.out.println("Введите имя, телефон или exit чтобы выйти");
        String input = scanner.nextLine();
        while (!input.toLowerCase().equals("exit")) {
            String formatName = "";
            String formatNumber = "";
            if (!checkName(input)) {//если пользователь ввел не имя, то проверим - возможно это номер
                if (!checkPhoneNumber(input)) { //если введен не номер
                    System.out.println("Введите корректное имя, телефон или exit чтобы выйти");
                    input = scanner.nextLine();
                    continue;
                } else { // если пользователь ввел номер
                    formatName += askName(input);
                    if (formatName.equals("")) {//введен номер, который уже есть в справонике
                        input = scanner.nextLine();
                        continue;
                    }
                    formatNumber += formatPhoneNumber(input);
                }
            } else {// если пользователь ввел имя
                formatNumber += askNumber(input);
                if (formatNumber.equals("")) {
                    input = scanner.nextLine();
                    continue;
                }
                formatName += formatName(input);
            }
            book = Arrays.copyOf(book, book.length + 1);
            indexOfLastElement = book.length - 1;
            book[indexOfLastElement] = new String[2];
            add(formatName, formatNumber);
            System.out.println("Введите имя, телефон или exit чтобы выйти");
            input = scanner.nextLine();
        }
        System.out.println("Показать все записи? y/n");
        if (scanner.nextLine().equals("y")) {
            list();
        }
    }

    public void list() {
        Func.list(Func.sort(book));
    }

    private String askNumber(String input) {
        String res = "";
        if (!isExistName(input)) {//в справочнике имени нет
            System.out.println("Введите номер телефона, пожалуйста");
            String number = scanner.nextLine();
            while (!checkPhoneNumber(number)) {
                System.out.println("некорректный номер");
                number = scanner.nextLine();
            }
            res += formatPhoneNumber(number);
        }
        return res;
    }

    private boolean isExistName(String name) {
        boolean res = false;
        String formatName = formatName(name);
        int indexOfLastElementRow = findNumberOfRowByName(book, formatName);// ищем имя в справочнике
        if (indexOfLastElementRow != -1) {// нашли имя в справочнике
            System.out.println(book[indexOfLastElementRow][1]);
            System.out.println("Введите имя, телефон или exit чтобы выйти");
            res = true;
        }
        return res;
    }

    private String askName(String input) {
        String res = "";
        if (!isExistNumber(input)) {// не нашли номер в справочнике
            System.out.println("Введите имя, пожалуйста");
            String name = scanner.nextLine();
            while (!checkName(name)) {
                System.out.println("Неверный формат. " +
                        "Введите в формате: Фамилия Имя Отчество через пробел");
                name = scanner.nextLine();
            }
            res += formatName(name);
        }
        return res;
    }

    private boolean isExistNumber(String number) {
        boolean res = false;
        String formatNumber = formatPhoneNumber(number);
        int indexOfLastElementRow = findNumberOfRowByNumber(book, formatNumber);
        if (indexOfLastElementRow != -1) {// если нашли номер телефона в справочнике
            System.out.println(book[indexOfLastElementRow][0]);
            System.out.println("Введите имя, телефон или exit чтобы выйти");
            res = true;
        }
        return res;
    }

    private static int findNumberOfRowByName(String[][] book, String formatName) {
        int res = -1;
        for (int i = 0; i < book.length; ++i) {
            if (book[i][0].equals(formatName)) {
                res = i;
                break;
            }
        }
        return res;
    }

    private static int findNumberOfRowByNumber(String[][] book, String formatNumber) {
        int res = -1;
        for (int i = 0; i < book.length; ++i) {
            if (book[i][1].equals(formatNumber)) {
                res = i;
                break;
            }
        }
        return res;
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        String cleanNumber = phoneNumber.replaceAll("[^0-9]", "");
        return cleanNumber.length() == 11; //
    }

    public static boolean checkName(String name) {
        boolean res = false;
        //цифр и пробелов в имени быть не должно
        if (name.trim().matches("[\\D&&\\S]+\\s{1}[\\D&&\\S]+\\s{1}[\\D&&\\S]+")) {
            String[] checked = name.trim().split(" ");
            if (checked.length == 3) {
                res = true;
            }
        }
        return res;
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
        return cleanNumber.charAt(0) + " " + cleanNumber.substring(1, 4) + " " + cleanNumber.substring(4, 7) + " "
                + cleanNumber.substring(7, 9) + " " + cleanNumber.substring(9);
    }

    private void add(String name, String number) {
        book[indexOfLastElement][0] = name;
        book[indexOfLastElement][1] = number;
    }
}
