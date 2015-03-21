package quannk.money.adautoclick;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class AutoClick {
	protected Robot robot = null;
	protected int width = -1;
	protected int height = -1;

	public AutoClick() {
		try {
			robot = new Robot();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			width = (int) screenSize.getWidth();
			height = (int) screenSize.getHeight();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		robot.delay(10);
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
		robot.delay(10);
		robot.mouseRelease(mouse);
		robot.delay(5);
	}

	public static Point findCoordinate(Color color, int x, int y)
			throws HeadlessException, AWTException {
		BufferedImage image = new Robot().createScreenCapture(new Rectangle(
				Toolkit.getDefaultToolkit().getScreenSize()));
		return findCoordinate(image, color, x, y);
	}

	public static Point findCoordinate(BufferedImage image, Color color, int x,
			int y) {
		int percent = 3;
		int width = image.getWidth();
		int height = image.getHeight();
		int minX = Math.max(0, x - width / percent);
		int maxX = Math.min(width - 1, x + width / percent);
		int minY = Math.max(0, y - height / percent);
		int maxY = Math.min(height - 1, y + height / percent);
		return findCoordinate(image, color, minX, maxX, minY, maxY);
	}

	public static Point findCoordinate(BufferedImage image, Color color,
			int minX, int maxX, int minY, int maxY) {
		int x, y;
		int avarageX = (maxX + minX) / 2;
		int avarageY = (maxY + minY) / 2;
		for (x = avarageX; x >= minX; x -= 5) {
			for (y = avarageY; y >= minY; y -= 5) {
				Color c = new Color(image.getRGB(x, y));
				if (isEqual(c, color)) {
					System.out.println("Found coordinate x = " + x + " y = "
							+ y);
					return new Point(x - 3, y - 3);
				}
			}
			for (y = avarageY; y <= maxY; y += 5) {
				Color c = new Color(image.getRGB(x, y));
				if (c.equals(color)) {
					System.out.println("Found coordinate x = " + x + " y = "
							+ y);
					return new Point(x - 3, y + 3);
				}
			}
		}
		for (x = avarageX; x <= maxX; x += 5) {
			for (y = avarageY; y >= minY; y -= 5) {
				Color c = new Color(image.getRGB(x, y));
				if (isEqual(c, color)) {
					System.out.println("Found coordinate x = " + x + " y = "
							+ y);
					return new Point(x + 3, y - 3);
				}
			}
			for (y = avarageY; y <= maxY; y += 5) {
				Color c = new Color(image.getRGB(x, y));
				if (isEqual(c, color)) {
					System.out.println("Found coordinate x = " + x + " y = "
							+ y);
					return new Point(x + 3, y + 3);
				}
			}
		}
		return null;
	}

	private static boolean isEqual(Color a, Color b) {
		return a.getRed() == b.getRed() && a.getBlue() == b.getBlue()
				&& a.getGreen() == b.getGreen();
	}
}
