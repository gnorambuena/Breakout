package com.cc3002.breakout.logic.level;

import com.cc3002.breakout.logic.brick.IBrick;
import com.cc3002.breakout.logic.observer.GameObserver;

import java.util.List;

/**
 * Interface de la clase RealLevel.
 * @author gabriel
 *
 */

public interface ILevel {

  String getLevelName();

  List<IBrick> getBricks();

  int getNumberOfBricks();

  int getRequiredPoints();
  
  public void setObservers(final List<GameObserver> newObservers);
  
  public List<GameObserver> getObservers();
  
  public void setRequiredPoints();
  
  public void setRequiredPoints(int newPoints);
  
  public void setNextLevel(ILevel newLevel);

  ILevel getCurrentLevel();

  boolean hasNextLevel();

  void setCurrentLevel(ILevel newLevel);

  void autoSwitchToNextLevel();

  boolean hasCurrentLevel();
  
  public Player getPlayer();
}