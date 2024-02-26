public class Diff {
    Node node1;
    Node node2;
    int diff;

    public Diff(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;
        this.diff = Math.abs(node1.counter - node2.counter);
    }

    public Diff getGreaterDiff(Node node) {
        int maxDiff = this.diff;
        Diff diffToReturn = this;
        Diff diff1 = new Diff(this.node1, node);
        Diff diff2 = new Diff(this.node2, node);

        if (diff1.diff > maxDiff) {
            maxDiff = diff1.diff;
            diffToReturn = diff1;
        }

        if (diff2.diff > maxDiff) {
            maxDiff = diff2.diff;
            diffToReturn = diff2;
        }

        return diffToReturn;
    }

    public Diff getGreaterDiff(Diff currentDiff) {
        int maxDiff = this.diff > currentDiff.diff ? this.diff : currentDiff.diff;
        Diff diffToReturn = this.diff > currentDiff.diff ? this : currentDiff;
        Diff diff1 = new Diff(this.node1, currentDiff.node1);
        Diff diff2 = new Diff(this.node1, currentDiff.node1);
        Diff diff3 = new Diff(this.node2, currentDiff.node2);
        Diff diff4 = new Diff(this.node2, currentDiff.node2);

        if (diff1.diff > maxDiff) {
            maxDiff = diff1.diff;
            diffToReturn = diff1;
        }

        if (diff2.diff > maxDiff) {
            maxDiff = diff2.diff;
            diffToReturn = diff2;
        }

        if (diff3.diff > maxDiff) {
            maxDiff = diff3.diff;
            diffToReturn = diff3;
        }

        if (diff4.diff > maxDiff) {
            maxDiff = diff4.diff;
            diffToReturn = diff4;
        }

        return diffToReturn;
    }
}
