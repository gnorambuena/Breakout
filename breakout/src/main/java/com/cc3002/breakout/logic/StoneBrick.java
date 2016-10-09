package com.cc3002.breakout.logic;
/**
 * Representa un StoneBrick del juego,
 * tiene tres hitpoints.
 * @author gabriel
 *
 */

public class StoneBrick implements IBrick {
  int hitpoints;
  
  public StoneBrick() {
    hitpoints = 3;
  }

  public int remainingHits() {
    return hitpoints;
  }

  public boolean isSoftBrick() {
    return false;
  }
  
  public boolean isStoneBrick() {
    return true;
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

  public String print(Printer printer) {
    return printer.printStoneBrick();
  }

  /** Retorna el puntaje ganado por el jugador.
   */
  
  public int getPoints() {
    if ( hitpoints == 0 ) {
      return 50;
    } else {
      return 0;
    }
  }
}

