package astroidsnickb;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class Bullet
{
    private Area bulletArea;
    double bulletSpeed = 5;
    double bulletXspeed;
    double bulletYspeed;
    double bulletXpos;
    double bulletYpos;
    double bulletSize;
    double bulletDeltaX;
    double bulletDeltaY;
    int bulletWidth = 3;
    int bulletHeight = 5;
    double shipSpeed;
    double shipHeading;
    Rectangle2D.Double bulletShape;
    private AffineTransform bulletAffineTransform = new AffineTransform();

    public Bullet(double shipXpos, double shipYpos, double shipSpeed, double shipHeading)
    {
        this.shipHeading = shipHeading;
        this.shipSpeed = shipSpeed;
        bulletXpos = shipXpos;
        bulletYpos = shipYpos;
        bulletShape = new Rectangle2D.Double(0, 0, bulletWidth, bulletHeight);
        bulletArea = new Area(bulletShape);
    }

    public void paintSelf(Graphics2D g2)
    {
        g2.setTransform(getBulletAffineTransform());
        g2.setColor(Color.RED);
        g2.setTransform(new AffineTransform());
        bulletDeltaX = Math.sin(Math.toRadians(shipHeading)) * (shipSpeed + bulletSpeed);
        bulletDeltaY = -Math.cos(Math.toRadians(shipHeading)) * (shipSpeed + bulletSpeed);
        bulletXpos += bulletDeltaX;
        bulletYpos += bulletDeltaY;
        g2.translate(bulletXpos, bulletYpos);
        g2.fill(bulletArea);
        bulletAffineTransform = g2.getTransform();
    }

    public AffineTransform getBulletAffineTransform()
    {
        return bulletAffineTransform;
    }

    public Area getBulletArea()
    {
        return bulletArea;
    }
}