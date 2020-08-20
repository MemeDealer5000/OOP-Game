package characters;

public class Player extends AbstractCharacter {

    public Player(int health, int x, int y) {
        super(health, x, y);
    }

    @Override
    public void move(int xDiff, int yDiff) {
        this.x += xDiff;
        this.y += yDiff;
    }
}
