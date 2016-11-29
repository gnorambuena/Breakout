package com.cc3002.breakout.test;

import static org.junit.Assert.assertTrue;

import com.cc3002.breakout.facade.Flyweight;
import com.cc3002.breakout.facade.HomeworkTwoFacade;
import com.cc3002.breakout.logic.bonus.BonusHandler;
import com.cc3002.breakout.logic.bonus.IBonus;
import com.cc3002.breakout.logic.brick.IBrick;
import com.cc3002.breakout.logic.level.GameConsole;
import com.cc3002.breakout.logic.level.ILevel;
import com.cc3002.breakout.logic.level.Player;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Observer;

public class ObserverTest {
  
  Player pl;
  List<Observer> observers;
  GameConsole gameConsole;
  BonusHandler bonusHandler;
  Flyweight flyweight;
  
  /**
   * Setting up para los test de los distintos Observer.
   * @throws Exception Tira una exception al fallar la creacion de los Observer.
   */
  @Before
  public void setUp() throws Exception {
    flyweight = new Flyweight();
    pl = flyweight.getPlayer();
    observers = flyweight.getObservers();
    bonusHandler = flyweight.getBonusHandler();
    gameConsole = flyweight.getGameConsole();
    gameConsole.setStream(System.out);
  }

  @Test
  public void testScoreObserver() {
    ILevel lvl = flyweight.newLevelWithSoftAndStoneBricks("Level one",32,0.4);
    List<IBrick> bricks = lvl.getBricks();
    boolean flag = true;
    try {
      for (IBrick brick : bricks) {
        brick.hit();
        brick.hit();
        brick.hit();
      }
    } catch (Exception exception) {
      flag = false;
    }
    assertTrue(flag);
  }

  @Test
  public void testLvlObserver() {
    ILevel lvl1 = flyweight.newLevelWithSoftAndStoneBricks("Level one",32,0.4);
    ILevel lvl2 = flyweight.newLevelWithSoftAndStoneBricks("Level two",32,0.5);
    HomeworkTwoFacade aux = new HomeworkTwoFacade();
    boolean flag = true;
    try {
      aux.setCurrentLevel(lvl1);
    } catch (Exception exception) {
      flag = false;
    }
    try {
      aux.setCurrentLevel(lvl2);
    } catch (Exception exception) {
      flag = false;
    }
    assertTrue(flag);
  }
  
  @Test
  public void testBonusObserver() {
    List<IBonus> bonuses = BonusHandler.genBonuses(10, 0.4,flyweight);
    bonusHandler.setBonuses(bonuses);
    boolean flag = true;
    try {
      for (int i = 0 ; i < 10 ; i++) {
        bonusHandler.reached();
      }
    } catch (Exception exception) {
      flag = false;
    }
    assertTrue(flag);
  }
}
