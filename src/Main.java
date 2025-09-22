import java.io.*;
import java.util.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try{
            System.out.println("Введите исходную строку");
            String allLine = br.readLine();

            System.out.println("Введите вставляемое слово");
            String line = br.readLine();
            //System.out.println(line);

            System.out.println("Введите количество букв");
            int num = Integer.parseInt(br.readLine());



            StringBuilder sb = new StringBuilder();
            StringTokenizer st = new StringTokenizer(allLine, " \t\n\r");
            while(st.hasMoreTokens()){
                String str = st.nextToken();
                if(str.length() == num)
                    sb.append(line);

                else {
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