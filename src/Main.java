import java.io.*;
import java.util.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try{
            String line = br.readLine();
            System.out.println(line);


            StringBuilder sb = new StringBuilder();
            StringTokenizer st = new StringTokenizer("Строка, которую мы хотим разобрать на слова", " \t\n\r");
            while(st.hasMoreTokens()){
                String str = st.nextToken();
                if(str.length() == 2)
                    sb.append(line);
                else {
                    sb.append(str);
                }
                sb.append(" ");
            }
            System.out.println(sb.toString());

        }
        catch (IOException e){
            System.out.println("Ошибка чтения с клавиатуры");
        }

    }
}