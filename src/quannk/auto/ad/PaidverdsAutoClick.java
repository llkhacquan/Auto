package quannk.auto.ad;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import quannk.auto.AutoClick;

public class PaidverdsAutoClick extends AutoClick {
	public static void main(String[] args) throws AWTException {
		DigAdzAutoClick theAuto = new DigAdzAutoClick();
		Color paidvertsColor = new Color(50, 199, 95);

		boolean isInCompany = theAuto.height == 1024;
		Point p;

		// select browser
		theAuto.delay(1000);
		theAuto.clickMouse(50, 400, InputEvent.BUTTON1_MASK);
		// click view-ad
		int count = 0;
		while (count < 200) {
			count++;
			theAuto.pressAKey(KeyEvent.VK_HOME);
			theAuto.delay(500);

			// click view bonus ad
			if (isInCompany) {
				p = findCoordinate(paidvertsColor, 280, 905);
			} else {
				theAuto.pressAKey(KeyEvent.VK_PAGE_DOWN);
				theAuto.delay(500);
				p = findCoordinate(paidvertsColor, 310, 310);
			}
			if (p == null)
				break;
			theAuto.clickMouse(p.x, p.y, InputEvent.BUTTON1_MASK);
			theAuto.waitForWebsite();

			// click 3 text
			theAuto.pressAKey(KeyEvent.VK_END);
			theAuto.delay(450);
			if (isInCompany) {
				theAuto.clickMouse(835, 200, InputEvent.BUTTON1_MASK);
				theAuto.clickMouse(835, 320, InputEvent.BUTTON1_MASK);
				theAuto.clickMouse(835, 440, InputEvent.BUTTON1_MASK);
			} else {
				for (int i = 0; i < 5; i++) {
					theAuto.pressAKey(KeyEvent.VK_UP);
				}
				theAuto.delay(100);
				theAuto.clickMouse(880, 150, InputEvent.BUTTON1_MASK);
				theAuto.clickMouse(880, 270, InputEvent.BUTTON1_MASK);
				theAuto.clickMouse(880, 390, InputEvent.BUTTON1_MASK);
			}
			theAuto.delay(100);
			// click view add
			theAuto.clickMouse(600, theAuto.height - 210, InputEvent.BUTTON1_MASK);

			// click captcha
			theAuto.delay(5000);
			theAuto.clickMouse(theAuto.width - 450, 105, InputEvent.BUTTON1_MASK);
			Toolkit.getDefaultToolkit().beep();
			while (true) { // wait 30s
				theAuto.delay(500);
				Color c = theAuto.robot.getPixelColor(theAuto.width - 50, 100);
				if (isEqualColor(c, new Color(0, 0, 0)))
					break;
			}
			// click confirm
			theAuto.clickMouse(theAuto.width - 50, 100, InputEvent.BUTTON1_MASK);
			// wait close ad show and click it
			theAuto.robot.mouseMove(theAuto.width - 400, 325);
			while (true) {
				theAuto.delay(500);
				Color c = theAuto.robot.getPixelColor(theAuto.width - 65, 125);
				if (isEqualColor(c, new Color(0, 0, 0)))
					break;
			}
			theAuto.clickMouse(theAuto.width - 65, 125, InputEvent.BUTTON1_MASK);

			// click view more ads
			theAuto.delay(300);
			theAuto.pressAKey(KeyEvent.VK_END);
			theAuto.delay(500);
			theAuto.clickMouse(560, theAuto.height - 395, InputEvent.BUTTON1_MASK);
			// wait the website refresh
			theAuto.waitForWebsite();
		}
	}
}