package com.cc3002.breakout.logic;

/**
 * Clase auxiliar que sirve para imprimir los IBricks.
 * @author gabriel
 *
 */
public class Printer {
  
  public String print(IBrick brick) {
    return brick.print(this);
  }
  
  public String printSoftBrick() {
    return "*"; 
  }
  
  public String printStoneBrick() {
    return "#";
  }
}
