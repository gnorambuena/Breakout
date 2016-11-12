package com.cc3002.breakout.logic.brick;

import java.util.List;

import com.cc3002.breakout.logic.level.Printer;
import com.cc3002.breakout.logic.level.Score;
import com.cc3002.breakout.logic.observer.GameObserver;
/**
 * Representa un SoftBrick del juego,
 * tiene solo un hitpoint.
 * @author gabriel
 *
 */

public class  SoftBrick implements IBrick {
  int hitpoints;
  Score playerScore;
  List<GameObserver>Observers;
  
  public SoftBrick(Score plScore, List<GameObserver>newObservers) {
    hitpoints = 1;
    playerScore = plScore;
    Observers = newObservers;
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
      playerScore.add(10);
      for(GameObserver obs : Observers) {
        obs.scoreSoftBrickUpdate();
      }
    }
  }
  
}