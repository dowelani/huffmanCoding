public class Node implements Comparable {
    private Node left;
    private Node right;
    private int f;

    public Node(Node left, Node right,int f) {
        this.left = left;
        this.right = right;
        this.f=f;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public int getF() {
        return f;
    }


    @Override
    public int compareTo(Object o) {
        Node n=(Node)o;
        return Integer.compare(f,n.getF());
    }
}
