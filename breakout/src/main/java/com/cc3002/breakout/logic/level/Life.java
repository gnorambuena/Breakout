package com.cc3002.breakout.logic.level;

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
  }
  
  public void addHearts() {
    hearts++;
  }
}
