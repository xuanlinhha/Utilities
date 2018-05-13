package pixelcolor;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;

public class RangeGetter {
	//77 28
	//77 712
	public static void getRange(Point p1, Point p2) throws AWTException {
		Robot robot = new Robot();
		for (int i = p1.y; i <= p2.y; i++) {
			System.out.println(robot.getPixelColor(p1.x, i));
		}
	}
}
