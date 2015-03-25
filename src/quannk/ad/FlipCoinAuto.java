package quannk.ad;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class FlipCoinAuto {
	static private Robot robot = null;
	static private Random random = new Random();
	static private int streakHead = 0;
	static private int streakTail = 0;

	public static void main(String[] args) throws AWTException,
			HeadlessException, UnsupportedFlavorException, IOException {
		File logFile = new File("log.txt");
		int winCount = 0;

		BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));
		String text = null;
		boolean justWin = true;
		int winStreak = 0;
		int lostStreak = 0;
		int lastIndex = 0;
		int[] values = new int[] { 30, 70, 145, 300, 650, 1400, 5900, 12000,
				24500, 50000 };
		robot = new Robot();
		robot.delay(1000);

		outerloop: while (true) {
			// select old value
			robot.mouseMove(240, 340 + 252);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseMove(300, 340 + 252);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.delay(100);

			// write new value
			if (lastIndex == values.length)
				break;
			pressNumber(values[lastIndex] * 5);
			robot.delay(rand(300, 500));

			// chose head or tail
			if (random.nextBoolean()) {
				// HEAD
				streakHead++;
				streakTail = 0;
				if (streakHead <= 5)
					robot.mouseMove(rand(132, 140), rand(440, 450) + 252);
				else
					robot.mouseMove(rand(294, 310), rand(437, 450) + 252);
			} else {
				// TAIL
				streakTail++;
				streakHead = 0;
				if (streakTail <= 5)
					robot.mouseMove(rand(294, 310), rand(437, 450) + 252);
				else
					robot.mouseMove(rand(132, 140), rand(440, 450) + 252);
			}

			// chose it
			robot.delay(rand(200, 500));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);

			boolean knowAnswer = false;
			while (knowAnswer == false) {
				robot.delay(1000);
				// select text
				robot.mouseMove(65, 318 + 245);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseMove(393, 340 + 245);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				robot.delay(100);

				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.delay(50);
				robot.keyPress(KeyEvent.VK_C);
				robot.keyRelease(KeyEvent.VK_C);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.delay(100);
				text = (String) Toolkit.getDefaultToolkit()
						.getSystemClipboard().getData(DataFlavor.stringFlavor);
				if (text.contains("called") || text.contains("Guess")) {
					knowAnswer = false;
					continue;
				} else if (text.contains("wrong")) {
					// we lost
					if (justWin) {
						winStreak = 0;
						lostStreak = 1;
					} else {
						winStreak = 0;
						lostStreak++;
					}
					lastIndex++;
					justWin = false;
					knowAnswer = true;
				} else if (text.contains("right")) {
					// we win
					if (justWin) {
						winStreak++;
						lostStreak = 0;
					} else {
						winStreak = 1;
						lostStreak = 0;
					}
					winCount++;
					lastIndex = 0;
					justWin = true;
					knowAnswer = true;
				} else
					break outerloop;
			}

			// log result
			System.out.print("turn " + winCount + " winStreak " + winStreak
					+ " lostStreak " + lostStreak + " ");
			writer.write("turn " + winCount + "winStreak " + winStreak
					+ " lostStreak " + lostStreak + " ");
			System.out.println(text);
			writer.write(text + "\n");
			// check if we won so much
			if (winStreak >= 10) {
				System.out.println("Win so much, should pause... 5 mins");
				writer.write("Win so much, should pause... 5 mins\n");
				robot.delay(60000);
				robot.delay(60000);
				robot.delay(60000);
				robot.delay(60000);
				robot.delay(60000);
				winStreak = 0;
				lostStreak = 0;
			}

			// check if we plays so much
			if (winCount % 20 == 19) {
				System.out.println("We should pause for 30 min now.");
				for (int i = 0; i < 30; i++)
					robot.delay(60000);
			}

			// click Play again
			robot.delay(rand(300, 500));
			robot.mouseMove(rand(227, 230), rand(358, 364) + 252);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.delay(rand(1000, 2000));
			Color c = robot.getPixelColor(30, 600);
			if (c.getBlue() != 255)
				break outerloop;
		}

		writer.close();
	}

	public static int rand(int x) {
		return Math.abs(random.nextInt() % x);
	}

	public static int rand(int x, int y) {
		assert (y > x);
		return Math.abs(random.nextInt() % (y - x)) + x;
	}

	public static void pressNumber(int i) {
		String s = Integer.toString(i);
		for (int j = 0; j < s.length(); j++) {
			pressCharacter(s.charAt(j));
		}
	}

	public static void pressCharacter(char c) {
		switch (c) {
		case '0':
			robot.keyPress(KeyEvent.VK_0);
			robot.keyRelease(KeyEvent.VK_0);
			break;
		case '1':
			robot.keyPress(KeyEvent.VK_1);
			robot.keyRelease(KeyEvent.VK_1);
			break;
		case '2':
			robot.keyPress(KeyEvent.VK_2);
			robot.keyRelease(KeyEvent.VK_2);
			break;
		case '3':
			robot.keyPress(KeyEvent.VK_3);
			robot.keyRelease(KeyEvent.VK_3);
			break;
		case '4':
			robot.keyPress(KeyEvent.VK_4);
			robot.keyRelease(KeyEvent.VK_4);
			break;
		case '5':
			robot.keyPress(KeyEvent.VK_5);
			robot.keyRelease(KeyEvent.VK_5);
			break;
		case '6':
			robot.keyPress(KeyEvent.VK_6);
			robot.keyRelease(KeyEvent.VK_6);
			break;
		case '7':
			robot.keyPress(KeyEvent.VK_7);
			robot.keyRelease(KeyEvent.VK_7);
			break;
		case '8':
			robot.keyPress(KeyEvent.VK_8);
			robot.keyRelease(KeyEvent.VK_8);
			break;
		case '9':
			robot.keyPress(KeyEvent.VK_9);
			robot.keyRelease(KeyEvent.VK_9);
			break;
		default:
			assert (false);
		}
	}
}
