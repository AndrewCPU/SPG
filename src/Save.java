import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Andrew on 3/8/2015.
 */
public class Save {
    File file;
    String UUID;
    public Save(File file) {
        this.file = file;
        setup();
    }
    int x;
    int y;
    int health;
    int level;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public void setup()
    {
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // process the line.
                    if(line.contains("X: "))
                    {
                        x = Integer.parseInt(line.split("X: ")[1]);
                    }
                    if(line.contains("Y: "))
                    {
                        y = Integer.parseInt(line.split("Y: ")[1]);
                    }
                    if(line.contains("HEALTH: "))
                    {
                        health = Integer.parseInt(line.split("HEALTH: ")[1]);
                    }
                    if(line.contains("LEVEL: "))
                    {
                        level = Integer.parseInt(line.split("LEVEL: ")[1]);
                    }
                    if (line.contains("NAME: "))
                    {
                        name = line.split("NAME: ")[1];
                    }
                    if (line.contains("UUID: "))
                    {
                        UUID =  line.split("UUID: ")[1];
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
