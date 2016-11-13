package com.cc3002.breakout.logic.level;

import com.cc3002.breakout.logic.brick.IBrick;
import com.cc3002.breakout.logic.observer.GameObserver;

import java.util.List;

/**
 * Clase abstracta que sirve de superclass para RealLevel y LevelHandler.
 * @author gabriel
 *
 */
public abstract class GameLevel implements ILevel {

  public String getLevelName() {
    return null;
  }

  public List<IBrick> getBricks() {
    return null;
  }

  public int getNumberOfBricks() {
    return 0;
  }

  public int getRequiredPoints() {
    return 0;
  }

  public abstract void setObservers(List<GameObserver> newObservers);

  public List<GameObserver> getObservers() {
    return null;
  }

  public abstract void setRequiredPoints(int newRequiredPoints);

  public abstract void setNextLevel(ILevel newLevel);

  public ILevel getCurrentLevel() {
    return null;
  }

  public boolean hasNextLevel() {
    return false;
  }

  public abstract void setCurrentLevel(ILevel newLevel);

  public abstract void autoSwitchToNextLevel();

  public boolean hasCurrentLevel() {
    return false;
  }

}
