package quannk.auto.dvd;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Huong dan su dung: mo tat ca cac file muon convert sang UTF-8 without BOM bang notepad++
 * Phong to maximum man hinh notepad++ tren man hinh HD 1366*768
 * Chon so luong file can chinh o vong for trong main.
 * Run
 * @author wind
 *
 */
public class AutoConvertToUTF8 extends quannk.auto.AutoClick {
	public AutoConvertToUTF8() {
		super();
	}

	public static void main(String[] args) {
		AutoConvertToUTF8 theAuto = new AutoConvertToUTF8();
		theAuto.clickMouse(200, 200, InputEvent.BUTTON1_MASK);
		for (int i = 0; i < 28; i++) {
			theAuto.pressAKey(KeyEvent.VK_ALT);
			theAuto.pressAKey(KeyEvent.VK_N);
			theAuto.clickMouse(237, 210, InputEvent.BUTTON1_MASK);
			theAuto.press2Key(KeyEvent.VK_CONTROL, KeyEvent.VK_S);
			theAuto.press2Key(KeyEvent.VK_CONTROL, KeyEvent.VK_W);
		}
	}

}
