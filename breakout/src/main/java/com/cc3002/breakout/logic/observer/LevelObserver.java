package com.cc3002.breakout.logic.observer;

import java.io.PrintStream;

public class LevelObserver extends GameObserver {

  public LevelObserver(PrintStream st) {
    super(st);
  }

  @Override
  public void levelUpdate(String name) {
    stream.println("Playing Level " + name + ".");
  }
}
