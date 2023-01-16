package animacje;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.Random;

public abstract class Figura implements Runnable, ActionListener {
	// wspolny bufor
	protected Graphics2D buffer;
	protected Area area;
	// do wykreslania
	protected Shape shape;
	// przeksztalcenie obiektu
	protected AffineTransform aft;
	private AnimPanel animPanel;
	private int width;
	private int height;
	private int delay;

	// przesuniecie
	private int dx, dy;
	// rozciaganie
	private double sf;
	// kat obrotu
	private double an;
	private Color clr;

	protected static final Random rand = new Random();

	public Figura(Graphics2D buf, AnimPanel animPanel) {
		this.animPanel = animPanel;
		delay = animPanel.getDelay();
		buffer = buf;
		width = animPanel.getWidth();
		height = animPanel.getHeight();

		clr = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), 255);
	}

	@Override
	public void run() {
		area.transform(aft);
		shape = area;

		while (true) {
			// przygotowanie nastepnego kadru
			shape = nextFrame();
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
			}
		}
	}

	protected Shape nextFrame() {
		aft = new AffineTransform();
		area = new Area(shape);
		Rectangle bounds = area.getBounds();

		int cx = bounds.x + bounds.width / 2;
		int cy = bounds.y + bounds.height / 2;

		Direction direction = animPanel.getDirection();

		switch (direction){
			case LEFT:
				dx = -4;
				dy = 0;
				break;
			case RIGHT:
				dx = 4;
				dy = 0;
				break;
			case TOP:
				dx = 0;
				dy = -4;
				break;
			case BOTTOM:
				dx = 0;
				dy = 4;
				break;
		}


		if(cx < 0 && direction == Direction.LEFT){
			dx = 0;
		}

		if(cx > width && direction == Direction.RIGHT){
			dx = 0;
		}

		if (cy < 0 && direction == Direction.TOP){
			dy = 0;
		}

		if (cy > height && direction == Direction.BOTTOM){
			dy = 0;
		}

		// konstrukcja przeksztalcenia
		aft.translate(dx, dy);
		// przeksztalcenie obiektu
		area.transform(aft);
		return area;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// wypelnienie obiektu
		buffer.setColor(clr.brighter());
		buffer.fill(shape);
		// wykreslenie ramki
		buffer.setColor(clr.darker());
		buffer.draw(shape);
	}

}
