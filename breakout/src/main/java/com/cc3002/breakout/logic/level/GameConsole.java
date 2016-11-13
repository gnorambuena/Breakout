package com.cc3002.breakout.logic.level;

import java.io.PrintStream;

/**
 * Abstraccion de la consola del juego.
 * @author gabriel
 *
 */
public class GameConsole {
  PrintStream stream;
  
  public GameConsole() {
    stream = System.out;
  }
  
  public void setStream(PrintStream newPrintStream) {
    stream = newPrintStream;
  }
  
  public void print(String text) {
    stream.print(text);
  }
}
