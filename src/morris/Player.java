package morris;

public class Player {
    //Attributes
    private String playerName;
    private int movesTaken;
    private int playerPosition;

    //Constructor
    public Player(String playerName) {
        this.playerName = playerName;
    }

    //Getter and Setter


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getMovesTaken() {
        return movesTaken;
    }

    public void setMovesTaken(int movesTaken) {
        this.movesTaken = movesTaken;
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(int playerPosition) {
        this.playerPosition = playerPosition;
    }

    //toString

    @Override
    public String toString() {
        return String.format("[%s]", playerName);
    }
}
