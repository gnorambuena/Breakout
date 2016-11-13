package com.cc3002.breakout.logic.observer;

import com.cc3002.breakout.logic.bonus.BonusHandler;
import com.cc3002.breakout.logic.level.GameConsole;
import com.cc3002.breakout.logic.level.LevelHandler;

/**
 * Clase abstracta para todos los Observer del juego.
 * @author gabriel
 *
 */
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
  
  public abstract void levelAutoSwitch();
  
  public abstract void setLevelHandler(final LevelHandler newLevelHandler);
 
  public abstract void setBonusHandler(final BonusHandler newBonusHandler);

  public abstract void bonusAutoSwitch();
}
