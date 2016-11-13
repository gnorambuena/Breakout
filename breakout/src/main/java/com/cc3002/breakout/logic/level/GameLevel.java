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

  public abstract String getLevelName();

  public abstract List<IBrick> getBricks();

  public abstract int getNumberOfBricks();

  public abstract int getRequiredPoints();

  public abstract void setObservers(List<GameObserver> newObservers);

  public abstract List<GameObserver> getObservers();

  public abstract void setRequiredPoints();

  public abstract void setNextLevel(ILevel newLevel);

  public abstract ILevel getCurrentLevel();

  public abstract boolean hasNextLevel();

  public abstract void setCurrentLevel(ILevel newLevel);

  public abstract void autoSwitchToNextLevel();

  public abstract boolean hasCurrentLevel();

  public abstract Player getPlayer();
}
