package com.cc3002.breakout.logic.brick;

import com.cc3002.breakout.logic.level.Printer;
import com.cc3002.breakout.logic.level.Score;
import com.cc3002.breakout.logic.observer.GameObserver;

import java.util.List;

/**
 * Representa un StoneBrick del juego,
 * tiene tres hitpoints.
 * @author gabriel
 *
 */

public class StoneBrick implements IBrick {
  int hitpoints;
  Score playerScore;
  List<GameObserver> observers;
  
  /**
   * Constructor del StoneBrick, toma una referencia al Score del jugador,
   * y la lista de Observers del Juego.
   * @param plScore Score del jugador.
   * @param newObservers Observers del juego.
   */
  public StoneBrick(Score plScore,List<GameObserver> newObservers) {
    hitpoints = 3;
    playerScore = plScore;
    observers = newObservers;
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
      if (isDestroyed()) {
        for (GameObserver obs : observers) {
          obs.scoreStoneBrickUpdate();
        }
        playerScore.add(50);
      }
    }
  }

  public String print(Printer printer) {
    return printer.printStoneBrick();
  }


  public boolean isDestroyed() {
    return hitpoints == 0;
  }
}

