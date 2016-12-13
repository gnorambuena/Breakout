package com.cc3002.breakout.gui.collisionhandler;

import com.almasb.ents.Entity;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.EntityView;
import com.almasb.fxgl.gameplay.GameWorld;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.cc3002.breakout.gui.AudioController;
import com.cc3002.breakout.gui.BreakoutApp;
import com.cc3002.breakout.gui.BrickEntity;
import com.cc3002.breakout.gui.EntityType;
import com.cc3002.breakout.logic.brick.IBrick;
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
   */
  public BrickBallCollisionHandler() {
    super(EntityType.BRICK, EntityType.BALL);
    
    breakout = (BreakoutApp) FXGL.getApp();
    gameworld = breakout.getGameWorld();
    scorePlayer = breakout.getScorePlayer();
    curScore = breakout.getFacade().getFlyweight().getCurScore();
  }
  
  @Override
  protected void onHitBoxTrigger(Entity firstEntity, Entity secondEntity,
      HitBox boxA, HitBox boxB) {
    //System.out.println(boxB.getName() + " hitted by "+ boxA.getName());
    BrickEntity brick = (BrickEntity)firstEntity;
    IBrick refBrick = brick.getRefBrick();
    playBrickSound(refBrick);
    refBrick.hit();
    if (refBrick.isDestroyed()) {
      if (refBrick.isPoisonBrick() ) {
        breakout.loseGame();
      }
      gameworld.removeEntity(firstEntity);
      scorePlayer.set((int)curScore.getPoints());
      if (breakout.getFacade()
                  .getFlyweight()
                  .isLevelCompleted()) {
        
        breakout.setFlagPassNextLevel();
      }
      
    } else if (refBrick.remainingHits() == 2) {
      //cambio de color para stone brick
      brick.getMainViewComponent()
           .setView(new EntityView(new Rectangle(35, 10, Color.DARKGOLDENROD)));
    } else if (refBrick.remainingHits() == 1) {
      brick.getMainViewComponent()
           .setView(
           new EntityView(
           new Rectangle(35 , 10 , 
           refBrick.isPoisonBrick() ? Color.BLUEVIOLET : Color.BISQUE)));
    }
  }
  
  private void playBrickSound(IBrick brick) {
    AudioController soundController = new AudioController();
    if (brick.isSoftBrick()) {
      soundController.playSound(breakout.getSound("ballSoftBrickHit"),0.7);
    }
    if (brick.isStoneBrick()) {
      soundController.playSound(breakout.getSound("ballStoneBrickHit"),0.6);
    }
    if (brick.isMetalBrick()) {
      soundController.playSound(breakout.getSound("ballMetalBrickHit"));
    }
    if (brick.isPoisonBrick()) {
      soundController.playSound(breakout.getSound("ballPoisonBrickHit"));
    }
  }
}
