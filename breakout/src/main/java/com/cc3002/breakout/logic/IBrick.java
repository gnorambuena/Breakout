package com.cc3002.breakout.logic;
/**
 * Interface para los Bricks del juego
 * se usa para generar los dos tipos de bricks
 * SoftBrick y StoneBrick.
 * @author gabriel
 *
 */

public interface IBrick {

  boolean isSoftBrick();

  boolean isStoneBrick();

  void hit();
  
  int remainingHits();
  
  int getPoints();
  
  public String print(Printer printer);
}