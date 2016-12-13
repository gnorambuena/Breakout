package com.cc3002.breakout.logic.bonus;

import com.cc3002.breakout.facade.Flyweight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * Esta clase se encarga de manejar los bonuses del level.
 * @author gabriel
 *
 */
public class BonusHandler {
  
  List<IBonus> bonuses;
  int currentBonus;
  Flyweight flyweight;

  /**
   * Objeto que se encarga de manejar los bonuses.
   * @param newFlyweight Flyweight del juego.
   */
  public BonusHandler(Flyweight newFlyweight) {
    flyweight = newFlyweight;
    bonuses = new ArrayList<IBonus>();
    currentBonus = 0;
  }

  /**
   * Setea los bonuses para este Handler.
   * @param newBonuses Los bonuses a setear.
   */
  public void setBonuses(final List<IBonus> newBonuses) {
    bonuses = newBonuses;
    currentBonus = 0;
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
   * @return Retorna una Lista con los bonos generados.
   */
  public static List<IBonus> genBonuses(int number, double probability,
      Flyweight newFlyweight) {
    List<IBonus> ans = new ArrayList<IBonus>();
    Random rand = new Random();
    ans.add(new AddBatSizeModifier(newFlyweight));
    int numberOfBonuses = (int)(number * probability);
    
    for (int i = 1 ; i < numberOfBonuses ; i++) {
      float chance = rand.nextFloat();
      if (chance < 0.5) {
        ans.add(new AddLifeModifier(newFlyweight));
      } else {
        ans.add(new AddScoreModifier(newFlyweight));
      }
    }
    float auxchance = rand.nextFloat();
    if (auxchance < 0.1) {
      ans.add(new AddBallModifier(newFlyweight));
      numberOfBonuses++;
    }
    for (int i = numberOfBonuses ; i < number ; i++) {
      float chance = rand.nextFloat();
      if (chance < 0.5) {
        ans.add(new LossLifeModifier(newFlyweight));
      } else {
        ans.add(new LossScoreModifier(newFlyweight));
      }
    }
    Collections.shuffle(ans);
    return ans;
  }

  /**
   * Acciona los bonuses que aun no se han usado.
   */
  public void autoSwitch() {
    while (currentBonus < bonuses.size()) {
      reached();
    }
  }
}
