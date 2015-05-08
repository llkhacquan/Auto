package quannk.auto.dvd;

import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import quannk.auto.AutoClick;

public class Merge2 extends AutoClick {
  static Merge2 theAuto = new Merge2();

  public Merge2() {
    super();
  }

  public static void main(String[] args) throws FileNotFoundException, IOException {
    theAuto.delay(1000);
    theAuto.clickMouse(10, 100, MouseEvent.BUTTON1_MASK);
    theAuto.pressAKey(KeyEvent.VK_HOME);
    File enFile, viFile;
    String outputFileName = null;

    for (int iSeason = 1; iSeason <= 9; iSeason++) {
      // Create new folder to contain new subtitles (done)
      File subFolder = new File("C:\\Users\\wind\\Dropbox\\HQ productions\\Subtiles\\Subs\\HIMYM\\HIMYM " + iSeason);
      File[] files = subFolder.listFiles();
      for (File f : files) {
        String filePath = f.getAbsolutePath();
        String fileName = f.getName();
        if (!fileName.contains(".en.srt"))
          continue;
        if (new File("C:/Users/wind/Downloads/" + fileName.replace("en.srt", "me.srt")).exists()) {
          continue;
        }
        enFile = f;
        viFile = new File(filePath.replace("en.srt", "vi.srt"));
        outputFileName = fileName.replace("en.srt", "me.srt");
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
      theAuto.delay(200);

      // chose codec
      theAuto.clickMouse(600, 380, InputEvent.BUTTON1_MASK);
      theAuto.pressAKey(KeyEvent.VK_HOME);
      theAuto.delay(10);
      theAuto.pressAKey(KeyEvent.VK_ENTER);
      theAuto.delay(200);
      // chose color
      theAuto.clickMouse(500, 398, InputEvent.BUTTON1_MASK);
      theAuto.pressAKey(KeyEvent.VK_END); // yellow
      theAuto.pressAKey(KeyEvent.VK_ENTER);
      theAuto.delay(200);
      // press Add file
      theAuto.clickMouse(430, 430, InputEvent.BUTTON1_MASK);
    }

    theAuto.waitForWebsite();
    {// Add file vietnam
      theAuto.clickMouse(600, 350, InputEvent.BUTTON1_MASK);
      theAuto.delay(200);
      AutoClick.setClipboardContent(viFile.getPath());
      theAuto.press2Key(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
      theAuto.pressAKey(KeyEvent.VK_ENTER);
      theAuto.delay(200);

      // chose color
      theAuto.clickMouse(500, 398, InputEvent.BUTTON1_MASK);
      theAuto.pressAKey(KeyEvent.VK_HOME); // default
      theAuto.pressAKey(KeyEvent.VK_ENTER);
      // press Add file
      theAuto.delay(200);
      theAuto.clickMouse(430, 430, InputEvent.BUTTON1_MASK);
      theAuto.delay(200);
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

    theAuto.pressAKey(KeyEvent.VK_HOME);
    theAuto.delay(200);
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
