package animacje;

import javax.swing.*;
import java.awt.*;

public class AnimFrame extends JFrame {
	private int windowWidth = 600;
	private int windowHeight = 450;
	private AnimPanel animPanel;

	public AnimFrame() throws HeadlessException {
		this("undefined");
	}

	public AnimFrame(String title) throws HeadlessException {
		super(title);
		buildFrame();
	}

	private void buildFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		// Set position and size of window
		setBounds((screenDimension.width - windowWidth)/2, (screenDimension.height - windowHeight)/2, windowWidth, windowHeight);
		setLayout(new BorderLayout());
		createBtns();
		createAnimPanel();

		setVisible(true);
	}

	private void createBtns(){
		JPanel btnPanel = new JPanel();

		JButton leftBtn = new JButton("Left");
		leftBtn.addActionListener(e->{
			animPanel.setDirection(Direction.LEFT);
		});

		JButton rightBtn = new JButton("Right");
		rightBtn.addActionListener(e->{
			animPanel.setDirection(Direction.RIGHT);
		});

		JButton topBtn = new JButton("Top");
		topBtn.addActionListener(e->{
			animPanel.setDirection(Direction.TOP);
		});

		JButton bottomBtn = new JButton("Bottom");
		bottomBtn.addActionListener(e->{
			animPanel.setDirection(Direction.BOTTOM);
		});

		JButton animBtn = new JButton("Animate");
		animBtn.addActionListener(e -> {
			animPanel.animate();
		});

		btnPanel.add(animBtn);
		btnPanel.add(leftBtn);
		btnPanel.add(rightBtn);
		btnPanel.add(topBtn);
		btnPanel.add(bottomBtn);

		add(btnPanel, BorderLayout.PAGE_END);
	}

	private void createAnimPanel(){
		animPanel = new AnimPanel();
		add(animPanel);
		SwingUtilities.invokeLater(()->{
			animPanel.initialize();
		});
	}

}