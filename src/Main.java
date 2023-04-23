import java.lang.Exception;
import java.util.Scanner;
import java.util.Arrays;

public class Main {

     static boolean roman = false;

    public static void main(String[] args) throws  ScannerExeption{
        // Принимаем выражение на вход, убираем все пробелы, если
        // введено выражение в Римской системе - toUpperCase() в верхний регистр:
        Scanner  sc = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String input = sc.nextLine().toUpperCase().replaceAll("\\s+", "");
        sc.close();
        // Выводим результат выражения:
        int result = Main.calc(input);

        if (roman) {
          System.out.println("Результат: " + RomanConverter.arabToRoman(result));
        }else{
            System.out.println("Результат: " + result);
        }

    }
    public static int calc(String input) throws  ScannerExeption{
        int result = 0;

        char[] operand = {'+', '-', '*', '/'};
        String[] regex = {"\\+", "\\-", "\\*", "\\/"};
        int indexChar = indexOperation(input, operand);

        String[] expSplit = input.split(regex[indexChar]);

        boolean[] romanOrArabic = romanManager(expSplit); // ind_0 - arabic ; ind_1 - roman
        int[] operands = {0,0};

        if (romanOrArabic[1] == true) { // ROMAN
            roman = true;
            operands[0] = RomanConverter.romanInArabic(expSplit[0]);
            operands[1] = RomanConverter.romanInArabic(expSplit[1]);

        } else if (romanOrArabic[0] == true) { // ARABIC
            operands[0] = Integer.parseInt(expSplit[0]);
            operands[1] = Integer.parseInt(expSplit[1]);
        }

        if (operands[0] > 10 | operands[1] > 10) {
            throw new ScannerExeption("Введите целое число от 1 до 10!");
        }

        String operandStr = String.valueOf(operand[indexChar]);
        switch (operandStr) {
            case "+": result = operands[0] + operands[1];break;
            case "-": result = operands[0] - operands[1];break;
            case "*": result = operands[0] * operands[1];break;
            case "/": if (operands[1] != 0) {result = operands[0] / operands[1];break;} else {
                throw new ScannerExeption("Делить на 0 нельзя!");
            }
        }
        if(result < 0 && roman==true) {
            throw new ScannerExeption("В Римской системе нет отрицательных чисел!");
        }
        return result;
    }
    static int indexOperation(String input, char[] operand) throws ScannerExeption{

        char[] expChar = input.toCharArray();
        int indexChar=-1;
        int countOperand = 0;
        for(int i = 0; i < operand.length; i++) {
            for(int j = 0; j < expChar.length; j++){
                if(operand[i] == expChar[j]){
                    countOperand++;
                    if(countOperand>1 | (expChar[0] == operand[i]) | (expChar[expChar.length - 1] == operand[i])) {
                        throw new ScannerExeption("Формат математической операции не " +
                                "удовлетворяет заданию - два операнда и один оператор");
                    }
                    indexChar = i;
                }
            }
        }
        if (countOperand == 0) {throw new ScannerExeption("Cтрока не является математической операцией!");}
        return indexChar;
    }

    static boolean[] romanManager(String[] expSplit) throws ScannerExeption {
        boolean[] arabicOrRoman = {false, false}; // 0 - arabic ; 1 - roman

        for(int i = 0; i<expSplit.length; i++) {
            if (expSplit[i].matches("[0-9]+")) {
                arabicOrRoman[0] = true;
            } else if (expSplit[i].matches("[IVXLCDM]+")) {
                arabicOrRoman[1] = true;
            }
            if (!arabicOrRoman[0] && !arabicOrRoman[1]) {
                throw new ScannerExeption("Некорректно записано число! ");
            }
        }
        if(arabicOrRoman[0] == arabicOrRoman[1]){
            throw new ScannerExeption("Используются одновременно разные системы счисления");
        }
        return  arabicOrRoman;
    }

}

