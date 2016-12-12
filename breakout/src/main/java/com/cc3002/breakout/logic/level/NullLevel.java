package com.cc3002.breakout.logic.level;


import com.cc3002.breakout.facade.Flyweight;
import com.cc3002.breakout.logic.brick.IBrick;

import java.util.List;

/**
 * Null Object pattern para un ILevel.
 * @author gabriel
 *
 */
public class NullLevel implements ILevel {

  @Override
  public String getLevelName() {
    return null;
  }

  @Override
  public List<IBrick> getBricks() {
    return null;
  }

  @Override
  public int getNumberOfBricks() {
    return 0;
  }

  @Override
  public int getRequiredPoints() {
    return 0;
  }

  @Override
  public void setRequiredPoints() {
    // TODO Auto-generated method stub
  }

  @Override
  public void setRequiredPoints(int newPoints) {
    // TODO Auto-generated method stub
  }

  @Override
  public void setNextLevel(ILevel newLevel) {
    // TODO Auto-generated method stub
  }

  @Override
  public ILevel getCurrentLevel() {
    return null;
  }

  @Override
  public boolean hasNextLevel() {
    return false;
  }

  @Override
  public void setCurrentLevel(ILevel newLevel) {
    // TODO Auto-generated method stub
  }

  @Override
  public void autoSwitchToNextLevel() {
    // TODO Auto-generated method stub
  }

  @Override
  public boolean hasCurrentLevel() {
    return false;
  }

  @Override
  public void setFlyweight(Flyweight flyweight) {
    // TODO Auto-generated method stub
  }

  @Override
  public boolean isLevel() {
    return false;
  }

}
