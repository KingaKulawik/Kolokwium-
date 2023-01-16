package animacje;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimPanel extends JPanel implements MouseListener, ActionListener {
	// bufor
	private Image image;
	// wykreslacz ekranowy
	private Graphics2D device;
	// wykreslacz bufora
	private Graphics2D buffer;
	private int delay = 40;
	private Timer timer;
	private Direction direction;
	private Point[] coordinates;

	public AnimPanel() {
		super();
		timer = new Timer(delay, this);
		direction = Direction.BOTTOM;
		coordinates = new Point[2];
		setBackground(Color.WHITE);

		addMouseListener(this);
	}

	public void initialize() {
		int width = getWidth();
		int height = getHeight();

		image = createImage(width, height);
		buffer = (Graphics2D) image.getGraphics();
		buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		device = (Graphics2D) getGraphics();
		device.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	void animate() {
		if (timer.isRunning()) {
			timer.stop();
		} else {
			timer.start();
		}
	}

	private void addRect(Point[] coordinates){
		Figura fig = new Kwadrat(buffer, this, coordinates);
		timer.addActionListener(fig);
		new Thread(fig).start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		device.drawImage(image, 0, 0, null);
		buffer.clearRect(0, 0, getWidth(), getHeight());
	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		Point point = new Point(mouseEvent.getX(), mouseEvent.getY());
		if(coordinates[0] == null){
			coordinates[0] = point;
		}else{
			coordinates[1] = point;
			addRect(coordinates);
			coordinates[0] = null;
			coordinates[1] = null;
		}
	}

	@Override
	public void mousePressed(MouseEvent mouseEvent) {

	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {

	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {

	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {

	}

	public int getDelay() {
		return delay;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}
}
