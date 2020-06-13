/*
 * Author: Faiq (iamfaiq)
 * Created in: May 2020
 */

package timesTablesPatterns;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model m = new Model(200, 2);// inputPoint, tableOf; 750, 10
		View v = new View(m);
		Controller c = new Controller(m, v);
		c.initController();
	}

}
