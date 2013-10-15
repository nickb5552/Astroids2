package astroidsnickb;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class Explosion
{
    private int shipDeadXposition;
    private int shipDeadYposition;
    private Ellipse2D.Double explo = new Ellipse2D.Double(shipDeadXposition, shipDeadYposition, 300, 300);
    private AffineTransform shipAffineTransform;
    
    public void paintSelf(Graphics2D g2)
    {
        g2.setTransform(new AffineTransform());
        explo.x = shipAffineTransform.getTranslateX() -150;
        explo.y = shipAffineTransform.getTranslateY() -150;
        g2.setColor(Color.RED);
        g2.fill(explo);
        explo.width += 0.8;
        explo.height += 0.8;
    }

  
    public void setShipDeadXposition(int shipDeadXposition)
    {
        this.shipDeadXposition = shipDeadXposition;
    }

  
    public void setShipDeadYposition(int shipDeadYposition)
    {
        this.shipDeadYposition = shipDeadYposition;
    }

    public void setShipAffineTransform(AffineTransform shipAffineTransform)
    {
        this.shipAffineTransform = shipAffineTransform;
    }
}
