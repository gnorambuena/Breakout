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
    gameConsole.print("Score discount emitted.");
  }
  
  public void scoreBonus() {
    gameConsole.print("Extra score bonus emitted.");
  }
  
  public void lifeDiscount() {
    gameConsole.print("Heart discount emitted.");
  }
  
  public void lifeBonus() {
    gameConsole.print("Extra heart bonus emitted.");
  }

  /**
   * Este observer no deberia responder nada a este mensaje.
   */ 
  @Override
  public void scoreSoftBrickUpdate() {}

  /**
   * Este observer no deberia responder nada a este mensaje.
   */
  @Override
  public void scoreStoneBrickUpdate() {}


  /**
   * Este observer no deberia responder nada a este mensaje.
   */
  @Override
  public void levelUpdate(String name) {}

  /**
   * Este observer no deberia responder nada a este mensaje.
   */
  @Override
  public void levelAutoSwitch() {}

  /**
   * Este observer no deberia responder nada a este mensaje.
   */
  @Override
  public void setLevelHandler(LevelHandler newLevelHandler) {}

  @Override
  public void setBonusHandler(BonusHandler newBonusHandler) {
    bonusHandler = newBonusHandler; 
  }

  @Override
  public void bonusAutoSwitch() {
    bonusHandler.autoSwitch();
  }
}
