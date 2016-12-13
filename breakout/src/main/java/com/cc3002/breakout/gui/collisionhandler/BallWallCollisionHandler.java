package com.cc3002.breakout.gui.collisionhandler;

import com.almasb.ents.Entity;
import com.almasb.fxgl.effect.ParticleControl;
import com.almasb.fxgl.effect.ParticleEmitter;
import com.almasb.fxgl.effect.ParticleEmitters;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.entity.control.ExpireCleanControl;
import com.almasb.fxgl.gameplay.GameWorld;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.cc3002.breakout.facade.HomeworkTwoFacade;
import com.cc3002.breakout.gui.AudioController;
import com.cc3002.breakout.gui.BreakoutApp;
import com.cc3002.breakout.gui.EntityFactory;
import com.cc3002.breakout.gui.EntityType;
import com.cc3002.breakout.gui.Overlay;

import javafx.beans.property.IntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Handler para la colision de la pelota con la muralla.
 * @author gabriel
 *
 */
public class BallWallCollisionHandler extends CollisionHandler {
  
  GameWorld gameworld;
  GameEntity bat;
  HomeworkTwoFacade game;
  IntegerProperty lifesPlayer;
  Overlay uioverlay;
  BreakoutApp breakout;
  
  /**
   * Constructor para el Handler de la colision entre la pelota y la muralla.
   * @param breakout Referencia a la app.
   */
  public BallWallCollisionHandler(BreakoutApp breakout) {
    super(EntityType.BALL, EntityType.WALL);
    gameworld = breakout.getGameWorld();
    game = breakout.getFacade();
    lifesPlayer = breakout.getLifesPlayer();
    uioverlay = breakout.getOverlay();
    this.breakout = breakout;
  }
  
  @Override
  protected void onHitBoxTrigger(Entity firstEntity, Entity secondEntity,
      HitBox boxA, HitBox boxB) {
    //System.out.println(boxB.getName() + " hitted by " + boxA.getName());
    if (boxB.getName().equals("TOP")) {
      new AudioController().playSound(breakout.getBallFallingSound());
      bat = breakout.getBat();
      //System.out.println("Event fired!");
      int totalLifes = game.lossOfHeart();
      lifesPlayer.set(totalLifes);
      if (totalLifes <= 0) {
        uioverlay.showMessage("Game Over!");
        breakout.pause();
      }
      Point2D pos = bat.getPosition();
      //System.out.println(pos.getX() + " " + pos.getY());
      pos = pos.add(35, -15);
      //System.out.println(pos.getX() + " " + pos.getY());
      breakout.setBall((GameEntity)EntityFactory.newBall(pos.getX(),pos.getY()));
      
      ParticleEmitter emitter = ParticleEmitters.newSparkEmitter();
      emitter.setColorFunction(() -> Color.GOLD);

      Entities.builder()
              .at(pos)
              .with(new ParticleControl(emitter))
              .with(new ExpireCleanControl(Duration.seconds(1)))
              .buildAndAttach(gameworld);
    } else {
      new AudioController().playSound(breakout.getWallHitSound(),0.35);
    }
  }
}
