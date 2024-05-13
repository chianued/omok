package omok.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Board {
    /** Create a new board of the default size. */
    private int size;
    protected Player[][] omokBoard;
    public Player winner = new Player("Win" ,'W');
    private Random rand = new Random();
    private ArrayList<Place> spots = new ArrayList<>();

    //check the array to see if the player is on grid


    /** Create a new board of the specified size. */
    public Board(int size) {
        this.size = size;
        this.omokBoard = new Player[size][size];
    }

    /** Return the size of this board. */
    public int size() {
        return this.size;
    }


    /** Removes all the stones placed on the board, effectively
     * resetting the board to its original state.
     */
    public void clear() {
        // null are empty spaces
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                this.omokBoard[i][j] = null;
            }
        }
    }

    /** Return a boolean value indicating whether all the places
     * on the board are occupied or not.
     */
    public boolean isFull() {
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if(this.omokBoard[i][j] == null){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Place a stone for the specified player at a specified
     * intersection (x, y) on the board.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     * @param player omok.model.Player whose stone is to be placed
     */
    public void placeStone(int x, int y, Player player) {
        if(!isFull()){
            if(isEmpty(x,y)){
                this.omokBoard[x][y] = player;
                spots.add(new Place(x,y));
            }
        }
    }

    public void computerStone(Player com){
        int x, y;
        Place temp;
        do {
            x = rand.nextInt(size);
            y = rand.nextInt(size);
            temp = new Place(x, y);
        } while (spots.contains(temp));
        placeStone(x, y, com);

    }



    /**
     * Return a boolean value indicating whether the specified
     * intersection (x, y) on the board is empty or not.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     * @return boolean
     */
    public boolean isEmpty(int x, int y) {
        if(this.omokBoard[x][y] != null){
            return false;
        }
        return true;
    }

    /**
     * Is the specified place on the board occupied?
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public boolean isOccupied(int x, int y) {
        return isEmpty(x,y);
    }

    /**
     * @return returns the board.
     * */
    public Player[][] getOmokBoard(){
        return this.omokBoard;
    }

    /**
     * Return a boolean value indicating whether the specified
     * intersection (x, y) on the board is occupied by the given
     * player or not.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public boolean isOccupiedBy(int x, int y, Player player) {
        if(this.omokBoard[x][y] == player){
            System.out.println("Occupied by: " + player.name());
            return true;
        }
        return false;

    }

    public Random getRand() {
        return rand;
    }

    /**
     * Return the player who occupies the specified intersection (x, y)
     * on the board. If the place is empty, this method returns null.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public Player playerAt(int x, int y) {
        if(isOccupied(x,y)){
            return this.omokBoard[x][y];
        }
        return null;
    }

    /**
     * Return a boolean value indicating whether the given player
     * has a winning row on the board. A winning row is a consecutive
     * sequence of five or more stones placed by the same player in
     * a horizontal, vertical, or diagonal direction.
     */
    public boolean isWonBy(Player player) {
        int n = this.size;
        Player [][]board = this.omokBoard;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (player == null) {
                    continue; // skip empty cells
                }
                // horizontal line check
                if (j <= n - 5 && player == board[i][j] && player == board[i][j+1] && player == board[i][j+2] && player == board[i][j+3] && player == board[i][j+4]) {
                    for(int x = 0; x < 5; x++){
                        board[i][x+j] = winner;
                    }
                    return true;
                }
                // vertical line check
                if (i <= n - 5 && player == board[i][j] && player == board[i+1][j] && player == board[i+2][j] && player == board[i+3][j] && player == board[i+4][j]) {
                    for(int x = 0; x < 5; x++){
                        board[x+i][j] = winner;
                    }
                    return true;
                }
                // top-left to bottom-right check
                if (i <= n - 5 && j <= n - 5 && player == board[i][j] && player == board[i+1][j+1] && player == board[i+2][j+2] && player == board[i+3][j+3] && player == board[i+4][j+4]) {
                    for(int x = 0; x < 5; x++){
                        board[x+i][x+j] = winner;
                    }
                    return true;
                }
                // bottom-left to top-right check
                if (i >= 4 && j <= n - 5 && player == board[i][j] && player == board[i-1][j+1] && player == board[i-2][j+2] && player == board[i-3][j+3] && player == board[i-4][j+4]) {
                    for(int x = 0; x < 5; x++){
                        board[x-i][x+j] = winner;
                    }
                    return true;
                }
            }
        }
        return false;

    }

    /** Return the winning row. For those who are not familiar with
     * the Iterable interface, you may return an object of
     * List<Place>. */
//    public Iterable<Place> winningRow() {
//        List placeList = new List();
//        return (Iterable<Place>) placeList;
//    }

    /**
     * An intersection on an Omok board identified by its 0-based column
     * index (x) and row index (y). The indices determine the position
     * of a place on the board, with (0, 0) denoting the top-left
     * corner and (n-1, n-1) denoting the bottom-right corner,
     * where n is the size of the board.
     */
    public static class Place {
        /** 0-based column index of this place. */
        public final int x;

        /** 0-based row index of this place. */
        public final int y;

        /** Create a new place of the given indices.
         *
         * @param x 0-based column (vertical) index
         * @param y 0-based row (horizontal) index
         */
        public Place(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        // other methods if needed ...
    }
}
