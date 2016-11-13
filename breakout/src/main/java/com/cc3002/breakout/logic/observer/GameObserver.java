package com.cc3002.breakout.logic.observer;

import java.io.PrintStream;


public abstract class GameObserver {
  
  PrintStream stream;
  
  public GameObserver(PrintStream st) {
    stream = st;
  }
  
  public void scoreSoftBrickUpdate(){}
  
  public void scoreStoneBrickUpdate(){}
  
  public void levelUpdate(String name){}
  
  public void scoreDiscount(){}
  
  public void scoreBonus(){}
  
  public void lifeDiscount(){}
  
  public void lifeBonus(){}
  
  public void setStream(PrintStream st) {
    stream = st;
  }
}
