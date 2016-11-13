package com.cc3002.breakout.logic.observer;

import com.cc3002.breakout.logic.level.GameConsole;

public class LevelObserver extends GameObserver {

  
  public LevelObserver(GameConsole gameConsole) {
    super(gameConsole);
  }

  @Override
  public void levelUpdate(String name) {
    gameConsole.print("Playing Level " + name + ".");
  }

  @Override
  public void scoreSoftBrickUpdate() {}

  @Override
  public void scoreStoneBrickUpdate() {}

  @Override
  public void scoreDiscount() {}

  @Override
  public void scoreBonus() {}

  @Override
  public void lifeDiscount() {}

  @Override
  public void lifeBonus() {}
}
