class Node {
    String letter;
    int counter;
    Node right;
    Node left;

    Node(String letter) {
        this.letter = letter;
        this.counter = 1;
        this.right = null;
        this.left = null;
    }

    public String toString() {
        return this.letter + "." + this.counter;
    }
}