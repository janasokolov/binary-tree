public class Main {
    public static void main(String[] args) {
        Tree t = new Tree();

        t.addMultiple("ROYIPRIIPAAAAAAAKJLLALRYAAIK");
        // t.addMultiple("BANDATA NA ANANASA I BANANA");

        t.printTree();

        Diff maxDiff = t.maxDiff();

        System.out.println("Max diff is between: " + maxDiff.node1.toString() + " - " + maxDiff.node2.toString() + " | with diff: " + maxDiff.diff);
    }
}
