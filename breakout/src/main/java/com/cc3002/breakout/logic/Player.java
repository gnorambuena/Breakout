package com.cc3002.breakout.logic;
/**
 * Abstraccion de un jugador,
 * sirve para mantener el Score,
 * y el numero de vidas.
 * @author gabriel
 *
 */

public class Player {
  Score sc;
  int hearts;
  
  public Player() {
    sc = new Score(0);
    hearts = 3;
  }
  
  public int getNumberOfHearts() {
    return hearts;
  }
  
  public void lossOfHeart() {
    hearts--;
  }
  
  public void addScore(long points) {
    sc.add(points);
  }
  
  public long getScore() {
    return sc.getPoints();
  }
}
