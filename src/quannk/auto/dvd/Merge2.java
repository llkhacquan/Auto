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
  private static final int _100 = 150;
  private static final int _200 = 300;
  private static final int _500 = 700;
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

    for (int iSeason = 1; iSeason <= 1; iSeason++) {
      // Create new folder to contain new subtitles (done)
      String folderPath = "C:\\Users\\wind\\Dropbox\\HQ productions\\final sub\\Twilight";
      File subFolder = new File(folderPath);

      File[] files = subFolder.listFiles();
      for (File f : files) {
        String filePath = f.getAbsolutePath();
        String fileName = f.getName();
        if (!fileName.contains(".en.srt"))
          continue;

        outputFileName = fileName.replace("en.srt", "srt");
        if (new File(System.getProperty("user.home") + "/Downloads/" + outputFileName).exists()) {
          continue;
        }
        enFile = f;
        viFile = new File(filePath.replace("en.srt", "vi.srt"));
        Merge(enFile, viFile, outputFileName);
      }
    }
  }

  public static void Merge(File enFile, File viFile, String outputFileName) {
    if (!enFile.exists() || !viFile.exists())
      return;
    {// Add file en
      theAuto.clickMouse(600, 350, InputEvent.BUTTON1_MASK);
      theAuto.delay(_500);
      AutoClick.setClipboardContent(enFile.getPath());
      theAuto.press2Key(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
      theAuto.pressAKey(KeyEvent.VK_ENTER);
      theAuto.delay(_200);

      // chose codec
      theAuto.clickMouse(600, 380, InputEvent.BUTTON1_MASK);
      theAuto.pressAKey(KeyEvent.VK_HOME);
      theAuto.delay(_100);
      theAuto.pressAKey(KeyEvent.VK_ENTER);
      theAuto.delay(_200);
      // chose color
      theAuto.clickMouse(500, 398, InputEvent.BUTTON1_MASK);
      theAuto.pressAKey(KeyEvent.VK_END); // yellow
      theAuto.pressAKey(KeyEvent.VK_ENTER);
      theAuto.delay(_200);
      // press Add file
      theAuto.clickMouse(430, 430, InputEvent.BUTTON1_MASK);
    }

    theAuto.waitForWebsite();
    {// Add file vi
      theAuto.clickMouse(600, 350, InputEvent.BUTTON1_MASK);
      theAuto.delay(_200);
      AutoClick.setClipboardContent(viFile.getPath());
      theAuto.press2Key(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
      theAuto.pressAKey(KeyEvent.VK_ENTER);
      theAuto.delay(_200);

      // chose color
      theAuto.clickMouse(500, 398, InputEvent.BUTTON1_MASK);
      theAuto.pressAKey(KeyEvent.VK_HOME); // default
      theAuto.pressAKey(KeyEvent.VK_ENTER);
      // press Add file
      theAuto.delay(_200);
      theAuto.clickMouse(430, 430, InputEvent.BUTTON1_MASK);
      theAuto.delay(_200);
    }
    theAuto.waitForWebsite();

    // Set output file name
    theAuto.pressAKey(KeyEvent.VK_END);
    theAuto.delay(_500);
    theAuto.clickMouse(600, 370, InputEvent.BUTTON1_MASK);
    theAuto.press2Key(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
    AutoClick.setClipboardContent(outputFileName);
    theAuto.press2Key(KeyEvent.VK_CONTROL, KeyEvent.VK_V);

    // click download
    theAuto.clickMouse(500, 490, InputEvent.BUTTON1_MASK);
    theAuto.waitForWebsite();

    theAuto.pressAKey(KeyEvent.VK_HOME);
    theAuto.delay(_200);
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
