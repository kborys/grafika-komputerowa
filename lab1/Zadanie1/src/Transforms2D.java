import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Transforms2D extends JPanel {

	private class Display extends JPanel {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.translate(300,300);  // Moves (0,0) to the center of the display.
			int whichTransform = transformSelect.getSelectedIndex();

			
			switch(whichTransform)
			{
				case 1:
					g2.scale(.5, .5);
					break;
				case 2:
					g2.rotate(Math.PI / 4);
					break;
				case 3:
					g2.rotate(Math.PI);
					g2.scale(-.5, 1);
					break;
				case 4:
					g2.shear(.25, 0);
					break;
				case 5:
					g2.translate(0, -300);
					break;
				case 6:
					g2.shear(-.25, 0);
					g2.rotate(Math.PI / 2);
					break;
				case 7:
					g2.rotate(Math.PI);
					g2.scale(.5, 1);
					break;
				case 8:
					g2.rotate(Math.PI / 6);
					g2.scale(1, .5);
					g2.translate(0, 200);
					break;
				case 9:
					g2.shear(0, .25);
					g2.rotate(Math.PI);
					g2.translate(-150, 0);
					break;
				default:
					break;
			}

			drawPolygon(g2, 12, 150);
		}
		
		private void drawPolygon(Graphics2D g, int sides, int radius) {
			int centerX = 0;
			int centerY = 0;
			int xPoints[] = new int[sides];
			int yPoints[] = new int[sides];
			for(int i = 0; i < sides; i++) {
				double angle = 2 * Math.PI * i / sides;
				xPoints[i] = (int) (centerX + radius * Math.cos(angle));
				yPoints[i] = (int) (centerY + radius * Math.sin(angle));
			}
			
			g.drawPolygon(xPoints, yPoints, sides);
		}
	}

	private Display display;
	private BufferedImage pic;
	private JComboBox<String> transformSelect;

	public Transforms2D() throws IOException {
		display = new Display();
		display.setBackground(Color.YELLOW);
		display.setPreferredSize(new Dimension(600,600));
		transformSelect = new JComboBox<String>();
		transformSelect.addItem("None");
		for (int i = 1; i < 10; i++) {
			transformSelect.addItem("No. " + i);
		}
		transformSelect.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.repaint();
			}
		});
		setLayout(new BorderLayout(3,3));
		setBackground(Color.GRAY);
		setBorder(BorderFactory.createLineBorder(Color.GRAY,10));
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER));
		top.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		top.add(new JLabel("Transform: "));
		top.add(transformSelect);
		add(display,BorderLayout.CENTER);
		add(top,BorderLayout.NORTH);
	}


	public static void main(String[] args) throws IOException {
		JFrame window = new JFrame("2D Transforms");
		window.setContentPane(new Transforms2D());
		window.pack();
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation( (screen.width - window.getWidth())/2, (screen.height - window.getHeight())/2 );
		window.setVisible(true);
	}

}