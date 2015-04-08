package quannk.ad;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class DigAdzAutoClick extends AutoClick {
	public DigAdzAutoClick() {
		super();
	}

	public static void main(String args[]) throws AWTException, HeadlessException, UnsupportedFlavorException,
			IOException {
		DigAdzAutoClick theAuto = new DigAdzAutoClick();
		// click quality ad

		theAuto.delay(1500);
		Color quality_adsColor = new Color(45, 204, 112);
		for (int times = 0; times < 20; times++) {
			theAuto.clickMouse(5, 400, InputEvent.BUTTON1_MASK);
			theAuto.delay(100);
			theAuto.pressAKey(KeyEvent.VK_END);
			theAuto.delay(500);
			Point p = findCoordinate(quality_adsColor, 210, 350);
			if (p == null)
				return;
			theAuto.clickMouse(p.x, p.y, InputEvent.BUTTON1_MASK);

			// click view add
			theAuto.waitForWebsite();
			theAuto.pressAKey(KeyEvent.VK_END);
			while (true) {
				theAuto.delay(500);
				p = findCoordinate(quality_adsColor, theAuto.width - (1280 - 900), theAuto.height - (1024 - 850));
				if (p != null)
					break;
			}
			theAuto.clickMouse(p.x, p.y, InputEvent.BUTTON1_MASK);

			// wait for captcha image
			theAuto.delay(5000);
			Color[] colors = new Color[100];
			for (int i = 0; i < colors.length; i++) {
				colors[i] = theAuto.robot.getPixelColor(360 + i, 100);
			}
			out: while (true) {
				theAuto.delay(500);
				for (int i = 0; i < colors.length; i++) {
					if (!AutoClick.isEqualColor(colors[i], theAuto.robot.getPixelColor(360 + i, 100)))
						break out;
				}
			}

			int correctCapcha = -1;

			int[] xHome = new int[] { 325, 385, 445, 520, 575 };
			int[] xCompany = new int[] { 300, 360, 430, 485, 540 };
			int[] x;
			if (theAuto.width == 1280)
				x = xCompany;
			else
				x = xHome;
			String[] url = new String[5];
			for (int i = 0; i < 5; i++) {
				theAuto.clickMouse(x[i], 100, InputEvent.BUTTON3_MASK);
				theAuto.delay(300);
				theAuto.pressAKey(KeyEvent.VK_O);
				theAuto.delay(150);
				url[i] = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
				System.out.println(url[i]);
			}
			outerloop: for (int i = 0; i < 5; i++) {
				for (int j = 4; j > i; j--) {
					if (url[i].compareTo(url[j]) == 0) {
						correctCapcha = j;
						break outerloop;
					}
				}
			}
			// click the correct captcha
			theAuto.clickMouse(x[correctCapcha], 100, InputEvent.BUTTON1_MASK);
			while (true) {
				theAuto.delay(500);
				p = findCoordinate(quality_adsColor, 450, 120);
				if (p != null)
					break;
			}
			// click the close window button
			theAuto.clickMouse(460, 130, InputEvent.BUTTON1_MASK);
			// wait to comeback to main
			theAuto.waitForWebsite();
		}
	}
}