package calkylator;

import java.io.*;
import java.text.NumberFormat;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите значение x, а после введите точность k");

        try {
            // Ввод
            double x = readDouble("x");
            int k = readInt("k");

            // Создание калькулятора и выполнение вычислений
            Calculator calculator = new Calculator(x, k);
            calculator.calculateExponential();

            // Вывод
            displayResults(calculator);

        } catch (IOException e) {
            System.out.println("Ошибка чтения с клавиатуры");
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат числа");
        }
    }

    private static double readDouble(String prompt) throws IOException {
        System.out.print("Введите " + prompt + ": ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return Double.parseDouble(reader.readLine());
    }

    private static int readInt(String prompt) throws IOException {
        System.out.print("Введите " + prompt + ": ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(reader.readLine());
    }

    private static void displayResults(Calculator calculator) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(3);

        System.out.println("\n=== РЕЗУЛЬТАТЫ ВЫЧИСЛЕНИЙ ===");
        System.out.println("x = " + calculator.getX());
        System.out.println("k = " + calculator.getK());
        System.out.println("epsilon = " + calculator.getEpsilon());
        System.out.println("Точное значение e^x: " + formatter.format(calculator.getExactValue()));
        System.out.println("Вычисленное значение: " + formatter.format(calculator.getCalculatedValue()));

    }
}