package omok.gui_console;

import omok.model.Player;

public class ConsoleUI {

    public ConsoleUI(){
    }

    public void welcome(){
        System.out.println("Welcome to Omok! a 5 in a row game.");
    }
    public void askForGame(){
        System.out.println("Would you like to play? Y - Yes, N - No:");
    }
    public void promptUser(){
        System.out.println("Press 1 for Human vs. Human Game. Press 2 for Man vs. Machine:");
    }
    public void pickSpace(Player player, Player[][] board){
        System.out.println(player.name() + " turn. Pick a spot on the board \nFrom 0 - "
                + board.length + ".\n " +
                "Your 1st input will be for the row, and the 2nd for the column.");
    }
    public void declareWinner(Player winner){
        System.out.println("The winner is " + winner.name() + "!!!");
    }
    public void declareTie(){
        System.out.println("A draw has occurred! No winners this game");
    }
    public void newMatch(){
        System.out.println("Would you like to play again?");
    }
    public void leave(){
        System.out.println("Bye Bye! Hope to see you again soon!");
    }

    public void displayBoard(Player[][] currBoard){
        System.out.print("  ");
        //this will print out the top row guide of numbers
        for (int i = 0; i < currBoard.length; i++) {
            System.out.printf("%2d ", i);
        }
        System.out.println();
        //this prints out the side guide
        for (int i= 0; i < currBoard.length; i++) {
            System.out.printf("%2d ", i);
            //System.out.print('|');

            for (int j = 0; j < currBoard.length; j++) {
                if(currBoard[i][j] != null){
                    System.out.print(currBoard[i][j].stone() + " ");
                }else{
                    System.out.print('.' + "  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
