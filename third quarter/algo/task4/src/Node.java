class Node {

    Node left, right;
    int data;

    // ������� ==> true, ������ ==> false
    boolean color;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;

// ����� ����, ������� ���������, �������� ������ �������� �����.
        color = true;
    }
}
