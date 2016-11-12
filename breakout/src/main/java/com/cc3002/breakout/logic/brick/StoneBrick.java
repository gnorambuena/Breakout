package com.cc3002.breakout.logic.brick;

import java.util.List;

import com.cc3002.breakout.logic.level.Printer;
import com.cc3002.breakout.logic.level.Score;
import com.cc3002.breakout.logic.observer.GameObserver;
/**
 * Representa un StoneBrick del juego,
 * tiene tres hitpoints.
 * @author gabriel
 *
 */

public class StoneBrick implements IBrick {
  int hitpoints;
  Score playerScore;
  List<GameObserver>Observers;
  
  public StoneBrick(Score plScore,List<GameObserver>newObservers) {
    hitpoints = 3;
    playerScore = plScore;
    Observers = newObservers;
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
      if (hitpoints == 0) {
        playerScore.add(50);
        for(GameObserver obs : Observers) {
          obs.scoreStoneBrickUpdate();
        }
      }
    }
  }

  public String print(Printer printer) {
    return printer.printStoneBrick();
  }

}

