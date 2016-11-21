package com.cc3002.breakout.test;

import static org.junit.Assert.assertSame;

import com.cc3002.breakout.facade.Flyweight;
import com.cc3002.breakout.logic.bonus.AddLifeModifier;
import com.cc3002.breakout.logic.bonus.AddScoreModifier;
import com.cc3002.breakout.logic.bonus.LossLifeModifier;
import com.cc3002.breakout.logic.bonus.LossScoreModifier;
import com.cc3002.breakout.logic.level.Life;
import com.cc3002.breakout.logic.level.Player;
import com.cc3002.breakout.logic.level.Score;


import org.junit.Before;
import org.junit.Test;


public class ModifierBonusTest {
  Flyweight flyweight;
  AddLifeModifier bonusLife;
  AddScoreModifier bonusScore;
  LossLifeModifier discountLife;
  LossScoreModifier discountScore;
  Player player;
  
  /**
   * Setting up de este test.
   * @throws Exception Tira una Exception si falla en crear los distintos modifiers.
   */
  @Before
  public void setUp() throws Exception {
    flyweight = new Flyweight();
    player = flyweight.getPlayer();
    bonusLife = new AddLifeModifier(flyweight);
    bonusScore = new AddScoreModifier(flyweight);
    discountLife = new LossLifeModifier(flyweight);
    discountScore = new LossScoreModifier(flyweight);
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
    Score aux = new Score(flyweight);
    aux.add(5L);
    bonusScore.reached();
    assertSame(aux.getPoints(),player.getScore().getPoints());
    aux.add(-3);
    discountScore.reached();
    assertSame(aux.getPoints(),player.getScore().getPoints());
  }
}
