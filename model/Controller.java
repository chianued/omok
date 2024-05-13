package omok.model;

import omok.gui_console.ConsoleUI;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Controller {
    private Game omok;
    private ConsoleUI ui = new ConsoleUI();
    private Scanner in = new Scanner(System.in);
    private Random rand = new Random();
    private int choice = 0;

    public Controller(){
    }


    public void run(){
        boolean enter = true;

        ui.welcome();
        ui.askForGame();

        char userOp = in.next().charAt(0);
        while(enter){
            if(userOp == 'Y' || userOp == 'y'){
                selectGame();
                break;
            }
            if(userOp == 'N' || userOp == 'n'){
                this.ui.leave();
                break;
            }
            this.ui.askForGame();
            userOp = in.next().charAt(0);
        }

    }

    private void selectGame(){
        ui.promptUser();
        while(true){
            this.choice = in.nextInt();
            if (choice == 1 || choice ==2 ){
                break;
            }
            ui.promptUser();
        }
        if(choice == 1){
            HumanGame();
        }else if(choice == 2){
            ComGame();
        }
    }

    private void newGame(){

    }

    private void HumanGame(){
        boolean inGame = true;
        int turn;
        this.omok = new Game(choice);
        ArrayList<Player> currPlayers = this.omok.getPlayers();

        while(inGame){
            if(omok.getBoard().isFull()){
                ui.declareTie();
                inGame = false;
                break;
            }
            ui.displayBoard(omok.getBoard().getOmokBoard());
            for(int i =0; i < 2; i++){
                turn = turn(currPlayers.get(i));
                if (turn == -1){
                    inGame = false;
                    ui.leave();
                    break;
                }
                if(omok.getBoard().isWonBy(currPlayers.get(i))){
                    inGame = false;
                    ui.displayBoard(omok.getBoard().getOmokBoard());
                    ui.declareWinner(currPlayers.get(i));
                    break;
                }
            }
        }

        System.out.println("escaped the loop");
    }

    private void ComGame(){
        boolean inGame = true;
        boolean comTurn = false;
        int turnP;
        int turnC;
        this.omok = new Game(choice);
        ArrayList<Player> currPlayers = this.omok.getPlayers();
        Player human = currPlayers.get(0);
        Player computer = currPlayers.get(1);

        while(inGame){
            if(omok.getBoard().isFull()){
                ui.declareTie();
                inGame = false;
                break;
            }
            ui.displayBoard(omok.getBoard().getOmokBoard());
            while(!comTurn){
                turnP = turn(human);
                if (turnP == -1){
                    inGame = false;
                    ui.leave();
                    break;
                }
                if(omok.getBoard().isWonBy(human)){
                    inGame = false;
                    ui.displayBoard(omok.getBoard().getOmokBoard());
                    ui.declareWinner(human);
                    break;
                }
                comTurn = true;
            }
            if(comTurn){
                omok.getBoard().computerStone(computer);
                comTurn = false;
            }

        }
    }




    private int turn(Player player){
        ui.pickSpace(player,omok.getBoard().omokBoard);
        boolean turnEnd = true;
        int [] newSpot = new int[2];
        while(turnEnd){
            for (int i = 0; i < 2; i++){
                newSpot[i] = in.nextInt();
            }
            if(newSpot[0] == -1 || newSpot[1] == -1){
                return -1;
            }
            if (omok.getBoard().isEmpty(newSpot[0],newSpot[1]) == true){
                omok.getBoard().placeStone(newSpot[0],newSpot[1],player);
                return 1;
            }

        }
        return 0;
    }
}
