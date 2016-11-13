package com.cc3002.breakout.logic.level;

import java.util.List;

import com.cc3002.breakout.logic.bonus.IBonus;
import com.cc3002.breakout.logic.brick.IBrick;
import com.cc3002.breakout.logic.observer.GameObserver;

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

  public void setBonuses(List<IBonus> newBonus) {}

  public void setObservers(List<GameObserver> newObservers) {}

  public List<GameObserver> getObservers() {
    return null;
  }

  public void setRequiredPoints(int newRequiredPoints) {}

  public void setNextLevel(ILevel newLevel) {}

  public ILevel getCurrentLevel() {
    return null;
  }

  public boolean hasNextLevel() {
    return false;
  }

  public void setCurrentLevel(ILevel newLevel) {}

  public void autoSwitchToNextLevel() {}

  public boolean hasCurrentLevel() {
    return false;
  }

}
