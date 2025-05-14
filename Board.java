/**
 * @author (Dhvani & Brianna)
 * 
 * This is the board class that sets up the structure of Tic Tac Toe.
 */
public class Board {
    private char[][] board;
    public static final int SIZE = 3;
    
    /**
     * public Board
     * 
     * This is the constructor for this class.
     */
    public Board() {
        board = new char[SIZE][SIZE];
        reset();
    }

    /**
     * public void reset
     * 
     * This resets the entire board.
     */
    public void reset() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    /**
     * public char checkTemporaryMove
     * 
     * This will detect the moves of the player. 
     * 
     * @param int row, int col, char player
     * 
     * @return result
     */
    public char checkTemporaryMove(int row, int col, char player) {
        if (!isCellEmpty(row, col)) return ' ';
        char original = board[row][col];
        board[row][col] = player;
        char result = checkWinner();
        board[row][col] = original; // Always restore original value
        return result;
    }
    
    /**
     * public boolean isCellEmpty
     * 
     * This will check to see if the board is empty.
     * 
     * @param int row, int col
     * 
     * @return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == ' '
     */
    public boolean isCellEmpty(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == ' ';
    }

    /**
     * public boolean makeMove
     * 
     * This is where the player can control their moves in their current turn.
     * 
     * @param int row, int col, char player
     * 
     * @return true, false
     */
    public boolean makeMove(int row, int col, char player) {
        if (isCellEmpty(row, col)) {
            board[row][col] = player;
            return true;
        }
        return false;
    }

    /**
     * public char checkWinner
     * 
     * This will check the board to see the patterns that match, which will determine the winner if it succeeds.
     * 
     * @return board[i][0], board[0][i], board[0][0], board[0][2], ' '
     */
    public char checkWinner() {
        // Check rows
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0];
            }
        }

        // Check columns
        for (int i = 0; i < SIZE; i++) {
            if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i];
            }
        }

        // Check diagonals
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];
        }
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2];
        }

        return ' '; // No winner
    }

    /**
     * public char getCell
     * 
     * This will retrieve the amount of rows and columns.
     * 
     * @param int row, int col
     * 
     * @return board[row][col], '\0'
     */
    public char getCell(int row, int col) {
        if (row >= 0 && row < SIZE && col >= 0 && col < SIZE) {
            return board[row][col];
        }
        return '\0';
    }
    
    /**
     * public boolean isFull
     * 
     * This method checks to see if the board is completely full, which would end the game if it's a tie.
     * 
     * @return false, true
     */
    public boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}