package com.cc3002.breakout.logic.level;

import java.io.PrintStream;

public class GameConsole {
  PrintStream stream;
  
  public GameConsole() {
    stream = null;
  }
  
  public void setStream(PrintStream newPrintStream) {
    stream = newPrintStream;
  }
  
  public void print(String text) {
    stream.println(text);
  }
}
