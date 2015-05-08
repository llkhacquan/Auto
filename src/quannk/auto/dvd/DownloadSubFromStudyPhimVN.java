package quannk.auto.dvd;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;

import quannk.auto.AutoClick;

public class DownloadSubFromStudyPhimVN extends AutoClick {

  public static class Season implements ISeason {
    int startEp;
    int endEp;
    int season;

    public String getURL(int ep) {
      assert (ep >= startEp && ep <= endEp);

      return "http://www.studyphim.vn/movies/friends-season-" + season + "/play?episode=" + ep;
    }

    public String getDownloadFolder() {
      return "C:\\Users\\wind\\Desktop\\Subs\\Friends\\Friends " + season;
    }

    public void run(int season, int start, int end) {
      this.season = season;
      this.startEp = start;
      this.endEp = end;
      for (int iEp = startEp; iEp <= endEp; iEp++) {
        // go to website
        theAuto.clickMouse(200, 42, InputEvent.BUTTON1_MASK);
        setClipboardContent(getURL(iEp));
        theAuto.delay(500);
        theAuto.press2Key(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
        theAuto.pressAKey(KeyEvent.VK_ENTER);
        theAuto.delay(500);
        // wait for srt to load
        theAuto.waitForPointChange(510, 417);
        theAuto.delay(500);

        // select 1nd srt and download it
        theAuto.clickMouse(100, 333, InputEvent.BUTTON3_MASK);
        theAuto.delay(150);
        theAuto.pressAKey(KeyEvent.VK_O);
        theAuto.waitForWebsite();
        theAuto.press2Key(KeyEvent.VK_CONTROL, KeyEvent.VK_S);
        theAuto.waitForPointChange(110, 13);
        theAuto.pressAKey(KeyEvent.VK_HOME);
        theAuto.delay(100);
        setClipboardContent(getDownloadFolder() + "\\");
        theAuto.press2Key(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
        theAuto.delay(100);
        theAuto.pressAKey(KeyEvent.VK_ENTER);
        theAuto.delay(100);
        theAuto.press2Key(KeyEvent.VK_CONTROL, KeyEvent.VK_W);
        theAuto.delay(500);

        // select 2nd srt and download it
        theAuto.clickMouse(100, 377, InputEvent.BUTTON3_MASK);
        theAuto.delay(150);
        theAuto.pressAKey(KeyEvent.VK_O);
        theAuto.waitForWebsite();
        theAuto.press2Key(KeyEvent.VK_CONTROL, KeyEvent.VK_S);
        theAuto.waitForPointChange(110, 13);
        theAuto.pressAKey(KeyEvent.VK_HOME);
        theAuto.delay(100);
        setClipboardContent(getDownloadFolder() + "\\");
        theAuto.press2Key(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
        theAuto.delay(100);
        theAuto.pressAKey(KeyEvent.VK_ENTER);
        theAuto.delay(100);
        theAuto.press2Key(KeyEvent.VK_CONTROL, KeyEvent.VK_W);
        theAuto.delay(500);
      }
    }

  }

  static DownloadSubFromStudyPhimVN theAuto = new DownloadSubFromStudyPhimVN();

  public DownloadSubFromStudyPhimVN() {
    super();
  }

  public static void main(String... args) {
    theAuto.delay(1000);
    theAuto.clickMouse(5, 100, MouseEvent.BUTTON1_MASK);

    Season ss = new DownloadSubFromStudyPhimVN.Season();
    ss.run(10, 1, 17);
  }
}

interface ISeason {
  String getURL(int ep);

  String getDownloadFolder();
}
