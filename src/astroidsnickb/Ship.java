package astroidsnickb;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

public class Ship
{
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    private double shipXpos = 950;
    private double shipYpos = 800;
    double shipSpeed = 0;
    double shipDeltaX = 0;
    double shipDeltaY = 0;
    double shipHeading = 0;
    private AffineTransform shipAffineTransform = new AffineTransform(); //clean affine transform
    int[] xPoints =
    {
        0, 3, 1, 1, -1, -1, -3
    };
    int[] yPoints =
    {
        -4, 0, 2, 1, 1, 2, 0
    };
    Polygon shipShape = new Polygon(xPoints, yPoints, xPoints.length);

    public void paintSelf(Graphics2D g2)
    {
        shipDeltaX = Math.sin(Math.toRadians(getShipHeading())) * getShipSpeed();
        shipDeltaY = -Math.cos(Math.toRadians(shipHeading)) * getShipSpeed();
        shipXpos = getShipXpos() + shipDeltaX;
        shipYpos += shipDeltaY;
        if (getShipYpos() < 0)
        {
            shipYpos = height;
        }

        if (getShipYpos() > height)
        {
            shipYpos = 0;
        }

        if (getShipXpos() < 0)
        {
            shipXpos = width;
        }
        if (getShipXpos() > width)
        {
            shipXpos = 0;
        }
        g2.setStroke(new BasicStroke(.01f));
        g2.setColor(Color.BLUE);
        g2.translate(getShipXpos(), getShipYpos());
        g2.scale(20, 20);
        g2.rotate(Math.toRadians(getShipHeading()));
        shipAffineTransform = g2.getTransform();
        g2.fill(shipShape);
        g2.setColor(Color.WHITE);
        g2.draw(shipShape);
        g2.drawString("Course " + getShipHeading(), 1800, 200);
        g2.drawString("Speed " + getShipSpeed(), 1800, 300);

    }

    public double getShipXpos() //returns the Ship X position
    {
        return shipXpos;
    }

    public double getShipYpos() //returns the Ship Y position
    {
        return shipYpos;
    }

    public double getShipSpeed()
    {
        return shipSpeed;
    }

    public double getShipHeading()
    {
        return shipHeading;
    }

    public AffineTransform getShipAffineTransform()
    {
        return shipAffineTransform;
    }
}
