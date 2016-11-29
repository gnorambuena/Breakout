package com.cc3002.breakout.logic.bonus;


import com.cc3002.breakout.facade.Flyweight;
import com.cc3002.breakout.logic.level.Player;

import java.util.Observable;
import java.util.Observer;

/**
 * Modificador que quita 3 puntos al Score.
 * @author gabriel
 *
 */
public class LossScoreModifier extends Observable implements IBonus {
  
  Player player;

  /**
   * Constructor de un modificador que quita puntaje.
   * @param flyweight flyweight del juego.
   */
  public LossScoreModifier(Flyweight flyweight) {
    player = flyweight.getPlayer();
    for (Observer observer: flyweight.getObservers()) {
      addObserver(observer);
    }
  }
  
  /**
   * Metodo que ejecuta el modificador.
   */
  public void reached() {
    player.addScore(-3);
    setChanged();
    notifyObservers("SD");
    clearChanged();
  }

  public boolean isExtraBonus() {
    return false;
  }

  public boolean isDiscount() {
    return true;
  }

}
