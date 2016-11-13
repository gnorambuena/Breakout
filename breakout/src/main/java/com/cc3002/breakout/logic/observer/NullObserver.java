package com.cc3002.breakout.logic.observer;

import com.cc3002.breakout.logic.bonus.BonusHandler;
import com.cc3002.breakout.logic.level.GameConsole;
import com.cc3002.breakout.logic.level.LevelHandler;

public class NullObserver extends GameObserver {

  public NullObserver(GameConsole newGameConsole) {
    super(newGameConsole);
  }

  public NullObserver() {
    super(new GameConsole());
  }
  
  @Override
  public void scoreSoftBrickUpdate() {}

  @Override
  public void scoreStoneBrickUpdate() {}

  @Override
  public void levelUpdate(String name) {}

  @Override
  public void scoreDiscount() {}

  @Override
  public void scoreBonus() {}

  @Override
  public void lifeDiscount() {}

  @Override
  public void lifeBonus() {}

  @Override
  public void levelAutoSwitch() {}

  @Override
  public void setLevelHandler(LevelHandler newLevelHandler) {}

  @Override
  public void setBonusHandler(BonusHandler newBonusHandler) {}

  @Override
  public void bonusAutoSwitch() {}

}
