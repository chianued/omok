package omok.gui;

import omok.model.Board;
import omok.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardPanel extends JPanel {
    private final Player player1 = new Player("Player 1", 'X');
    private final Player player2 = new Player("Player 2", 'O');
    private final Player end = new Player("end", 'C');
    private JPanel grid;
    private boolean turn = true;
    int gameChoice;
    private int[] currStone = new int[2];



    public BoardPanel(Board board, int gameChoice) {
        this.gameChoice = gameChoice;

        this.grid = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                setBackground(new Color(255,217,63));
                super.paintComponent(g);
                int w = getWidth();
                int h = getHeight();
                int gridSize = Math.min(w, h) / 15;
                for (int i = 0; i <= 15; i++) {
                    int x = i * gridSize;
                    //prints the gridlines
                    g.drawLine(x, 0, x, h);
                    g.drawLine(0, x, w, x);
                }


                //for each element in the board, check if there are player spots
                //after placing a stone, the boardpanel will repaint and update it accordingly
                for (int i = 0; i < board.getOmokBoard().length; i++) {
                    for (int j = 0; j < board.getOmokBoard().length; j++) {
                        if(board.getOmokBoard()[i][j] != null){
                            if (board.getOmokBoard()[i][j].stone() == 'X') {
                                g.setColor(Color.BLACK);
                                g.fillOval((i * gridSize + gridSize / 2)+10, (j * gridSize + gridSize / 2)+10,
                                        gridSize / 2, gridSize / 2);
                            } else if (board.getOmokBoard()[i][j].stone() == 'O') {
                                g.setColor(Color.WHITE);
                                g.fillOval((i * gridSize + gridSize / 2)+10, (j * gridSize + gridSize / 2)+10,
                                        gridSize / 2, gridSize / 2);
                            }else if (board.getOmokBoard()[i][j].stone() == 'W') {
                                g.setColor(Color.GREEN);
                                g.fillOval((i * gridSize + gridSize / 2)+10, (j * gridSize + gridSize / 2)+10,
                                        gridSize / 2, gridSize / 2);
                            }
                        }
                    }
                }
            }
        };

        boolean canChange = true;


        grid.addMouseListener(new MouseAdapter() {


            @Override
            public void mouseClicked(MouseEvent e) {
                int w = grid.getWidth();
                int h = grid.getHeight();
                int gridSize = Math.min(w, h) / 15;


                int xPos = e.getX() / gridSize;
                int yPos = e.getY() / gridSize;

                    if(gameChoice == 1){
                        if (turn) {
                            if (board.isEmpty(xPos, yPos)) {
                                currStone[0] = xPos;
                                currStone[1] = yPos;
                                board.placeStone(xPos, yPos, player1);
                                grid.repaint();
                                turn = false;
                            }
                        } else {
                            if (board.isEmpty(xPos, yPos)) {
                                currStone[0] = xPos;
                                currStone[1] = yPos;
                                board.placeStone(xPos, yPos, player2);
                                grid.repaint();
                                turn = true;
                            }
                        }
                    }

                    if(gameChoice == 2){

                        if (board.isEmpty(xPos, yPos)) {
                            board.placeStone(xPos, yPos, player1);
                            board.computerStone(player2);
                            grid.repaint();
                            turn = false;
                        }

                    }
            }

        });
        this.grid.setPreferredSize(new Dimension(500, 500));
    }

    public int[] getCurrStone() {
        return currStone;
    }

    public JPanel getPanel(){
        return grid;
    }

}