package com.cc3002.breakout.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cc3002.breakout.facade.HomeworkTwoFacade;
import com.cc3002.breakout.logic.bonus.Bonus;
import com.cc3002.breakout.logic.bonus.IBonus;
import com.cc3002.breakout.logic.brick.IBrick;
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
  
  @Before
  public void setUp() throws Exception {
    pl = new Player();
    GameObserver ScOb = new ScoreObserver(System.out);
    GameObserver BnOb = new BonusObserver(System.out);
    GameObserver LvOb = new LevelObserver(System.out);
    Observers = new ArrayList<GameObserver>();
    Observers.add(ScOb);
    Observers.add(LvOb);
    Observers.add(BnOb);
  }

  @Test
  public void testScoreObserver() {
    ILevel lvl = new RealLevel("Level one",32,0.4,pl);
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
    ILevel lvl1 = new RealLevel("Level one",32,0.4,pl);
    ILevel lvl2 = new RealLevel("Level two",32,0.5,pl);
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
    Observer.add(new BonusObserver(System.out));
    List<IBonus>bonuses = Bonus.genBonuses(10, 0.4, pl,Observer);
    ILevel lvl1 = new RealLevel("Level one",32,0.4,pl);
    lvl1.setBonuses(bonuses);
    
  }
}
