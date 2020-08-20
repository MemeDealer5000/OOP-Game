import characters.Player;

import java.awt.*;
import java.security.InvalidParameterException;
import java.util.Random;
import java.util.Scanner;

public class Map {
    private int width;
    private int height;
    private int[][] map;
    private Player player;

    private  int getWidth(){
        return width;
    }

    private int getHeight(){
        return height;
    }

    public int[][] getMap() {
        return map;
    }

    public void printMapAndGetMove(){
        System.out.flush();
        var playerPoint = player.getPosition();
        for(var i =0; i < height; i++){
            for(var j=0; j< width; j++){
                var currentPoint = new Point(i,j);
                if(!currentPoint.equals(playerPoint)) {
                    System.out.print(map[i][j]);
                }
                else {
                    System.out.print("P");
                }
            }
            System.out.print("\n");
        }
        makeMove(playerPoint);
    }

    private void makeMove(Point playerPoint){
        var gameKeySet = new GameKeySet();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter move direction: ");
        var direction = input.next();
        gameKeySet.readInput(direction);
        var moveDifference = gameKeySet.getFinalDifference();
        var destinationPoint = new Point(playerPoint.x + moveDifference.x, playerPoint.y + moveDifference.y);
        if (map[destinationPoint.x][destinationPoint.y] == TerrainTypes.WALL.ordinal() ||
                map[destinationPoint.x][destinationPoint.y] == TerrainTypes.RIVER.ordinal()){
            moveDifference = new Point(0,0);
        }
        player.move(moveDifference.x, moveDifference.y);
    }

    private enum TerrainTypes { GROUND, WALL, RIVER, P_SPAWN, P_FINISH }

    public Map(int width, int height, Player player){
        if (width < 5 || height < 5 || width!=height ){
            throw new InvalidParameterException("Width and height must be equal and above 4");
        }
        this.height = height;
        this.width = width;
        this.map = generateRandom();
        this.player = player;
    }

    private int[][] generateRandom(){
        var map = new int[height][width];
        var spareSpace = width*height - 2*(width+height-2);
        var waterPercentage = Math.round(spareSpace * 0.2);
        generateWalls(map);
        generateWater(map,waterPercentage);
        generateSpawns(map);
        return map;
    }

    private void generateSpawns(int[][] map){
        map[1][1] = TerrainTypes.P_SPAWN.ordinal();
        map[height-2][width-2] = TerrainTypes.P_FINISH.ordinal();
    }

    private void generateWater(int[][] map, long waterPercentage){
        var rnd = new Random();
        for(var i = 0; i < waterPercentage; i++){
            var coordinateWidth = rnd.nextInt(width-2 )+1;
            var coordinateHeight = rnd.nextInt(height-2)+1;
            map[coordinateHeight][coordinateWidth] = TerrainTypes.RIVER.ordinal();
        }
    }

    private void generateWalls(int[][] map){
        for (var i = 0; i < width; i++){
            map[0][i] = TerrainTypes.WALL.ordinal();
        }
        for (var i = 0; i < height; i++){
            map[i][0] = TerrainTypes.WALL.ordinal();
        }
        for (var i = 0; i < width; i++){
            map[height-1][i] = TerrainTypes.WALL.ordinal();
        }
        for (var i = 0; i < height; i++){
            map[i][width-1] = TerrainTypes.WALL.ordinal();
        }
    }
}
