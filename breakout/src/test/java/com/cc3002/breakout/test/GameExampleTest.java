package com.cc3002.breakout.test;

import com.cc3002.breakout.facade.Flyweight;
import com.cc3002.breakout.facade.HomeworkTwoFacade;
import com.cc3002.breakout.logic.bonus.AddLifeModifier;
import com.cc3002.breakout.logic.bonus.AddScoreModifier;
import com.cc3002.breakout.logic.bonus.BonusHandler;
import com.cc3002.breakout.logic.bonus.IBonus;
import com.cc3002.breakout.logic.bonus.LossLifeModifier;
import com.cc3002.breakout.logic.bonus.LossScoreModifier;
import com.cc3002.breakout.logic.brick.IBrick;
import com.cc3002.breakout.logic.brick.SoftBrick;
import com.cc3002.breakout.logic.brick.StoneBrick;
import com.cc3002.breakout.logic.level.ILevel;
import com.cc3002.breakout.logic.level.RealLevel;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class GameExampleTest {

  HomeworkTwoFacade game;
  Flyweight flyweight;
  
  /**
   * Setting up de el juego, basicamente se quiere jugar
   * lo que dice el enunciado de la tarea.
   * @throws Exception tira una Exception cuando no se puede instanciar un objeto.
   */
  @Before
  public void setUp() throws Exception {
    game = new HomeworkTwoFacade();
    flyweight = game.getFlyweight();
    game.setGameConsoleOutput(System.out);
    List<IBrick> aux = new ArrayList<IBrick>();
    aux.add(new SoftBrick(flyweight));
    aux.add(new StoneBrick(flyweight));
    aux.add(new SoftBrick(flyweight));
    aux.add(new StoneBrick(flyweight));
    ILevel auxlvl = new RealLevel("Basic", aux, flyweight);
    game.setCurrentLevel(auxlvl);
    aux = new ArrayList<IBrick>();
    aux.add(new StoneBrick(flyweight));
    aux.add(new StoneBrick(flyweight));
    ILevel auxlvl2 = new RealLevel("Medium", aux,flyweight);
    game.setNextLevel(auxlvl2);
    List<IBonus> bonuses = new ArrayList<IBonus>();
    bonuses.add(new LossScoreModifier(flyweight));
    bonuses.add(new LossLifeModifier(flyweight));
    game.registerBonuses(bonuses);
  }

  @Test
  public void test() {
    List<IBrick> bricks = game.getBricks();
    BonusHandler bonusHandler = flyweight.getBonusHandler();
    game.autoSwitchToNextLevel();
    bricks.get(0).hit();
    bricks.get(1).hit();
    bricks.get(1).hit();
    bricks.get(1).hit();
    bricks.get(2).hit();
    bricks.get(3).hit();
    bricks.get(3).hit();
    game.autoSwitchToNextLevel();
    bricks.get(3).hit();
    List<IBonus> bonuses = new ArrayList<IBonus>();
    bonuses.add(new AddLifeModifier(flyweight));
    bonuses.add(new AddScoreModifier(flyweight));
    game.registerBonuses(bonuses);
    bricks = game.getBricks();
    bricks.get(0).hit();
    bricks.get(0).hit();
    bricks.get(0).hit();
    bricks.get(1).hit();
    bricks.get(1).hit();
    bricks.get(1).hit();
  }

}
