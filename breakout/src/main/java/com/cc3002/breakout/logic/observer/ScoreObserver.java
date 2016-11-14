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
   * @param name Nombre para ser updated.
   */
  @Override
  public void levelUpdate(String name) {
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

  /**
   * Este observer no deberia responder nada a este mensaje.
   */
  @Override
  public void levelAutoSwitch() {
  //Filler comment.
  }
  

  /**
   * Este observer no deberia responder nada a este mensaje.
   * @o
   */
  @Override
  public void setLevelHandler(LevelHandler newLevelHandler) {
  //Filler comment.
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
