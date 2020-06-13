/*
 * Author: Faiq (iamfaiq)
 * Created in: May 2020
 */

package timesTablesPatterns;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Model {
	private int ipoints;
	private int tableOf;
	private List<line> totalLines;

	public Model(int ipoints, int tableOf) {
		totalLines = new ArrayList<line>();
		this.ipoints = ipoints; // user input 10<=iP<=2500
		this.tableOf = tableOf; // user input 2<=tO<=4096

		this.updateModel();
	}

	public void updateModel() {
		int rad = 300; // user input; but do have to adjust the circle boundary points translation
		// 15<=r<=350
		Point2D[] allCirclePoints = new Point2D[ipoints];
		int a = (ipoints) / 2;// total number of lines

		// to match the points arrangement in the video going:
		int j = 0;

		// generating the points around the circle
		for (int i = (ipoints - 1); i >= 0; i--) {
			// radius*cos(pi/a) since going around 180 and
			// then repeating basically

			// the negative multiplication is reflect the image, to match the one in the
			// video
			double tx = (rad + 5) + (-1) * rad * Math.cos((i + 1) * (Math.PI / a));
			double ty = (rad + 5) + rad * Math.sin((i + 1) * (Math.PI / a));
			allCirclePoints[j] = new Point2D.Double(tx, ty);
			j++;
		}

		// clearing the lines from previous shape, if any
		totalLines.clear();

		// generating lines of the new shape based on given parameters
		for (int i = 1; i < ipoints; i++) {
			int next = (tableOf * i) % ipoints;
			totalLines.add((new line(allCirclePoints[i], allCirclePoints[next])));
		}
	}

	// for debugging
	public void printPoint(Point2D p) {
		System.out.println("(" + p.getX() + ", " + p.getY() + ")");
	}

	/*
	 * get functions to relay the information to the view mainly
	 * 
	 * set functions to update the model with new information from the controller
	 */

	public int getInputPoints() {
		return ipoints;
	}

	public void setInputPoints(int ipoints) {
		this.ipoints = ipoints;
		updateModel();
	}

	public int getTabOf() {
		return tableOf;
	}

	public void setTabOf(int tableOf) {
		this.tableOf = tableOf;
		updateModel();
	}

	public List<line> getModel() {
		return totalLines;
	}

	public class line {

		private Point2D p1;
		private Point2D p2;

		public line(Point2D point1, Point2D point2) {
			p1 = point1;
			p2 = point2;
		}

		public Point2D getP1() {
			return p1;
		}

		public Point2D getP2() {
			return p2;
		}

		public void setP1(Point2D p1) {
			this.p1 = p1;
		}

		public void setP2(Point2D p2) {
			this.p2 = p2;
		}

		// for debugging
		public void printLine() {
			System.out.print("(" + p1.getX() + ", " + p1.getY() + "),   ");
			System.out.println("(" + p2.getX() + ", " + p2.getY() + ")");
		}
	}

}
