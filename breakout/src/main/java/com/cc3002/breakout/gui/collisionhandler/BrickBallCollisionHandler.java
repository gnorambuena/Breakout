package com.cc3002.breakout.gui.collisionhandler;

import com.almasb.ents.Entity;
import com.almasb.fxgl.entity.EntityView;
import com.almasb.fxgl.gameplay.GameWorld;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.cc3002.breakout.gui.AudioController;
import com.cc3002.breakout.gui.BreakoutApp;
import com.cc3002.breakout.gui.BrickEntity;
import com.cc3002.breakout.gui.EntityType;
import com.cc3002.breakout.logic.level.Score;

import javafx.beans.property.IntegerProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * Handler para la colision producida por la pelota y el brick.
 * @author gabriel
 *
 */
public class BrickBallCollisionHandler extends CollisionHandler {
  
  GameWorld gameworld;
  IntegerProperty scorePlayer;
  Score curScore;
  BreakoutApp breakout;
  
  /**
   * Constructor para el handler de la colision entre un brick y la pelota.
   * @param breakout Referencia a la app.
   */
  public BrickBallCollisionHandler(BreakoutApp breakout) {
    super(EntityType.BRICK, EntityType.BALL);
    gameworld = breakout.getGameWorld();
    scorePlayer = breakout.getScorePlayer();
    curScore = breakout.getFacade().getFlyweight().getCurScore();
    this.breakout = breakout;
  }
  
  @Override
  protected void onHitBoxTrigger(Entity firstEntity, Entity secondEntity,
      HitBox boxA, HitBox boxB) {
    //System.out.println(boxB.getName() + " hitted by "+ boxA.getName());
    BrickEntity brick = (BrickEntity)firstEntity;
    if (brick.getRefBrick().isSoftBrick()) {
      new AudioController().playSound(breakout.getSound("ballSoftBrickHit"),0.7);
    }
    if (brick.getRefBrick().isStoneBrick()) {
      new AudioController().playSound(breakout.getSound("ballStoneBrickHit"),0.6);
    }
    brick.getRefBrick().hit();
    if (brick.getRefBrick().isDestroyed()) {
      gameworld.removeEntity(firstEntity);
      scorePlayer.set((int)curScore.getPoints());
      if (breakout.getFacade().getFlyweight().isLevelCompleted()) {
        breakout.setFlagPassNextLevel();;
      }
    } else if (brick.getRefBrick().remainingHits() == 2) {
      brick.getMainViewComponent()
        .setView(new EntityView(new Rectangle(35, 10, Color.DARKGOLDENROD)));
    } else {
      brick.getMainViewComponent()
        .setView(new EntityView(new Rectangle(35 , 10 , Color.BISQUE)));
    }
  }
}
