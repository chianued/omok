package omok.testing;

import omok.model.Board;
import omok.model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void sizeTest() {
        Board game = new Board(15);
        assertEquals(15, game.size());
    }

    @Test
    void clearTest() {
        Board game = new Board(15);
        Board cleared = new Board(15);

        Player x = new Player("X", 'x');
        game.placeStone(5,5,x);
        game.clear();

        Player [][] main = game.getOmokBoard();
        Player [][] clear = game.getOmokBoard();

        assertArrayEquals(clear,main);
    }

    @Test
    void isFullTest() {
        Board game = new Board(15);
        Player x = new Player("X", 'x');
    }

    @Test
    void placeStoneTest() {
        Board game = new Board(15);
        Player x = new Player("X",'x');
        game.placeStone(0,3,x);

        Player check = game.getOmokBoard()[0][3];

        assertEquals(x.stone(),check.stone());
        assertEquals(check,x);
    }

    @Test
    void isEmptyTest() {
        Board game = new Board(15);
        Player x = new Player("X",'x');
        game.placeStone(0,4,x);

        assertEquals(false,game.isEmpty(0,4));

        game.clear();
        assertEquals(true,game.isEmpty(0,4));

    }

    @Test
    void isOccupiedTest() {
        Board game = new Board(15);
        Player x = new Player("X",'x');
        game.placeStone(0,4,x);

        //check if it is occupied
        assertEquals(false,game.isOccupied(0,4));

        game.clear();
        //check if not after clearing
        assertEquals(true,game.isOccupied(0,4));

    }

    @Test
    void getOmokBoardTest() {
        Board game = new Board(15);
        Player [][] x = game.getOmokBoard();

        assertEquals(x,game.getOmokBoard());
    }

    @Test
    void isOccupiedByTest() {
        Board game = new Board(15);
        Player x = new Player("Xand", 'x');
        game.placeStone(0,9,x);

        assertEquals(true, game.isOccupiedBy(0,9,x));
        assertEquals(false, game.isOccupiedBy(0,2,x));
    }

    @Test
    void playerAt() {
        Board game = new Board(15);
        Player x = new Player("Xand", 'x');
        game.placeStone(0,9,x);

        assertEquals(true, game.isOccupiedBy(0,9,x));
        assertEquals(false, game.isOccupiedBy(0,2,x));
    }

    @Test
    void isWonBy() {
        Board game = new Board(15);
        Player x = new Player("Xand", 'x');
        game.placeStone(0,4,x);
        game.placeStone(0,5,x);
        game.placeStone(0,6,x);
        game.placeStone(0,7,x);
        game.placeStone(0,8,x);

        assertEquals(true,game.isWonBy(x));

    }

    @Test
    void winningRow() {
    }
}