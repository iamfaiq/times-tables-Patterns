/*
 * Author: Faiq (iamfaiq)
 * Created in: May 2020
 */

package timesTablesPatterns;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Line2D;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import timesTablesPatterns.Model.line;

public class View {
	private JFrame frame;
	private JPanel controllersPanel;
	private DrawingArea drawingPanel;
	private JSlider tableSlider;
	private JLabel tableLabel;
	private JSlider pointsSlider;
	private JLabel pointsLabel;
	private Model currentModel;

	// list<line> tLines = Model.getModel();

	public View(Model m) {
		currentModel = m;
		frame = new JFrame("Table Patterns");
		frame.setSize(650, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setLayout(new BorderLayout());
		frame.setLayout(new BorderLayout());

		controllersPanel = new JPanel(new GridLayout(2, 2, 10, 0));// row, col, horizontal space between cells, vertical
		// space

		tableSlider = new JSlider(JSlider.HORIZONTAL, 2, 4096, m.getTabOf()); // sliderType, minValue, maxValue, //
																				// startsAt
		tableSlider.setMajorTickSpacing(500); // a divider appears after 500 points
		tableSlider.setPaintTicks(true); // ensures tick marks appear below the slider
		controllersPanel.add(tableSlider);

		pointsSlider = new JSlider(JSlider.HORIZONTAL, 10, 2500, m.getInputPoints());
		pointsSlider.setMajorTickSpacing(500);
		pointsSlider.setPaintTicks(true);
		controllersPanel.add(pointsSlider);

		tableLabel = new JLabel();
		tableLabel.setText("Table of: " + m.getTabOf());
		controllersPanel.add(tableLabel);

		pointsLabel = new JLabel();
		pointsLabel.setText("Total points around circle: " + m.getInputPoints());
		controllersPanel.add(pointsLabel);

		// init drawing component:
		drawingPanel = new DrawingArea();

		frame.add(drawingPanel, BorderLayout.CENTER);
		frame.add(controllersPanel, BorderLayout.SOUTH);

		frame.setVisible(true);

	}

	public JSlider getTableSlider() {
		return tableSlider;
	}

	public JSlider getPointsSlider() {
		return pointsSlider;
	}

	public void setTableLabel(int value) {
		tableLabel.setText("Table of: " + value);
		drawingPanel.setTableOf(value);
		drawingPanel.repaint();
	}

	public JLabel getTableLabel() {
		return tableLabel;
	}

	public void setPointsLabel(int value) {
		pointsLabel.setText("Total points around circle: " + value); // update the label value
		drawingPanel.setInputPoint(value); // update the value in the Drawing class
		drawingPanel.repaint(); // repaint the panel of the Drawing class
	}

	public JLabel getPointsLabel() {
		return pointsLabel;
	}

	public JPanel getDrawing() {
		return drawingPanel;
	}

	@SuppressWarnings("serial")
	class DrawingArea extends JPanel {
		int dInputPoints = currentModel.getInputPoints();
		int dTableOf = currentModel.getTabOf();

		// no parameter constructor of the class
		DrawingArea() {
			setSize(650, 650);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.BLACK);

			// getting every point/line on the circle's boundary according to the
			// new/updated Model
			List<line> linesToDraw = currentModel.getModel();

			int totalLines = linesToDraw.size(); // using this because of one less line than total points and to improve
													// readability of the code
			// drawing the cardiod/object
			for (int i = 0; i < totalLines; i++) {
				line t = linesToDraw.get(i);
				g2.draw(new Line2D.Double(t.getP1(), t.getP2()));
			}
		}

		public void setInputPoint(int tPoints) {
			dInputPoints = tPoints;
		}

		public void setTableOf(int tOf) {
			dTableOf = tOf;
		}
	}
}
