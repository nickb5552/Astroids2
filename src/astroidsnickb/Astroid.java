package astroidsnickb;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Astroid
{
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    double astroidHeading;
    double astroidXpos;
    double astroidYpos;
    double astroidSpeed;
    double astroidXspeed;
    double astroidYspeed;
    double astroidSize;
    double astroidDeltaX;
    double astroidDeltaY;
    Area astroidArea;
    double scaleX = 1;
    double scaleY = 1;
    Ellipse2D.Double astroidShape;
    private AffineTransform astroidAffineTransform = new AffineTransform();

    public Astroid() //Constructor 
    {
        astroidSize = (Math.random() * 9) + 3;
        astroidSpeed = (Math.random() * 5) + 2;
        int edge = (int) ((Math.random() * 4) + 1);
        switch (edge)
        {
            case 1: //top
                astroidXpos = Math.random() * width;
                astroidYpos = 0;
                astroidHeading = (Math.random() * 180) + 90;
                astroidDeltaX = Math.sin(Math.toRadians(astroidHeading)) * astroidSpeed;
                astroidDeltaY = -Math.cos(Math.toRadians(astroidHeading)) * astroidSpeed;
                astroidShape = new Ellipse2D.Double(0, 0, astroidSize, astroidSize);
                astroidArea = new Area(astroidShape);
                break;
            case 2: //right side
                astroidXpos = width;
                astroidYpos = Math.random() * height;
                astroidHeading = (Math.random() * 180) + 180;
                astroidDeltaX = Math.sin(Math.toRadians(astroidHeading)) * astroidSpeed;
                astroidDeltaY = -Math.cos(Math.toRadians(astroidHeading)) * astroidSpeed;
                astroidShape = new Ellipse2D.Double(0, 0, astroidSize, astroidSize);
                astroidArea = new Area(astroidShape);
                break;
            case 3: //bottom
                astroidXpos = Math.random() * width;
                astroidYpos = height;
                astroidHeading = (Math.random() * 180) + 270;
                astroidDeltaX = Math.sin(Math.toRadians(astroidHeading)) * astroidSpeed;
                astroidDeltaY = -Math.cos(Math.toRadians(astroidHeading)) * astroidSpeed;
                astroidShape = new Ellipse2D.Double(0, 0, astroidSize, astroidSize);
                astroidArea = new Area(astroidShape);
                break;
            case 4: //left side
                astroidXpos = 0;
                astroidYpos = Math.random() * height;
                astroidHeading = (Math.random() * 180) + 0;
                astroidDeltaX = Math.sin(Math.toRadians(astroidHeading)) * astroidSpeed;
                astroidDeltaY = -Math.cos(Math.toRadians(astroidHeading)) * astroidSpeed;
                astroidShape = new Ellipse2D.Double(0, 0, astroidSize, astroidSize);
                astroidArea = new Area(astroidShape);
                break;
        }
    }

    public void paintSelf(Graphics2D g2)
    {
        g2.setTransform(new AffineTransform());
        g2.setColor(new Color(0x8B, 0x45, 0x13));
        g2.translate(astroidXpos, astroidYpos);
        g2.scale(astroidSize, astroidSize);
        astroidXpos += astroidDeltaX;
        astroidYpos += astroidDeltaY;
        scaleX += .003;
        scaleY += .003;
        g2.scale(scaleX, scaleY);
        astroidAffineTransform = g2.getTransform();
        g2.fill(astroidShape);
        astroidArea.createTransformedArea(astroidAffineTransform);
    }

    public AffineTransform getAstroidAffineTransform()
    {
        return astroidAffineTransform;
    }
}
