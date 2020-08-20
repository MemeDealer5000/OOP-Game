import characters.Player;

import java.awt.*;
import java.util.Random;

public class Game {
    public static void main(String[] args){
        System.out.println("**** Welcome to Escape the maze ****");
        var player = new Player(100, 1,1);
        var rnd = new Random().nextInt(5) + 5 ;
        var map = new Map(rnd,rnd,player);
        var exit = new Point(rnd-2,rnd-2);
        do {
            doGameLoop(player,map);
        } while(player.isCharacterAlive() && !(player.getPosition().equals(exit)));
        System.out.println("Congratulations! You won!");
    }

    private static void doGameLoop(Player player, Map map){
        map.printMapAndGetMove();
    }
}