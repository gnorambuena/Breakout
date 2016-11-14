package com.cc3002.breakout.test;

import static org.junit.Assert.assertTrue;

import com.cc3002.breakout.facade.HomeworkTwoFacade;
import com.cc3002.breakout.logic.bonus.BonusHandler;
import com.cc3002.breakout.logic.bonus.IBonus;
import com.cc3002.breakout.logic.brick.IBrick;
import com.cc3002.breakout.logic.level.GameConsole;
import com.cc3002.breakout.logic.level.ILevel;
import com.cc3002.breakout.logic.level.Player;
import com.cc3002.breakout.logic.level.RealLevel;
import com.cc3002.breakout.logic.observer.BonusObserver;
import com.cc3002.breakout.logic.observer.GameObserver;
import com.cc3002.breakout.logic.observer.LevelObserver;
import com.cc3002.breakout.logic.observer.ScoreObserver;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ObserverTest {
  
  Player pl;
  List<GameObserver> observers;
  GameConsole gameConsole;
  BonusHandler bonusHandler;
  
  /**
   * Setting up para los test de los distintos Observer.
   * @throws Exception Tira una exception al fallar la creacion de los Observer.
   */
  @Before
  public void setUp() throws Exception {
    pl = new Player();
    observers = new ArrayList<GameObserver>();
    bonusHandler = new BonusHandler();
    gameConsole = new GameConsole();
    gameConsole.setStream(System.out);
    GameObserver firstObserver = new ScoreObserver(gameConsole);
    GameObserver secondObserver = new BonusObserver(gameConsole);
    GameObserver thirdObserver = new LevelObserver(gameConsole);
    observers.add(firstObserver);
    observers.add(secondObserver);
    observers.add(thirdObserver);
  }

  @Test
  public void testScoreObserver() {
    ILevel lvl = new RealLevel("Level one",32,0.4,pl,gameConsole);
    lvl.setBonusHandler(new BonusHandler());
    lvl.setObservers(observers);
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
    ILevel lvl1 = new RealLevel("Level one",32,0.4,pl,gameConsole);
    ILevel lvl2 = new RealLevel("Level two",32,0.5,pl,gameConsole);
    lvl1.setObservers(observers);
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
    List<GameObserver> auxObservers = new ArrayList<GameObserver>();
    auxObservers.add(new BonusObserver(gameConsole));
    List<IBonus> bonuses = BonusHandler.genBonuses(10, 0.4, pl,auxObservers);
    ILevel lvl1 = new RealLevel("Level one",32,0.4,pl,gameConsole);
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
