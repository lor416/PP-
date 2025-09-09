package Matrix;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Ввод матрицы
            //int[][] matrix = MatrixOperations.inputMatrix();
            int[][] matrix = MatrixOperations.inputMatrixFromFile("input.txt");


            // Вывод исходной матрицы
            System.out.println("\nИсходная матрица:");
            MatrixOperations.printMatrix(matrix);

            // 1. Поиск максимальной суммы модулей среди строк с нечетными элементами
            int maxSum = MatrixOperations.findMaxSumOfOddRows(matrix);
            if (maxSum != 0) {
                System.out.println("\nМаксимальная сумма модулей значений строк, содержащих только нечетные элементы = " + maxSum);
            } else {
                System.out.println("\nМатрица не содержит строк, состоящих только из нечетных значений");
            }

            // 2. Подсчет столбцов с уникальными элементами
            int uniqueColumnsCount = MatrixOperations.countColumnsWithUniqueElements(matrix);
            System.out.println("Количество столбцов с попарно различными числами = " + uniqueColumnsCount);

            // 3. Сортировка строк по максимальным элементам
            int[][] sortedByRows = MatrixOperations.sortRowsByMaxElement(matrix);
            System.out.println("\nМатрица после упорядочивания строк по неубыванию их наибольших элементов:");
            MatrixOperations.printMatrix(sortedByRows);


        } catch (IOException e) {
            System.out.println("Ошибка чтения с клавиатуры: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введено не число: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }
}