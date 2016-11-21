package com.cc3002.breakout.logic.observer;

import com.cc3002.breakout.facade.Flyweight;

/**
 * Observer para los ILevel.
 * @author gabriel
 *
 */
public class LevelObserver extends GameObserver {

  public LevelObserver(Flyweight newFlyweight) {
    super(newFlyweight);
  }

  @Override
  public void levelUpdate(String name) {
    flyweight.getGameConsole().print("Playing Level " + name + ".");
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
    if (flyweight.getAutoSwitch()) {
      flyweight.getLevelHandler().autoSwitchToNextLevel();
    }
  }

  /**
   * Este observer no deberia responder nada a este mensaje.
   */
  @Override
  public void bonusAutoSwitch() {
    //Filler comment.
  }
}
