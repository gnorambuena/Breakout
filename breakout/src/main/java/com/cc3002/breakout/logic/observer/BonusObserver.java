package com.cc3002.breakout.logic.observer;

import com.cc3002.breakout.logic.level.GameConsole;

public class BonusObserver extends GameObserver {

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

  @Override
  public void scoreSoftBrickUpdate() {}

  @Override
  public void scoreStoneBrickUpdate() {}

  @Override
  public void levelUpdate(String name) {}
}
