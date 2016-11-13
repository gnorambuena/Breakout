package com.cc3002.breakout.logic.observer;

import java.util.List;

import com.cc3002.breakout.logic.bonus.IBonus;
import com.cc3002.breakout.logic.level.GameConsole;

public abstract class GameObserver {
  
  GameConsole gameConsole;
  
  public GameObserver(GameConsole newGameConsole) {
    gameConsole = newGameConsole;
  }
  
  public abstract void scoreSoftBrickUpdate();
  
  public abstract void scoreStoneBrickUpdate();
  
  public abstract void levelUpdate(String name);
  
  public abstract void scoreDiscount();
  
  public abstract void scoreBonus();
  
  public abstract void lifeDiscount();
  
  public abstract void lifeBonus();
 
}
