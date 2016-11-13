package com.cc3002.breakout.test;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cc3002.breakout.logic.bonus.AddLifeModifier;
import com.cc3002.breakout.logic.bonus.AddScoreModifier;
import com.cc3002.breakout.logic.bonus.LossLifeModifier;
import com.cc3002.breakout.logic.bonus.LossScoreModifier;
import com.cc3002.breakout.logic.level.GameConsole;
import com.cc3002.breakout.logic.level.Life;
import com.cc3002.breakout.logic.level.Player;
import com.cc3002.breakout.logic.level.Score;
import com.cc3002.breakout.logic.observer.BonusObserver;
import com.cc3002.breakout.logic.observer.GameObserver;

public class ModifierBonusTest {
  Player player;
  GameConsole gameConsole;
  AddLifeModifier ALM;
  AddScoreModifier ASM;
  LossLifeModifier LLM;
  LossScoreModifier LSM;
  
  @Before
  public void setUp() throws Exception {
    player = new Player();
    gameConsole = new GameConsole();
    gameConsole.setStream(System.out);
    List<GameObserver>Observer = new ArrayList<GameObserver>();
    Observer.add(new BonusObserver(gameConsole));
    ALM = new AddLifeModifier(player,Observer);
    ASM = new AddScoreModifier(player,Observer);
    LLM = new LossLifeModifier(player,Observer);
    LSM = new LossScoreModifier(player,Observer);
  }

  @Test
  public void testLifeModifier() {
    Life aux = new Life();
    aux.addHearts();
    ALM.reached();
    assertSame(player.getNumberOfHearts(),aux.getHearts());
    aux.lessHearts();
    LLM.reached();
    assertSame(player.getNumberOfHearts(),aux.getHearts());
  }
  
  @Test
  public void testScoreModifier(){
    Score aux = new Score();
    aux.add(5L);
    ASM.reached();
    assertSame(aux.getPoints(),player.getScore().getPoints());
    aux.add(-3);
    LSM.reached();
    assertSame(aux.getPoints(),player.getScore().getPoints());
  }
}
