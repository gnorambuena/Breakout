package com.cc3002.breakout.gui.collisionhandler;

import com.almasb.ents.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.cc3002.breakout.gui.AudioController;
import com.cc3002.breakout.gui.BreakoutApp;
import com.cc3002.breakout.gui.EntityType;

public class BatBallCollisionHandler extends CollisionHandler {
  
  BreakoutApp breakout;
  public BatBallCollisionHandler(BreakoutApp breakout) {
    super(EntityType.PLAYER_BAT, EntityType.BALL);
    this.breakout = breakout;
  }
  
  @Override
  protected void onHitBoxTrigger(Entity a, Entity b, HitBox boxA, HitBox boxB) {
      //System.out.println(boxB.getName() + " hitted by "+ boxA.getName());
      new AudioController().playSound(breakout.getBatHitSound(),1.5);
  }
}
