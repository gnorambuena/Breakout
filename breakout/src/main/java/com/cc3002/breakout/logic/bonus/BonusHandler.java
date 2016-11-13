package com.cc3002.breakout.logic.bonus;

import com.cc3002.breakout.logic.level.Player;
import com.cc3002.breakout.logic.observer.GameObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Esta clase se encarga de manejar los bonuses del level.
 * @author gabriel
 *
 */
public class BonusHandler {
  
  List<IBonus> bonuses;
  List<GameObserver> observers;
  int currentBonus;
  
  public BonusHandler() {
    bonuses = new ArrayList<IBonus>();
    currentBonus = 0;
  }
  
  /**
   * Setea los bonuses para este Handler.
   * @param newBonuses Los bonuses a setear.
   */
  public void setBonuses(final List<IBonus> newBonuses) {
    bonuses = newBonuses;
    if (bonuses != null) {
      setObservers(bonuses.get(0).getObservers());
    }
    currentBonus = 0;
  }
  
  /**
   * Setea los observers para cada nivel.
   * @param newObservers Los Observers a setear.
   */
  public void setObservers(List<GameObserver> newObservers) {
    observers = newObservers;
    for (GameObserver observer : observers) {
      observer.setBonusHandler(this);
    }
  }
  
  /**
   * Metodo que se ejecuta cuando se quiere usar un modificador,
   * solo funciona si no quedan modificadores por accionar.
   */
  public void reached() {
    if (currentBonus < bonuses.size()) {
      currentBonus++;
      bonuses.get(currentBonus - 1).reached();
    }
  }
  
  /**
   * Metodo estatico que genera una lista de number elementos con probability
   * de ocurrencia de bonos.
   * @param number Numero de bonos a generar.
   * @param probability Probabilidad de ocurrencia de un bono.
   * @param player Referencia al jugador.
   * @param observers GameObservers del juego.
   * @return Retorna una Lista con los bonos generados.
   */
  public static List<IBonus> genBonuses(int number, double probability,
      Player player, List<GameObserver> observers) {
    List<IBonus> ans = new ArrayList<IBonus>();
    Random rand = new Random();
    
    for (int i = 0 ; i < number ; i++) {
      float chance = rand.nextFloat();
      if (chance < probability) {
        if (chance < probability / 2.0) {
          ans.add(new AddLifeModifier(player,observers));
        } else {
          ans.add(new AddScoreModifier(player,observers));
        }
      } else {
        if (chance < (1.0 + probability) / 2.0) {
          ans.add(new LossLifeModifier(player,observers));
        } else {
          ans.add(new LossScoreModifier(player,observers));
        }
      }
    }
    
    return ans;
  }

  /**
   * Acciona los bonuses que aun no se han usado.
   */
  public void autoSwitch() {
    for ( ; currentBonus < bonuses.size() ;) {
      reached();
    }
  }
}
