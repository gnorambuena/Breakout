package com.cc3002.breakout.test;

import static org.junit.Assert.assertTrue;

import com.cc3002.breakout.logic.bonus.BonusHandler;
import com.cc3002.breakout.logic.level.GameConsole;
import com.cc3002.breakout.logic.level.Player;
import com.cc3002.breakout.logic.observer.GameObserver;
import com.cc3002.breakout.logic.observer.NullObserver;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class BonusTest {
  
  GameConsole gameConsole;
  
  @Before
  public void setUp() throws Exception {
    gameConsole = new GameConsole();
    gameConsole.setStream(System.out);
  }

  @Test
  public void test() {
    boolean flag = true;
    try {
      List<GameObserver> observer = new ArrayList<GameObserver>();
      observer.add(new NullObserver());
      BonusHandler.genBonuses(32, 0.5, new Player(),observer);
    } catch (Exception exception) {
      flag = false;
    }
    assertTrue(flag);
  }

}
