package astroidsnickb;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public class Ship
{

    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    private double shipXpos = 950;
    private double shipYpos = 800;
    private double shipSpeed = 0;
    double shipDeltaX = 0;
    double shipDeltaY = 0;
    private double shipHeading = 0;
    AffineTransform shipAffineTransform = new AffineTransform();
    int[] xPoints =
    {
        0, 3, 1, 1, -1, -1, -3
    };
    int[] yPoints =
    {
        -4, 0, 2, 1, 1, 2, 0
    };
    Polygon shipShape = new Polygon(xPoints, yPoints, xPoints.length);
    private Area shipArea = new Area(shipShape);

    public void paintSelf(Graphics2D g2)
    {
        g2.setTransform(new AffineTransform()); // clean up
        g2.drawString("Course " + getShipHeading(), 1800, 200);
        g2.drawString("Speed " + getShipSpeed(), 1800, 300);
        shipDeltaX = Math.sin(Math.toRadians(getShipHeading())) * getShipSpeed();
        shipDeltaY = -Math.cos(Math.toRadians(shipHeading)) * getShipSpeed();
        shipXpos = shipXpos + shipDeltaX;
        shipYpos = shipYpos + shipDeltaY;
        if (getShipYpos() < 0)
        {
            setShipYpos(height);
        }

        if (getShipYpos() > height)
        {
            setShipYpos(0);
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
        g2.translate(getShipXpos(), getShipYpos()); //moving the screen
        g2.scale(20, 20);
        g2.rotate(Math.toRadians(getShipHeading()));
        shipArea = shipArea.createTransformedArea(shipAffineTransform);
        g2.fill(shipArea);
        g2.setColor(Color.WHITE);
        g2.draw(shipArea);
        g2.setTransform(new AffineTransform());
        shipAffineTransform = g2.getTransform();
    }

    public double getShipXpos()
    {
        return shipXpos;
    }

    public double getShipYpos()
    {
        return shipYpos;
    }

    public void setShipYpos(double shipYpos)
    {
        this.shipYpos = shipYpos;
    }

    public double getShipSpeed()
    {
        return shipSpeed;
    }

    public double getShipHeading()
    {
        return shipHeading;
    }

    public void setShipHeading(double shipHeading)
    {
        this.shipHeading = shipHeading;
    }

    public void setShipSpeed(double shipSpeed)
    {
        this.shipSpeed = shipSpeed;
    }

    public Area getShipArea()
    {
        return shipArea;
    }
}