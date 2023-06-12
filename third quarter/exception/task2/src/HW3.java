import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HW3 {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("¬ведите любой текст кроме пустой строки");
        try {
            String result = reader.readLine();
            if(result.equals("")) throw new RuntimeException("ѕустую строку вводить нельз€");

            System.out.println(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
