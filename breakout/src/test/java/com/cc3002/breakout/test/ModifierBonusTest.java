package com.cc3002.breakout.test;

import static org.junit.Assert.assertSame;


import com.cc3002.breakout.logic.bonus.AddLifeModifier;
import com.cc3002.breakout.logic.bonus.AddScoreModifier;
import com.cc3002.breakout.logic.bonus.LossLifeModifier;
import com.cc3002.breakout.logic.bonus.LossScoreModifier;
import com.cc3002.breakout.logic.level.GameConsole;
import com.cc3002.breakout.logic.level.Life;
import com.cc3002.breakout.logic.level.Player;
import com.cc3002.breakout.logic.level.Score;
import com.cc3002.breakout.logic.observer.GameObserver;
import com.cc3002.breakout.logic.observer.NullObserver;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ModifierBonusTest {
  Player player;
  GameConsole gameConsole;
  AddLifeModifier bonusLife;
  AddScoreModifier bonusScore;
  LossLifeModifier discountLife;
  LossScoreModifier discountScore;
  
  /**
   * Setting up de este test.
   * @throws Exception Tira una Exception si falla en crear los distintos modifiers.
   */
  @Before
  public void setUp() throws Exception {
    player = new Player();
    gameConsole = new GameConsole();
    List<GameObserver> observer = new ArrayList<GameObserver>();
    observer.add(new NullObserver());
    player.setObservers(observer);
    bonusLife = new AddLifeModifier(player,observer);
    bonusScore = new AddScoreModifier(player,observer);
    discountLife = new LossLifeModifier(player,observer);
    discountScore = new LossScoreModifier(player,observer);
  }

  @Test
  public void testLifeModifier() {
    Life aux = new Life();
    aux.addHearts();
    bonusLife.reached();
    assertSame(player.getNumberOfHearts(),aux.getHearts());
    aux.lessHearts();
    discountLife.reached();
    assertSame(player.getNumberOfHearts(),aux.getHearts());
  }
  
  @Test
  public void testScoreModifier() {
    Score aux = new Score();
    aux.add(5L);
    bonusScore.reached();
    assertSame(aux.getPoints(),player.getScore().getPoints());
    aux.add(-3);
    discountScore.reached();
    assertSame(aux.getPoints(),player.getScore().getPoints());
  }
}
