package com.cc3002.breakout.logic.level;

import com.cc3002.breakout.logic.brick.IBrick;

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
