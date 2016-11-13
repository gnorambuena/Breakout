package com.cc3002.breakout.logic.observer;

import com.cc3002.breakout.logic.level.GameConsole;

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
}
