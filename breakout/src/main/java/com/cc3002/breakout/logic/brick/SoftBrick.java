package com.cc3002.breakout.logic.brick;

import com.cc3002.breakout.logic.bonus.BonusHandler;
import com.cc3002.breakout.logic.level.Printer;
import com.cc3002.breakout.logic.level.Score;
import com.cc3002.breakout.logic.observer.GameObserver;

import java.util.List;

/**
 * Representa un SoftBrick del juego,
 * tiene solo un hitpoint.
 * @author gabriel
 *
 */

public class  SoftBrick implements IBrick {
  int hitpoints;
  Score playerScore;
  List<GameObserver> observers;
  BonusHandler bonusHandler;
  
  /**
   * Constructor del StoneBrick, toma una referencia al Score del jugador,
   * y la lista de Observers del Juego.
   * @param plScore Score del jugador.
   * @param newObservers Observers del juego.
   */
  public SoftBrick(Score plScore, List<GameObserver> newObservers, BonusHandler newBonusHandler) {
    hitpoints = 1;
    playerScore = plScore;
    observers = newObservers;
    bonusHandler = newBonusHandler;
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
      for (GameObserver obs : observers) {
        obs.scoreSoftBrickUpdate();
      }

      bonusHandler.reached();
      playerScore.add(10);
    }
  }

  public boolean isDestroyed() {
    return hitpoints == 0;
  }


  public void setBonusHandler(BonusHandler newBonusHandler) {
    bonusHandler = newBonusHandler;
  }
  
}