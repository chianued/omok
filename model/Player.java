package omok.model;

public class Player {
    /** Name of this player. */
    private final String name;
    private final char stone;

    /** Create a new player with the given name. */
    public Player(String name, char stone) {
        this.name = name;
        this.stone = stone;
    }

    /** Return the name of this player. */
    public String name() {
        return name;
    }
    /** return the stone of this player */
    public char stone(){
        return stone;
    }


}
