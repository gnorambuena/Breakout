package com.cc3002.breakout.logic.brick;

import com.cc3002.breakout.logic.level.Printer;

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
  
  public String print(Printer printer);
  
  public boolean isDestroyed();
  
}