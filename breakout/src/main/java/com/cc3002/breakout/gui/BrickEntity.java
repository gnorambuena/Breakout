package com.cc3002.breakout.gui;

import com.almasb.fxgl.entity.GameEntity;
import com.cc3002.breakout.logic.brick.IBrick;

public class BrickEntity extends GameEntity {
  private IBrick refBrick = null;
  
  public void setRefBrick(IBrick brick) {
    refBrick = brick;
  }
  
  public IBrick getRefBrick() {
    return refBrick;
  }
}
