package com.cc3002.breakout.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cc3002.breakout.logic.bonus.Bonus;
import com.cc3002.breakout.logic.level.Player;
import com.cc3002.breakout.logic.observer.BonusObserver;
import com.cc3002.breakout.logic.observer.GameObserver;

public class BonusTest {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void test() {
    boolean f = true;
    try{
      List<GameObserver>Observer = new ArrayList<GameObserver>();
      Observer.add(new BonusObserver(System.out));
      Bonus.genBonuses(32, 0.5, new Player(),Observer);
    } catch (Exception e) {
      f =false;
    }
    assertTrue(f);
  }

}
