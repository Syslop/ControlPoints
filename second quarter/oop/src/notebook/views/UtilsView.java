package notebook.views;

import java.util.Scanner;

public class UtilsView {
    public static String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}