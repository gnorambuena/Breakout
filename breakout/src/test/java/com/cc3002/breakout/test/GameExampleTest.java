package com.cc3002.breakout.test;

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
  
  /**
   * Setting up de el juego, basicamente se quiere jugar
   * lo que dice el enunciado de la tarea.
   * @throws Exception tira una Exception cuando no se puede instanciar un objeto.
   */
  @Before
  public void setUp() throws Exception {
    game = new HomeworkTwoFacade();
    game.setGameConsoleOutput(System.out);
    List<IBrick> aux = new ArrayList<IBrick>();
    aux.add(new SoftBrick(game.getPlayer().getScore(),game.getGameObservers()));
    aux.add(new StoneBrick(game.getPlayer().getScore(),game.getGameObservers()));
    aux.add(new SoftBrick(game.getPlayer().getScore(),game.getGameObservers()));
    aux.add(new StoneBrick(game.getPlayer().getScore(),game.getGameObservers()));
    ILevel auxlvl = new RealLevel("Basic", aux, game.getPlayer(), game.getGameConsole());
    auxlvl.setObservers(game.getGameObservers());
    game.setCurrentLevel(auxlvl);
    aux = new ArrayList<IBrick>();
    aux.add(new StoneBrick(game.getPlayer().getScore(),game.getGameObservers()));
    aux.add(new StoneBrick(game.getPlayer().getScore(),game.getGameObservers()));
    ILevel auxlvl2 = new RealLevel("Medium", aux, game.getPlayer(),game.getGameConsole());
    game.setNextLevel(auxlvl2);
    List<IBonus> bonuses = new ArrayList<IBonus>();
    bonuses.add(new LossScoreModifier(game.getPlayer(),game.getGameObservers()));
    bonuses.add(new LossLifeModifier(game.getPlayer(),game.getGameObservers()));
    game.registerBonuses(bonuses);
  }

  @Test
  public void test() {
    List<IBrick> bricks = game.getBricks();
    BonusHandler bonusHandler = game.getBonusHandler();
    bricks.get(0).hit();
    bonusHandler.reached();
    bricks.get(1).hit();
    bricks.get(1).hit();
    bricks.get(1).hit();
    bricks.get(2).hit();
    bricks.get(3).hit();
    bricks.get(3).hit();
    bricks.get(3).hit();
    List<IBonus> bonuses = new ArrayList<IBonus>();
    bonuses.add(new AddLifeModifier(game.getPlayer(),game.getGameObservers()));
    bonuses.add(new AddScoreModifier(game.getPlayer(),game.getGameObservers()));
    game.registerBonuses(bonuses);
    bricks = game.getBricks();
    bricks.get(0).hit();
    bricks.get(0).hit();
    bricks.get(0).hit();
    bonusHandler.reached();
    bricks.get(1).hit();
    bricks.get(1).hit();
    bricks.get(1).hit();
    bonusHandler.reached();
    
  }

}
