import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tree {
    Node root;
    private static int NodeLength = 4;

    public void addMultiple(String word) {
        for (char c : word.toCharArray()) {
            if (c != ' ') {
                this.add("" + c);
            }
        }
    }

    public void add(String letter) {
        root = add(root, letter);
    }

    public int maxLevel() {
        return this.maxLevel(root);
    }

    public Diff maxDiff() {
        return this.maxDiff(root);
    }

    public void printTree() {
        int maxLevel = maxLevel();
        this.printTree(Collections.singletonList(this.root), 1, maxLevel);
    }

    private Diff maxDiff(Node current) {
        Diff maxDiff = new Diff(current, current);

        if (current.left != null) {
            Diff leftDiff = maxDiff(current.left);
            maxDiff = leftDiff.getGreaterDiff(current);
        }

        if (current.right != null) {
            Diff rigthDiff = maxDiff(current.right);
            maxDiff = rigthDiff.getGreaterDiff(maxDiff);
        }

        return maxDiff;
    }

    private Node add(Node current, String letter) {
        if (current == null) {
            return new Node(letter);
        }

    
        if (letter.compareTo(current.letter) < 0) {
            current.left = add(current.left, letter);
        } else if (letter.compareTo(current.letter) > 0) {
            current.right = add(current.right, letter);
        } else {
            current.counter++;
        }
    
        return current;
    }

    private int maxLevel(Node node) {
        if (node == null) {
            return 0;
        }

        return Math.max(this.maxLevel(node.left), this.maxLevel(node.right)) + 1;
    }

    private void printTree(List<Node> nodes, int currentLevel, int maxLevel) {
        if (currentLevel > maxLevel) {
            return;
        }

        List<Node> newNodes = new ArrayList<>();
        boolean fistNode = true;
        for (Node node : nodes) {
            this.printSpaces(fistNode, currentLevel, maxLevel);
            this.printNode(node);
            fistNode = false;
            
            if (node != null) {
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
            }
        }

        System.out.println("");
        this.printLines(nodes, currentLevel, maxLevel);
        this.printTree(newNodes, currentLevel + 1, maxLevel);
    }

    private void printLines(List<Node> nodes, int currentLevel, int maxLevel) {
        int startSpaces = this.getNumberOfSpaces(true, currentLevel, maxLevel);
        int endSpaces = this.getNumberOfSpaces(true, currentLevel + 1, maxLevel);
        for (int i = 0; i < startSpaces - endSpaces; i++) {
            boolean fistNode = true;
            for (Node node : nodes) {
                int spaces = this.getNumberOfSpaces(fistNode, currentLevel, maxLevel);
                if (!fistNode) {
                    spaces += 2;
                }

                if (fistNode) {
                    this.printSpaces(spaces - i);
                } else {
                    this.printSpaces(spaces - (i * 2));
                }

                if (node != null) {
                    if (node.left != null) {
                        System.out.print("/");
                    } else {
                        this.printSpaces(1);
                    }
                } else {
                    this.printSpaces(1);
                }
                
                this.printSpaces(i * 2);

                if (node != null) {
                    if (node.right != null) {
                        System.out.print("\\");
                    } else {
                        this.printSpaces(1);
                    }
                } else {
                    this.printSpaces(1);
                }

                fistNode = false;
            }
    
            System.out.println("");
        }
        
    }

    private void printSpaces(boolean isFirstNode, int currentLevel, int maxLevel) {
        int maxNodes = (int)Math.pow(2, (maxLevel - 1));

        if (isFirstNode) {
            int currentMaxNodes = (int)Math.pow(2, (currentLevel - 1));
            int spaces = (maxNodes / currentMaxNodes - 1) * Tree.NodeLength;
            this.printSpaces(spaces);
            return;
        }

        int previousMaxNodes = (int)Math.pow(2, currentLevel - 2);

        int spaces = (maxNodes / previousMaxNodes - 1) * Tree.NodeLength;
        this.printSpaces(spaces);
    }

    private int getNumberOfSpaces(boolean isFirstNode, int currentLevel, int maxLevel) {
        int maxNodes = (int)Math.pow(2, (maxLevel - 1));

        if (isFirstNode) {
            int currentMaxNodes = (int)Math.pow(2, (currentLevel - 1));
            int spaces = (maxNodes / currentMaxNodes - 1) * Tree.NodeLength;
            return spaces;
        }

        int previousMaxNodes = (int)Math.pow(2, currentLevel - 2);

        int spaces = (maxNodes / previousMaxNodes - 1) * Tree.NodeLength;
        return spaces;
    }

    private void printNode (Node node) {
        if (node == null) {
            this.printSpaces(Tree.NodeLength);
            return;
        }

        String stringToPrint = node.letter + "." + node.counter;

        System.out.print(stringToPrint);

        int currentNodeLength = stringToPrint.length();
        if (currentNodeLength < Tree.NodeLength) {
            this.printSpaces(Tree.NodeLength - currentNodeLength);
        }
    }

    private void printSpaces(int numberOfSpaces) {
        for (int i = 0; i < numberOfSpaces; i++) {
            System.out.print(" ");
        } 
    }
}