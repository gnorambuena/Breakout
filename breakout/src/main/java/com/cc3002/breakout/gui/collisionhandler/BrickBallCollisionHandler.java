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
import sun.audio.AudioPlayer;

public class BrickBallCollisionHandler extends CollisionHandler {
  
  GameWorld gameworld;
  IntegerProperty scorePlayer;
  Score curScore;
  BreakoutApp breakout;
  
  public BrickBallCollisionHandler(BreakoutApp breakout) {
    super(EntityType.BRICK, EntityType.BALL);
    gameworld = breakout.getGameWorld();
    scorePlayer = breakout.getScorePlayer();
    curScore = breakout.getFacade().getFlyweight().getCurScore();
    this.breakout = breakout;
  }
  
  @Override
  protected void onHitBoxTrigger(Entity a, Entity b, HitBox boxA, HitBox boxB) {
    //System.out.println(boxB.getName() + " hitted by "+ boxA.getName());
    new AudioController().playSound(breakout.getBrickHitSound());
    BrickEntity brick = (BrickEntity)a;
    brick.getRefBrick().hit();
    if (brick.getRefBrick().isDestroyed()) {
      gameworld.removeEntity(a);
      scorePlayer.set((int)curScore.getPoints());
      if (breakout.getFacade().getFlyweight().isLevelCompleted()) {
        breakout.playNextLevel();
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
