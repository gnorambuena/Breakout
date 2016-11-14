package com.cc3002.breakout.logic.observer;

import com.cc3002.breakout.logic.bonus.BonusHandler;
import com.cc3002.breakout.logic.level.GameConsole;
import com.cc3002.breakout.logic.level.LevelHandler;

/**
 * Observer para los ILevel.
 * @author gabriel
 *
 */
public class LevelObserver extends GameObserver {

  LevelHandler levelHandler;
  
  public LevelObserver(GameConsole gameConsole) {
    super(gameConsole);
  }

  @Override
  public void levelUpdate(String name) {
    gameConsole.print("Playing Level " + name + ".");
  }
  
  public void setLevelHandler(final LevelHandler newLevelHandler) {
    levelHandler = newLevelHandler;
  }

  /**
   * Este observer no deberia responder nada a este mensaje.
   */
  @Override
  public void scoreSoftBrickUpdate() {
    //Filler comment.
  }


  /**
   * Este observer no deberia responder nada a este mensaje.
   */
  @Override
  public void scoreStoneBrickUpdate() {
    //Filler comment.
  }

  /**
   * Este observer no deberia responder nada a este mensaje.
   */
  @Override
  public void scoreDiscount() {
    //Filler comment.
  }

  /**
   * Este observer no deberia responder nada a este mensaje.
   */
  @Override
  public void scoreBonus() {
    //Filler comment.
  }

  /**
   * Este observer no deberia responder nada a este mensaje.
   */
  @Override
  public void lifeDiscount() {
    //Filler comment.
  }

  /**
   * Este observer no deberia responder nada a este mensaje.
   */
  @Override
  public void lifeBonus() {
    //Filler comment.
  }

  @Override
  public void levelAutoSwitch() {
    if (levelHandler.getAutoSwitch()) {
      levelHandler.autoSwitchToNextLevel();
    }
  }

  /**
   * Este observer no deberia responder nada a este mensaje.
   */
  @Override
  public void setBonusHandler(BonusHandler newBonusHandler) {
    //Filler comment.
  }

  /**
   * Este observer no deberia responder nada a este mensaje.
   */
  @Override
  public void bonusAutoSwitch() {
    //Filler comment.
  }
}
