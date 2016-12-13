package com.cc3002.breakout.logic.level;

/**
 * Esta clase representa las vidas que tiene el player.
 * @author gabriel
 *
 */
public class Life {
  private int hearts;
  
  public Life() {
    hearts = 3;
  }
  
  public int getHearts() {
    return hearts;
  }
  
  public void lessHearts() {
    hearts--;
    System.out.println("Current hearts:" + hearts);
  }
  
  public void addHearts() {
    hearts++;
    System.out.println("Current hearts:" + hearts);
  }
}
