package quannk.money.adautoclick;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class PaidverdsAutoClick extends AutoClick{
	public static void main(String[] args) throws AWTException {
		DigAdzAutoClick theAuto = new DigAdzAutoClick();
		Color view_ads_color = new Color(50, 199, 95);

		// click view-ad
		int count = 0;
		while (count < 200) {
			count++;
			theAuto.delay(2000);
			theAuto.clickMouse(5, 400, InputEvent.BUTTON1_MASK);

			// theAuto.keyPress(KeyEvent.VK_END);
			// theAuto.keyRelease(KeyEvent.VK_END);
			theAuto.pressAKey(KeyEvent.VK_HOME);
			theAuto.delay(500);
			theAuto.pressAKey(KeyEvent.VK_PAGE_DOWN);
			theAuto.delay(500);
			Point p = findCoordinate(view_ads_color, 310, 290);
			if (p == null)
				break;
			theAuto.clickMouse(p.x, p.y, InputEvent.BUTTON1_MASK);

			// click 3 text
			theAuto.delay(2500);
			theAuto.pressAKey(KeyEvent.VK_END);
			theAuto.delay(450);
			for (int i = 0; i < 5; i++) {
				theAuto.pressAKey(KeyEvent.VK_UP);
			}
			theAuto.delay(600);
			theAuto.clickMouse(880, 230, InputEvent.BUTTON1_MASK);
			theAuto.clickMouse(880, 350, InputEvent.BUTTON1_MASK);
			theAuto.clickMouse(880, 470, InputEvent.BUTTON1_MASK);
			theAuto.delay(100);
			// click view add
			theAuto.clickMouse(600, 640, InputEvent.BUTTON1_MASK);

			// click captcha
			theAuto.delay(5000);
			Toolkit.getDefaultToolkit().beep();
			theAuto.clickMouse(915, 115, InputEvent.BUTTON1_MASK);
			theAuto.delay(2000);

			theAuto.delay(26000);
			// click confirm
			theAuto.clickMouse(1250, 110, InputEvent.BUTTON1_MASK);

			// click close ads
			theAuto.delay(2000);
			theAuto.clickMouse(1250, 135, InputEvent.BUTTON1_MASK);

			// click view more ads
			theAuto.delay(500);
			theAuto.pressAKey(KeyEvent.VK_END);

			theAuto.delay(500);
			theAuto.clickMouse(1250, 135, InputEvent.BUTTON1_MASK);
			theAuto.delay(500);
			theAuto.clickMouse(560, 375, InputEvent.BUTTON1_MASK);
			theAuto.delay(500);
		}
	}
}
