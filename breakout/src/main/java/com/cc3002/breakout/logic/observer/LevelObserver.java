package com.cc3002.breakout.logic.observer;

import com.cc3002.breakout.logic.level.GameConsole;

/**
 * Observer para los ILevel.
 * @author gabriel
 *
 */
public class LevelObserver extends GameObserver {

  
  public LevelObserver(GameConsole gameConsole) {
    super(gameConsole);
  }

  @Override
  public void levelUpdate(String name) {
    gameConsole.print("Playing Level " + name + ".");
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
}
