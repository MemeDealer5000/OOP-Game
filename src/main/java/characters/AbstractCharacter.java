package characters;
import java.awt.*;
import java.io.IOException;
import java.security.InvalidParameterException;


public abstract class AbstractCharacter {
    protected int x;
    protected int y;
    protected int health;
    protected boolean isAlive;

    public AbstractCharacter(int health, int x, int y){
        this.x = x;
        this.y = y;
        if (health < 0){
            throw new InvalidParameterException("Health must be positive value");
        }
        this.health = health;
        this.isAlive = true;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int healthAmount) throws IOException {
        if (healthAmount <= 0){
            throw new IOException("Health can't be below zero");
        }
        this.health = healthAmount;
    }

    public Point getPosition(){
        return new Point(x,y);
    }

    public void killCharacter(){
        this.isAlive = false;
    }

    public boolean isCharacterAlive() {
        return isAlive;
    }

    public abstract void move(int xDiff, int yDiff);
}
