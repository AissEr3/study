package Problem1;

import java.util.Calendar;

public class Checker {

  public Checker(Environmental anEnv) {
    env = anEnv;
  }

  /**
   * After 5 o'clock, remind people to go home
   * by playing a whistle
   */
  public void reminder() {
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(env.getTime());
    int hour = cal.get(Calendar.HOUR_OF_DAY);

    if (hour >= 17) { // 5:00PM
      env.playWavFile("quit_whistle.wav");
    }
  }

  // ...
    
  private Environmental env;
}
  
