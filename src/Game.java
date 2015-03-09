import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Andrew on 3/8/2015.
 */
public class Game extends JFrame implements KeyListener {
    public void keyPressed(KeyEvent event)
    {

    }
    public void keyReleased(KeyEvent event)
    {

    }
    public void keyTyped(KeyEvent event)
    {

    }
    int x;
    int y;
    String name;
    int level;
    int health;
    /*
    int jumpheight value to decrease on tick to manage jupming and physics instead of pulling them down and detecting for space <3
    -10:42
     */
    public Game() {
        setBounds(0,0,1000,500);
        setLayout(null);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
