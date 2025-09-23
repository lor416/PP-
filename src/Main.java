import java.io.*;
import java.util.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try{

            System.out.println("Введите исходный текст (для завершения введите пустую строку):");
            StringBuilder inputText = new StringBuilder();
            String lineInput;
            while (!(lineInput = br.readLine()).isEmpty()) {
                inputText.append(lineInput).append("\n");
            }

            String allLine = inputText.toString().trim();

            /*
            System.out.println("Введите исходную строку");
            //String allLine = br.readLine();
            String allLine = "Строка, да, которую мы меняем: и! один два; три) ()  ";
            System.out.println(allLine);*/

            System.out.println("Введите вставляемое слово");
            String line = br.readLine();

            System.out.println("Введите количество букв");
            int num = Integer.parseInt(br.readLine());

            StringBuffer sb = new StringBuffer();
            StringTokenizer st = new StringTokenizer(allLine, " \t\r");
            while(st.hasMoreTokens()){
                String str = st.nextToken();

                char lastChar = str.charAt(str.length() - 1);
                int length = str.length();
                boolean hasPunctuation = (lastChar == ',' || lastChar == '.' ||
                        lastChar == ':' || lastChar == ';'|| lastChar == '!' ||
                        lastChar == ')' || lastChar == '(');

                if(hasPunctuation)
                    if(length-1 == num)
                        sb.append(line);
                    else
                        sb.append(str);
                else{
                    if(length == num)
                        sb.append(line);
                    else
                        sb.append(str);
                }
                sb.append(' ');
            }
            System.out.println(sb.toString());

        }
        catch (IOException e){
            System.out.println("Ошибка чтения с клавиатуры");
        }
    }
}