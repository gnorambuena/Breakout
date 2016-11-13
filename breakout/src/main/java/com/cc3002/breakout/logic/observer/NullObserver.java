package com.cc3002.breakout.logic.observer;

import com.cc3002.breakout.logic.bonus.BonusHandler;
import com.cc3002.breakout.logic.level.GameConsole;
import com.cc3002.breakout.logic.level.LevelHandler;

/**
 * Observador nulo para debuguear.
 * @author gabriel
 *
 */
public class NullObserver extends GameObserver {

  public NullObserver(GameConsole newGameConsole) {
    super(newGameConsole);
  }

  public NullObserver() {
    super(new GameConsole());
  }
  
  @Override
  public void scoreSoftBrickUpdate() {
    //Filler comment.
  }

  @Override
  public void scoreStoneBrickUpdate() {
    //Filler comment.
  }

  @Override
  public void levelUpdate(String name) {
    //Filler comment.
  }

  @Override
  public void scoreDiscount() {
    //Filler comment.
  }

  @Override
  public void scoreBonus() {
    //Filler comment.
  }

  @Override
  public void lifeDiscount() {
    //Filler comment.
  }

  @Override
  public void lifeBonus() {
    //Filler comment.
  }

  @Override
  public void levelAutoSwitch() {
    //Filler comment.
  }

  @Override
  public void setLevelHandler(LevelHandler newLevelHandler) {
    //Filler comment.
  }

  @Override
  public void setBonusHandler(BonusHandler newBonusHandler) {
    //Filler comment.
  }

  @Override
  public void bonusAutoSwitch() {
    //Filler comment.
  }

}
