package Problem1;

public class SystemEnvironment implements Environmental {
  public long getTime() {
    return System.currentTimeMillis();
  }
  // other methods ...


  public void playWavFile(String name) {
    // Left as an exercise to the reader...
  }
}

