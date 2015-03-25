package quannk.dvd;

import java.io.File;

public class Rename {
	public static void main(String... args){
		File folder = new File("F:/Friends/Season 01/");
		File[] files = folder.listFiles();
		for (File file: files){
			if (file.isFile()){
				if (file.getName().contains(".mkv")){
					
				}
			}
		}
	}
}
