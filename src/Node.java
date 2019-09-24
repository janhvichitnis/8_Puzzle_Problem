public class Node
{
    States state;
    Node Parent=null;
    int Cost;
    int Depth=0;

    public Node(States state, int Depth) {
        this.state = state;
        this.Depth = Depth;
    }

    public void setParent(Node parent) {
        Parent = parent;
    }

    public States getState() {
        return state;
    }

    public Node getParent() {
        return Parent;
    }

    public int getDepth() {
        return Depth;
    }

    public Node(States state, Node parent, int cost, int depth) {
        this.state = state;
        Parent = parent;
        Cost = cost;
        Depth = depth;
    }
};