public class Func {
    public static void list(String[][] book) {
        for (String[] strings : book) {
            System.out.println(strings[0] + ": " + strings[1]);
        }
        //print phone book
    }

    public static String[][] sort(String[][] array) {
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
