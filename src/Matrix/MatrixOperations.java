package Matrix;

//import java.util.Arrays;
import java.io.*;
import java.util.Scanner;
import java.util.*;


public class MatrixOperations {

    // Метод для ввода матрицы с консоли
    public static int[][] inputMatrix() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        System.out.println("Введите размерность матрицы");

        System.out.print("Количество строк: ");
        int n = Integer.parseInt(br.readLine());

        System.out.print("Количество столбцов: ");
        int m = Integer.parseInt(br.readLine());

        System.out.println("n = " + n);
        System.out.println("m = " + m);

        int[][] matrix = new int[n][m];

        // Ввод элементов матрицы с клавиатуры
        System.out.println("Введите элементы матрицы:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print("Элемент [" + i + "][" + j + "]: ");
                matrix[i][j] = Integer.parseInt(br.readLine());
            }
        }

        return matrix;
    }
    // Метод для ввода матрицы из файла
    public static int[][] inputMatrixFromFile(String filename) throws IOException {
        File file = new File(filename);
        Scanner sc = new Scanner(file);

        // Читаем размерность матрицы
        if (!sc.hasNextInt()) {
            sc.close();
            throw new IOException("Не найдено количество строк");
        }
        int n = sc.nextInt();

        if (!sc.hasNextInt()) {
            sc.close();
            throw new IOException("Не найдено количество столбцов");
        }
        int m = sc.nextInt();

        // Создаем матрицу
        int[][] matrix = new int[n][m];

        // Заполняем матрицу данными
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (sc.hasNextInt()) {
                    matrix[i][j] = sc.nextInt();
                } else {
                    sc.close();
                    throw new IOException("Недостаточно чисел в файле");
                }
            }
        }

        sc.close();
        return matrix;
    }

    // Метод для вывода матрицы
    public static void printMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 1. Найти максимальную сумму модулей элементов среди строк с только нечетными элементами
    /*public static int findMaxSumOfOddRows(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int maxSum = 0;
        int I = 0;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            boolean allOdd = true;

            for (int j = 0; j < m && allOdd; j++) {
                if (matrix[i][j] % 2 != 0) {
                    sum += Math.abs(matrix[i][j]);
                } else {
                    allOdd = false;
                    sum = 0;
                }
            }

            if (allOdd && sum > maxSum) {
                maxSum = sum;
                I = i;
            }
        }

        return maxSum;

    }*/


    // Метод для нахождения максимальной суммы модулей и номера строки
    public static int[] findMaxSumOfOddRows(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int maxSum = 0;
        int maxRowIndex = -1;  // -1 означает, что подходящих строк не найдено

        for (int i = 0; i < n; i++) {
            int sum = 0;
            boolean allOdd = true;

            // Проверяем, все ли элементы в строке нечетные
            for (int j = 0; j < m && allOdd; j++) {
                if (matrix[i][j] % 2 != 0) {
                    sum += Math.abs(matrix[i][j]);
                } else {
                    allOdd = false;
                    sum = 0;
                }
            }

            // Если строка подходит и сумма больше текущего максимума
            if (allOdd && sum > maxSum) {
                maxSum = sum;
                maxRowIndex = i;  // запоминаем номер строки
            }
        }

        // Возвращаем массив из двух элементов: [максимальная сумма, номер строки]
        return new int[]{maxSum, maxRowIndex};
    }

    // 2. Подсчитать количество столбцов с попарно различными числами
    public static int countColumnsWithUniqueElements(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int count = 0;

        for (int j = 0; j < m; j++) {
            boolean hasDuplicates = false;

            // Проверяем каждый столбец на наличие дубликатов
            for (int i = 0; i < n - 1 && !hasDuplicates; i++) {
                for (int k = i + 1; k < n; k++) {
                    if (matrix[i][j] == matrix[k][j]) {
                        hasDuplicates = true;
                        break;
                    }
                }
            }

            if (!hasDuplicates) {
                count++;
            }
        }

        return count;
    }

    // 3. Упорядочить строки матрицы по неубыванию их наибольших элементов
    public static int[][] sortRowsByMaxElement(int[][] matrix) {
        int n = matrix.length;

        // Находим максимальные элементы для каждой строки
        int[] maxElements = new int[n];
        for (int i = 0; i < n; i++) {
            int max = matrix[i][0];
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
            maxElements[i] = max;
        }

        // Сортируем строки по их максимальным элементам
        for (int k = 0; k < n - 1; k++) {
            for (int i = 0; i < n - k - 1; i++) {
                if (maxElements[i] > maxElements[i + 1]) {
                    // Меняем местами максимальные элементы
                    int tempMax = maxElements[i];
                    maxElements[i] = maxElements[i + 1];
                    maxElements[i + 1] = tempMax;

                    // Меняем местами строки матрицы
                    int[] tempRow = matrix[i];
                    matrix[i] = matrix[i + 1];
                    matrix[i + 1] = tempRow;
                }
            }
        }

        return matrix;
    }


}