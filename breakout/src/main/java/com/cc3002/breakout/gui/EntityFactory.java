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


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

/**
 * Factory de todas las entidades del juego, sirve para crear el bat, el ball
 * y los bricks.
 * @author gabriel
 *
 */
public class EntityFactory {
  
  /**
   * Metodo que se encarga de generar un nuevo bate para el juego.
   * @param xpos Posicion en x donde aparecera el bate.
   * @param ypos Posicion en y donde aparecera el bate.
   * @return Una nueva entidad que representa al bate.
   */
  public static Entity newBat(double xpos, double ypos, double width, double height) {
    GameEntity bat = new GameEntity();
    bat.getTypeComponent().setValue(EntityType.PLAYER_BAT);
    bat.getPositionComponent().setValue(xpos, ypos);
    bat.getBoundingBoxComponent().addHitBox(new HitBox("BAT", BoundingShape.box(width, height)));
    bat.getMainViewComponent().setView(new Rectangle(width,height, Color.BLUE));

    PhysicsComponent batPhysics = new PhysicsComponent();
    batPhysics.setBodyType(BodyType.KINEMATIC);

    FixtureDef def = new FixtureDef();
    //def.setFriction(0.0f);
    def.setDensity(0.5f);
    def.setRestitution(1.0f);

    batPhysics.setFixtureDef(def);

    //batPhysics.setOnPhysicsInitialized(() -> batPhysics.setLinearVelocity(0, 0));
    bat.addComponent(batPhysics);
    bat.addComponent(new CollidableComponent(true));
    bat.addControl(new BatControl());

    return bat;
  }
  
  /**
   * Metodo que se encarga de generar una nueva pelota para el juego.
   * @param xpos Posicion en x donde aparecera la pelota.
   * @param ypos Posicion en y donde aparecera la pelota.
   * @return Una nueva entidad que representa la pelota.
   */
  public static Entity newBall(double xpos, double ypos) {
    GameEntity ball = new GameEntity();
    ball.getTypeComponent().setValue(EntityType.BALL);
    ball.getPositionComponent().setValue(xpos, ypos);
    ball.getBoundingBoxComponent().addHitBox(new HitBox("BODY", BoundingShape.circle(5)));
    ball.getMainViewComponent().setView(new Circle(5, Color.LIGHTGRAY));

    PhysicsComponent ballPhysics = new PhysicsComponent();
    ballPhysics.setBodyType(BodyType.DYNAMIC);

    FixtureDef def = new FixtureDef();
    def.setDensity(0.3f);
    def.setRestitution(1.0f);
    //def.setFriction(0.05f);
    ballPhysics.setFixtureDef(def);
    ballPhysics.setOnPhysicsInitialized(() -> ballPhysics.setLinearVelocity(5 * 60, -5 * 60));

    ball.addComponent(ballPhysics);
    ball.addComponent(new CollidableComponent(true));
    ball.addControl(new BallControl());

    return ball;
  }

  /**
   * Metodo que se encarga de generar un nuevo brick para el juego.
   * @param xpos Posicion en x donde aparecera el brick.
   * @param ypos Posicion en y donde aparacera el brick.
   * @param refBrick IBrick de referencia, la parte logica del brick.
   * @return Una nueva entidad que representa al brick.
   */
  public static BrickEntity newBrick(double xpos, double ypos, IBrick refBrick) {
    
    BrickEntity brick = new BrickEntity();
    brick.setRefBrick(refBrick);
    brick.getTypeComponent().setValue(EntityType.BRICK);
    brick.getPositionComponent().setValue(xpos, ypos);
    brick.getMainViewComponent()
        .setView(new EntityView(
          new Rectangle(35, 10, refBrick.isSoftBrick() ? Color.BISQUE :
            refBrick.isStoneBrick() ? Color.BROWN :
              refBrick.isMetalBrick() ? Color.DARKGREY : Color.DARKMAGENTA)), true);

    PhysicsComponent brickPhysics = new PhysicsComponent();
    brickPhysics.setBodyType(BodyType.STATIC);
    
    FixtureDef def = new FixtureDef();
    def.setDensity(0.2f);
    def.setRestitution(1.0f);
    def.setFriction(0.005f);
    brickPhysics.setFixtureDef(def);

    brick.addComponent(brickPhysics);
    brick.addComponent(new CollidableComponent(true));
    return brick;
  }
}
