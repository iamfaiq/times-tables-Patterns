/*
 * Author: Faiq (iamfaiq)
 * Created in: May 2020
 */

package timesTablesPatterns;

public class Controller {
	private Model model;
	private View view;

	public Controller(Model m, View v) {
		model = m;
		view = v;
		// initController();
	}

	public void initController() {
		view.getTableSlider().addChangeListener(e -> tableChanged());
		view.getPointsSlider().addChangeListener(e -> numPointsChanged());

	}

	private void tableChanged() {
		int value = view.getTableSlider().getValue();
		model.setTabOf(value);
		view.setTableLabel(value);
		view.getDrawing().repaint();
	}

	private void numPointsChanged() {
		int value = view.getPointsSlider().getValue();
		model.setInputPoints(value);
		view.setPointsLabel(value);
		view.getDrawing().repaint();
	}
}
