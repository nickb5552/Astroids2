package astroidsnickb;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class Explosion
{
    Ellipse2D.Double explo;
    
    public Explosion(Ship battleCruiser)
    {
        battleCruiser.getShipAffineTransform();
        explo = new Ellipse2D.Double(battleCruiser.getShipXpos() - 100, battleCruiser.getShipYpos() - 100, 200, 200);
    }

    public void paintSelf(Graphics2D g2)
    {
        g2.setTransform(new AffineTransform());
        g2.setColor(Color.RED);
        g2.fill(explo);
        explo.width += 0.8;
        explo.height += 0.8;
    }
}
