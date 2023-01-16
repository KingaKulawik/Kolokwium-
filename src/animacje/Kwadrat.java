package animacje;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class Kwadrat extends Figura{
    public Kwadrat(Graphics2D buf, AnimPanel animPanel, Point[] coordinates) {
        super(buf, animPanel);
        Point firstPoint = coordinates[0];
        Point secondPoint = coordinates[1];
        shape = new Rectangle2D.Float((float) firstPoint.getX(), (float) firstPoint.getY(), (float) (secondPoint.getX() - firstPoint.getX()), (float) (secondPoint.getY() - firstPoint.getY()));
        aft = new AffineTransform();
        area = new Area(shape);
    }
}
