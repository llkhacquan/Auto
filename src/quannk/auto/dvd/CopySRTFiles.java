package quannk.auto.dvd;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CopySRTFiles {
	static String getStringFromNumber(int x) {
		if (x > 99 || x < 0)
			return null;
		if (x < 10)
			return "0" + Integer.toString(x);
		else
			return Integer.toString(x);
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		for (int iSeason = 1; iSeason <= 10; iSeason++) {
			String number = getStringFromNumber(iSeason);
			String folderName = "F:/Friends/Season " + number;

			// Create new folder to contain new subtitles (done)
			File newSubFolder = new File("F:/Friends-sub/ss" + number);
			// if (!newSubFolder.exists()) {
			// if (!newSubFolder.mkdir()) {
			// System.out.println("Error, cannot create new folder: "
			// + newSubFolder.getAbsolutePath());
			// }
			// }

			// copy files to new folder (done)
			// Files.walk(Paths.get(folderName)).forEach(
			// filePath -> {
			// if (Files.isRegularFile(filePath)) {
			// if (filePath.toString().contains(".srt")) {
			// String fileName = filePath.getFileName()
			// .toString();
			// System.out.println(fileName);
			// if (filePath.toString().toLowerCase()
			// .contains("sub v")) {
			// filePath.toFile().renameTo(
			// new File(newSubFolder.toString()
			// + "/vi_"
			// + fileName.toString()));
			// } else {
			// filePath.toFile().renameTo(
			// new File(newSubFolder.toString()
			// + "/en_"
			// + fileName.toString()));
			// }
			// }
			// }
			// });

			Files.walk(Paths.get(newSubFolder.toURI()))
					.forEach(
							filePath -> {
								if (Files.isRegularFile(filePath)) {
									String fileName = filePath.getFileName()
											.toString();
									for (int i = 1; i <= 25; i++) {
										if (!fileName.contains("SEASON_")
												&& (fileName
														.contains("x"
																+ getStringFromNumber(i)) || fileName
														.contains("E"
																+ getStringFromNumber(i)))) {
											fileName = "SEASON_" + number + "x"
													+ getStringFromNumber(i)
													+ "_" + fileName;
											System.out.println(fileName);
											File newFile = new File(filePath.getParent().toString()+"/"+fileName);
											filePath.toFile().renameTo(newFile);
											System.out.println(newFile);
											break;
										}
									}
								}
							});
		}
	}
}
