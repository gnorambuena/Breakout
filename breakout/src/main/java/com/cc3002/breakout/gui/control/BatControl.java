package com.cc3002.breakout.gui.control;

import com.almasb.ents.AbstractControl;
import com.almasb.ents.Entity;
import com.almasb.fxgl.entity.component.BoundingBoxComponent;
import com.almasb.fxgl.entity.component.PositionComponent;
import com.almasb.fxgl.physics.PhysicsComponent;

/**
 * Clase encargada de manejar el bate con el input del usuario.
 * @author gabriel
 *
 */
public class BatControl extends AbstractControl {

  protected PositionComponent position;
  protected PhysicsComponent bat;
  protected BoundingBoxComponent bbox;
  
  @Override
  public void onAdded(Entity entity) {
    bat = entity.getComponentUnsafe(PhysicsComponent.class);
    position = entity.getComponentUnsafe(PositionComponent.class);
    bbox = entity.getComponentUnsafe(BoundingBoxComponent.class);
  }

  public void onUpdate(Entity entity, double tpf) {}

  /**
   * Metodo que hace que el bate se mueva a la izquierda.
   */
  public void left() {
    if (position.getX() >= 5) {
      try {
        bat.setLinearVelocity(-5 * 90, 0);
      } catch (Exception exception) {
        System.out.println("Catch exception!");
        //Filler comment.
      }
    } else {
      stop();
    }
  }

  /**
   * Metodo que hace que el bate se mueva a la derecha.
   */
  public void right() {
    if (bbox.getMaxXWorld() <= 600 - 5) {
      try { 
        bat.setLinearVelocity(5 * 90, 0);
      } catch (Exception exception) {
        //Filler comment
        System.out.println("Catch exception!");
      }
    } else {
      stop();
    }
  }

  public void stop() {
    bat.setLinearVelocity(0, 0);
  }
}
