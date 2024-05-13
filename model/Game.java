package omok.model;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players = new ArrayList<>();
    private Player player1;
    private Player player2;
    private Board board = new Board(15);


    public Game(int mode){

        if(mode == 1){
            HumanVHuman();
        }

        if(mode == 2){
            ManVsMachine();
        }

    }

    private void HumanVHuman(){
        this.player1 = new Player("Player 1", 'X');
        this.player2 = new Player("Player 2", 'O');
        this.players.add(player1);
        this.players.add(player2);
    }

    private void ManVsMachine(){
        this.player1 = new Player("Human", 'X');
        this.player2 = new Player("Computer", 'O');
        this.players.add(player1);
        this.players.add(player2);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2(){
        return player2;
    }

    public Board getBoard() {
        return board;
    }
}
