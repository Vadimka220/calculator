import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine(); // вывод наших вводных в кансоль

        try {
            String result = calc(input);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calc(String input) throws Exception {
        String[] parts = input.split(" ");   // создаем массив "части" и делим ее составные пробелом
        if (parts.length != 3) {   // фиксируем колличество частей
            throw new IllegalArgumentException("Некорректное выражение");
        }

        String number1 = parts[0];
        String operator = parts[1];
        String number2 = parts[2];

        int x, y;    // объявляем части
        boolean isRoman = false;   // ставим значение фолз, т.к. по умолчанию мы используем арабские цифры

        try {
            x = Integer.parseInt(number1);   // преобразовываем номер 1 и номер 2 в целочисленные значения
            y = Integer.parseInt(number2);   // и присваеваем переменным х и у
        } catch (NumberFormatException e) {
            x = RomanNumeral.convertToInteger(number1);
            y = RomanNumeral.convertToInteger(number2);
            isRoman = true;
        }

        if (x < 1 || x > 10 || y < 1 || y > 10) {  // убеждаемся что наши вводные соответсвуют критериям
            throw new IllegalArgumentException("Числа должны быть от 1 до 10");
        }

        int resultat;
        switch (operator) {     // определяем операнд
            case "+":
                resultat = x + y;
                break;
            case "-":
                resultat = x - y;
                break;
            case "*":
                resultat = x * y;
                break;
            case "/":
                resultat = x / y;
                break;
            default:
                throw new IllegalArgumentException("Некорректная операция");
        }

        if (isRoman) {
            if (resultat < 1) {
                throw new IllegalArgumentException("Результат не может быть меньше единицы");
            }
            return RomanNumeral.convertToRoman(resultat);
        } else {
            return String.valueOf(resultat);
        }
    }
}

class RomanNumeral {
    private static final String[] ROMAN_SYMBOLS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static final int[] ROMAN_VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
         //
    public static int convertToInteger(String roman) throws IllegalArgumentException {
        int resultat = 0;
        int i = 0;

        for (int j = 0; j < ROMAN_SYMBOLS.length; j++) {
            String symbol = ROMAN_SYMBOLS[j];
            int value = ROMAN_VALUES[j];

            while (roman.startsWith(symbol, i)) {
                resultat += value;
                i += symbol.length();
            }
        }

        if (i != roman.length()) {
            throw new IllegalArgumentException("Некорректное римское число");
        }

        return resultat;
    }

    public static String convertToRoman(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Число должно быть положительным");
        }

        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < ROMAN_VALUES.length; i++) {
            int value = ROMAN_VALUES[i];
            String symbol = ROMAN_SYMBOLS[i];

            while (number >= value) {
                roman.append(symbol);
                number -= value;
            }
        }

        return roman.toString();
    }
}