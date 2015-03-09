import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

/**
 * Created by Andrew on 3/9/2015.
 */
public class Player {
    private JLabel playerLabel = new JLabel();
    ImageIcon icon =null;
    int x = -1;
    int y = -1;
    public Player()
    {
        try {
            icon =  new ImageIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("player.png")).getScaledInstance(75,75,1)).getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        playerLabel.setIcon(icon);
    }
    public void addPlayer(JFrame frame)
    {
        frame.add(playerLabel);
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
}
