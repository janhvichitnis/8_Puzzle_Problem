import java.util.ArrayList;
import java.util.List;

public class FrontierList {
    ArrayList<Node> Frontier_List = new ArrayList<Node>();


    public List<Node> getFrontier_List() {
        return Frontier_List;
    }

    public void Add_inQueue(Node node) {
        Frontier_List.add(node);
    }
    public Node get_and_Remove_from_Queue() {
        int best_index = 0;
        int best_priority = Frontier_List.get(0).Cost;
        for (int i = 1; i < Frontier_List.size(); i++) {
            if (best_priority > Frontier_List.get(i).Cost) {
                best_priority = Frontier_List.get(i).Cost;
                best_index = i;
            }
        }
        Node n = Frontier_List.get(best_index);
        Frontier_List.remove(best_index);
        return n;
    }
    public int size() {
        return getFrontier_List().size();
    }
}
