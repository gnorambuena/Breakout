package com.cc3002.breakout.logic.bonus;

import com.cc3002.breakout.facade.Flyweight;
import com.cc3002.breakout.logic.level.Player;

import java.util.Observable;
import java.util.Observer;

/**
 * Modificador que aumenta en uno el numero de vidas.
 * @author gabriel
 *
 */
public class AddLifeModifier extends Observable implements IBonus {

  Player player;
  
  /**
   * Constructor de un modificador que agrega vidas.
   * @param flyweight flyweight del juego.
   */
  public AddLifeModifier(Flyweight flyweight) {
    player = flyweight.getPlayer();
    for (Observer observer: flyweight.getObservers()) {
      addObserver(observer);
    }
  }
  
  /**
   * Metodo que ejecuta el modificador.
   */
  public void reached() {
    player.addHearts();
    setChanged();
    notifyObservers("EH");
    clearChanged();
  }

  public boolean isExtraBonus() {
    return true;
  }

  public boolean isDiscount() {
    return false;
  }
}
