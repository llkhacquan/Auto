package quannk.auto.dvd;

import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import quannk.auto.AutoClick;

public class Merger extends AutoClick {
	static Merger theAuto = new Merger();

	public Merger() {
		super();
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		theAuto.delay(500);
		theAuto.clickMouse(100, 10, MouseEvent.BUTTON1_MASK);
		File enFile, viFile;
		String outputFileName = null;

		for (int iSeason = 1; iSeason <= 4; iSeason++) {
			// Create new folder to contain new subtitles (done)
			File subFolder = new File("C:\\Users\\wind\\Desktop\\Subs\\PB SS" + iSeason);
			File[] films = subFolder.listFiles();
			for (File film : films) {
				String filePath = film.getAbsolutePath();
				String fileName = film.getName();
				fileName = fileName.substring(0, fileName.length() - 4);
				if (!filePath.contains(".mkv") && !filePath.contains(".mp4") && !filePath.contains(".avi"))
					continue;
				if (new File("C:/Users/wind/Downloads/" + fileName + ".Me.srt").exists()) {
					continue;
				}
				enFile = new File(filePath.substring(0, filePath.length() - 4) + ".En.srt");
				viFile = new File(filePath.substring(0, filePath.length() - 4) + ".Vi.srt");
				outputFileName = fileName + ".Me.srt";
				Merge(enFile, viFile, outputFileName);
			}
		}
	}

	public static void Merge(File enFile, File viFile, String outputFileName) {
		{// Add file english
			if (!enFile.exists() || !viFile.exists())
				return;
			theAuto.clickMouse(600, 350, InputEvent.BUTTON1_MASK);
			theAuto.delay(500);
			AutoClick.setClipboardContent(enFile.getPath());
			theAuto.press2Key(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
			theAuto.pressAKey(KeyEvent.VK_ENTER);
			theAuto.delay(500);

			// chose codec
			theAuto.clickMouse(600, 380, InputEvent.BUTTON1_MASK);
			theAuto.pressAKey(KeyEvent.VK_HOME);
			theAuto.pressAKey(KeyEvent.VK_ENTER);
			theAuto.delay(500);
			// chose color
			theAuto.clickMouse(500, 398, InputEvent.BUTTON1_MASK);
			theAuto.pressAKey(KeyEvent.VK_END); // yellow
			theAuto.pressAKey(KeyEvent.VK_ENTER);
			theAuto.delay(500);
			// press Add file
			theAuto.clickMouse(430, 430, InputEvent.BUTTON1_MASK);
		}

		theAuto.waitForWebsite();
		{// Add file vietnam
			theAuto.clickMouse(600, 350, InputEvent.BUTTON1_MASK);
			theAuto.delay(500);
			AutoClick.setClipboardContent(viFile.getPath());
			theAuto.press2Key(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
			theAuto.pressAKey(KeyEvent.VK_ENTER);
			theAuto.delay(500);

			// chose codec
			theAuto.clickMouse(500, 380, InputEvent.BUTTON1_MASK);
			theAuto.pressAKey(KeyEvent.VK_HOME);
			theAuto.pressAKey(KeyEvent.VK_ENTER);
			theAuto.delay(500);
			// chose color
			theAuto.clickMouse(500, 398, InputEvent.BUTTON1_MASK);
			theAuto.pressAKey(KeyEvent.VK_HOME); // default
			theAuto.pressAKey(KeyEvent.VK_ENTER);
			// press Add file
			theAuto.delay(500);
			theAuto.clickMouse(430, 430, InputEvent.BUTTON1_MASK);
			theAuto.delay(500);
		}
		theAuto.waitForWebsite();

		// Set output file name
		theAuto.pressAKey(KeyEvent.VK_END);
		theAuto.delay(500);
		theAuto.clickMouse(600, 370, InputEvent.BUTTON1_MASK);
		theAuto.press2Key(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
		AutoClick.setClipboardContent(outputFileName);
		theAuto.press2Key(KeyEvent.VK_CONTROL, KeyEvent.VK_V);

		// click download
		theAuto.clickMouse(500, 490, InputEvent.BUTTON1_MASK);
		theAuto.waitForWebsite();

		theAuto.pressAKey(KeyEvent.VK_F5);
		theAuto.waitForWebsite();
	}

	public void waitForWebsite() {
		while (true) {
			theAuto.delay(500);
			Color c = theAuto.robot.getPixelColor(75, 42);
			if (!AutoClick.isEqualColor(c, new Color(110, 110, 110)))
				break;
		}
	}
}
