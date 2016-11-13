package com.cc3002.breakout.logic.observer;

import com.cc3002.breakout.logic.bonus.BonusHandler;
import com.cc3002.breakout.logic.level.GameConsole;
import com.cc3002.breakout.logic.level.LevelHandler;

/**
 * Observer para los bonuses.
 * @author gabriel
 *
 */
public class BonusObserver extends GameObserver {

  BonusHandler bonusHandler;
  
  public BonusObserver(GameConsole gameConsole) {
    super(gameConsole);
  }

  public void scoreDiscount() {
    gameConsole.print("Score discount emitted." + System.lineSeparator());
  }
  
  public void scoreBonus() {
    gameConsole.print("Extra score bonus emitted." + System.lineSeparator());
  }
  
  public void lifeDiscount() {
    gameConsole.print("Heart discount emitted." + System.lineSeparator());
  }
  
  public void lifeBonus() {
    gameConsole.print("Extra heart bonus emitted." + System.lineSeparator());
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
  public void setBonusHandler(BonusHandler newBonusHandler) {
    bonusHandler = newBonusHandler; 
  }

  @Override
  public void bonusAutoSwitch() {
    bonusHandler.autoSwitch();
  }
}
