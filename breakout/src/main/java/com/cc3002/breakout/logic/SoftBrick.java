package com.cc3002.breakout.logic;
/**
 * Representa un SoftBrick del juego,
 * tiene solo un hitpoint.
 * @author gabriel
 *
 */

public class  SoftBrick implements IBrick {
  int hitpoints;
    
  public SoftBrick() {
    hitpoints = 1;
  }

 
  public boolean isSoftBrick() {
    return true;
  }

  public boolean isStoneBrick() {
    return false;
  }
  
  public int remainingHits() {
    return hitpoints;
  }

  public String print(Printer printer) {
    return printer.printSoftBrick();
  }
  
  /**
   * Le quita uno a los hitpoints
   * del IBrick.
   */
  
  public void hit() {
    if (hitpoints > 0) {
      hitpoints--;
    }
  }
  
  /**
   * Retorna el puntaje ganado por el jugador.
   */
  
  public int getPoints() {
    if ( hitpoints == 0 ) {
      return 10;
    } else {
      return 0;
    }
  }
}