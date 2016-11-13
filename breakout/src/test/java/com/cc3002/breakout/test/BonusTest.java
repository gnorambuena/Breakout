package com.cc3002.breakout.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.cc3002.breakout.facade.HomeworkTwoFacade;
import com.cc3002.breakout.logic.bonus.BonusHandler;
import com.cc3002.breakout.logic.bonus.IBonus;
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
  HomeworkTwoFacade game;
  
  @Before
  public void setUp() throws Exception {
    game = new HomeworkTwoFacade();
    gameConsole = new GameConsole();
  }

  @Test
  public void test() {
    boolean flag = true;
    int numberOfBonuses = 0;
    int numberOfDiscount = 0;
    try {
      List<GameObserver> observer = new ArrayList<GameObserver>();
      observer.add(new NullObserver());
      List<IBonus> bonuses = BonusHandler.genBonuses(32, 0.3, new Player(),observer);
      for (int i = 0 ; i < bonuses.size() ; i++) {
        if (bonuses.get(i).isExtraBonus()) {
          numberOfBonuses++;
        } else if (bonuses.get(i).isDiscount()) {
          numberOfDiscount++;
        }
      }
    } catch (Exception exception) {
      flag = false;
    }
    assertTrue(flag);
    assertSame(numberOfBonuses, 9);
    assertSame(numberOfDiscount, 23);
  }
  
  @Test
  public void test2() {
    IBonus aux = game.newExtraHeart();
    assertTrue(aux.isExtraBonus());
    assertFalse(aux.isDiscount());
    aux.reached();
    assertSame(game.getPlayer().getNumberOfHearts(),4);
    aux = game.newHeartDiscount();
    assertTrue(aux.isDiscount());
    assertFalse(aux.isExtraBonus());
    aux.reached();
    assertSame(game.getPlayer().getNumberOfHearts(),3);
    aux = game.newExtraScore();
    assertTrue(aux.isExtraBonus());
    assertFalse(aux.isDiscount());
    aux.reached();
    assertSame(game.earnedScore(),5L);
    aux = game.newScoreDiscount();
    assertTrue(aux.isDiscount());
    assertFalse(aux.isExtraBonus());
    aux.reached();
    assertSame(game.earnedScore(),2L);
  }
}
