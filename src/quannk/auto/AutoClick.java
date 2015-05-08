package quannk.auto;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.IOException;

// this should be run on google chrome, bookmark bar is hiden

public class AutoClick {
  public Robot robot = null;
  public static Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
  public int width = -1;
  public int height = -1;

  public AutoClick() {
    try {
      robot = new Robot();
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      width = (int) screenSize.getWidth();
      height = (int) screenSize.getHeight();
    } catch (AWTException e) {
      e.printStackTrace();
    }
  }

  public void waitForPointChange(int x, int y) {
    Color lastColor = robot.getPixelColor(x, y), currentColor;
    while (true) {
      robot.delay(1000);
      currentColor = robot.getPixelColor(x, y);
      if (currentColor.getRGB() != lastColor.getRGB()) {
        break;
      } else {
        lastColor = currentColor;
      }
    }
  }

  public void waitForWebsite() {
    while (true) {
      robot.delay(1000);
      Color c = robot.getPixelColor(75, 42);
      if (!AutoClick.isEqualColor(c, new Color(110, 110, 110))) {
        break;
      }
    }
  }

  public void delay(int x) {
    assert (robot != null);
    robot.delay(x);
  }

  public void delay() {
    delay(50);
  }

  public void pressAKey(int keycode) {
    assert (robot != null);
    robot.keyPress(keycode);
    robot.keyRelease(keycode);
  }

  public void press2Key(int keycode, int keycode2) {
    assert (robot != null);
    robot.keyPress(keycode);
    robot.delay(10);
    pressAKey(keycode2);
    robot.keyRelease(keycode);
  }

  public void clickMouse(int x, int y, int mouseCode) {
    assert (robot != null);
    robot.mouseMove(x, y);
    robot.delay(50);
    clickMouse(mouseCode);
  }

  public void clickMouse(int mouse) {
    assert (robot != null);
    robot.mousePress(mouse);
    robot.mouseRelease(mouse);
    robot.delay(50);
  }

  public static String getClipboardContent(){
    try {
      return (String) clip.getData(DataFlavor.stringFlavor);
    } catch (UnsupportedFlavorException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  public static void setClipboardContent(String input) {
    StringSelection selection = new StringSelection(input);
    clip.setContents(selection, selection);
  }

  public static Point findCoordinate(Color color, int x, int y) throws HeadlessException, AWTException {
    BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
    return findCoordinate(image, color, x, y);
  }

  public static Point findCoordinate(BufferedImage image, Color color, int x, int y) {
    int percent = 3;
    int width = image.getWidth();
    int height = image.getHeight();
    int minX = Math.max(0, x - width / percent);
    int maxX = Math.min(width - 1, x + width / percent);
    int minY = Math.max(0, y - height / percent);
    int maxY = Math.min(height - 1, y + height / percent);
    return findCoordinate(image, color, minX, maxX, minY, maxY);
  }

  public static Point findCoordinate(BufferedImage image, Color color, int minX, int maxX, int minY, int maxY) {
    int x, y;
    int avarageX = (maxX + minX) / 2;
    int avarageY = (maxY + minY) / 2;
    for (x = avarageX; x >= minX; x -= 5) {
      for (y = avarageY; y >= minY; y -= 5) {
        Color c = new Color(image.getRGB(x, y));
        if (isEqualColor(c, color)) {
          System.out.println("Found coordinate x = " + x + " y = " + y);
          return new Point(x - 3, y - 3);
        }
      }
      for (y = avarageY; y <= maxY; y += 5) {
        Color c = new Color(image.getRGB(x, y));
        if (c.equals(color)) {
          System.out.println("Found coordinate x = " + x + " y = " + y);
          return new Point(x - 3, y + 3);
        }
      }
    }
    for (x = avarageX; x <= maxX; x += 5) {
      for (y = avarageY; y >= minY; y -= 5) {
        Color c = new Color(image.getRGB(x, y));
        if (isEqualColor(c, color)) {
          System.out.println("Found coordinate x = " + x + " y = " + y);
          return new Point(x + 3, y - 3);
        }
      }
      for (y = avarageY; y <= maxY; y += 5) {
        Color c = new Color(image.getRGB(x, y));
        if (isEqualColor(c, color)) {
          System.out.println("Found coordinate x = " + x + " y = " + y);
          return new Point(x + 3, y + 3);
        }
      }
    }
    return null;
  }

  public static boolean isEqualColor(Color a, Color b) {
    return a.getRed() == b.getRed() && a.getBlue() == b.getBlue() && a.getGreen() == b.getGreen();
  }
}
