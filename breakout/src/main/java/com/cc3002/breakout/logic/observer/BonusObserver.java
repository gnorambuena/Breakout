package com.cc3002.breakout.logic.observer;

import java.io.PrintStream;

public class BonusObserver extends GameObserver {

  public BonusObserver(PrintStream st) {
    super(st);
  }

  public void scoreDiscount() {
    stream.println("Score discount emitted.");
  }
  
  public void scoreBonus() {
    stream.println("Extra score bonus emitted.");
  }
  
  public void lifeDiscount() {
    stream.println("Heart discount emitted.");
  }
  
  public void lifeBonus() {
    stream.println("Extra heart bonus emitted.");
  }
}
