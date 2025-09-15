package Matrix;

import java.util.Arrays;
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
        try (Scanner scanner = new Scanner(new File(filename))) {
            List<int[]> rows = new ArrayList<>();

            // Читаем все строки файла
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue; // Пропускаем пустые строки

                // Разбиваем строку на числа
                String[] numbers = line.split("\\s+");
                int[] row = new int[numbers.length];

                for (int i = 0; i < numbers.length; i++) {
                    row[i] = Integer.parseInt(numbers[i]);
                }

                rows.add(row);
            }

            if (rows.isEmpty()) {
                throw new IOException("Файл пуст");
            }

            // Проверяем, что все строки одинаковой длины
            int columns = rows.get(0).length;
            for (int i = 1; i < rows.size(); i++) {
                if (rows.get(i).length != columns) {
                    throw new IOException("Строки матрицы имеют разную длину");
                }
            }

            // Преобразуем в двумерный массив
            return rows.toArray(new int[0][]);
        }
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
    public static int findMaxSumOfOddRows(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int maxSum = 0;

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
            }
        }

        return maxSum;
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
        int m = matrix[0].length;

        // Создаем массив для хранения максимальных элементов каждой строки и их индексов
        int[][] rowInfo = new int[n][2]; // [0] - максимальный элемент, [1] - индекс строки

        for (int i = 0; i < n; i++) {
            int maxInRow = matrix[i][0];
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] > maxInRow) {
                    maxInRow = matrix[i][j];
                }
            }
            rowInfo[i][0] = maxInRow;
            rowInfo[i][1] = i;
        }

        // Сортируем массив rowInfo по максимальным элементам строк
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (rowInfo[i][0] > rowInfo[j][0]) {
                    // Меняем местами максимальные элементы
                    int tempMax = rowInfo[i][0];
                    int tempIndex = rowInfo[i][1];

                    rowInfo[i][0] = rowInfo[j][0];
                    rowInfo[i][1] = rowInfo[j][1];

                    rowInfo[j][0] = tempMax;
                    rowInfo[j][1] = tempIndex;
                }
            }
        }

        // Создаем новую матрицу с упорядоченными строками
        int[][] sortedMatrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            int originalRowIndex = rowInfo[i][1];
            sortedMatrix[i] = Arrays.copyOf(matrix[originalRowIndex], m);
        }

        return sortedMatrix;
    }


}