package com.cc3002.breakout.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

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

public class ObserverTest {
  
  Player pl;
  List<GameObserver>Observers;
  GameConsole gameConsole;
  BonusHandler bonusHandler;
  
  @Before
  public void setUp() throws Exception {
    pl = new Player();
    bonusHandler = new BonusHandler();
    gameConsole = new GameConsole();
    gameConsole.setStream(System.out);
    GameObserver ScOb = new ScoreObserver(gameConsole);
    GameObserver BnOb = new BonusObserver(gameConsole);
    GameObserver LvOb = new LevelObserver(gameConsole);
    Observers = new ArrayList<GameObserver>();
    Observers.add(ScOb);
    Observers.add(LvOb);
    Observers.add(BnOb);
  }

  @Test
  public void testScoreObserver() {
    ILevel lvl = new RealLevel("Level one",32,0.4,pl,gameConsole);
    lvl.setObservers(Observers);
    List<IBrick>bricks = lvl.getBricks();
    boolean f = true;
    try{
      for(IBrick brick : bricks) {
        brick.hit();
        brick.hit();
        brick.hit();
      }
    } catch(Exception e){
      f = false;
    }
    assertTrue(f);
  }

  @Test
  public void testLvlObserver(){
    ILevel lvl1 = new RealLevel("Level one",32,0.4,pl,gameConsole);
    ILevel lvl2 = new RealLevel("Level two",32,0.5,pl,gameConsole);
    lvl1.setObservers(Observers);
    HomeworkTwoFacade aux = new HomeworkTwoFacade();
    boolean f = true;
    try{
      aux.setCurrentLevel(lvl1);
    } catch (Exception e){
      f = false;
    }
    try{
      aux.setCurrentLevel(lvl2);
    } catch (Exception e){
      f = false;
    }
    assertTrue(f);
  }
  
  @Test
  public void testBonusObserver(){
    List<GameObserver> Observer = new ArrayList<GameObserver>();
    Observer.add(new BonusObserver(gameConsole));
    List<IBonus>bonuses = BonusHandler.genBonuses(10, 0.4, pl,Observer);
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
