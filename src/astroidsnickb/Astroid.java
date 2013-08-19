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
    private double astroidXtranslation;
    private double astroidYtranslation;
    private double astroidInitialXPos;
    private double astroidInitialYPos;
    double astroidSpeed;
    double astroidXspeed;
    double astroidYspeed;
    double astroidSize;
    double astroidDeltaX;
    double astroidDeltaY;
    double scaleX;
    double scaleY;
    double astroidScaling = 1;
    Ellipse2D.Double astroidShape;
    private AffineTransform astroidAffineTransform = new AffineTransform(); //idenity transform
    private Area astroidArea;

    public Astroid() //Constructor 
    {
        astroidSize = (Math.random() * 50) + 5;
        astroidSpeed = (Math.random() * 5) + 2;
        int edge = (int) ((Math.random() * 4) + 1);
        switch (edge)
        {
            case 1: //top
                astroidInitialXPos = Math.random() * width;
                astroidInitialYPos = 0;
                astroidHeading = (Math.random() * 180) + 90;
                break;
            case 2: //right
                astroidInitialXPos = width;
                astroidInitialYPos = Math.random() * height;
                astroidHeading = (Math.random() * 180) + 180;
                break;
            case 3: //bottom
                astroidInitialXPos = Math.random() * width;
                astroidInitialYPos = height;
                astroidHeading = (Math.random() * 180) + 270;
                break;
            case 4: //left
                astroidInitialXPos = 0;
                astroidInitialYPos = Math.random() * height;
                astroidHeading = (Math.random() * 180) + 0;
                break;
        }
        astroidDeltaX = Math.sin(Math.toRadians(astroidHeading)) * astroidSpeed;
        astroidDeltaY = -Math.cos(Math.toRadians(astroidHeading)) * astroidSpeed;
        astroidShape = new Ellipse2D.Double(0, 0, astroidSize, astroidSize);
        astroidArea = new Area(astroidShape);
        astroidAffineTransform.setToTranslation(astroidInitialXPos, astroidInitialYPos);
    }

    public void paintSelf(Graphics2D g2)
    {
        g2.setTransform(getAstroidAffineTransform());
        g2.setColor(new Color(0x8B, 0x45, 0x13));
        astroidXtranslation += astroidDeltaX;
        astroidYtranslation += astroidDeltaY;
        g2.translate(astroidDeltaX, astroidDeltaY); 
        astroidScaling *= 1.002;
        astroidAffineTransform = g2.getTransform();
        g2.scale(astroidScaling, astroidScaling);
        g2.fill(astroidArea);
    }

    public Area getAstroidArea()
    {
        return astroidArea;
    }

    public AffineTransform getAstroidAffineTransform()
    {
        return astroidAffineTransform;
    }

    public double getAstroidXtranslation()
    {
        return astroidXtranslation;
    }

    public double getAstroidYtranslation()
    {
        return astroidYtranslation;
    }
}