package com.cc3002.breakout.logic.bonus;



import com.cc3002.breakout.facade.Flyweight;
import com.cc3002.breakout.logic.level.Score;

import java.util.Observable;
import java.util.Observer;

/**
 * Modificador que quita 3 puntos al Score.
 * @author gabriel
 *
 */
public class LossScoreModifier extends Observable implements IBonus {
  
  Score curScore;

  /**
   * Constructor de un modificador que quita puntaje.
   * @param flyweight flyweight del juego.
   */
  public LossScoreModifier(Flyweight flyweight) {
    curScore = flyweight.getCurScore();
    for (Observer observer: flyweight.getObservers()) {
      addObserver(observer);
    }
  }
  
  /**
   * Metodo que ejecuta el modificador.
   */
  public void reached() {
    curScore.add(-3);
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

  @Override
  public boolean isBatResize() {
    return false;
  }
  
  @Override
  public boolean isAddBall() {
    return false;
  }
}
