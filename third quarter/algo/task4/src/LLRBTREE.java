public class LLRBTREE {


    // ������� ��� �������� ���� ������ ������� �������.
    Node rotateLeft(Node myNode) {
        System.out.printf("������� �����!!\n");
        Node child = myNode.right;
        Node childLeft = child.left;

        child.left = myNode;
        myNode.right = childLeft;

        return child;
    }

    // ������� ��� �������� ���� �� ������� �������.
    Node rotateRight(Node myNode) {
        System.out.printf("�������� ������\n");
        Node child = myNode.left;
        Node childRight = child.right;

        child.right = myNode;
        myNode.left = childRight;

        return child;
    }

    // ������� ��� �������� ����, �������� �� ���� �������� ����� ��� ���.
    boolean isRed(Node myNode) {
        if (myNode == null) {
            return false;
        }
        return (myNode.color == true);
    }

    // ������� ��� ��������� ����� ���� ����.
    void swapColors(Node node1, Node node2) {
        boolean temp = node1.color;
        node1.color = node2.color;
        node2.color = temp;
    }

    // ������� � ������������� ������-������ ������.
    Node insert(Node myNode, int data) {
// ������� ��� ������� ��� ������ ��������� �����
        if (myNode == null) {
            return new Node(data);
        }

        if (data < myNode.data) {
            myNode.left = insert(myNode.left, data);
        } else if (data > myNode.data) {
            myNode.right = insert(myNode.right, data);
        } else {
            return myNode;
        }

// ������ 1.
        // ����� ������ �������� ������� �������, � ����� �������� ������� ������ ��� �� ����������.
        if (isRed(myNode.right) && !isRed(myNode.left)) {
// ��������� ����  �����
            myNode = rotateLeft(myNode);

// �������� ������� ����� ��������� ���� ������ ������ ���� �������
            swapColors(myNode, myNode.left);
        }

// ������ 2
        // ����� ����� �������, � ����� ����� ���� �������� ������� ������
        if (isRed(myNode.left) && isRed(myNode.left.left)) {
// ��������� ���� � �����
            myNode = rotateRight(myNode);
            swapColors(myNode, myNode.right);
        }

// ������ 3
        // ����� � �����, � ������ �������� �������� �������� � ������� ����.
        if (isRed(myNode.left) && isRed(myNode.right)) {
// ������������� ���� ���� ��� ����� � ������ ����.
            myNode.color = !myNode.color;

// �������� ���� �� ������.
            myNode.left.color = false;
            myNode.right.color = false;
        }

        return myNode;
    }

    // ����� �� �������
    void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            char c = '?';
            if (node.color == false)
                c = '?';
            System.out.print(node.data + "" + c + " ");
            inorder(node.right);
        }
    }

}
