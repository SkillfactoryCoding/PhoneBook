public class PhoneBook {
    public static void main(String[] args) {
        String[][] book = {{"Фомина Александра Олеговна", "8 123 456 78 99"},
                {"Иванов Иван Иваныч", "8 911 999 99 99"},
                {"Сергеева Мария Анатольевна", "8 888 454 33 33"},
                {"Яшкин Валентин Петрович", "8 788 567 44 44"},
                {"Грозный Иван Васильевич", "7 787 567 44 44"}};
        Phone phoneBook = new Phone(book);
        phoneBook.useBook();
    }
}