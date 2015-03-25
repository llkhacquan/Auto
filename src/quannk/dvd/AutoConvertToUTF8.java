package quannk.dvd;


import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import quannk.ad.AutoClick;

public class AutoConvertToUTF8 extends AutoClick {
	public AutoConvertToUTF8() {
		super();
	}

	public static void main(String[] args) {
		AutoConvertToUTF8 theAuto = new AutoConvertToUTF8();
		theAuto.clickMouse(200, 300, InputEvent.BUTTON1_MASK);
		for (int i = 0; i < 24; i++) {
			theAuto.pressAKey(KeyEvent.VK_ALT);
			theAuto.pressAKey(KeyEvent.VK_N);
			theAuto.clickMouse(237, 100, InputEvent.BUTTON1_MASK);
			theAuto.press2Key(KeyEvent.VK_CONTROL, KeyEvent.VK_S);
			theAuto.press2Key(KeyEvent.VK_CONTROL, KeyEvent.VK_W);
		}
	}

}
