package com.cc3002.breakout.gui;


import com.almasb.ents.Entity;
import com.almasb.fxgl.entity.EntityView;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.cc3002.breakout.gui.EntityType;
import com.cc3002.breakout.gui.control.BallControl;
import com.cc3002.breakout.gui.control.BatControl;
import com.cc3002.breakout.logic.brick.IBrick;

import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class EntityFactory {
  
  public static Entity newBat (double x, double y) {
    GameEntity bat = new GameEntity();
    bat.getTypeComponent().setValue(EntityType.PLAYER_BAT);
    bat.getPositionComponent().setValue(x, y);
    bat.getBoundingBoxComponent().addHitBox(new HitBox("BAT", BoundingShape.box(80, 15)));
    bat.getMainViewComponent().setView(new Rectangle(80,15, Color.BLUE));

    PhysicsComponent batPhysics = new PhysicsComponent();
    batPhysics.setBodyType(BodyType.KINEMATIC);

    FixtureDef def = new FixtureDef();
    def.setDensity(0.5f);
    def.setRestitution(1.0f);

    batPhysics.setFixtureDef(def);

    bat.addComponent(batPhysics);
    bat.addComponent(new CollidableComponent(true));
    bat.addControl(new BatControl());

    return bat;
  }
  
  public static Entity newBall(double x, double y) {
    GameEntity ball = new GameEntity();
    ball.getTypeComponent().setValue(EntityType.BALL);
    ball.getPositionComponent().setValue(x, y);
    ball.getBoundingBoxComponent().addHitBox(new HitBox("BODY", BoundingShape.circle(5)));
    ball.getMainViewComponent().setView(new Circle(5, Color.LIGHTGRAY));

    PhysicsComponent ballPhysics = new PhysicsComponent();
    ballPhysics.setBodyType(BodyType.DYNAMIC);

    FixtureDef def = new FixtureDef();
    def.setDensity(0.3f);
    def.setRestitution(1.0f);

    ballPhysics.setFixtureDef(def);
    ballPhysics.setOnPhysicsInitialized(() -> ballPhysics.setLinearVelocity(5 * 60, -5 * 60));

    ball.addComponent(ballPhysics);
    ball.addComponent(new CollidableComponent(true));
    ball.addControl(new BallControl());

    return ball;
  }

  
  public static BrickEntity newBrick(double x, double y, IBrick refBrick) {
    
    BrickEntity brick = new BrickEntity();
    brick.setRefBrick(refBrick);
    brick.getTypeComponent().setValue(EntityType.BRICK);
    brick.getPositionComponent().setValue(x, y);
    brick.getMainViewComponent().setView(new EntityView(new Rectangle(35, 10, refBrick.isSoftBrick() ? Color.BISQUE : Color.BROWN)), true);

    PhysicsComponent brickPhysics = new PhysicsComponent();
    brickPhysics.setBodyType(BodyType.STATIC);
    
    FixtureDef def = new FixtureDef();
    def.setDensity(0.2f);
    def.setRestitution(1.0f);

    brickPhysics.setFixtureDef(def);

    brick.addComponent(brickPhysics);
    brick.addComponent(new CollidableComponent(true));
    return brick;
  }
}
