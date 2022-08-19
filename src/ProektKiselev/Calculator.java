package ProektKiselev;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp = scn.nextLine();
        if(!exp.matches("([-0-9]+|[IVX]+)[\\+\\-\\*/]([-0-9]+|[IVX]+)")){
            throw new RuntimeException("Неверный формат");
        }
        //Определяем арифметическое действие:
        int actionIndex=-1;
        for (int i = 0; i < actions.length; i++) {
            if(exp.contains(actions[i])){
                actionIndex = i;
                break;
            }
        }
        //Если не нашли арифметического действия, завершаем программу
        if(actionIndex==-1){
            System.out.println("Некорректное выражение");
            return;
        }
        //Делим строчку по найденному арифметическому знаку
        String[] data = exp.split(regexActions[actionIndex]);
        //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
        if(converter.isRoman(data[0]) == converter.isRoman(data[1])){
            int a,b;
            //Определяем, римские ли это числа
            boolean isRoman = converter.isRoman(data[0]);
            if(isRoman){
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);
            if(actions[actionIndex].equals("-") && (b >= a)){
                        throw new RuntimeException ("В римской системе нет отрицательных чисел");
                    }
                //если римские, то конвертируем их в арабские
            }else{
                //если арабские, конвертируем их из строки в число
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
                                            }
            if((a<=10) && (b<=10) && (a>1) && (b>1)) {
                //выполняем с числами арифметическое действие
                int result;
                switch (actions[actionIndex]) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    default:
                        result = a / b;
                        break;
                }


                if (isRoman) {
                    //если числа были римские, возвращаем результат в римском числе
                    System.out.println(converter.intToRoman(result));
                } else {
                    //если числа были арабские, возвращаем результат в арабском числе
                    System.out.println(result);
                }
            }else{
                throw new RuntimeException ("Калькулятор должен принимать на вход числа от 1 до 10 включительно");
            }

        }else{
            throw new RuntimeException ("Числа должны быть в одном формате");
        }


    }
}
