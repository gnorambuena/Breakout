package com.cc3002.breakout.logic.bonus;

import com.cc3002.breakout.facade.Flyweight;
import com.cc3002.breakout.logic.level.Score;

import java.util.Observable;
import java.util.Observer;

/**
 * Modificador que agrega 5 en Score.
 * @author gabriel
 *
 */
public class AddScoreModifier extends Observable implements IBonus {
  
  Score curScore;

  /**
   * Constructor de un modificador que agrega puntaje.
   * @param flyweight flyweight del juego.
   */
  public AddScoreModifier(Flyweight flyweight) {
    curScore = flyweight.getCurScore();
    for (Observer observer: flyweight.getObservers()) {
      addObserver(observer);
    }
  }
  
  /**
   * Metodo que ejecuta el modificador.
   */
  public void reached() {
    curScore.add(5);
    setChanged();
    notifyObservers("ES");
    clearChanged();
  }

  public boolean isExtraBonus() {
    return true;
  }

  public boolean isDiscount() {
    return false;
  }

  @Override
  public boolean isBatResize() {
    return false;
  }
  
  @Override
  public boolean isAddBall() {
    return false;
  }
}
