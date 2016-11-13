package com.cc3002.breakout.logic.observer;

import java.io.PrintStream;

public class ScoreObserver extends GameObserver {

  public ScoreObserver(PrintStream st) {
    super(st);
  }
  
  @Override
  public void scoreSoftBrickUpdate() {
    stream.println("Soft brick destroyed and gained 10 points.");
  }
  
  @Override
  public void scoreStoneBrickUpdate() {
    stream.println("Stone brick destroyed and gained 50 points.");
  }
}
