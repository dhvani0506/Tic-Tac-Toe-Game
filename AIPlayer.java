public class AIPlayer {
    private final char aiMark;
    private final char playerMark;

    public AIPlayer(char mark) {
        this.aiMark = mark;
        this.playerMark = (mark == 'X') ? 'O' : 'X';
    }

    public void makeMove(Board board) {
        int[] bestMove = findBestMove(board);
        if (bestMove != null) {
            board.makeMove(bestMove[0], bestMove[1], aiMark);
        }
    }

    private int[] findBestMove(Board board) {
        // 1. Check for winning move
        int[] winningMove = findWinningMove(board, aiMark);
        if (winningMove != null) return winningMove;

        // 2. Block player's winning move
        int[] blockingMove = findWinningMove(board, playerMark);
        if (blockingMove != null) return blockingMove;

        // 3. Take center if available
        if (board.isCellEmpty(1, 1)) return new int[]{1, 1};

        // 4. Take a corner
        int[][] corners = {{0,0}, {0,2}, {2,0}, {2,2}};
        for (int[] corner : corners) {
            if (board.isCellEmpty(corner[0], corner[1])) {
                return corner;
            }
        }

        // 5. Take any available edge
        int[][] edges = {{0,1}, {1,0}, {1,2}, {2,1}};
        for (int[] edge : edges) {
            if (board.isCellEmpty(edge[0], edge[1])) {
                return edge;
            }
        }

        return null;
    }

    private int[] findWinningMove(Board board, char mark) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.isCellEmpty(row, col)) {
                    if (board.checkTemporaryMove(row, col, mark) == mark) {
                        return new int[]{row, col};
                    }
                }
            }
        }
        return null;
    }
}