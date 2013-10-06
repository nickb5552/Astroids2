package astroidsnickb;

/**
 * *************************************************
 * copyright Nick Barber 2013 Rev131006A
 * *************************************************
 */
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Controller extends JComponent implements KeyListener, ActionListener, Runnable
{
    JFrame astroidField;
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    Ship battleCruiser;
    Timer paintTicker;
    Timer astroidTicker;
    ArrayList<Astroid> astroidList;
    ArrayList<Bullet> bulletList;
    Image spaceImage;
    double shipXpos;
    double shipYpos;
    double shipSpeed;
    double shipHeading;
    Explosion shipExplosion;
    private boolean shipDestroyed = false; 
    AffineTransform shipAffineTransform = new AffineTransform();
    Area shipArea = new Area();
    Area astroidArea = new Area();
    Area bulletArea = new Area();
    URL fireSoundAddress = getClass().getResource("bullet.wav");
    AudioClip fireFile = JApplet.newAudioClip(fireSoundAddress);
    URL exploSoundAddress = getClass().getResource("Bomb_Exploding.wav");
    AudioClip explosionFile = JApplet.newAudioClip(exploSoundAddress);
    int paintTickerCounter = 0;

    public static void main(String[] joe)
    {
        SwingUtilities.invokeLater(new Controller());
    }

    @Override
    public void run()
    {
        astroidList = new ArrayList<Astroid>();
        bulletList = new ArrayList<Bullet>();
        paintTicker = new Timer(20, this);
        paintTicker.start();
        astroidTicker = new Timer(1000, this);
        astroidTicker.start();
        astroidField = new JFrame("Astroids");
        astroidField.setVisible(true);
        astroidField.setSize(width, height);
        astroidField.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        battleCruiser = new Ship();
        shipExplosion = new Explosion(battleCruiser);
        astroidField.add(this);
        astroidField.addKeyListener(this);
        spaceImage = new ImageIcon(this.getClass().getResource("SpaceBG.jpg")).getImage();
    }

    public boolean collision(Area area1, Area area2)
    {
        Area arealclone = (Area) area1.clone();
        arealclone.intersect(area2);
        if (!arealclone.isEmpty()) //colliding
        {
            return true;
        } else
        {
            return false;
        }
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(spaceImage, 0, 0, width, height, null);
        g2.setTransform(new AffineTransform());
        g2.setColor(Color.WHITE);
        g2.drawString(astroidList.size() + " astroids", (width - 760), 400);
        g2.drawString(bulletList.size() + " bullets", (width - 760), 500);
        this.shipXpos = battleCruiser.getShipXpos();
        this.shipYpos = battleCruiser.getShipYpos();
        this.shipHeading = battleCruiser.getShipHeading();
        shipSpeed = battleCruiser.getShipSpeed();
        if (shipDestroyed)
        {
            shipExplosion.paintSelf(g2);
        }
        battleCruiser.paintSelf(g2);
        for (int i = 0; i < bulletList.size(); i++)
        {
            Bullet b = bulletList.get(i);
            b.paintSelf(g2);
            if (b.bulletXpos > width)
            {
                bulletList.remove(i);
            }
            if (b.bulletXpos < -100)
            {
                bulletList.remove(i);
            }
            if (b.bulletYpos > height)
            {
                bulletList.remove(i);
            }
            if (b.bulletYpos < -100)
            {
                bulletList.remove(i);
            }
            for (int j = 0; j < astroidList.size(); j++) //collision checker
            {
                Astroid a = astroidList.get(j);
                astroidArea = a.getAstroidArea();
                astroidArea = astroidArea.createTransformedArea(a.getAstroidAffineTransform());
                bulletArea = b.getBulletArea();
                bulletArea = bulletArea.createTransformedArea(b.getBulletAffineTransform());
                if (collision(bulletArea, astroidArea))
                {
                    astroidList.remove(j);
                }
            }
        }
        for (int i = 0; i < astroidList.size(); i++)
        {
            Astroid a = astroidList.get(i);
            a.paintSelf(g2);
            if (a.getAstroidXtranslation() > width) //right
            {
                astroidList.remove(i);
            }
            if (a.getAstroidXtranslation() < -1000) //left
            {
                astroidList.remove(i);
            }
            if (a.getAstroidYtranslation() > height) //bottom
            {
                astroidList.remove(i);
            }
            if (a.getAstroidYtranslation() < -2000) //top
            {
                astroidList.remove(i);
            }
            shipArea = battleCruiser.getShipArea();
            shipArea = shipArea.createTransformedArea(battleCruiser.getShipAffineTransform());
            astroidArea = a.getAstroidArea();
            astroidArea = astroidArea.createTransformedArea(a.getAstroidAffineTransform());
            if (collision(shipArea, astroidArea))
            {
                shipDestroyed = true;
                explosionFile.play();
                battleCruiser.setShipDestroyed(shipDestroyed);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke)
    {
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        if (!shipDestroyed)
        {
            if (ke.getKeyCode() == KeyEvent.VK_LEFT)//turns left
        {
            battleCruiser.setShipHeading(battleCruiser.getShipHeading() - 5);
        }

        if (ke.getKeyCode() == KeyEvent.VK_RIGHT)//turns right
        {
            battleCruiser.setShipHeading(battleCruiser.getShipHeading() + 5);
        }

        if (ke.getKeyCode() == KeyEvent.VK_UP)//increase speed
        {
            battleCruiser.setShipSpeed(battleCruiser.getShipSpeed() + 1);
        }
        if (ke.getKeyCode() == KeyEvent.VK_DOWN)//decrease speed
        {
            battleCruiser.setShipSpeed(battleCruiser.getShipSpeed() - 1);
        }
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) //spacebar shoot bullet
        {
            fireFile.play();
            bulletList.add(new Bullet(shipXpos, shipYpos, shipSpeed, shipHeading));
        }
        }
                
    }

    @Override
    public void keyReleased(KeyEvent ke)
    {
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource() == astroidTicker)
        {
            astroidList.add(new Astroid());
        }
        if (ae.getSource() == paintTicker)
        {
            repaint();
        }
        if (shipDestroyed)
        {
            paintTickerCounter++;
            if (paintTickerCounter >= 150)
            {
                paintTicker.stop();
            }
        }
    }
}
