import java.util.*;

public class Main {

    public static String calc(String input) throws Exception {

        /** необходимые данные*/
        ArrayList<String> inputNumbersA = new ArrayList<>();
        Collections.addAll(inputNumbersA, "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        ArrayList<String> inputNumbersR = new ArrayList<>();
        Collections.addAll(inputNumbersR, "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");
        ArrayList<String> NumbersA = new ArrayList<>();
        Collections.addAll(NumbersA, "100", "90", "50", "40", "10", "9", "5", "4", "1");
        ArrayList<String> NumbersR = new ArrayList<>();
        Collections.addAll(NumbersR, "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I");
        String rNumberStr = "IIIVIIIX";
        String aNumberStr =  "12345678910";
        String mExampleStr = "+-/*";

        /** данные для алгоритмов*/
        int resultA = 0;
        boolean flagA = true;
        String resultR = "";
        String[] example = input.split(" ");

        /** обрабатывает исключения*/
        boolean flagE = true;
        if (example.length < 3){
            throw new Exception("throws Exception //т.к. строка не является математической операцией");
        }else {
            for (String e : example) {
                if (flagE) {
                    if (!(rNumberStr.contains(e) || (aNumberStr.contains(e)))) {
                        throw new Exception("throws Exception //т.к. строка не является математической операцией");
                    } else {
                        flagE = false;
                    }
                } else {
                    if (!((mExampleStr.contains(e)))) {
                        throw new Exception("throws Exception //т.к. строка не является математической операцией");
                    } else {
                        flagE = true;
                    }
                }
            }
            if (!(rNumberStr.contains(example[example.length - 1]) || (aNumberStr.contains(example[example.length - 1])))) {
                throw new Exception("throws Exception //т.к. строка не является математической операцией");
            }
            if (example.length > 3){
                throw new Exception("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
            if (!((rNumberStr.contains(example[0]) & rNumberStr.contains(example[2])) || (aNumberStr.contains(example[0]) & aNumberStr.contains(example[2])))){
                throw new Exception("throws Exception //т.к. используются одновременно разные системы счисления");
            }
        }

        /** данные для алгоритмов*/
        String firstValues = example[0];
        String secondVales = example[2];
        String mathExample = example[1];

        /** смотрит являются ли числа римскими и заменяет их на арабские*/
        if (rNumberStr.contains(firstValues)){
            firstValues = inputNumbersA.get(inputNumbersR.indexOf(firstValues));
            secondVales = inputNumbersA.get(inputNumbersR.indexOf(secondVales));
            flagA = false;
        }

        /** решает пример*/
        switch (mathExample){
            case "+":
                resultA = Integer.parseInt(firstValues) + Integer.parseInt(secondVales);
                break;
            case "-":
                resultA = Integer.parseInt(firstValues) - Integer.parseInt(secondVales);
                break;
            case "/":
                resultA = Integer.parseInt(firstValues) / Integer.parseInt(secondVales);
                break;
            case "*":
                resultA = Integer.parseInt(firstValues) * Integer.parseInt(secondVales);
                break;
        }

        /** обрабатывает исключение*/
        if ((!flagA) & (resultA < 0)){
            throw new Exception("throws Exception //т.к. в римской системе нет отрицательных чисел");
        }
        if ((!flagA) & (resultA == 0)){
            throw new Exception("throws Exception //т.к. в римской системе нет нуля");
        }

        /** возвращает решение в нужном формате*/
        if (flagA){
            return Integer.toString(resultA);
        }
        else {
            while (resultA != 0){
                for(String n : NumbersA) {
                    if (resultA >= Integer.parseInt(n)){
                        resultA -= Integer.parseInt(n);
                        resultR += NumbersR.get(NumbersA.indexOf(n));
                    }
                }
            }
            return resultR;
        }
    }


    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);
        String num = in.nextLine();
        System.out.println(calc(num));

    }

}
