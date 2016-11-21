package com.cc3002.breakout.logic.level;

import com.cc3002.breakout.facade.Flyweight;
import com.cc3002.breakout.logic.brick.IBrick;

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
  
  public void setRequiredPoints();
  
  public void setRequiredPoints(int newPoints);
  
  public void setNextLevel(ILevel newLevel);

  ILevel getCurrentLevel();

  boolean hasNextLevel();

  void setCurrentLevel(ILevel newLevel);

  void autoSwitchToNextLevel();

  boolean hasCurrentLevel();

  void setFlyweight(Flyweight flyweight);
}