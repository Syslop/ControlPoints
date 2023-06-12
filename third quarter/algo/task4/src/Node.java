class Node {

    Node left, right;
    int data;

    // красный ==> true, черный ==> false
    boolean color;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;

// Ќовый узел, который создаетс€, €вл€етс€ всегда красного цвета.
        color = true;
    }
}
