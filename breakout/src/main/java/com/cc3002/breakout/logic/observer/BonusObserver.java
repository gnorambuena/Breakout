package com.cc3002.breakout.logic.observer;

import com.cc3002.breakout.facade.Flyweight;
import com.cc3002.breakout.logic.level.LevelHandler;

/**
 * Observer para los bonuses.
 * @author gabriel
 *
 */
public class BonusObserver extends GameObserver {
  
  public BonusObserver(Flyweight newFlyweight) {
    super(newFlyweight);
  }

  public void scoreDiscount() {
    flyweight.getGameConsole().print("Score discount emitted.");
  }
  
  public void scoreBonus() {
    flyweight.getGameConsole().print("Extra score bonus emitted.");
  }
  
  public void lifeDiscount() {
    flyweight.getGameConsole().print("Heart discount emitted.");
  }
  
  public void lifeBonus() {
    flyweight.getGameConsole().print("Extra heart bonus emitted.");
  }

  /**
   * Este observer no deberia responder nada a este mensaje.
   * 
   */
  public void scoreSoftBrickUpdate() {
     //Filler comment.
  }

  /**
   * Este observer no deberia responder nada a este mensaje.
   * 
   */ 
  public void scoreStoneBrickUpdate() {
     //Filler comment.
  }


  /**
   * Este observer no deberia responder nada a este mensaje.
   * 
   */ 
  public void levelUpdate(String name) {
     //Filler comment.
  }

  /**
   * Este observer no deberia responder nada a este mensaje.
   * 
   */
  public void levelAutoSwitch() {
     //Filler comment.
  }

  /**
   * Este observer no deberia responder nada a este mensaje.
   * 
   */
  public void setLevelHandler(LevelHandler newLevelHandler) {
     //Filler comment.
  }


  @Override
  public void bonusAutoSwitch() {
    flyweight.getBonusHandler().autoSwitch();
  }
}
