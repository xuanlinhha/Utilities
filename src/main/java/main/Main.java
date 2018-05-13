package main;

import java.awt.AWTException;
import java.awt.Point;

import pixelcolor.RangeGetter;

public class Main {
	public static void main(String[] args) {
		// SVGCreator.create("/Users/xuanlinhha/Documents/CC/svg");
		// DictionaryExtractor.extract("/Users/xuanlinhha/Documents/CC");
		try {
			Thread.sleep(2000);
			RangeGetter.getRange(new Point(77, 28), new Point(77, 712));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}
}
