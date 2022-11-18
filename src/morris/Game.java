package morris;

import java.util.ArrayList;

public class Game {
    private Player player1;
    private Player player2;
    private River map1;
    private String[] customMessages;
    private int playerTurn;

    private int trapCount;
    private int currentCount;
    private int shipyardCount;

    private Dice dice;

    //Constructor
    public Game(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
        this.dice = new Dice();
        this.map1 = new River();
    }

    public Game(Player player1, Player player2, int trapCount, int currentCount, int shipyardCount) {
        this.player1 = player1;
        this.player2 = player2;
        this.trapCount = trapCount;
        this.currentCount = currentCount;
        this.shipyardCount = shipyardCount;
        this.dice = new Dice();
        this.map1 = new River(trapCount, currentCount, shipyardCount);
    }

    public void runGame() {
        while (hasGameEnded()) {
            makeMove(map1.getMapView(), player1);
            makeMove(map1.getMapView(), player2);
        }
    }

    public void makeMove(ArrayList<Entity> mapView, Player player) {
        int move = player.getPlayerPosition() + dice.roll();
        if (!(move > 100)) {
            player.setPlayerPosition(move);
        }
        updatePosition(mapView, player);
    }

    public void updatePosition(ArrayList<Entity> mapView, Player player) {
        String entity = String.valueOf(mapView.get(player.getPlayerPosition()));
        if (entity.equals("Trap") || entity.equals("Current") || entity.equals("Shipyard")) {
            //TODO: pass value
            player.setPlayerPosition();
            updatePosition();
        } else if (entity.equals("Water") || entity.equals("Player")) {
            //TODO: pass value
            player.setPlayerPosition();
            map1.setMapView();
        }
    }

    public boolean hasGameEnded() {
        return player1.getPlayerPosition() > 100 || player2.getPlayerPosition() > 100 ? true : false;
    }
}
