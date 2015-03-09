import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * Created by Andrew on 3/8/2015.
 */
public class MainMenuGUI extends JFrame implements KeyListener {
    int location = 1;
    File file = new File(System.getenv("APPDATA") + "/.ANDREWCPU/");
    File saveLocation = new File(file.getAbsolutePath() + "/saves/");
    public void setup()
    {

        if(!file.exists())
        {
            file.mkdir();
        }

        if(!saveLocation.exists())
        {
            saveLocation.mkdir();
        }

    }
    public void loadSaves()
    {
        int n = 0;
        File[] listOfFiles = saveLocation.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                saves.add(new Save(listOfFiles[i]));
                n++;
            }
        }
        System.out.print("Loaded " + n + " files...\n");
    }
    List<Save> saves = new ArrayList<>();


    public void loadSave(Save s)
    {
        System.out.print("Loading " + s.getName() + "\n");
        game.setHealth(s.getHealth());
        game.setName(s.getName());
        game.setLevel(s.getLevel());
        game.setX(s.getX());
        game.setY(s.getY());
        System.out.print("Completed loading...\n");

        slowType(name, s.getName());
        String health = "";
        for(int i = 0; i<game.getHealth(); i++)
        {
            health+="❤";
        }
        slowType(healthLabel, health);
        healthLabel.setForeground(Color.RED);
        slowType(level, "Lvl. " + s.getLevel());
        repaint();
        writeFile(new File(file.getAbsolutePath() + "/LastPlayed.txt"), s.getFile().getName().replaceAll(".txt",""));
    }
    public Save getLastPlayed()
    {
        try {
            String firstLine = "";
            try (BufferedReader br = new BufferedReader(new FileReader(new File(file.getAbsolutePath() + "/LastPlayed.txt")))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if(firstLine.equalsIgnoreCase(""))
                    {
                        firstLine = line;
                    }
                }
            }
            String uuid = firstLine.replaceAll(".txt","");
            for(Save save : saves)
            {
                if(save.getUUID().equalsIgnoreCase(uuid))
                {
                    return save;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void keyPressed(KeyEvent event)
    {
        if(event.getKeyCode()==KeyEvent.VK_DOWN)
        {


            location++;
            if(location==6)
            {
                location = 1;
            }
        }
        if(event.getKeyCode()==KeyEvent.VK_UP)
        {
            location--;
           if(location==0)
           {
               location = 5;
           }


        }

        if(event.getKeyCode()==KeyEvent.VK_ENTER)
        {
            if(location==5)
            {
                System.exit(0);
            }
            if(location==4)
            {
                slowType(infoBox, "This is Andrewcpu's Game.","It has no purpose,","BUT TO FIGHT MONSTERS","TRY NOT TO GET ADDICTED","NO ONE LIKES 8 BIT GAMES ANYWAY - NO ONE EVER", "Andrewcpu's Game");
            }
            if(location==3)
            {
                JFrame savePanel = new JFrame("Saves");
                savePanel.setBackground(Color.YELLOW);
                saves = new ArrayList<>();
                loadSaves();
                int saveNumber = 0;
                savePanel.setLayout(null);
                savePanel.setBounds(0,0,500,500);
                for(Save save : saves)
                {
                    JButton jButton = new JButton(save.getName());
                    jButton.setBounds(0,saveNumber * 25, savePanel.getWidth(), 25);
                    savePanel.add(jButton);
                    jButton.setBackground(Color.YELLOW);
                    savePanel.repaint();
                    jButton.setBorder(BorderFactory.createEmptyBorder());
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            loadSave(save);
                            savePanel.dispose();
                        }
                    });
                    saveNumber++;
                }
                savePanel.setBounds(0,0,500,50 + saveNumber * 25);
                savePanel.setVisible(true);

            }
            if(location==2)
            {
                JFrame frame = new JFrame("NEW GAME");
                frame.setBounds(0,0,400,400);
                frame.setLayout(null);
                JLabel label = new JLabel("Choose a username:");
                JTextField username = new JTextField();
                label.setBounds(0,0,400,200);
                username.setBounds(0,200,400,200);
                frame.add(label);
                frame.add(username);
                username.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(e.getKeyCode()==KeyEvent.VK_ENTER)
                        {
                            try {
                                createSave(username.getText());
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                            frame.dispose();
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                });


                frame.setVisible(true);
            }
            if(location==1)
            {
                loadSaves();
                loadSave(getLastPlayed());
            }
        }
        update();
    }
    public void writeFile(File file, String toWrite)
    {
        try {

            if(!file.exists())
            {
                file.createNewFile();
            }


            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(toWrite);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createSave(String username)
    {
        String line = System.getProperty("line.separator");

        UUID uuid = UUID.randomUUID();
        String toWrite = "NAME: " + username + line + "LEVEL: 1" + line + "HEALTH: 10" + line + "X: 50" + line + "Y: 50" + line  +"UUID: " + uuid;
        File file = new File(saveLocation.getAbsolutePath() + "/" + uuid + ".txt");
        writeFile(file, toWrite);
    }
    JLabel contin = new JLabel("CONTINUE");
    JLabel newGame = new JLabel("NEW GAME");
    JLabel loadGame = new JLabel("LOAD GAME");
    JLabel info = new JLabel("INFO");
    JLabel quit = new JLabel("QUIT");
    JLabel background = new JLabel();
    Game game = new Game();
    public void update()
    {
        contin.setBackground(new Color(0,0,0,0));
        newGame.setBackground(new Color(0,0,0,0));
        loadGame.setBackground(new Color(0,0,0,0));
        info.setBackground(new Color(0,0,0,0));
        quit.setBackground(new Color(0,0,0,0));
        contin.setForeground(Color.BLACK);
        newGame.setForeground(Color.BLACK);
        info.setForeground(Color.BLACK);
        loadGame.setForeground(Color.BLACK);
        quit.setForeground(Color.BLACK);

        if(location==1)
        {
            contin.setBackground(new Color(1,1,1,25));
            contin.setForeground(Color.RED);
           // slowType("SELECTED: CONTINUE");
        }
        if(location==2)
        {
            newGame.setBackground(new Color(1,1,1,25));
            newGame.setForeground(Color.RED);
           // slowType("SELECTED: NEW GAME");
        }
        if(location==3)
        {
            loadGame.setBackground(new Color(1,1,1,25));
            loadGame.setForeground(Color.RED);
            //slowType("SELECTED: LOAD GAME");
        }
        if(location==4)
        {
            info.setBackground(new Color(1,1,1,25));
            info.setForeground(Color.RED);
          //  slowType("SELECTED: INFO");
        }
        if(location==5) {
            quit.setBackground(new Color(0, 0, 0,25));
            quit.setForeground(Color.RED);
          //  slowType("SELECTED: QUIT");
        }
        repaint();


    }
    public void keyReleased(KeyEvent event)
    {

    }
    public void keyTyped(KeyEvent event)
    {

    }
    JLabel infoBox = new JLabel();
    public Thread slowType(final JLabel label, final String... string)
    {



        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {
                for(final String s : string)
                {
                    Thread thread = new Thread()
                    {
                        @Override
                        public void run()
                        {
                            java.util.List<String> letters = new ArrayList<String>();
                            String[] split = s.split("");

                            for(String s : split)
                            {
                                letters.add(s);
                            }
                            String message = "";
                            repaint();
                            for(String s : letters)
                            {
                                label.setText("");
                                message+=s.toUpperCase();
                                label.setText(message
                                );
                                try
                                {
                                    sleep(100);
                                }catch(Exception ex){}

                            }
                        }

                    };
                    thread.run();

                }
            }
        });
       // thread.run();


        return null;

    }
    JLabel healthLabel = new JLabel();
    JLabel name = new JLabel();
    JLabel level = new JLabel();
    public static void main(String[] args)
    {
        new MainMenuGUI();
    }
    public MainMenuGUI()
    {
        setBounds(0,0,1000,700);
        setTitle("Main Menu");
        setLayout(null);
        background.setBounds(getBounds());
        try {
            background.setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("8bitbg.png")).getScaledInstance(getWidth(),getHeight(),1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setContentPane(background);
        add(contin);
        add(info);
        add(quit);
        add(loadGame);
        add(newGame);
        add(infoBox);
        add(healthLabel);
        add(level);
        add(name);
        setup();

        contin.setOpaque(true);
        info.setOpaque(true);
        quit.setOpaque(true);
        loadGame.setOpaque(true);
        newGame.setOpaque(true);
        
        contin.setHorizontalAlignment(SwingConstants.CENTER);
        info.setHorizontalAlignment(SwingConstants.CENTER);
        quit.setHorizontalAlignment(SwingConstants.CENTER);
        loadGame.setHorizontalAlignment(SwingConstants.CENTER);
        newGame.setHorizontalAlignment(SwingConstants.CENTER);
        infoBox.setHorizontalAlignment(SwingConstants.CENTER);
        contin.setVerticalAlignment(SwingConstants.HORIZONTAL);
        info.setVerticalAlignment(SwingConstants.HORIZONTAL);
        quit.setVerticalAlignment(SwingConstants.HORIZONTAL);
        loadGame.setVerticalAlignment(SwingConstants.HORIZONTAL);
        newGame.setVerticalAlignment(SwingConstants.HORIZONTAL);
        infoBox.setVerticalAlignment(SwingConstants.HORIZONTAL);
        infoBox.setBounds(getWidth() / 2 - 250, 50, 500, 25);

        name.setBounds(0,0, 300, 25);
        healthLabel.setBounds(0,25,300,25);
        level.setBounds(0,50,300,25);







        int height = 25;
        int num = 0;
        int y = getHeight() / 2;
        contin.setBounds(getWidth() / 2 - 50, y+  height * num, 100, height);
        num++;
        newGame.setBounds(getWidth() / 2 - 50,y + height * num, 100, height);
        num++;
        loadGame.setBounds(getWidth() / 2 - 50, y+  height * num, 100, height);
        num++;
        info.setBounds(getWidth() / 2 - 50, y + height * num, 100, height);
        num++;
        quit.setBounds(getWidth() / 2 - 50, y + height * num, 100, height);
        setVisible(true);
        update();

        slowType(infoBox, "Welcome to the game!","Nothing has been completed yet","I'm sorry doll","We'll finish it ASAP <3","andrewcpu's game");


        addKeyListener(this);
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                new Thread()
                {
                    @Override
                    public void run()
                    {
                        background.setBounds(getBounds());
                        try {
                            background.setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("8bitbg.png")).getScaledInstance(getWidth(),getHeight(),1)));
                        } catch (IOException ee) {
                            ee.printStackTrace();
                        }
                    }

                }.run();

                int height = 25;
                int num = 0;
                int y = getHeight() / 2;
                contin.setBounds(getWidth() / 2 - 50, y+  height * num, 100, height);
                num++;
                newGame.setBounds(getWidth() / 2 - 50,y + height * num, 100, height);
                num++;
                loadGame.setBounds(getWidth() / 2 - 50, y+  height * num, 100, height);
                num++;
                info.setBounds(getWidth() / 2 - 50, y + height * num, 100, height);
                num++;
                quit.setBounds(getWidth() / 2 - 50, y + height * num, 100, height);
                setVisible(true);
                update();
                repaint();
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });



    }

}
