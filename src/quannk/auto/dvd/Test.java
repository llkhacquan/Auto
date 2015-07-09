package quannk.auto.dvd;

public class Test {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    for (int iSeason = 1; iSeason <=9; iSeason++){
      for (int iEp = 1; iEp <= 22; iEp++){
        String s ;
        String inputFile = "F:\\TV shows\\HIMYM HD\\DVD0" + iSeason + "\\HIMYM.S0" + iSeason + "E";
        if (iEp < 10)
          inputFile+="0" + iEp;
        else 
          inputFile += iEp;
        s = "if not exist \""+ inputFile+ ".mp4\" (ffmpeg -i ";
        s += "\"" + inputFile +".mkv\" -c copy ";
        s += "\"" + inputFile +".mp4\")";
        System.out.println(s);
      }
    }
  }

}
