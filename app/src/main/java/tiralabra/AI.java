package tiralabra;

public class AI {

    public int minimax(Node node, int depth, boolean maximizingPlayer) {
        if (depth == 0 /* or node is terminal */) {
            return node.value;
        }
        if (maximizingPlayer) {
            int value = -999999999;
            for ( Node child : node.getChildren()) {
                value = Math.max(value, minimax(child, depth-1, false));
            }
            return value;
        } else { // minimizing player
            int value = 999999999;
            for ( Node child : node.getChildren()) {
                value = Math.min(value, minimax(child, depth-1, true));
            }
            return value;
        }
    }

}
