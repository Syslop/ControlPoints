import java.util.Scanner;

public class Main {
    private static Node root = null;

    public static void main(String[] args) {
        LLRBTREE node = new LLRBTREE();
        Scanner scan = new Scanner(System.in);

        char ch;
        do {
            System.out.println("������� ����� �����");

            int num = scan.nextInt();
            root = node.insert(root, num);

            node.inorder(root);
            System.out.println("\n�� ������ ����������? (������� y ��� n)");
            ch = scan.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }
}

