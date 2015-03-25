package quannk.dvd;

import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import quannk.ad.AutoClick;

public class Merger extends AutoClick {
	static Merger theAuto = new Merger();

	public Merger() {
		super();
	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		theAuto.delay(1000);

		for (int iSeason = 1; iSeason <= 10; iSeason++) {
			String number = CopySRTFiles.getStringFromNumber(iSeason);
			// Create new folder to contain new subtitles (done)
			File subFolder = new File("F:/Friends-sub/ss" + number);
			File[] films = subFolder.listFiles();
			for (int iFilm = 1; iFilm <= 25; iFilm++) {
				String outputFileName = "merged_ss_"
						+ CopySRTFiles.getStringFromNumber(iSeason) + "_"
						+ CopySRTFiles.getStringFromNumber(iFilm) + ".srt";
				File out = new File("C:/Users/wind/Downloads/" + outputFileName);
				if (out.exists()) {
					boolean found = false;
					try (BufferedReader br = new BufferedReader(new FileReader(
							"C:/Users/wind/Downloads/" + outputFileName))) {
						String line;
						while ((line = br.readLine()) != null) {
							if (line.contains("font color=")) {
								found = true;
								break;
							}
							if (line.contains("ô")) {
								found = true;
								break;
							}
							if (line.contains("à")) {
								found = true;
								break;
							}
						}
						if (found)
							continue;
					}
					out.delete();
				}
				int enIndex, viIndex;
				String vi = "SEASON_"
						+ CopySRTFiles.getStringFromNumber(iSeason) + "x"
						+ CopySRTFiles.getStringFromNumber(iFilm) + "_vi_";
				String en = "SEASON_"
						+ CopySRTFiles.getStringFromNumber(iSeason) + "x"
						+ CopySRTFiles.getStringFromNumber(iFilm) + "_en_";
				for (viIndex = 0; viIndex < films.length; viIndex++)
					if (films[viIndex].toString().contains(vi))
						break;
				for (enIndex = 0; enIndex < films.length; enIndex++)
					if (films[enIndex].toString().contains(en))
						break;
				if (viIndex == films.length || enIndex == films.length)
					continue;

				Merge(films[enIndex], films[viIndex], outputFileName);
			}
		}
	}

	public static void Merge(File enFile, File viFile, String outputFileName) {
		{// Add file english
			theAuto.clickMouse(500, 350, InputEvent.BUTTON1_MASK);
			theAuto.delay(1000);
			AutoClick.setClipboardContent(enFile.getPath());
			theAuto.press2Key(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
			theAuto.pressAKey(KeyEvent.VK_ENTER);
			theAuto.delay(1000);

			// chose codec
			theAuto.clickMouse(500, 380, InputEvent.BUTTON1_MASK);
			theAuto.pressAKey(KeyEvent.VK_HOME);
			theAuto.pressAKey(KeyEvent.VK_ENTER);
			theAuto.delay(1000);
			// chose color
			theAuto.clickMouse(390, 398, InputEvent.BUTTON1_MASK);
			theAuto.pressAKey(KeyEvent.VK_END); // yellow
			theAuto.pressAKey(KeyEvent.VK_ENTER);
			theAuto.delay(1000);
			// press Add file
			theAuto.clickMouse(330, 430, InputEvent.BUTTON1_MASK);
		}

		theAuto.waitForWebsite();
		{// Add file vietnam
			theAuto.clickMouse(500, 350, InputEvent.BUTTON1_MASK);
			theAuto.delay(1000);
			AutoClick.setClipboardContent(viFile.getPath());
			theAuto.press2Key(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
			theAuto.pressAKey(KeyEvent.VK_ENTER);
			theAuto.delay(1000);

			// chose codec
			theAuto.clickMouse(500, 380, InputEvent.BUTTON1_MASK);
			theAuto.pressAKey(KeyEvent.VK_HOME);
			theAuto.pressAKey(KeyEvent.VK_ENTER);
			theAuto.delay(1000);
			// chose color
			theAuto.clickMouse(390, 398, InputEvent.BUTTON1_MASK);
			theAuto.pressAKey(KeyEvent.VK_HOME); // default
			theAuto.pressAKey(KeyEvent.VK_ENTER);
			// press Add file
			theAuto.delay(1000);
			theAuto.clickMouse(330, 430, InputEvent.BUTTON1_MASK);
			theAuto.delay(1000);
		}
		theAuto.waitForWebsite();

		// Set output file name
		theAuto.pressAKey(KeyEvent.VK_END);
		theAuto.delay(1000);
		theAuto.clickMouse(500, 370, InputEvent.BUTTON1_MASK);
		theAuto.press2Key(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
		AutoClick.setClipboardContent(outputFileName);
		theAuto.press2Key(KeyEvent.VK_CONTROL, KeyEvent.VK_V);

		// click download
		theAuto.clickMouse(400, 490, InputEvent.BUTTON1_MASK);
		theAuto.waitForWebsite();

		theAuto.pressAKey(KeyEvent.VK_F5);
		theAuto.waitForWebsite();
	}

	public void waitForWebsite() {
		while (true) {
			theAuto.delay(1000);
			Color c = theAuto.robot.getPixelColor(75, 42);
			if (!AutoClick.isEqualColor(c, new Color(110, 110, 110)))
				break;
		}
	}
}
