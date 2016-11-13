package com.cc3002.breakout.logic.observer;

import com.cc3002.breakout.logic.bonus.BonusHandler;
import com.cc3002.breakout.logic.level.GameConsole;
import com.cc3002.breakout.logic.level.LevelHandler;

/**
 * Observer para el Score.
 * @author gabriel
 *
 */
public class ScoreObserver extends GameObserver {

  public ScoreObserver(GameConsole gameConsole) {
    super(gameConsole);
  }
  
  @Override
  public void scoreSoftBrickUpdate() {
    gameConsole.print("Soft brick destroyed and gained 10 points.");
  }
  
  @Override
  public void scoreStoneBrickUpdate() {
    gameConsole.print("Stone brick destroyed and gained 50 points.");
  }


  /**
   * Este observer no deberia responder nada a este mensaje.
   */
  @Override
  public void levelUpdate(String name) {}

  /**
   * Este observer no deberia responder nada a este mensaje.
   */
  @Override
  public void scoreDiscount() {}

  /**
   * Este observer no deberia responder nada a este mensaje.
   */
  @Override
  public void scoreBonus() {}

  /**
   * Este observer no deberia responder nada a este mensaje.
   */
  @Override
  public void lifeDiscount() {}

  /**
   * Este observer no deberia responder nada a este mensaje.
   */
  @Override
  public void lifeBonus() {}

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
  

  /**
   * Este observer no deberia responder nada a este mensaje.
   */
  @Override
  public void setBonusHandler(BonusHandler newBonusHandler) {}
  

  /**
   * Este observer no deberia responder nada a este mensaje.
   */
  @Override
  public void bonusAutoSwitch() {}

}
