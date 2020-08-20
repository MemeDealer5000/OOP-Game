import java.awt.*;

public class GameKeySet {
    private int leftKeyPressed = 0;
    private int rightKeyPressed = 0;
    private int upKeyPressed = 0;
    private int downKeyPressed = 0;

    public GameKeySet(){}

    public void refreshKeys(){
        leftKeyPressed = 0;
        rightKeyPressed = 0;
        upKeyPressed = 0;
        downKeyPressed = 0;
    }

    public Point getFinalDifference(){
        return new Point( upKeyPressed+downKeyPressed, leftKeyPressed+rightKeyPressed);
    }

    public void readInput(String direction){
        if (direction.toLowerCase().equals("left")){
            leftKeyPressed = -1;
        }
        else if (direction.toLowerCase().equals("right")){
            rightKeyPressed = 1;
        }
        else if (direction.toLowerCase().equals("up")){
            upKeyPressed = -1;
        }
        else if (direction.toLowerCase().equals("down")){
            downKeyPressed = 1;
        }
    }
}
