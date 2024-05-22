import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Правила калькулятора:" +
                        "\nНеобходимо ввести два числа( 1 и больше 2 не подходит )" +
                        "\nЧисла не должный быть от 0 до 10" +
                        "\nЧисла должны быть целыми" +
                        "\nМатематические операции должны быть ( * / - +), нельзя использовать другие символы" +
                        "\nСлева и справа от символа должны быть пробелы( Пример: Правильный вариант: 2 + 3 / Неправильный вариант: 2+3 )" +
                        "\nНельзя вычислять римские числа с арабскими. Только римские с римскими, а арабские с арабскими" +
                        "\nВведите числа для калькулятора: ");
        String text = scanner.nextLine();
        System.out.println(parse(text));
    }

    public static String parse(String text) throws Exception {
        boolean isArab;
        String simb;
        int num1 = 0;
        int num2 = 0;
        String result;

        String[] numbers = text.split(" ");
        if (numbers.length != 3) throw new Exception("Вы ввели 1 или более 2 чисел или забыли пробелы у символа.");
        simb = examination(numbers[1]);
        if(simb == null) throw new Exception("Вы ввели некорректный математический символ");
        if (Roman.isArab(numbers[0]) && Roman.isArab(numbers[2])) {
            num1 = Roman.convertToArab(numbers[0]);
            num2 = Roman.convertToArab(numbers[2]);
            isArab = false;
        } else if (!Roman.isArab(numbers[0]) && !Roman.isArab(numbers[2])) {
            num1 = Integer.parseInt(numbers[0]);
            num2 = Integer.parseInt(numbers[2]);
            isArab = true;
        } else throw new Exception("Числа для вычисления должны быть одинаковыми");

        if(num1 > 10 || num2 > 10) throw new Exception("Числа должны быть от 0 до 10");

        int arabia = calc(num1, num2, simb);
        if(isArab) {
            result = String.valueOf(arabia);
        } else {
            if(arabia <= 0) throw new Exception("Римское число не может быть отрицательным");
            result = Roman.convertToRoman(arabia);
        }
        return result;
    }

    static int calc(int num1, int num2, String simb) {
        int result = 0;
        switch (simb) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            default: break;
        }
        return result;
    }

    static String examination(String operation) {
        String result;
    switch (operation) {
        case "+": result = "+";
            break;
        case "-": result = "-";
            break;
        case "/": result = "/";
            break;
        case "*": result = "*";
            break;
        default: result = null;
    }
        return result;
    }
}

class Roman {
    static String[] romanArray = new String[] {
            "O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };
    public static boolean isArab(String elem) {
        for(String number : romanArray) {
            if(elem.equals(number)) {
                return true;
            }
        }
        return false;
    };

    public static int convertToArab(String elem) {
        for( int i = 0; i < romanArray.length; i++) {
            if(elem.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    };

    public static String convertToRoman(int index) {
      return romanArray[index];
    };
}