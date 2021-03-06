package com.cc3002.breakout.gui.collisionhandler;

import com.almasb.ents.Entity;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.cc3002.breakout.gui.AudioController;
import com.cc3002.breakout.gui.BreakoutApp;
import com.cc3002.breakout.gui.EntityType;

/**
 * Handler para la colision del bate y la pelota.
 * @author gabriel
 *
 */
public class BatBallCollisionHandler extends CollisionHandler {
  
  BreakoutApp breakout;
  
  public BatBallCollisionHandler() {
    super(EntityType.PLAYER_BAT, EntityType.BALL);
    breakout = (BreakoutApp)FXGL.getApp();
  }
  
  @Override
  protected void onHitBoxTrigger(Entity firstEntity, Entity secondEntity,
      HitBox boxA, HitBox boxB) {
    //System.out.println(boxB.getName() + " hitted by "+ boxA.getName());
    new AudioController().playSound(breakout.getSound("batBallHit"),1.5);
  }
}
