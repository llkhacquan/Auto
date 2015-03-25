package quannk.dvd;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class RenameSubOfFriends {
	// Copy sub to a folder
	public static void main(String... arg) throws IOException {
		for (int iSeason = 1; iSeason <= 10; iSeason++) {
			File folder = new File("F:/Friends/Season " + CopySRTFiles.getStringFromNumber(iSeason));
			File[] files = folder.listFiles();
			for (File file : files) {
				String fileName = file.getName();
				File newFile = new File("F:/Friends-sub/ss"+ CopySRTFiles.getStringFromNumber(iSeason) + "/" +file.getName());
				if (fileName.contains(".srt")) {
					System.out.println(file.getAbsolutePath());
					System.out.println(newFile.getAbsolutePath() + "\n");
					Files.copy(file.toPath(), newFile.toPath());
				}
			}
		}
	}

	// Rename mkv files. DONE
	public static void renameMKVFiles() {
		for (int iSeason = 1; iSeason <= 9; iSeason++) {
			File folder = new File("F:/Friends/Season 0" + Integer.toString(iSeason));
			File[] files = folder.listFiles();
			for (File file : files) {
				String fileName = file.getName();
				if (fileName.contains(".mkv")) {
					// Friends - [1x01] - The One where Monica gets a Roommate

					int iFilm = Integer.parseInt(fileName.substring(13, 15));
					int iFilm2 = -1;
					if (fileName.charAt(15) != ']') {
						int i = fileName.indexOf(']');
						iFilm2 = Integer.parseInt(fileName.substring(i - 2, i));
					}
					if (iFilm2 < 0)
						System.out.println(iFilm + "\t" + fileName);
					else
						System.out.println(iFilm + "\t" + iFilm2 + "\t" + fileName);
					int index = fileName.indexOf("The ");
					String ep = fileName.substring(index);
					fileName = "S0" + iSeason + "E" + CopySRTFiles.getStringFromNumber(iFilm);
					if (iFilm2 > 0)
						fileName += "E" + CopySRTFiles.getStringFromNumber(iFilm2);
					fileName += " - " + ep;
					System.out.println(fileName + "\n");
					file.renameTo(new File(file.getParent() + "/" + fileName));
				}
			}
		}

		File folder = new File("F:/Friends/Season 10");
		File[] files = folder.listFiles();
		for (File file : files) {
			String fileName = file.getName();
			if (fileName.contains(".mkv")) {
				// Friends - [10x01] - The One where Monica gets a Roommate

				int iFilm = Integer.parseInt(fileName.substring(14, 16));
				int iFilm2 = -1;
				if (fileName.charAt(16) != ']') {
					int i = fileName.indexOf(']');
					iFilm2 = Integer.parseInt(fileName.substring(i - 2, i));
				}
				if (iFilm2 < 0)
					System.out.println(iFilm + "\t" + fileName);
				else
					System.out.println(iFilm + "\t" + iFilm2 + "\t" + fileName);
				int index = fileName.indexOf("The ");
				String ep = fileName.substring(index);
				fileName = "S10E" + CopySRTFiles.getStringFromNumber(iFilm);
				if (iFilm2 > 0)
					fileName += "E" + CopySRTFiles.getStringFromNumber(iFilm2);
				fileName += " - " + ep;
				System.out.println(fileName + "\n");
				file.renameTo(new File(file.getParent() + "/" + fileName));
			}
		}
	}

	// Done
	public static void renameMergedSub() {
		for (int iSeason = 1; iSeason <= 10; iSeason++) {
			File folder = new File("F:/Friends/Season " + CopySRTFiles.getStringFromNumber(iSeason));
			File[] files = folder.listFiles();
			for (File file : files) {
				String fileName = file.getName();
				if (fileName.contains("merged_ss_")) { // merged_ss_04_01
					// Friends - [1x01] - The One where Monica gets a Roommate
					int iFilm = Integer.parseInt(fileName.substring(13, 15));
					System.out.println(iFilm + "\t" + fileName);
					String s = "S" + CopySRTFiles.getStringFromNumber(iSeason) + "E"
							+ CopySRTFiles.getStringFromNumber(iFilm);
					for (File fileMkv : files) {
						if (fileMkv.getName().contains(s)) {
							String mkvName = fileMkv.getAbsolutePath();
							String newSrtName = mkvName.substring(0, mkvName.length() - 3) + "srt";
							System.out.println(newSrtName);
							file.renameTo(new File(newSrtName));
							break;
						}
					}
				}
			}
		}
	}

	// Done
	public static void rename2SubFiles() {
		for (int iSeason = 1; iSeason <= 10; iSeason++) {
			File folder = new File("F:/Friends-sub/ss" + CopySRTFiles.getStringFromNumber(iSeason));
			File[] files = folder.listFiles();
			for (File file : files) {
				String fileName = file.getName();
				// SEASON_09x02_vi_Friends - [9x02] - The One Where Emma Cries
				if (fileName.contains("SEASON")) {
					int iFilm = Integer.parseInt(fileName.substring(10, 12));

					File mkvFolder = new File("F:/Friends/Season " + CopySRTFiles.getStringFromNumber(iSeason));
					for (File fileMkv : mkvFolder.listFiles()) {
						if (fileMkv.getName().contains("E" + CopySRTFiles.getStringFromNumber(iFilm))) {
							System.out.println(fileName);
							System.out.println(fileMkv.getName());
							StringBuilder newName = new StringBuilder(fileMkv.getAbsolutePath());
							if (fileName.contains("_vi_"))
								newName.insert(newName.indexOf(" - "), "_vi");
							else if (fileName.contains("_en_"))
								newName.insert(newName.indexOf(" - "), "_en");
							newName.delete(newName.length() - 4, newName.length());
							newName.append(".srt");
							System.out.println(newName + "\n");
							file.renameTo(new File(newName.toString()));
							break;
						}
					}
				}
			}
		}
	}
}
