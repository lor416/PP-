import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            List<Book> allBooks = Book.readFromFile("input.txt");
            List<Book> students = Book.readFromFile("input.txt");

            Book.saveAllToJson(students, "students.json");
            List<Book> fromJson = Book.loadAllFromJson("students.json");

            System.out.println("Выберите тип студентов для вывода:");
            System.out.println("0 - Все студенты");
            System.out.println("1 - Отличники");
            System.out.println("2 - Двоечники");
            System.out.print("Ваш выбор: ");

            int choice = scanner.nextInt();


            if (choice == 1 || choice == 2 || choice == 0) {
                Book.writeStudentsToFile(fromJson, "output.txt", choice);
                System.out.println("Данные успешно записаны в файл output.txt");
            } else {
                System.out.println("Неверный выбор, допустимые значения: 0, 1 или 2");
            }
            System.out.println("проверка для гит2");



        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлами: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
