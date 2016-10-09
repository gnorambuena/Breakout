package com.cc3002.breakout.logic;
/**
 * Abstraccion del Score,
 * sirve para mantener el score del nivel
 * y el del Player.
 * @author gabriel
 *
 */

public class Score {
  long points;
  
  public Score() {
    points = 0;
  }
  
  public Score(long pt) {
    points = pt;
  }
  
  public void add(Score sc) {
    points += sc.getPoints();
  }
  
  public void add(long pt) {
    points += pt;
  }
  
  public long getPoints() {
    return points;
  }
}
