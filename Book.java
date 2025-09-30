import java.io.*;
import java.util.*;
public class Book {
    private String name1;
    private String name2;
    private String name3;
    private int curse;
    private int group;
    private List <WriteMark> markList;

    public class WriteMark{
        private int numSession;
        private String subject;
        private int mark;
        //private String type;

        public WriteMark(int numSession, String subject, int mark){
            this.numSession = numSession;
            this.subject = subject;
            this.mark = mark;
            //this.type = type;
        }
        public int getNumSession(){
            return numSession;
        }
        public String getSubject(){
            return subject;
        }
        public int getMark() {
            return mark;
        }
        /*public String getType(){
            return type;
        }*/
        @Override
        public String toString(){
            return name1 + ' ' + name2 + ' ' + name3 + "ну и тд дописать";

        }
        public String toFileString(){
            return name1 + ' ' + name2 + ' ' + name3 + "ну и тд дописать файл";

        }



    }
    public Book(String name1, int curse, int group){
        this.name1 = name1;
        this.curse = curse;
        this.group = group;
        this.markList = new ArrayList<>();
    }
    public void addList(int numSession, String subject, int mark){
        WriteMark list = new WriteMark(numSession, subject, mark);
        markList.add(list);
    }
    public int isGood(){
        if(markList.isEmpty())
            return 0;
        boolean hasExcellent = true;
        for(WriteMark mark : markList){
            if(mark.mark < 4) {
                return 2;  // 2 -- двоечник
            }
            if(mark.mark < 9) {
                hasExcellent = false;
                break;
            }
        }
        if (hasExcellent)
            return 1; // 1 -- отличник (все оценки >= 9)
        else
            return 0; // 0 -- обычный студент
    }
    public double calculateAverageMark() {
        if (markList.isEmpty()) {
            return 0.0;
        }

        int sum = 0;
        for (WriteMark mark : markList) {
            sum += mark.mark;
        }

        return (double) sum / markList.size();
    }
    public void calculateAndWriteSessionAverages(PrintWriter writer) {
        for (int session = 1; session <= 9; session++) {
            int sum = 0;
            int count = 0;
            for (WriteMark mark : markList) {
                if (mark.getNumSession() == session) {
                    sum += mark.getMark();
                    count++;
                }
            }
            if (count > 0) {
                double average = (double) sum / count;
                writer.printf("Средний балл за сессию %d: %.2f\n", session, average);
            }
        }
    }





    public static List<Book> readFromFile(String inputFile) throws IOException {
        List <Book> allBooks = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            Book nowBook = null;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue; // Пропуск пустых строк

                if (line.startsWith("Студент:")) {
                    String[] parts = line.split(",");
                    String[] FIO = parts[0].replace("Студент:", "").trim().split(" ");
                    String name1 = FIO[0];
                    String name2 = FIO[1];
                    String name3 = FIO[2];
                    int curse = Integer.parseInt(parts[1].replace("курс", "").trim());
                    int group = Integer.parseInt(parts[2].replace("группа", "").trim());

                    nowBook = new Book(name1, curse, group);
                    allBooks.add(nowBook); // Добавление в коллекцию

                } else if (nowBook != null && line.contains("сессия")) {
                    String[] parts = line.split(",");
                    int numSession = Integer.parseInt(parts[0].replace("сессия", "").trim());
                    String subject = parts[1].trim();
                    int mark = Integer.parseInt(parts[2].trim());
                    //String типСдачи = части[3].trim();

                    nowBook.addList(numSession, subject, mark);
                }

            }
        } catch (IOException e) {
        System.err.println("Ошибка при чтении файла: " + e.getMessage());}
        return allBooks;
    }

    public static void writeStudentsToFile(List<Book> allBooks, String outputFile, int studentType) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            if (studentType == 1) {
                writer.println("ОТЛИЧНИКИ:\n");
            } else if (studentType == 2) {
                writer.println("ДВОЕЧНИКИ:\n");
            } else if (studentType == 0)
                writer.println("ВСЕ СТУДЕНТЫ:\n");

            for (Book book : allBooks) {
                if (studentType == 0 || book.isGood() == studentType) {
                    writer.println("Студент: " + book.name1 + ", " + book.curse + " курс, группа " + book.group);
                    writer.println("Оценки:");
                    for (WriteMark mark : book.markList) {
                        writer.println("  - Сессия " + mark.getNumSession() + ", " +
                                mark.getSubject() + ", оценка: " + mark.getMark());
                    }
                    writer.println("Средний балл: "+ book.calculateAverageMark());
                    book.calculateAndWriteSessionAverages(writer);

                }
            }
        }
    }
    public String getName1() { return name1; }
    public String getName2() { return name2; }
    public String getName3() { return name3; }
    public int getCourse() { return curse; }
    public int getGroup() { return group; }
    public List<WriteMark> getMarkList() { return new ArrayList<>(markList); }

}
