import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }
    public static int romanToArabic(String input) throws Exception {
        String roman = input.toUpperCase();
        int result = 0;
        List<Roman> Roman1 = Roman.getReverseSortedValues();
        int i = 0;
        while ((roman.length() > 0) && (i < Roman1.size())) {
            Roman symbol = Roman1.get(i);
            if (roman.startsWith(symbol.name())) {
                result += symbol.getValue();
                roman = roman.substring(symbol.name().length());
            } else {
                i++;
            }
        }
        if (roman.length() > 0) {
            throw new Exception("т.к. используются одновременно разные системы счисления");
        }
        return result;
    }
    public static String calc (String input) throws Exception {
        String[] splitSpaceInput = input.split(" ");
        String output;
        if (splitSpaceInput.length == 1)
            throw new Exception("т.к. строка не является математической операцией");
        if (splitSpaceInput.length != 3)
            throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        else {
            int a;
            int b;
            if (Character.isDigit(splitSpaceInput[0].charAt(0))) {
                a = Integer.parseInt(splitSpaceInput[0]);
                b = Integer.parseInt(splitSpaceInput[2]);
            } else if (splitSpaceInput[0].charAt(0) == '-') {
               throw new Exception("неподходящие числа");
            } else {
                a = romanToArabic(splitSpaceInput[0]);
                b = romanToArabic(splitSpaceInput[2]);
            }
            int c;
            if ((a > 0 && a <= 10) && (b > 0 && b <= 10)) {
                if (splitSpaceInput[1].equals("+"))
                    c = a + b;
                else if (splitSpaceInput[1].equals("-"))
                    c = a - b;
                else if (splitSpaceInput[1].equals("*"))
                    c = a * b;
                else if (splitSpaceInput[1].equals("/"))
                    c = a / b;
                else
                    throw new Exception("неподходящий математический оператор");
            } else
                throw new Exception("неподходящие числа");
            if (Character.isDigit(splitSpaceInput[0].charAt(0))) {
                output = String.valueOf(c);
            } else {
                List<Roman> romanNumer = Roman.getSortedValues();
                Roman currsym;
                try {
                    currsym = romanNumer.get(c - 1);
                } catch (Exception e) {
                    throw new Exception("т.к. в римской системе нет отрицательных чисел и нуля");
                }
                output = String.valueOf(currsym);
            }
        }
        return output;
    }
}